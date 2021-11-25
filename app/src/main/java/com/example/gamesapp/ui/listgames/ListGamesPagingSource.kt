package com.example.gamesapp.ui.listgames

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gamesapp.data.ApiRepository
import com.example.gamesapp.data.entity.GameResponse

class ListGamesPagingSource(
    private val apiRepository: ApiRepository,
    var search: String,
) : PagingSource<Int, GameResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameResponse> {
        val pageNumber = params.key ?: 1


        val response = apiRepository.getGames(search,pageNumber,20)


        if (!response.isSuccessful) {
            return LoadResult.Error(Throwable("Can't retrieve data!"))
        }

        val nextPageNumber = checkNextOrPrevPage(response.body()!!.next)
        val prevPageNumber = checkNextOrPrevPage(response.body()!!.previous)

        return LoadResult.Page(
            data = response.body()?.results!!,
            prevKey = prevPageNumber,
            nextKey = nextPageNumber
        )
    }

    private fun checkNextOrPrevPage(item:String?): Int? {
        if(item != null) {
            val uri = Uri.parse(item)
            val nextPageQuery = uri.getQueryParameter("page")
            return nextPageQuery?.toInt()
        }else{
            return null
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GameResponse>): Int? {

        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}