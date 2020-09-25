package com.example.ibtikarandroidtask.domain.dto.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
class Popular(
    @field:PrimaryKey val id: Int,
    val name: String?,
    @field:ColumnInfo(name = "known_for_department") val knownForDepartment: String?,
    @field:ColumnInfo(name = "profile_path") val profilePath: String?
)