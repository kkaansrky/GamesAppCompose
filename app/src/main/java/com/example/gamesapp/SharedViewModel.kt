package com.example.gamesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

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
            val response = repository.getGames(search, page, pageSize, apiKey)

            _gamesLiveData.postValue(response.body)
        }
    }

    fun getListData(
        search: String,
        apiKey: String
    ): Flow<PagingData<GameResponse>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 100),
            pagingSourceFactory = { GamePagingSource(repository, search, apiKey) }).flow.cachedIn(
            viewModelScope
        )
    }
}

