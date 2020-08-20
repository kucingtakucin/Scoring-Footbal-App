package com.android.scoringapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {
    lateinit var team1Score: MutableLiveData<Int>
    lateinit var team2Score: MutableLiveData<Int>

    init {
        team1Score.value = 0
        team2Score.value = 0
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun updateScore(team: Int) {
        when (team) {
            1 -> team1Score.value = team1Score.value?.plus(1)
            2 -> team2Score.value = team2Score.value?.plus(1)
            else -> return
        }
    }

    fun setScore(team: Int, value: Int) {
        when (team) {
            1 -> team1Score.value = value
            2 -> team2Score.value = value
            else -> return
        }
    }
}