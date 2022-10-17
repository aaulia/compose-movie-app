package aaulia.compose.movie.data.local.model

import aaulia.compose.movie.data.local.model.relation.MovieCastRelation
import aaulia.compose.movie.data.local.model.relation.MovieGenreRelation
import androidx.room.*
import aaulia.compose.movie.data.remote.model.MovieCommon as RemoteMovieCommon
import aaulia.compose.movie.data.remote.model.MovieExtras as RemoteMovieExtras

//@formatter:off
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val movieId : Int,

    val title   : String,
    val overview: String,
    val poster  : String,
    val backdrop: String,

    val tagline : String?   = null,
    val status  : String?   = null,
    val imdbId  : String?   = null,
    val runtime : Int?      = null,
    val homepage: String?   = null,
)
//@formatter:on

//@formatter:off
data class MovieCommon(
    val movieId : Int,

    val title   : String,
    val overview: String,
    val poster  : String,
    val backdrop: String,
)
//@formatter:on

//@formatter:off
data class MovieExtras(
    val movieId : Int,

    val tagline : String?   = null,
    val status  : String?   = null,
    val imdbId  : String?   = null,
    val runtime : Int?      = null,
    val homepage: String?   = null,
)
//@formatter:on


//@formatter:off
fun RemoteMovieCommon.toMovieCommon() =
    MovieCommon(
        movieId     = id,

        title       = title,
        overview    = overview,
        poster      = poster.complete,
        backdrop    = backdrop.complete
    )
//@formatter:on

//@formatter:off
fun RemoteMovieExtras.toMovieExtras() =
    MovieExtras(
        movieId     = id,

        tagline     = tagline,
        status      = status,
        imdbId      = imdbId,
        runtime     = runtime,
        homepage    = homepage,
    )
//@formatter:on

//@formatter:off
data class MovieDetail(
    @Embedded
    val extras: MovieExtras,

    @Relation(
        parentColumn    = "movieId",
        entityColumn    = "genreId",
        associateBy     = Junction(MovieGenreRelation::class)
    )
    val genres: List<Genre>,

    @Relation(
        parentColumn    = "movieId",
        entityColumn    = "castId",
        associateBy     = Junction(MovieCastRelation::class)
    )
    val casts: List<Cast>
)
//@formatter:on
