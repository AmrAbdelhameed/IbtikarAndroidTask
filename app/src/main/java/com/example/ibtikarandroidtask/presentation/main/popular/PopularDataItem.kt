package com.example.ibtikarandroidtask.presentation.main.popular

import com.example.ibtikarandroidtask.utils.AppConstants
import java.io.Serializable

class PopularDataItem(
    val id: Int,
    val name: String?,
    val knownForDepartment: String?,
    val profilePath: String?,
) : Serializable {
    val imageUrl = AppConstants.MEDIA_BASE_URL + profilePath
}