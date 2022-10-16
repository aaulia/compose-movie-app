package aaulia.compose.movie.features.info

import aaulia.compose.movie.data.local.model.MovieDetail
import aaulia.compose.movie.data.repository.MovieRepository
import aaulia.compose.movie.di.Injector
import aaulia.compose.movie.features.info.model.MovieCommon
import aaulia.compose.movie.features.info.model.MovieExtras
import aaulia.compose.movie.features.info.model.toMovieCommon
import aaulia.compose.movie.features.info.model.toMovieExtras
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InfoViewModel(
    movieId: Int,
    movieRepo: MovieRepository = Injector.repository
) : ViewModel() {

    init {
        viewModelScope.launch {
            movieRepo.fetchMovie(movieId)
        }
    }

    private val movieDetail = movieRepo
        .queryMovieDetail(movieId)

    val common = movieDetail.map(MovieDetail::toMovieCommon)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MovieCommon())

    val extras = movieDetail.map(MovieDetail::toMovieExtras)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), MovieExtras())
}