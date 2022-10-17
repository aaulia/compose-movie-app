package aaulia.compose.movie.data.local.dao

import aaulia.compose.movie.data.local.model.*
import aaulia.compose.movie.data.local.model.relation.MovieCastRelation
import aaulia.compose.movie.data.local.model.relation.MovieGenreRelation
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Upsert(entity = Movie::class)
    suspend fun upsertMovies(vararg movies: MovieCommon)

    @Update(entity = Movie::class)
    suspend fun updateDetail(extras: MovieExtras)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(vararg genres: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieGenreRelations(vararg refs: MovieGenreRelation)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCasts(vararg casts: Cast)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieCastRelations(vararg refs: MovieCastRelation)


    @Query(
        """
        SELECT movieId, title, overview, poster, backdrop FROM movies WHERE movieId = :id LIMIT 1
        """
    )
    fun selectMovieCommon(id: Int): Flow<MovieCommon>

    @Transaction
    @Query(
        """
        SELECT movieId, tagline, status, imdbId, runtime, homepage FROM movies WHERE movieId = :id LIMIT 1
        """
    )
    fun selectMovieDetail(id: Int): Flow<MovieDetail>

}