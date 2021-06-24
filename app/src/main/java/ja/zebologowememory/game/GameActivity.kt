package ja.zebologowememory.game

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import ja.zebologowememory.R
import ja.zebologowememory.data.Repository
import ja.zebologowememory.data.Score
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class GameActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "GameActivity"

    private lateinit var moves : TextView
    private lateinit var time : TextView

    private lateinit var tile00 : ImageView
    private lateinit var tile01 : ImageView
    private lateinit var tile02 : ImageView
    private lateinit var tile03 : ImageView
    private lateinit var tile10 : ImageView
    private lateinit var tile11 : ImageView
    private lateinit var tile12 : ImageView
    private lateinit var tile13 : ImageView
    private lateinit var tile20 : ImageView
    private lateinit var tile21 : ImageView
    private lateinit var tile22 : ImageView
    private lateinit var tile23 : ImageView
    private lateinit var tile30 : ImageView
    private lateinit var tile31 : ImageView
    private lateinit var tile32 : ImageView
    private lateinit var tile33 : ImageView

    private var tilesArray = arrayListOf<String>("dentalfloss", "dentalfloss1", "dentalmirror", "dentalmirror1", "forceps", "forceps1", "implant", "implant1", "scalpel", "scalpel1", "syringe", "syringe1", "tooth", "tooth1", "toothpaste", "toothpaste1")

    private var dentalfloss : Int? = null
    private var dentalfloss1 : Int? = null
    private var dentalmirror : Int? = null
    private var dentalmirror1 : Int? = null
    private var forceps : Int? = null
    private var forceps1 : Int? = null
    private var implant : Int? = null
    private var implant1 : Int? = null
    private var questionmark : Int? = null
    private var scalpel : Int? = null
    private var scalpel1 : Int? = null
    private var syringe : Int? = null
    private var syringe1 : Int? = null
    private var tooth : Int? = null
    private var tooth1 : Int? = null
    private var toothpaste : Int? = null
    private var toothpaste1 : Int? = null

    private var firstTile : String? = null
    private var secondTile : String? = null

    private var clickedFirst : Int? = null
    private var clickedSecond : Int? = null

    private var tileNumber : Int = 1

    private val timerHandler : Handler = Handler()
    private var seconds : Int = 0
    private var displayMinutes : Int = 0
    private var displaySeconds : Int = 0
    private var turn : Int = 0
    private var points : Int? = null

    @RequiresApi(Build.VERSION_CODES.O)
    private fun check() {
        if (tile00.getVisibility() == View.INVISIBLE && tile01.getVisibility() == View.INVISIBLE && tile02.getVisibility() == View.INVISIBLE && tile03.getVisibility() == View.INVISIBLE && tile10.getVisibility() == View.INVISIBLE && tile11.getVisibility() == View.INVISIBLE && tile12.getVisibility() == View.INVISIBLE && tile13.getVisibility() == View.INVISIBLE && tile20.getVisibility() == View.INVISIBLE && tile21.getVisibility() == View.INVISIBLE && tile22.getVisibility() == View.INVISIBLE && tile23.getVisibility() == View.INVISIBLE && tile30.getVisibility() == View.INVISIBLE && tile31.getVisibility() == View.INVISIBLE && tile32.getVisibility() == View.INVISIBLE && tile33.getVisibility() == View.INVISIBLE) {
            stopTimer()

            var t00Id : Int = resources.getIdentifier(tilesArray[0], "drawable", packageName)
            var t00 : Drawable = resources.getDrawable(t00Id)
            var t01Id : Int = resources.getIdentifier(tilesArray[1], "drawable", packageName)
            var t01 : Drawable = resources.getDrawable(t01Id)
            var t02Id : Int = resources.getIdentifier(tilesArray[2], "drawable", packageName)
            var t02 : Drawable = resources.getDrawable(t02Id)
            var t03Id : Int = resources.getIdentifier(tilesArray[3], "drawable", packageName)
            var t03 : Drawable = resources.getDrawable(t03Id)
            var t10Id : Int = resources.getIdentifier(tilesArray[4], "drawable", packageName)
            var t10 : Drawable = resources.getDrawable(t10Id)
            var t11Id : Int = resources.getIdentifier(tilesArray[5], "drawable", packageName)
            var t11 : Drawable = resources.getDrawable(t11Id)
            var t12Id : Int = resources.getIdentifier(tilesArray[6], "drawable", packageName)
            var t12 : Drawable = resources.getDrawable(t12Id)
            var t13Id : Int = resources.getIdentifier(tilesArray[7], "drawable", packageName)
            var t13 : Drawable = resources.getDrawable(t13Id)
            var t20Id : Int = resources.getIdentifier(tilesArray[8], "drawable", packageName)
            var t20 : Drawable = resources.getDrawable(t20Id)
            var t21Id : Int = resources.getIdentifier(tilesArray[9], "drawable", packageName)
            var t21 : Drawable = resources.getDrawable(t21Id)
            var t22Id : Int = resources.getIdentifier(tilesArray[10], "drawable", packageName)
            var t22 : Drawable = resources.getDrawable(t22Id)
            var t23Id : Int = resources.getIdentifier(tilesArray[11], "drawable", packageName)
            var t23 : Drawable = resources.getDrawable(t23Id)
            var t30Id : Int = resources.getIdentifier(tilesArray[12], "drawable", packageName)
            var t30 : Drawable = resources.getDrawable(t30Id)
            var t31Id : Int = resources.getIdentifier(tilesArray[13], "drawable", packageName)
            var t31 : Drawable = resources.getDrawable(t31Id)
            var t32Id : Int = resources.getIdentifier(tilesArray[14], "drawable", packageName)
            var t32 : Drawable = resources.getDrawable(t32Id)
            var t33Id : Int = resources.getIdentifier(tilesArray[15], "drawable", packageName)
            var t33 : Drawable = resources.getDrawable(t33Id)

            tile00.setImageDrawable(t00)
            tile01.setImageDrawable(t01)
            tile02.setImageDrawable(t02)
            tile03.setImageDrawable(t03)
            tile10.setImageDrawable(t10)
            tile11.setImageDrawable(t11)
            tile12.setImageDrawable(t12)
            tile13.setImageDrawable(t13)
            tile20.setImageDrawable(t20)
            tile21.setImageDrawable(t21)
            tile22.setImageDrawable(t22)
            tile23.setImageDrawable(t23)
            tile30.setImageDrawable(t30)
            tile31.setImageDrawable(t31)
            tile32.setImageDrawable(t32)
            tile33.setImageDrawable(t33)

            tile00.setVisibility(View.VISIBLE)
            tile01.setVisibility(View.VISIBLE)
            tile02.setVisibility(View.VISIBLE)
            tile03.setVisibility(View.VISIBLE)
            tile10.setVisibility(View.VISIBLE)
            tile11.setVisibility(View.VISIBLE)
            tile12.setVisibility(View.VISIBLE)
            tile13.setVisibility(View.VISIBLE)
            tile20.setVisibility(View.VISIBLE)
            tile21.setVisibility(View.VISIBLE)
            tile22.setVisibility(View.VISIBLE)
            tile23.setVisibility(View.VISIBLE)
            tile30.setVisibility(View.VISIBLE)
            tile31.setVisibility(View.VISIBLE)
            tile32.setVisibility(View.VISIBLE)
            tile33.setVisibility(View.VISIBLE)

            val zone = ZoneId.of("Europe/Warsaw")
            val date = ZonedDateTime.now(zone)
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")
            val dateFormat: String = date.format(formatter)

            points = 160000 / turn + 160000 / (seconds * 2)
            val score = Score(points!!.toString(), dateFormat)

            Repository.addScoresDataProgress(score)

            val duration = Toast.LENGTH_LONG
            val toast = Toast.makeText(applicationContext, "Punkty: " + points +  " " + "Data: " + dateFormat, duration)
            toast.show()

            onBackPressed()

            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
        }
    }

    private fun doStuff(imageview : ImageView, tile : Int) {
        if (tilesArray[tile] == "dentalfloss") {
            imageview.setImageResource(dentalfloss!!)
        }
        else if (tilesArray[tile] == "dentalfloss1") {
            imageview.setImageResource(dentalfloss1!!)
        }
        else if (tilesArray[tile] == "dentalmirror") {
            imageview.setImageResource(dentalmirror!!)
        }
        else if (tilesArray[tile] == "dentalmirror1") {
            imageview.setImageResource(dentalmirror1!!)
        }
        else if (tilesArray[tile] == "forceps") {
            imageview.setImageResource(forceps!!)
        }
        else if (tilesArray[tile] == "forceps1") {
            imageview.setImageResource(forceps1!!)
        }
        else if (tilesArray[tile] == "implant") {
            imageview.setImageResource(implant!!)
        }
        else if (tilesArray[tile] == "implant1") {
            imageview.setImageResource(implant1!!)
        }
        else if (tilesArray[tile] == "scalpel") {
            imageview.setImageResource(scalpel!!)
        }
        else if (tilesArray[tile] == "scalpel1") {
            imageview.setImageResource(scalpel1!!)
        }
        else if (tilesArray[tile] == "syringe") {
            imageview.setImageResource(syringe!!)
        }
        else if (tilesArray[tile] == "syringe1") {
            imageview.setImageResource(syringe1!!)
        }
        else if (tilesArray[tile] == "tooth") {
            imageview.setImageResource(tooth!!)
        }
        else if (tilesArray[tile] == "tooth1") {
            imageview.setImageResource(tooth1!!)
        }
        else if (tilesArray[tile] == "toothpaste") {
            imageview.setImageResource(toothpaste!!)
        }
        else if (tilesArray[tile] == "toothpaste1") {
            imageview.setImageResource(toothpaste1!!)
        }

        if (tileNumber == 1) {
            firstTile = tilesArray[tile]
            tileNumber = 2
            clickedFirst = tile
            imageview.setEnabled(false)
        }
        else if (tileNumber == 2) {
            turn = turn + 1
            moves.setText("Ruchy: " + turn.toString())

            secondTile = tilesArray[tile]
            tileNumber = 1
            clickedSecond = tile
            tile00.setEnabled(false)
            tile01.setEnabled(false)
            tile02.setEnabled(false)
            tile03.setEnabled(false)
            tile10.setEnabled(false)
            tile11.setEnabled(false)
            tile12.setEnabled(false)
            tile13.setEnabled(false)
            tile20.setEnabled(false)
            tile21.setEnabled(false)
            tile22.setEnabled(false)
            tile23.setEnabled(false)
            tile30.setEnabled(false)
            tile31.setEnabled(false)
            tile32.setEnabled(false)
            tile33.setEnabled(false)

            val handler : Handler = Handler()
            handler.postDelayed(object : Runnable{
                override fun run() {
                    logic()
                }
            }, 700)
        }
    }

    private fun loadTiles() {
        dentalfloss = R.drawable.dentalfloss
        dentalfloss1 = R.drawable.dentalfloss1
        dentalmirror = R.drawable.dentalmirror
        dentalmirror1 = R.drawable.dentalmirror1
        forceps = R.drawable.forceps
        forceps1 = R.drawable.forceps1
        implant = R.drawable.implant
        implant1 = R.drawable.implant1
        scalpel = R.drawable.scalpel
        scalpel1 = R.drawable.scalpel1
        syringe = R.drawable.syringe
        syringe1 = R.drawable.syringe1
        tooth = R.drawable.tooth
        tooth1 = R.drawable.tooth1
        toothpaste = R.drawable.toothpaste
        toothpaste1 = R.drawable.toothpaste1
    }

    private fun logic() {
        var firstTileTemp : Int? = null
        var secondTileTemp : Int? = null

        if (firstTile == "dentalfloss" || firstTile == "dentalfloss1") {
            firstTileTemp = 0
        }
        else if (firstTile == "dentalmirror" || firstTile == "dentalmirror1") {
            firstTileTemp = 1
        }
        else if (firstTile == "forceps" || firstTile == "forceps1") {
            firstTileTemp = 2
        }
        else if (firstTile == "implant" || firstTile == "implant1") {
            firstTileTemp = 3
        }
        else if (firstTile == "scalpel" || firstTile == "scalpel1") {
            firstTileTemp = 4
        }
        else if (firstTile == "syringe" || firstTile == "syringe1") {
            firstTileTemp = 5
        }
        else if (firstTile == "tooth" || firstTile == "tooth1") {
            firstTileTemp = 6
        }
        else if (firstTile == "toothpaste" || firstTile == "toothpaste1") {
            firstTileTemp = 7
        }

        if (secondTile == "dentalfloss" || secondTile == "dentalfloss1") {
            secondTileTemp = 0
        }
        else if (secondTile == "dentalmirror" || secondTile == "dentalmirror1") {
            secondTileTemp = 1
        }
        else if (secondTile == "forceps" || secondTile == "forceps1") {
            secondTileTemp = 2
        }
        else if (secondTile == "implant" || secondTile == "implant1") {
            secondTileTemp = 3
        }
        else if (secondTile == "scalpel" || secondTile == "scalpel1") {
            secondTileTemp = 4
        }
        else if (secondTile == "syringe" || secondTile == "syringe1") {
            secondTileTemp = 5
        }
        else if (secondTile == "tooth" || secondTile == "tooth1") {
            secondTileTemp = 6
        }
        else if (secondTile == "toothpaste" || secondTile == "toothpaste1") {
            secondTileTemp = 7
        }

        if (firstTileTemp == secondTileTemp) {
            if (clickedFirst == 0) {
                tile00.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 1) {
                tile01.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 2) {
                tile02.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 3) {
                tile03.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 4) {
                tile10.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 5) {
                tile11.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 6) {
                tile12.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 7) {
                tile13.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 8) {
                tile20.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 9) {
                tile21.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 10) {
                tile22.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 11) {
                tile23.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 12) {
                tile30.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 13) {
                tile31.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 14) {
                tile32.setVisibility(View.INVISIBLE)
            }
            else if (clickedFirst == 15) {
                tile33.setVisibility(View.INVISIBLE)
            }

            if (clickedSecond == 0) {
                tile00.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 1) {
                tile01.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 2) {
                tile02.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 3) {
                tile03.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 4) {
                tile10.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 5) {
                tile11.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 6) {
                tile12.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 7) {
                tile13.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 8) {
                tile20.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 9) {
                tile21.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 10) {
                tile22.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 11) {
                tile23.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 12) {
                tile30.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 13) {
                tile31.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 14) {
                tile32.setVisibility(View.INVISIBLE)
            }
            else if (clickedSecond == 15) {
                tile33.setVisibility(View.INVISIBLE)
            }
        }
        else {
            tile00.setImageResource(R.drawable.questionmark)
            tile01.setImageResource(R.drawable.questionmark)
            tile02.setImageResource(R.drawable.questionmark)
            tile03.setImageResource(R.drawable.questionmark)
            tile10.setImageResource(R.drawable.questionmark)
            tile11.setImageResource(R.drawable.questionmark)
            tile12.setImageResource(R.drawable.questionmark)
            tile13.setImageResource(R.drawable.questionmark)
            tile20.setImageResource(R.drawable.questionmark)
            tile21.setImageResource(R.drawable.questionmark)
            tile22.setImageResource(R.drawable.questionmark)
            tile23.setImageResource(R.drawable.questionmark)
            tile30.setImageResource(R.drawable.questionmark)
            tile31.setImageResource(R.drawable.questionmark)
            tile32.setImageResource(R.drawable.questionmark)
            tile33.setImageResource(R.drawable.questionmark)
        }

        tile00.setEnabled(true)
        tile01.setEnabled(true)
        tile02.setEnabled(true)
        tile03.setEnabled(true)
        tile10.setEnabled(true)
        tile11.setEnabled(true)
        tile12.setEnabled(true)
        tile13.setEnabled(true)
        tile20.setEnabled(true)
        tile21.setEnabled(true)
        tile22.setEnabled(true)
        tile23.setEnabled(true)
        tile30.setEnabled(true)
        tile31.setEnabled(true)
        tile32.setEnabled(true)
        tile33.setEnabled(true)

        check()
    }

    fun startTimer() {
        timerHandler.postDelayed(object : Runnable{
            override fun run() {
                seconds = seconds + 1
                displaySeconds = displaySeconds + 1
                if (displaySeconds >= 60) {
                    displayMinutes = displayMinutes + 1
                    displaySeconds = 0
                }
                var displaySecondsString : String
                var displayMinutesString : String
                if (displaySeconds < 10) {
                    displaySecondsString = "0" + displaySeconds.toString()
                }
                else {
                    displaySecondsString = displaySeconds.toString()
                }
                if (displayMinutes < 10) {
                    displayMinutesString = "0" + displayMinutes.toString()
                }
                else {
                    displayMinutesString = displayMinutes.toString()
                }
                time.setText("Czas: " + displayMinutesString + ":" + displaySecondsString)
                timerHandler.postDelayed(this, 1000)
            }
        }, 1000)
    }

    fun stopTimer() {
        timerHandler.removeCallbacksAndMessages(null);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        MobileAds.initialize(this) {}

        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d(TAG, adError?.message)
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })

        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                Log.d(TAG, "Ad was dismissed.")
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                Log.d(TAG, "Ad failed to show.")
            }

            override fun onAdShowedFullScreenContent() {
                Log.d(TAG, "Ad showed fullscreen content.")
                mInterstitialAd = null;
            }
        }

        moves = findViewById<TextView>(R.id.text_view_moves)
        time = findViewById<TextView>(R.id.text_view_time)

        tile00 = findViewById<ImageView>(R.id.tile00)
        tile01 = findViewById<ImageView>(R.id.tile01)
        tile02 = findViewById<ImageView>(R.id.tile02)
        tile03 = findViewById<ImageView>(R.id.tile03)
        tile10 = findViewById<ImageView>(R.id.tile10)
        tile11 = findViewById<ImageView>(R.id.tile11)
        tile12 = findViewById<ImageView>(R.id.tile12)
        tile13 = findViewById<ImageView>(R.id.tile13)
        tile20 = findViewById<ImageView>(R.id.tile20)
        tile21 = findViewById<ImageView>(R.id.tile21)
        tile22 = findViewById<ImageView>(R.id.tile22)
        tile23 = findViewById<ImageView>(R.id.tile23)
        tile30 = findViewById<ImageView>(R.id.tile30)
        tile31 = findViewById<ImageView>(R.id.tile31)
        tile32 = findViewById<ImageView>(R.id.tile32)
        tile33 = findViewById<ImageView>(R.id.tile33)

        tile00.setTag("0")
        tile01.setTag("1")
        tile02.setTag("2")
        tile03.setTag("3")
        tile10.setTag("4")
        tile11.setTag("5")
        tile12.setTag("6")
        tile13.setTag("7")
        tile20.setTag("8")
        tile21.setTag("9")
        tile22.setTag("10")
        tile23.setTag("11")
        tile30.setTag("12")
        tile31.setTag("13")
        tile32.setTag("14")
        tile33.setTag("15")

        loadTiles()

        tilesArray.shuffle()

        startTimer()

        tile00.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile00, theTile)
            }
        })

        tile01.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile01, theTile)
            }
        })

        tile02.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile02, theTile)
            }
        })

        tile03.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile03, theTile)
            }
        })

        tile10.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile10, theTile)
            }
        })

        tile11.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile11, theTile)
            }
        })

        tile12.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile12, theTile)
            }
        })

        tile13.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile13, theTile)
            }
        })

        tile20.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile20, theTile)
            }
        })

        tile21.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile21, theTile)
            }
        })

        tile22.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile22, theTile)
            }
        })

        tile23.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile23, theTile)
            }
        })

        tile30.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile30, theTile)
            }
        })

        tile31.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile31, theTile)
            }
        })

        tile32.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile32, theTile)
            }
        })

        tile33.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var theTile = view!!.getTag().toString().toInt()
                doStuff(tile33, theTile)
            }
        })
    }
}