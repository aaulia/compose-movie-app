package aaulia.compose.movie.data.remote

import aaulia.compose.movie.data.model.MoviePage
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/now_playing")
    suspend fun getPlaying(@Query("page") page: Int): MoviePage

    @GET("movie/popular")
    suspend fun getPopular(@Query("page") page: Int): MoviePage

    @GET("movie/upcoming")
    suspend fun getNearing(@Query("page") page: Int): MoviePage
}