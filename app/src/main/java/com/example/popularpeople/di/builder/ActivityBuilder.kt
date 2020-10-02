package com.example.popularpeople.di.builder

import com.example.popularpeople.presentation.main.MainActivity
import com.example.popularpeople.presentation.main.favorites.FavoritesFragmentProvider
import com.example.popularpeople.presentation.main.popular.PopularFragmentProvider
import com.example.popularpeople.presentation.main.popular_details.PopularDetailsFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [PopularFragmentProvider::class, PopularDetailsFragmentProvider::class, FavoritesFragmentProvider::class])
    abstract fun bindMainActivity(): MainActivity
}