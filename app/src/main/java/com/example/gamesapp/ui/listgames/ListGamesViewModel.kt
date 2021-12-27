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

    fun games(search:String?) = Pager(PagingConfig(pageSize = 20)) {
        ListGamesPagingSource(apiRepository,search)
    }.flow
}