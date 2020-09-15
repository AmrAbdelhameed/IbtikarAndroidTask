package com.example.ibtikarandroidtask.ui.main.popular_details

import androidx.databinding.ObservableField
import com.example.ibtikarandroidtask.ui.base.BaseItemListener

class ImageItemViewModel(
    imagesDataItem: ImagesDataItem,
    private val onItemClick: () -> Unit
) {
    val imageUrl: ObservableField<String?> = ObservableField(imagesDataItem.imageUrl)

    fun onItemClick() = onItemClick.invoke()

    interface ImageItemViewModelListener : BaseItemListener<ImagesDataItem> {
        override fun onRetryClick() {}
    }

}