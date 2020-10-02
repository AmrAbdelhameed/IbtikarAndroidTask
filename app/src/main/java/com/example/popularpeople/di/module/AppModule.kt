package com.example.popularpeople.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.popularpeople.BuildConfig
import com.example.popularpeople.data.PopularDataSource
import com.example.popularpeople.data.PopularDetailsDataSource
import com.example.popularpeople.data.PopularFavoritesDataSource
import com.example.popularpeople.data.local.AppDatabase
import com.example.popularpeople.data.remote.ApiService
import com.example.popularpeople.di.ApiInfo
import com.example.popularpeople.domain.repository.PopularDetailsRepository
import com.example.popularpeople.domain.repository.PopularFavoritesRepository
import com.example.popularpeople.domain.repository.PopularRepository
import com.example.popularpeople.utils.AppConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @ApiInfo
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppConstants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun providePopularDataSource(popularRepository: PopularRepository): PopularDataSource {
        return popularRepository
    }

    @Provides
    @Singleton
    fun providePopularDetailsDataSource(popularDetailsRepository: PopularDetailsRepository): PopularDetailsDataSource {
        return popularDetailsRepository
    }

    @Provides
    @Singleton
    fun providePopularFavoritesDataSource(popularFavoritesRepository: PopularFavoritesRepository): PopularFavoritesDataSource {
        return popularFavoritesRepository
    }
}