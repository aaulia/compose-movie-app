package aaulia.compose.movie.features.list

import aaulia.compose.movie.data.remote.model.*
import aaulia.compose.movie.data.repository.MovieRepository
import aaulia.compose.movie.di.Injector
import aaulia.compose.movie.features.list.model.Movie
import aaulia.compose.movie.features.list.model.toMovie
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*


class ListViewModel(
    movieType: MovieType,
    movieRepo: MovieRepository = Injector.repository
) : ViewModel() {
    val movieFlow = Pager(
        config = PagingConfig(
            pageSize = MoviePage.PAGE_SIZE,
            prefetchDistance = MoviePage.PREFETCH_DISTANCE,
        )
    ) { ListPagingSource(movieType, movieRepo) }
        .flow
        .cachedIn(viewModelScope)
}

private class ListPagingSource(
    movieType: MovieType,
    movieRepo: MovieRepository
) : PagingSource<Int, Movie>() {
    val getPageData: suspend (Int) -> MoviePage = when (movieType) {
        MovieType.PLAYING -> movieRepo::getPlaying
        MovieType.POPULAR -> movieRepo::getPopular
        MovieType.NEARING -> movieRepo::getNearing
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageNumber = params.key ?: 1
        val pageResult = getPageData(pageNumber)
        return LoadResult.Page(
            data = pageResult.results.map(MovieCommon::toMovie),
            prevKey = pageResult.prevPage,
            nextKey = pageResult.nextPage,
            itemsAfter = pageResult.itemsAfter,
            itemsBefore = pageResult.itemsBefore,
        )
    }
}