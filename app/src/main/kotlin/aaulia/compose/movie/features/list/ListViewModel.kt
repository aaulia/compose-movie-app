package aaulia.compose.movie.features.list

import aaulia.compose.movie.data.repository.MovieRepository
import aaulia.compose.movie.di.Injector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn


class ListViewModel(
    movieType: MovieType,
    movieRepo: MovieRepository = Injector.tmdbRepository
) : ViewModel() {
    val movieFlow = Pager(PagingConfig(2)) { ListPagingSource(movieType, movieRepo) }
        .flow
        .cachedIn(viewModelScope)
}

