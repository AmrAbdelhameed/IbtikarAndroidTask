package com.example.ibtikarandroidtask.presentation.main.popular_details

import com.example.ibtikarandroidtask.utils.AppConstants
import java.io.Serializable

class ImageDataItem(
    filePath: String?
) : Serializable {
    val imageUrl = AppConstants.MEDIA_BASE_URL + filePath
}