package com.example.movieapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.local.db.entity.MovieEntity
import com.example.movieapp.data.remote.api.MovieDto
import com.example.movieapp.data.remote.datasource.RemoteDataSource


class MoviePagingSource(
    private val api: RemoteDataSource,
    private val category: String,
) : PagingSource<Int, MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val page = params.key ?: 1
        return try {
            val response = api.getMoviesList(category, page = page)
            val movies = response.body()?.results ?: emptyList()

            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == response.body()?.total_pages) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}