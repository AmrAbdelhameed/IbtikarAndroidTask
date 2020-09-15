package com.example.ibtikarandroidtask.di.builder

import com.example.ibtikarandroidtask.ui.main.MainActivity
import com.example.ibtikarandroidtask.ui.main.popular.PopularFragmentProvider
import com.example.ibtikarandroidtask.ui.main.popular_details.PopularDetailsFragmentProvider
import com.example.ibtikarandroidtask.ui.main.favorites.FavoritesFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [PopularFragmentProvider::class, PopularDetailsFragmentProvider::class, FavoritesFragmentProvider::class])
    abstract fun bindMainActivity(): MainActivity
}