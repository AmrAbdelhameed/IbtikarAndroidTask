package com.example.ibtikarandroidtask.presentation.main.popular

import java.io.Serializable

class PopularDataItem(
    val id: Int,
    val name: String?,
    val knownForDepartment: String?,
    val imageUrl: String?,
) : Serializable