package aaulia.compose.movie.di

import aaulia.compose.movie.BuildConfig
import aaulia.compose.movie.data.remote.TMDBService
import aaulia.compose.movie.data.repository.MovieRepository
import aaulia.compose.movie.data.repository.TMDBRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.create

object Injector {
    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply { level = Level.BODY }
            )
            .addInterceptor { chain ->
                val request = chain.request()
                val nextUrl = request.url.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                    .addQueryParameter("region", BuildConfig.TMDB_REGION)
                    .build()

                chain.proceed(
                    request.newBuilder()
                        .url(nextUrl)
                        .build()
                )
            }
            .build()
    }

    private val json by lazy {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit by lazy {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BuildConfig.TMDB_API_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    private val service: TMDBService by lazy {
        retrofit.create()
    }

    val repository: MovieRepository by lazy { TMDBRepository(service) }
}