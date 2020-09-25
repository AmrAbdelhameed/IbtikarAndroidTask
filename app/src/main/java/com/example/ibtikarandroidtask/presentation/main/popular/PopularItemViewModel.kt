package com.example.ibtikarandroidtask.presentation.main.popular

import com.example.ibtikarandroidtask.presentation.base.BaseItemListener

class PopularItemViewModel(
    popularDataItem: PopularDataItem,
    private val onItemClick: () -> Unit
) {
    val imageUrl: String = popularDataItem.imageUrl
    val name: String? = popularDataItem.name
    val knownForDepartment: String? = popularDataItem.knownForDepartment

    fun onItemClick() = onItemClick.invoke()

    interface PopularItemViewModelListener : BaseItemListener<PopularDataItem>
}