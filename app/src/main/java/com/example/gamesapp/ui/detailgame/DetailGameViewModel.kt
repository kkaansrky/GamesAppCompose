package com.example.gamesapp.ui.detailgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.gamesapp.data.ApiRepository
import com.example.gamesapp.data.entity.GameResponse
import com.example.gamesapp.utils.Constants
import com.example.gamesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.Response
import javax.inject.Inject

@HiltViewModel
class DetailGameViewModel @Inject constructor(
    private var apiRepository: ApiRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    lateinit var game :LiveData<Resource<GameResponse>>

    init {
        savedStateHandle.get<String>(Constants.PARAM_GAME_ID)?.let { gameId ->
            getGame(Integer.parseInt(gameId))

        }
    }

    fun getGame(gameId: Int){
        game = apiRepository.getGame(gameId)
    }
}