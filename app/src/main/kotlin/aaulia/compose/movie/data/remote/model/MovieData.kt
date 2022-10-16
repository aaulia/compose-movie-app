package aaulia.compose.movie.data.remote.model

import aaulia.compose.movie.BuildConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@formatter:off
@Serializable
data class MovieSimple(
    val id      : Int,
    val title   : String,
    val overview: String    = "",
    @SerialName("poster_path")
    val poster  : Poster    = Poster(),
    @SerialName("backdrop_path")
    val backdrop: Backdrop  = Backdrop()
)
//@formatter:on

//@formatter:off
@Serializable
data class MovieDetail(
    val id      : Int,
    val title   : String,
    val overview: String    = "",
    @SerialName("poster_path")
    val poster  : Poster    = Poster(),
    @SerialName("backdrop_path")
    val backdrop: Backdrop  = Backdrop(),

    @SerialName("imdb_id")
    val imdbId  : String    = "",
    val tagline : String    = "",
    val homepage: String    = "",
)
//@formatter:on


interface MovieImage {
    val relative: String
    val complete: String
}

@Serializable
@JvmInline
value class Poster(private val path: String? = null) : MovieImage {
    override val relative: String
        get() = path.orEmpty()

    override val complete: String
        get() = path
            ?.let { "${BuildConfig.TMDB_IMG_URL}${BuildConfig.TMDB_POSTER_SIZE}$it" }
            ?: ""
}

@Serializable
@JvmInline
value class Backdrop(private val path: String? = null) : MovieImage {
    override val relative: String
        get() = path.orEmpty()

    override val complete: String
        get() = path
            ?.let { "${BuildConfig.TMDB_IMG_URL}${BuildConfig.TMDB_BACKDROP_SIZE}$it" }
            ?: ""
}
