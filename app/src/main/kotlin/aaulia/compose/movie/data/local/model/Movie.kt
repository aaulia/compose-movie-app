package aaulia.compose.movie.data.local.model

import aaulia.compose.movie.data.remote.model.MovieDetail
import aaulia.compose.movie.data.remote.model.MovieSimple
import androidx.room.Entity
import androidx.room.PrimaryKey

//@formatter:off
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val movieId : Int,

    val title   : String,
    val overview: String,
    val poster  : String,
    val backdrop: String,

    val tagline : String,
)
//@formatter:on

//@formatter:off
fun MovieSimple.toMovie() =
    Movie(
        movieId          = id,

        title       = title,
        overview    = overview,
        poster      = poster.complete,
        backdrop    = backdrop.complete,

        tagline     = "",
    )
//@formatter:on

//@formatter:off
fun MovieDetail.toMovie() =
    Movie(
        movieId          = id,

        title       = title,
        overview    = overview,
        poster      = poster.complete,
        backdrop    = backdrop.complete,

        tagline     = tagline
    )
//@formatter:on
