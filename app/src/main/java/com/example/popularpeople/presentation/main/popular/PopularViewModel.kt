package com.example.popularpeople.presentation.main.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.popularpeople.data.PopularDataSource
import com.example.popularpeople.domain.dto.Result
import com.example.popularpeople.domain.dto.api.PopularResponse
import com.example.popularpeople.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class PopularViewModel(
    private val popularDataSource: PopularDataSource
) : BaseViewModel() {
    private val results: MutableList<com.example.popularpeople.domain.dto.api.Result> = arrayListOf()
    private val popularLiveData: MutableLiveData<List<PopularDataItem>> = MutableLiveData()
    var page: Int = 1

    init {
        fetchPopular(page)
    }

    fun fetchPopular(page: Int) {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = popularDataSource.getPopular(page)) {
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

    private fun mapPopularDataItem(result: List<com.example.popularpeople.domain.dto.api.Result>) {
        popularLiveData.value = result.map {
            PopularDataItem(
                it.id,
                it.name,
                it.knownForDepartment,
                it.imageUrl
            )
        }
    }
}