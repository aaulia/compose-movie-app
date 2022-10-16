package aaulia.compose.movie.features.info.model

import aaulia.compose.movie.data.local.model.Genre
import aaulia.compose.movie.data.local.model.MovieDetail

//@formatter:off
data class MovieCommon(
    val title   : String = "",
    val poster  : String = "",
    val backdrop: String = "",
    val overview: String = ""
)
//@formatter:on

fun MovieDetail.toMovieCommon() =
    MovieCommon(
        movie.title,
        movie.poster,
        movie.backdrop,
        movie.overview
    )

//@formatter:off
data class MovieExtras(
    val tagline : String        = "",
    val genres  : List<String>  = emptyList()
)
//@formatter:on

fun MovieDetail.toMovieExtras() =
    MovieExtras(
        movie.tagline,
        genres.map(Genre::name)
    )
