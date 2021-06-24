package ja.zebologowememory.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {

    var scores : MutableLiveData<ArrayList<Score>> = MutableLiveData()

    init {
        scores.value = Database.scoreDao.getAllScores()
    }

    fun getAllScores() : LiveData<ArrayList<Score>> {
        return scores
    }

    fun addScoresDataProgress(score: Score) {
        Database.scoreDao.addScoresProcessData(score)
        scores.value = Database.scoreDao.getScores(score)
    }

    fun removeScores(){
        Database.scoreDao.removeAllScores()
        //scores.value = null
    }
}