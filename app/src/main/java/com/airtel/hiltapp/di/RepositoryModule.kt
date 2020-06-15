package com.airtel.hiltapp.di

import com.airtel.hiltapp.network.ApiService
import com.airtel.hiltapp.network.CountryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by SURYA N on 13/6/20.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    private val BASE_URL = "https://raw.githubusercontent.com/"

    @Provides
    @ActivityRetainedScoped
    fun providesRetrofit(): ApiService {
        return createApiInstance()
    }

    private fun createApiInstance(): ApiService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor) // same for .addInterceptor(...)
            .connectTimeout(30, TimeUnit.SECONDS) //Backend is really slow
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @ActivityRetainedScoped
    fun providesCountry(apiService: ApiService) = CountryService(apiService)


}