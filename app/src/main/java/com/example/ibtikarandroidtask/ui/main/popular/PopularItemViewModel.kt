package com.example.ibtikarandroidtask.ui.main.popular

import androidx.databinding.ObservableField
import com.example.ibtikarandroidtask.ui.base.BaseItemListener

class PopularItemViewModel(
    popularDataItem: PopularDataItem,
    private val onItemClick: () -> Unit
) {
    val imageUrl: ObservableField<String?> = ObservableField(popularDataItem.imageUrl)
    val name: ObservableField<String?> = ObservableField(popularDataItem.name)
    val knownForDepartment: ObservableField<String?> = ObservableField(popularDataItem.knownForDepartment)

    fun onItemClick() = onItemClick.invoke()

    interface PopularItemViewModelListener : BaseItemListener<PopularDataItem>
}