package com.example.popularpeople.presentation.main

import androidx.lifecycle.ViewModelProvider
import com.example.popularpeople.BR
import com.example.popularpeople.R
import com.example.popularpeople.ViewModelProviderFactory
import com.example.popularpeople.databinding.ActivityMainBinding
import com.example.popularpeople.presentation.base.BaseActivity
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    HasAndroidInjector {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this, factory).get(MainViewModel::class.java)
}