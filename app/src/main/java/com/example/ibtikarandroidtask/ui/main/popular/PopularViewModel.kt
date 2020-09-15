package com.example.ibtikarandroidtask.ui.main.popular

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ibtikarandroidtask.data.AppDataSource
import com.example.ibtikarandroidtask.data.model.Result
import com.example.ibtikarandroidtask.data.model.api.PopularResponse
import com.example.ibtikarandroidtask.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class PopularViewModel(
    application: Application,
    appDataSource: AppDataSource
) : BaseViewModel(application, appDataSource) {
    private val results: MutableList<com.example.ibtikarandroidtask.data.model.api.Result> = arrayListOf()
    private val popularLiveData: MutableLiveData<List<PopularDataItem>> = MutableLiveData()
    var page: Int = 1

    init {
        fetchPopular(page)
    }

    fun fetchPopular(page: Int) {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = appDataSource.getApiRepository().getPopular(page)) {
                is Result.Success<PopularResponse> -> {
                    results.addAll(result.data.results)
                    mapPopularDataItem(results)
                    isLoading.value = false
                }
                is Result.Error -> {
                    isLoading.value = false
                    showToast.value = result.message
                }
            }
        }
    }

    val popularListLiveData: LiveData<List<PopularDataItem>> = popularLiveData

    private fun mapPopularDataItem(popular: List<com.example.ibtikarandroidtask.data.model.api.Result>) {
        popularLiveData.value = popular.map {
            PopularDataItem(
                it.id,
                it.name,
                it.knownForDepartment,
                it.profilePath
            )
        }
    }
}