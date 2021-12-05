package com.prephouse.prephouse.modules

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.prephouse.prephouse.datasources.feedback.FeedbackRemoteDataSource
import com.prephouse.prephouse.modules.qualifiers.CacheInterceptorOkHttpClient
import com.prephouse.prephouse.utils.isConnectedToNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "http://192.168.0.1:3001" // TODO change for production build

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Singleton
    @Provides
    fun providesCache(@ApplicationContext context: Context) = Cache(
        File(context.cacheDir, "http-cache"),
        20 * 1024 * 1024
    )

    @Singleton
    @Provides
    @CacheInterceptorOkHttpClient
    fun providesCacheInterceptor(@ApplicationContext context: Context) = Interceptor { chain ->
        val originalRequest = chain.request()
        val cacheHeaderValue = if (context.isConnectedToNetwork()) {
            "public, max-age=2419200"
        } else {
            "public, only-if-cached, max-stale=2419200"
        }
        val request = originalRequest.newBuilder().build()
        val response = chain.proceed(request)

        response.newBuilder()
            .removeHeader("Pragma")
            .removeHeader("Cache-Control")
            .header("Cache-Control", cacheHeaderValue)
            .build()
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        cache: Cache,
        @CacheInterceptorOkHttpClient cacheInterceptor: Interceptor
    ) = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(interceptor)
        .addInterceptor(cacheInterceptor)
        .addNetworkInterceptor(cacheInterceptor)
        .build()

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Singleton
    @Provides
    fun providesFeedbackRemoteDataSource(retrofit: Retrofit): FeedbackRemoteDataSource =
        retrofit.create(FeedbackRemoteDataSource::class.java)
}
