package aaulia.compose.movie.data.local.model

import aaulia.compose.movie.data.remote.model.MovieDetail
import aaulia.compose.movie.data.remote.model.MovieSimple
import aaulia.compose.movie.data.remote.model.fullBackdropPath
import aaulia.compose.movie.data.remote.model.fullPosterPath
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val id: Int,

    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,

    val tagline: String,
)

fun MovieSimple.toMovie() =
    Movie(
        id = id,

        title = title,
        overview = overview,
        posterPath = fullPosterPath,
        backdropPath = fullBackdropPath,

        tagline = "",
    )

fun MovieDetail.toMovie() =
    Movie(
        id = id,

        title = title,
        overview = overview,
        posterPath = fullPosterPath,
        backdropPath = fullBackdropPath,

        tagline = tagline
    )
