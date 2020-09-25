package com.example.ibtikarandroidtask.presentation.main.popular_details

import com.example.ibtikarandroidtask.presentation.base.BaseItemListener

class ImageItemViewModel(
    imagesDataItem: ImagesDataItem,
    private val onItemClick: () -> Unit
) {
    val imageUrl: String = imagesDataItem.imageUrl

    fun onItemClick() = onItemClick.invoke()

    interface ImageItemViewModelListener : BaseItemListener<ImagesDataItem> {
        override fun onRetryClick() {}
    }

}