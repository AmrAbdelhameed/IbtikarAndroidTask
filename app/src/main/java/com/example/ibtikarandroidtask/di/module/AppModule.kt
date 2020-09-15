package com.example.ibtikarandroidtask.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.ibtikarandroidtask.BuildConfig
import com.example.ibtikarandroidtask.data.AppDataManager
import com.example.ibtikarandroidtask.data.AppDataSource
import com.example.ibtikarandroidtask.data.local.db.AppDatabase
import com.example.ibtikarandroidtask.data.remote.network.ApiService
import com.example.ibtikarandroidtask.di.ApiInfo
import com.example.ibtikarandroidtask.utils.AppConstants
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
    @Singleton
    fun provideAppDataSource(appDataManager: AppDataManager): AppDataSource {
        return appDataManager
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
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)
    }
}