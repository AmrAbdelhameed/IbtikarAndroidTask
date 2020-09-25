package com.example.ibtikarandroidtask.presentation.main.popular_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ibtikarandroidtask.data.PopularDetailsDataSource
import com.example.ibtikarandroidtask.domain.dto.Result
import com.example.ibtikarandroidtask.domain.dto.api.ImagesResponse
import com.example.ibtikarandroidtask.domain.dto.api.Profile
import com.example.ibtikarandroidtask.domain.dto.db.Popular
import com.example.ibtikarandroidtask.presentation.base.BaseViewModel
import com.example.ibtikarandroidtask.presentation.main.popular.PopularDataItem
import kotlinx.coroutines.launch

class PopularDetailsViewModel(
    private val popularDetailsDataSource: PopularDetailsDataSource
) : BaseViewModel() {
    private val isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    private val imagesLiveData: MutableLiveData<List<ImagesDataItem>> = MutableLiveData()

    private fun insertPopular(popularDataItem: PopularDataItem) {
        viewModelScope.launch {
            popularDetailsDataSource.insert(
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
            popularDetailsDataSource.delete(
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
            when (popularDetailsDataSource.getFavoriteById(id)) {
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
            when (val result = popularDetailsDataSource.getImages(personId)) {
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