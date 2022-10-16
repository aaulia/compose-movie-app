package aaulia.compose.movie.features.info.model

import aaulia.compose.movie.data.local.model.Movie as MovieEntity

data class Movie(
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,

    val tagline: String,
) {
    companion object {
        val EMPTY = Movie(
            title = "",
            overview = "",
            posterPath = "",
            backdropPath = "",

            tagline = "",
        )
    }
}

fun MovieEntity.toMovie() =
    Movie(
        title,
        overview,
        poster,
        backdrop,

        tagline,
    )
