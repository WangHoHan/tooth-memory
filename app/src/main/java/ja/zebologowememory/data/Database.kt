package ja.zebologowememory.data

import ja.zebologowememory.data.dao.ScoreDao

object Database {
    var _scoreDao : ScoreDao? = null
    val scoreDao : ScoreDao
        get() {
            if (_scoreDao == null)
                _scoreDao = ScoreDao()
            return _scoreDao!!
        }
}