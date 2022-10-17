package aaulia.compose.movie.features.info.model

import aaulia.compose.movie.data.local.model.Cast
import aaulia.compose.movie.data.local.model.Genre
import aaulia.compose.movie.data.local.model.MovieCommon as LocalMovieCommon
import aaulia.compose.movie.data.local.model.MovieDetail as LocalMovieDetail

//@formatter:off
data class MovieCommon(
    val title   : String = "",
    val poster  : String = "",
    val backdrop: String = "",
    val overview: String = ""
)
//@formatter:on

fun LocalMovieCommon.toMovieCommon() =
    MovieCommon(
        title,
        poster,
        backdrop,
        overview
    )

//@formatter:off
data class MovieCast(
    val name        : String,
    val character   : String,
    val image       : String
)
//@formatter:on

fun Cast.toCast() =
    MovieCast(
        name,
        character,
        image
    )

//@formatter:off
data class MovieExtras(
    val tagline : String            = "",
    val genres  : List<String>      = emptyList(),
    val casts   : List<MovieCast>   = emptyList()
)
//@formatter:on

fun LocalMovieDetail.toMovieExtras() =
    MovieExtras(
        extras.tagline ?: "",
        genres.map(Genre::name),
        casts.map(Cast::toCast)
    )
