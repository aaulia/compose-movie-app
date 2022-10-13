package aaulia.compose.movie.features.list

import aaulia.compose.movie.data.model.MovieItem
import aaulia.compose.movie.data.model.MoviePage
import aaulia.compose.movie.data.model.nextPage
import aaulia.compose.movie.data.repository.MovieRepository
import androidx.paging.PagingSource
import androidx.paging.PagingState

class ListPagingSource(
    movieType: MovieType,
    movieRepo: MovieRepository
) : PagingSource<Int, MovieItem>() {
    val getPageData: suspend (Int) -> MoviePage = when (movieType) {
        MovieType.PLAYING -> movieRepo::getPlaying
        MovieType.POPULAR -> movieRepo::getPopular
        MovieType.NEARING -> movieRepo::getNearing
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val pageNumber = params.key ?: 1
        val pageResult = getPageData(pageNumber)
        return LoadResult.Page(
            data = pageResult.results,
            prevKey = null,
            nextKey = pageResult.nextPage
        )
    }
}