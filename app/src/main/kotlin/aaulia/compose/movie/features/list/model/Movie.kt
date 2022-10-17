package aaulia.compose.movie.features.list.model

import aaulia.compose.movie.data.remote.model.MovieCommon

//@formatter:off
data class Movie(
    val id      : Int,
    val title   : String,
    val poster  : String
)
//@formatter:on

//@formatter:off
fun MovieCommon.toMovie() =
    Movie(
        id      = id,
        title   = title,
        poster  = poster.complete
    )
//@formatter:on
