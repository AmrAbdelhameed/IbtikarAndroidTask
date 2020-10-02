package com.example.popularpeople.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.popularpeople.data.local.dao.PopularDao
import com.example.popularpeople.domain.dto.db.Popular

@Database(
    entities = [Popular::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun popularDao(): PopularDao
}