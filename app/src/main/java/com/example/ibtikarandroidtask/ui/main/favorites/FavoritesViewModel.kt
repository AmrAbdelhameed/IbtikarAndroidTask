package com.example.ibtikarandroidtask.ui.main.favorites

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.ibtikarandroidtask.data.AppDataSource
import com.example.ibtikarandroidtask.data.model.db.Popular
import com.example.ibtikarandroidtask.ui.base.BaseViewModel

class FavoritesViewModel(
    application: Application,
    appDataSource: AppDataSource
) : BaseViewModel(application, appDataSource) {
    val popularLiveData: LiveData<List<Popular>> = appDataSource.getDbRepository().getAllFavorites()
}