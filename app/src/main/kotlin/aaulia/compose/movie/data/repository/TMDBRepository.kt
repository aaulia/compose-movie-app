package aaulia.compose.movie.data.repository

import aaulia.compose.movie.data.local.dao.MovieDao
import aaulia.compose.movie.data.local.model.Movie
import aaulia.compose.movie.data.local.model.toMovie
import aaulia.compose.movie.data.remote.TMDBService
import aaulia.compose.movie.data.remote.model.MoviePage
import aaulia.compose.movie.data.remote.model.MovieSimple
import kotlinx.coroutines.flow.Flow

class TMDBRepository(
    private val service: TMDBService,
    private val dao: MovieDao
) : MovieRepository {
    override suspend fun getPlaying(page: Int) = service.getPlaying(page).apply { store(this) }
    override suspend fun getPopular(page: Int) = service.getPopular(page).apply { store(this) }
    override suspend fun getNearing(page: Int) = service.getNearing(page).apply { store(this) }

    override fun fetchMovie(id: Int): Flow<Movie> = dao.selectMovie(id)

    private suspend fun store(moviePage: MoviePage) {
        val movieData = moviePage.results.map(MovieSimple::toMovie).toTypedArray()
        dao.insertMovies(*movieData)
    }
}