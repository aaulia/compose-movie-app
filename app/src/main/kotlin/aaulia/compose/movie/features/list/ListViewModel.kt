package aaulia.compose.movie.features.list

import aaulia.compose.movie.data.remote.model.MoviePage
import aaulia.compose.movie.data.remote.model.MovieSimple
import aaulia.compose.movie.data.remote.model.nextPage
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
    val movieFlow = Pager(PagingConfig(2)) { ListPagingSource(movieType, movieRepo) }
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
            data = pageResult.results.map(MovieSimple::toMovie),
            prevKey = null,
            nextKey = pageResult.nextPage
        )
    }
}