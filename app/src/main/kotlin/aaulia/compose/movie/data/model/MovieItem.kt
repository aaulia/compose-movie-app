package aaulia.compose.movie.data.model

import aaulia.compose.movie.BuildConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieItem(
    val id: Int,
    val title: String,
    val overview: String,

    @SerialName("poster_path")
    private val _poster: String?
) {
    val poster: String
        get() = _poster
            ?.let { "${BuildConfig.TMDB_IMG_URL}${BuildConfig.TMDB_POSTER_SIZE}$it" }
            ?: ""
}
