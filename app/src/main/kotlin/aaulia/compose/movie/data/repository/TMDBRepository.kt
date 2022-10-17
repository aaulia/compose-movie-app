package aaulia.compose.movie.data.repository

import aaulia.compose.movie.data.local.dao.MovieDao
import aaulia.compose.movie.data.local.model.*
import aaulia.compose.movie.data.local.model.relation.MovieCastRelation
import aaulia.compose.movie.data.local.model.relation.MovieGenreRelation
import aaulia.compose.movie.data.remote.TMDBService
import aaulia.compose.movie.data.remote.model.MovieCast
import aaulia.compose.movie.data.remote.model.MovieGenre
import aaulia.compose.movie.data.remote.model.MoviePage
import kotlinx.coroutines.flow.Flow
import aaulia.compose.movie.data.remote.model.MovieCommon as RemoteMovieCommon
import aaulia.compose.movie.data.remote.model.MovieExtras as RemoteMovieExtras

class TMDBRepository(
    private val service: TMDBService,
    private val dao: MovieDao
) : MovieRepository {
    override suspend fun getPlaying(page: Int) = service.getPlaying(page).apply { store(this) }
    override suspend fun getPopular(page: Int) = service.getPopular(page).apply { store(this) }
    override suspend fun getNearing(page: Int) = service.getNearing(page).apply { store(this) }

    override suspend fun fetchMovie(id: Int) {
        service.getDetails(id).apply {
            updateMovie()
            storeGenres()
            storeCasts()
        }
    }

    override fun queryMovieCommon(id: Int): Flow<MovieCommon> = dao.selectMovieCommon(id)

    override fun queryMovieDetail(id: Int): Flow<MovieDetail> = dao.selectMovieDetail(id)

    private suspend fun store(moviePage: MoviePage) {
        val movieData = moviePage.results.map(RemoteMovieCommon::toMovieCommon).toTypedArray()
        dao.upsertMovies(*movieData)
    }


    private suspend fun RemoteMovieExtras.updateMovie() {
        dao.updateDetail(toMovieExtras())
    }

    private suspend fun RemoteMovieExtras.storeGenres() {
        val values = genres.map(MovieGenre::toGenre).toTypedArray()
        val relMap = values
            .map { (genreId, _) -> MovieGenreRelation(id, genreId) }
            .toTypedArray()

        dao.insertGenres(*values)
        dao.insertMovieGenreRelations(*relMap)
    }

    private suspend fun RemoteMovieExtras.storeCasts() {
        val values = credits.casts.map(MovieCast::toCast).toTypedArray()
        val relMap = values
            .map { (castId, _) -> MovieCastRelation(id, castId) }
            .toTypedArray()

        dao.insertCasts(*values)
        dao.insertMovieCastRelations(*relMap)
    }
}