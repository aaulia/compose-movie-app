package aaulia.compose.movie.features.list

import aaulia.compose.movie.data.MovieRepository
import aaulia.compose.movie.features.list.MovieType.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*


class ListViewModel(movieType: MovieType, movieRepo: MovieRepository) : ViewModel() {
    val movieFlow = Pager(PagingConfig(2)) { MoviePagingSource(movieType, movieRepo) }
        .flow
        .cachedIn(viewModelScope)
}

class MoviePagingSource(
    movieType: MovieType,
    movieRepo: MovieRepository
) : PagingSource<Int, Int>() {
    val getData: (Int) -> List<Int> = when (movieType) {
        PLAYING -> movieRepo::getPlayingMovie
        POPULAR -> movieRepo::getPopularMovie
        NEARING -> movieRepo::getNearingMovie
    }

    override fun getRefreshKey(state: PagingState<Int, Int>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Int> {
        val nextPageNum = params.key ?: 1
        return LoadResult.Page(
            data = getData(nextPageNum),
            prevKey = null,
            nextKey = nextPageNum + 1
        )
    }
}