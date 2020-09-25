package com.example.ibtikarandroidtask.presentation.main.popular

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PopularFragmentProvider {
    @ContributesAndroidInjector
    abstract fun providePopularFragmentFactory(): PopularFragment
}