package com.example.ibtikarandroidtask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ibtikarandroidtask.data.local.dao.PopularDao
import com.example.ibtikarandroidtask.domain.dto.db.Popular

@Database(
    entities = [Popular::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun popularDao(): PopularDao
}