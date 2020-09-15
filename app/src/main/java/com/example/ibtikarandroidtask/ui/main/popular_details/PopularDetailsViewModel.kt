package com.example.ibtikarandroidtask.ui.main.popular_details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ibtikarandroidtask.data.AppDataSource
import com.example.ibtikarandroidtask.data.model.Result
import com.example.ibtikarandroidtask.data.model.api.ImagesResponse
import com.example.ibtikarandroidtask.data.model.api.Profile
import com.example.ibtikarandroidtask.data.model.db.Popular
import com.example.ibtikarandroidtask.ui.base.BaseViewModel
import com.example.ibtikarandroidtask.ui.main.popular.PopularDataItem
import kotlinx.coroutines.launch

class PopularDetailsViewModel(
    application: Application,
    appDataSource: AppDataSource
) : BaseViewModel(application, appDataSource) {
    private val isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    private val imagesLiveData: MutableLiveData<List<ImagesDataItem>> = MutableLiveData()

    private fun insertPopular(popularDataItem: PopularDataItem) {
        viewModelScope.launch {
            appDataSource.getDbRepository().insert(
                Popular(
                    popularDataItem.id,
                    popularDataItem.name,
                    popularDataItem.knownForDepartment,
                    popularDataItem.profilePath
                )
            )
            isFavorite.value = true
        }
    }

    private fun deletePopular(popularDataItem: PopularDataItem) {
        viewModelScope.launch {
            appDataSource.getDbRepository().delete(
                Popular(
                    popularDataItem.id,
                    popularDataItem.name,
                    popularDataItem.knownForDepartment,
                    popularDataItem.profilePath
                )
            )
            isFavorite.value = false
        }
    }

    fun getFavoriteById(id: Int) {
        viewModelScope.launch {
            when (appDataSource.getDbRepository().getFavoriteById(id)) {
                is Result.Success<Popular> -> {
                    isFavorite.value = true
                }
                is Result.Error -> {
                    isFavorite.value = false
                }
            }
        }
    }

    fun onFavClick(
        isFavorite: Boolean,
        popularDataItem: PopularDataItem
    ) {
        if (isFavorite) deletePopular(popularDataItem) else insertPopular(popularDataItem)
    }

    fun getIsFavorite(): LiveData<Boolean> {
        return isFavorite
    }

    fun fetchImages(personId: Int) {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = appDataSource.getApiRepository().getImages(personId)) {
                is Result.Success<ImagesResponse> -> {
                    mapImagesDataItem(result.data.profiles)
                    isLoading.value = false
                }
                is Result.Error -> {
                    isLoading.value = false
                    showToast.value = result.message
                }
            }
        }
    }

    val imagesListLiveData: LiveData<List<ImagesDataItem>> = imagesLiveData

    private fun mapImagesDataItem(popular: List<Profile>) {
        imagesLiveData.value = popular.map {
            ImagesDataItem(
                it.filePath
            )
        }
    }
}