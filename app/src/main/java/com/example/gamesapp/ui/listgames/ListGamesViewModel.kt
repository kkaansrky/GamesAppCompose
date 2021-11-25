package com.example.gamesapp.ui.listgames

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.gamesapp.data.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListGamesViewModel @Inject constructor(
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getGames(
        search: String,
    ) = Pager(
        PagingConfig(20, 40, enablePlaceholders = false)
    ) {
        ListGamesPagingSource(apiRepository,search)
    }.flow
        .cachedIn(viewModelScope)
}