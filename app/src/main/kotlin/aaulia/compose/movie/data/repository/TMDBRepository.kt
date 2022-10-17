package aaulia.compose.movie.data.repository

import aaulia.compose.movie.data.local.dao.MovieDao
import aaulia.compose.movie.data.local.model.*
import aaulia.compose.movie.data.remote.TMDBService
import aaulia.compose.movie.data.remote.model.MovieCast
import aaulia.compose.movie.data.remote.model.MovieGenre
import aaulia.compose.movie.data.remote.model.MoviePage
import kotlinx.coroutines.flow.Flow
import aaulia.compose.movie.data.remote.model.MovieCommon as RemoteMovieCommon

class TMDBRepository(
    private val service: TMDBService,
    private val dao: MovieDao
) : MovieRepository {
    override suspend fun getPlaying(page: Int) = service.getPlaying(page).apply { store(this) }
    override suspend fun getPopular(page: Int) = service.getPopular(page).apply { store(this) }
    override suspend fun getNearing(page: Int) = service.getNearing(page).apply { store(this) }

    override suspend fun fetchMovie(id: Int) {
        //@formatter:off
        val remote = service.getDetails(id)
        val extras = remote.toMovieExtras()
        val genres = remote.genres.map(MovieGenre::toGenre)
        val casts  = remote.credits.casts.map(MovieCast::toCast)
        //@formatter:on

        dao.updateMovie(extras, genres, casts)
    }

    override fun queryMovieCommon(id: Int): Flow<MovieCommon> = dao.selectMovieCommon(id)

    override fun queryMovieDetail(id: Int): Flow<MovieDetail> = dao.selectMovieDetail(id)

    private suspend fun store(moviePage: MoviePage) {
        val movieData = moviePage.results.map(RemoteMovieCommon::toMovieCommon).toTypedArray()
        dao.upsertMovies(*movieData)
    }
}