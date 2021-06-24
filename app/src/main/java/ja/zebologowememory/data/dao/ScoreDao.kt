package ja.zebologowememory.data.dao

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ja.zebologowememory.MainApplication
import ja.zebologowememory.data.DataSource
import ja.zebologowememory.data.Score

class ScoreDao {

    companion object {
        const val SHARED_PREFERENCES_TAG = "ScoreDao"
    }

    init {
        readFromSharedPreferences()
    }

    fun getScores(score: Score) : ArrayList<Score> {
        if (!DataSource.scoreData.contains(score))
            DataSource.scoreData.add(score)
        return DataSource.scoreData!!
    }

    fun getAllScores() : ArrayList<Score> {
        DataSource.scoreData
        return DataSource.scoreData
    }

    fun addScoresProcessData(score: Score) {
        with(score) {
            var listScoreData = getScores(score)
            saveInSharedPreferences()
        }
    }

    fun saveInSharedPreferences() {
        val context = MainApplication.applicationContext()
        var dataToSave: ArrayList<Score> = arrayListOf()
        DataSource.scoreData.forEach { arrayList ->
            arrayList.let { dataToSave.add(arrayList) }
        }

        saveListInSharedPreferences(context, SHARED_PREFERENCES_TAG, dataToSave)
    }

    private fun saveListInSharedPreferences(
        context: Context,
        sharedPreferencesTag: String,
        dataToSave: java.util.ArrayList<Score>
    ) {
        var sharedPreferences = context.getSharedPreferences(sharedPreferencesTag, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = gson.toJson(dataToSave)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(sharedPreferencesTag, json)
        editor.commit()
    }

    fun readFromSharedPreferences() {
        val context = MainApplication.applicationContext()
        val readedData: List<Score> = loadScoresFromSharedPreferneces(
            context, SHARED_PREFERENCES_TAG
        )
        if (DataSource.scoreData == null)
            DataSource.scoreData = ArrayList<Score>()
        DataSource.scoreData.clear()
        readedData.forEach {
            if (!DataSource.scoreData.contains(it))
                DataSource.scoreData.add(it)
        }
    }

    private fun loadScoresFromSharedPreferneces(
        context: Context,
        sharedPreferencesTag: String
    ): List<Score> {
        val gson = Gson()
        var sharedPreferences = context.getSharedPreferences(sharedPreferencesTag, Context.MODE_PRIVATE)

        val json = sharedPreferences.getString(SHARED_PREFERENCES_TAG, "")
        if (json == null || json.length < 10)
            return arrayListOf()
        val itemType = object : TypeToken<List<Score>>() {}.type
        val dataListFromSharedPreferneces: List<Score> =
            gson.fromJson<List<Score>>(json, itemType)
        return dataListFromSharedPreferneces
    }

    fun removeAllScores(){
        DataSource.scoreData.clear()
        saveInSharedPreferences()
    }
}