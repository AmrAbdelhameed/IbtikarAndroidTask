package com.example.popularpeople.presentation.main.popular_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.popularpeople.data.PopularDetailsDataSource
import com.example.popularpeople.domain.dto.Result
import com.example.popularpeople.domain.dto.api.ImagesResponse
import com.example.popularpeople.domain.dto.api.Profile
import com.example.popularpeople.domain.dto.db.Popular
import com.example.popularpeople.presentation.base.BaseViewModel
import com.example.popularpeople.presentation.main.popular.PopularDataItem
import kotlinx.coroutines.launch

class PopularDetailsViewModel(
    private val popularDetailsDataSource: PopularDetailsDataSource
) : BaseViewModel() {
    private val isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    private val imageLiveData: MutableLiveData<List<ImageDataItem>> = MutableLiveData()

    private fun insertPopular(popularDataItem: PopularDataItem) {
        viewModelScope.launch {
            popularDetailsDataSource.insert(
                Popular(
                    popularDataItem.id,
                    popularDataItem.name,
                    popularDataItem.knownForDepartment,
                    popularDataItem.imageUrl
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
                    popularDataItem.imageUrl
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

    val imageListLiveData: LiveData<List<ImageDataItem>> = imageLiveData

    private fun mapImagesDataItem(popular: List<Profile>) {
        imageLiveData.value = popular.map {
            ImageDataItem(
                it.filePath
            )
        }
    }
}