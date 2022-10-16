package aaulia.compose.movie.features.list.model

import aaulia.compose.movie.data.remote.model.MovieSimple

//@formatter:off
data class Movie(
    val id      : Int,
    val title   : String,
    val poster  : String
)
//@formatter:on

//@formatter:off
fun MovieSimple.toMovie() =
    Movie(
        id      = id,
        title   = title,
        poster  = poster.complete
    )
//@formatter:on
