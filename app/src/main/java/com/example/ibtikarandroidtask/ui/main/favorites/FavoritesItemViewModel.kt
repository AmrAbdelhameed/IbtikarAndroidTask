package com.example.ibtikarandroidtask.ui.main.favorites

import androidx.databinding.ObservableField
import com.example.ibtikarandroidtask.data.model.db.Popular
import com.example.ibtikarandroidtask.ui.base.BaseItemListener
import com.example.ibtikarandroidtask.utils.AppConstants

class FavoritesItemViewModel(
    popular: Popular,
    private val action: () -> Unit
) {
    val imageUrl: ObservableField<String?> = ObservableField(AppConstants.MEDIA_BASE_URL + popular.profilePath)
    val name: ObservableField<String?> = ObservableField(popular.name)
    val knownForDepartment: ObservableField<String?> = ObservableField(popular.knownForDepartment)

    fun onItemClick() {
        action.invoke()
    }

    interface FavoritesItemViewModelListener : BaseItemListener<Popular> {
        override fun onRetryClick() {}
    }
}