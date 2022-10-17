package aaulia.compose.movie.data.remote.model

import aaulia.compose.movie.BuildConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

//@formatter:off
@Serializable
data class MovieCommon(
    val id      : Int,

    val title   : String,
    val overview: String            = "",
    @SerialName("poster_path")
    val poster  : Poster            = Poster(),
    @SerialName("backdrop_path")
    val backdrop: Backdrop          = Backdrop()
)
//@formatter:on

//@formatter:off
@Serializable
data class MovieExtras(
    val id      : Int,

    val tagline : String    = "",
    val status  : String    = "",
    @SerialName("imdb_id")
    val imdbId  : String    = "",
    val runtime : Int       = 0,
    val homepage: String    = "",

    val genres  : List<MovieGenre>  = emptyList(),
    val credits : MovieCredits      = MovieCredits()
)
//@formatter:on

//@formatter:off
@Serializable
data class MovieGenre(
    val id  : Int,
    val name: String
)
//@formatter:on

@Serializable
data class MovieCredits(
    @SerialName("cast")
    val casts: List<MovieCast> = emptyList()
)

//@formatter:off
@Serializable
data class MovieCast(
    val id          : Int,
    val name        : String,
    val character   : String = "",
    @SerialName("profile_path")
    val image       : Profile = Profile()
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

@Serializable
@JvmInline
value class Profile(private val path: String? = null) : MovieImage {
    override val relative: String
        get() = path.orEmpty()

    override val complete: String
        get() = path
            ?.let { "${BuildConfig.TMDB_IMG_URL}${BuildConfig.TMDB_PROFILE_SIZE}$it" }
            ?: ""
}
