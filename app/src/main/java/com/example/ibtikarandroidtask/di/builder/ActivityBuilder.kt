package com.example.ibtikarandroidtask.di.builder

import com.example.ibtikarandroidtask.presentation.main.MainActivity
import com.example.ibtikarandroidtask.presentation.main.favorites.FavoritesFragmentProvider
import com.example.ibtikarandroidtask.presentation.main.popular.PopularFragmentProvider
import com.example.ibtikarandroidtask.presentation.main.popular_details.PopularDetailsFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [PopularFragmentProvider::class, PopularDetailsFragmentProvider::class, FavoritesFragmentProvider::class])
    abstract fun bindMainActivity(): MainActivity
}