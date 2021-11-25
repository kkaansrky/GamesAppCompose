package com.example.gamesapp.ui.detailgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.gamesapp.data.ApiRepository
import com.example.gamesapp.data.entity.GameResponse
import com.example.gamesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailGameViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getGame(gameId: Int): LiveData<Resource<GameResponse>> {
        return apiRepository.getGame(gameId)
    }
}