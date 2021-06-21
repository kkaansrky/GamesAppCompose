package com.example.gamesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val repository = SharedRepository()

    private val _gameByIdLiveData = MutableLiveData<GameResponse>()
    val gameByIdLiveData: LiveData<GameResponse> = _gameByIdLiveData

    private val _gamesLiveData = MutableLiveData<GamesResponse>()
    val gamesLiveData: LiveData<GamesResponse> = _gamesLiveData

    fun refreshGame(gameId: Int, apiKey: String) {
        viewModelScope.launch {
            val response = repository.getGameById(gameId, apiKey)

            _gameByIdLiveData.postValue(response)
        }
    }

    fun getGames(
        search: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ) {
        viewModelScope.launch {
            val response = repository.getGames(search,page,pageSize,apiKey)

            _gamesLiveData.postValue(response)
        }
    }
}

