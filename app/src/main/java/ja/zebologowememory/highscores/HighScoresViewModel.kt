package ja.zebologowememory.highscores

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ja.zebologowememory.data.Repository
import ja.zebologowememory.data.Score
import kotlin.collections.ArrayList

class HighScoresViewModel : ViewModel() {
    var scores : LiveData<ArrayList<Score>> = Repository.getAllScores()
    // TODO: Implement the ViewModel
}