package aaulia.compose.movie.features.info.model

import aaulia.compose.movie.data.local.model.Movie as MovieEntity

data class Movie(
    val title: String
)

fun MovieEntity.toMovie() = Movie(title)
