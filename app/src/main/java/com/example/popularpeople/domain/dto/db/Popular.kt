package com.example.popularpeople.domain.dto.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
class Popular(
    @field:PrimaryKey val id: Int,
    val name: String?,
    @field:ColumnInfo(name = "known_for_department") val knownForDepartment: String?,
    @field:ColumnInfo(name = "image_url") val imageUrl: String?
)