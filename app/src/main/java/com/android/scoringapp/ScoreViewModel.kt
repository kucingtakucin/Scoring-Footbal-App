package com.android.scoringapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {
    private var _team1Score = MutableLiveData<Int?>()
    val team1Score: LiveData<Int?>
        get() = _team1Score
    private var _team2Score = MutableLiveData<Int?>()
    val team2Score: LiveData<Int?>
        get() = _team2Score

    init {
        _team1Score.value = 0
        _team2Score.value = 0
    }

    fun updateScore(team: Int) {
        when (team) {
            1 -> _team1Score.value = _team1Score.value?.plus(1)
            2 -> _team2Score.value = _team2Score.value?.plus(1)
            else -> return
        }
    }

    fun setScore(team: Int, value: Int) {
        when (team) {
            1 -> _team1Score.value = value
            2 -> _team2Score.value = value
            else -> return
        }
    }
}