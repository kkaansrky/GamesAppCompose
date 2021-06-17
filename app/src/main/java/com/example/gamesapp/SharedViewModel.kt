package com.example.gamesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val repository = SharedRepository()

    private val _gameByIdLiveData = MutableLiveData<GamesResponse>()
    val gameByIdLiveData: LiveData<GamesResponse> = _gameByIdLiveData

    fun refreshGame(gameId: Int,apiKey: String) {
        viewModelScope.launch{
            val response = repository.getGameById(gameId,apiKey)

            _gameByIdLiveData.postValue(response)
        }
    }
}
