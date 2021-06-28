package com.example.gamesapp

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState

class GamePagingSource(val repository: SharedRepository,
                       val search: String,
                       val apiKey: String): PagingSource<Int, GameResponse>() {
    override fun getRefreshKey(state: PagingState<Int, GameResponse>): Int? {

        return state.anchorPosition

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameResponse> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = repository.getGames(search,nextPage,20,apiKey)

            var nextPageNumber: Int? = null

            if(response?.body?.next != null) {
                val uri = Uri.parse(response?.body?.next!!)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            var prevPageNumber: Int? = null
            if(response?.body?.previous != null) {
                val uri = Uri.parse(response?.body?.previous!!)
                val prevPageQuery = uri.getQueryParameter("page")

                prevPageNumber = prevPageQuery?.toInt()
            }

            LoadResult.Page(data = response.body!!.results,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber)
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }



}