package aaulia.compose.movie.data.local.dao

import aaulia.compose.movie.data.local.model.*
import aaulia.compose.movie.data.local.model.relation.MovieCastRelation
import aaulia.compose.movie.data.local.model.relation.MovieGenreRelation
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MovieDao {

    @Upsert(entity = Movie::class)
    abstract suspend fun upsertMovies(vararg movies: MovieCommon)

    @Update(entity = Movie::class)
    abstract suspend fun updateExtras(extras: MovieExtras)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertGenres(genres: List<Genre>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMovieGenreRelations(refs: List<MovieGenreRelation>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCasts(casts: List<Cast>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMovieCastRelations(refs: List<MovieCastRelation>)


    @Transaction
    open suspend fun updateMovie(extras: MovieExtras, genres: List<Genre>, casts: List<Cast>) {
        updateExtras(extras)

        insertGenres(genres)
        insertMovieGenreRelations(genres.map { MovieGenreRelation(extras.movieId, it.genreId) })

        insertCasts(casts)
        insertMovieGenreRelations(casts.map { MovieGenreRelation(extras.movieId, it.castId) })
    }


    @Query(
        """
        SELECT movieId, title, overview, poster, backdrop FROM movies WHERE movieId = :id LIMIT 1
        """
    )
    abstract fun selectMovieCommon(id: Int): Flow<MovieCommon>

    @Transaction
    @Query(
        """
        SELECT movieId, tagline, status, imdbId, runtime, homepage FROM movies WHERE movieId = :id LIMIT 1
        """
    )
    abstract fun selectMovieDetail(id: Int): Flow<MovieDetail>

}