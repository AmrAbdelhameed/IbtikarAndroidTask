package com.example.ibtikarandroidtask.ui.main

import androidx.lifecycle.ViewModelProvider
import com.example.ibtikarandroidtask.BR
import com.example.ibtikarandroidtask.R
import com.example.ibtikarandroidtask.ViewModelProviderFactory
import com.example.ibtikarandroidtask.databinding.ActivityMainBinding
import com.example.ibtikarandroidtask.ui.base.BaseActivity
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