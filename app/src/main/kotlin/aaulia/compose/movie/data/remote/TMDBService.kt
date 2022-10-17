package aaulia.compose.movie.data.remote

import aaulia.compose.movie.data.remote.model.MovieExtras
import aaulia.compose.movie.data.remote.model.MoviePage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/now_playing")
    suspend fun getPlaying(@Query("page") page: Int): MoviePage

    @GET("movie/popular")
    suspend fun getPopular(@Query("page") page: Int): MoviePage

    @GET("movie/upcoming")
    suspend fun getNearing(@Query("page") page: Int): MoviePage

    @GET("movie/{movieId}?append_to_response=credits")
    suspend fun getDetails(@Path("movieId") id: Int): MovieExtras
}