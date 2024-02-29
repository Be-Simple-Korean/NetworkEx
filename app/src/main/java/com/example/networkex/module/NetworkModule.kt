package com.example.networkex.module

import com.example.networkex.network.GitApi
import com.example.networkex.repository.GitRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.github.com/"

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getApiInstance(retrofit: Retrofit): GitApi {
        return retrofit.create(GitApi::class.java)
    }

    @Provides
    @Singleton
    fun getRepositoryImpl(api: GitApi): GitRepositoryImpl {
        return GitRepositoryImpl(api)
    }
}