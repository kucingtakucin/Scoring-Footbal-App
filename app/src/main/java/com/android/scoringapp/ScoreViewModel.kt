package com.android.scoringapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {
    private val _teamScore = MutableLiveData<MutableMap<String?,Int?>?>()
    val teamScore: LiveData<MutableMap<String?,Int?>?>
        get() = _teamScore
    private val _eventFinish = MutableLiveData<Boolean?>()
    val eventFinish: LiveData<Boolean?>
        get() = _eventFinish
    private val _teamName = MutableLiveData<MutableMap<String?, String?>?>()
    val teamName: LiveData<MutableMap<String?,String?>?>
        get() = _teamName
    private val _winners = MutableLiveData<String?>()
    val winners: LiveData<String?>
        get() = _winners

    init {
        _teamName.value?.put("team1", "")
        _teamName.value?.put("team2", "")
        _teamScore.value?.put("team1", 0)
        _teamScore.value?.put("team2", 0)
        _eventFinish.value = false
    }

    fun setTeam(team1: String, team2: String) {
        _teamName.value?.put("team1", team1)
        _teamName.value?.put("team2", team2)
    }

    fun updateScore(team: Int) {
        when (team) {
            1 -> _teamScore.value!!["team1"] = _teamScore.value!!["team1"]?.plus(1)
            2 -> _teamScore.value!!["team2"] = _teamScore.value!!["team2"]?.plus(1)
            else -> return
        }
        if (_teamScore.value!!["team1"]!! >= 5 || _teamScore.value!!["team2"]!! >= 5)
            _eventFinish.value = true
            _winners.value = if (teamScore.value!!["team1"]!! > teamScore.value!!["team2"]!!)
                _teamName.value!!["team1"]!! else _teamName.value!!["team2"]!!
    }
}