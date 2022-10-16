package aaulia.compose.movie.data.repository

import aaulia.compose.movie.data.local.dao.MovieDao
import aaulia.compose.movie.data.local.model.MovieDetail
import aaulia.compose.movie.data.local.model.relation.MovieCastRelation
import aaulia.compose.movie.data.local.model.relation.MovieGenreRelation
import aaulia.compose.movie.data.local.model.toCast
import aaulia.compose.movie.data.local.model.toGenre
import aaulia.compose.movie.data.local.model.toMovie
import aaulia.compose.movie.data.remote.TMDBService
import aaulia.compose.movie.data.remote.model.MovieCast
import aaulia.compose.movie.data.remote.model.MovieGenre
import aaulia.compose.movie.data.remote.model.MoviePage
import aaulia.compose.movie.data.remote.model.MovieSimple
import kotlinx.coroutines.flow.Flow
import aaulia.compose.movie.data.remote.model.MovieDetail as RemoteMovieDetail

class TMDBRepository(
    private val service: TMDBService,
    private val dao: MovieDao
) : MovieRepository {
    override suspend fun getPlaying(page: Int) = service.getPlaying(page).apply { store(this) }
    override suspend fun getPopular(page: Int) = service.getPopular(page).apply { store(this) }
    override suspend fun getNearing(page: Int) = service.getNearing(page).apply { store(this) }

    override suspend fun fetchMovie(id: Int) {
        service.getDetails(id).apply {
            storeMovie()
            storeGenres()
            storeCasts()
        }
    }

    override fun queryMovieDetail(id: Int): Flow<MovieDetail> = dao.selectMovieDetail(id)

    private suspend fun store(moviePage: MoviePage) {
        val movieData = moviePage.results.map(MovieSimple::toMovie).toTypedArray()
        dao.insertMovies(*movieData)
    }


    private suspend fun RemoteMovieDetail.storeMovie() {
        dao.insertMovies(toMovie())
    }

    private suspend fun RemoteMovieDetail.storeGenres() {
        val values = genres.map(MovieGenre::toGenre).toTypedArray()
        val relMap = values
            .map { (genreId, _) -> MovieGenreRelation(id, genreId) }
            .toTypedArray()

        dao.insertGenres(*values)
        dao.insertMovieGenreRelations(*relMap)
    }

    private suspend fun RemoteMovieDetail.storeCasts() {
        val values = credits.casts.map(MovieCast::toCast).toTypedArray()
        val relMap = values
            .map { (castId, _) -> MovieCastRelation(id, castId) }
            .toTypedArray()

        dao.insertCasts(*values)
        dao.insertMovieCastRelations(*relMap)
    }
}