package aaulia.compose.movie.features.info

import aaulia.compose.movie.data.repository.MovieRepository
import aaulia.compose.movie.di.Injector
import aaulia.compose.movie.features.info.model.Movie
import aaulia.compose.movie.features.info.model.toMovie
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import aaulia.compose.movie.data.local.model.Movie as MovieEntity

class InfoViewModel(
    movieId: Int,
    movieRepo: MovieRepository = Injector.repository
) : ViewModel() {

    init {
        viewModelScope.launch {
            movieRepo.fetchMovie(movieId)
        }
    }

    val movie = movieRepo
        .queryMovie(movieId)
        .map(MovieEntity::toMovie)
        .stateIn(viewModelScope, SharingStarted.Lazily, Movie.EMPTY)
}