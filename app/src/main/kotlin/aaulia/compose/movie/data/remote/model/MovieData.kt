package aaulia.compose.movie.data.remote.model

import aaulia.compose.movie.BuildConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


interface MovieImages {
    val posterPath: String
    val backdropPath: String
}


@Serializable
data class MovieSimple(
    val id: Int,
    val title: String,
    val overview: String,
    val popularity: Float = 0.0f,

    @SerialName("vote_average")
    val voteAverage: Float = 0.0f,

    @SerialName("vote_count")
    val voteCount: Int = 0,

    @SerialName("poster_path")
    override val posterPath: String = "",

    @SerialName("backdrop_path")
    override val backdropPath: String = ""
) : MovieImages


@Serializable
data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val popularity: Float = 0.0f,

    @SerialName("vote_average")
    val voteAverage: Float = 0.0f,

    @SerialName("vote_count")
    val voteCount: Int = 0,

    @SerialName("poster_path")
    override val posterPath: String = "",

    @SerialName("backdrop_path")
    override val backdropPath: String = "",

    /**
     * detail info
     */

    @SerialName("imdb_id")
    val imdbId: String = "",

    val tagline: String = "",
    val homepage: String = "",
    val genres: List<MovieGenre> = emptyList()
) : MovieImages


@Serializable
data class MovieGenre(
    val id: Int,
    val name: String
)


val MovieImages.fullPosterPath: String
    get() = if (posterPath.isNotEmpty())
        "${BuildConfig.TMDB_IMG_URL}${BuildConfig.TMDB_POSTER_SIZE}$posterPath"
    else
        ""

val MovieImages.fullBackdropPath: String
    get() = if (backdropPath.isNotEmpty())
        "${BuildConfig.TMDB_IMG_URL}${BuildConfig.TMDB_BACKDROP_SIZE}$backdropPath"
    else
        ""
