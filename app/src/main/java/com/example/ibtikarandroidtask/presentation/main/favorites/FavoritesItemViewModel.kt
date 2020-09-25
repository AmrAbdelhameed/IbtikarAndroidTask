package com.example.ibtikarandroidtask.presentation.main.favorites

import com.example.ibtikarandroidtask.domain.dto.db.Popular
import com.example.ibtikarandroidtask.presentation.base.BaseItemListener
import com.example.ibtikarandroidtask.utils.AppConstants

class FavoritesItemViewModel(
    popular: Popular,
    private val action: () -> Unit
) {
    val imageUrl: String = AppConstants.MEDIA_BASE_URL + popular.profilePath
    val name: String? = popular.name
    val knownForDepartment: String? = popular.knownForDepartment

    fun onItemClick() {
        action.invoke()
    }

    interface FavoritesItemViewModelListener : BaseItemListener<Popular> {
        override fun onRetryClick() {}
    }
}