package com.example.ibtikarandroidtask.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ibtikarandroidtask.data.local.db.dao.PopularDao
import com.example.ibtikarandroidtask.data.model.db.Popular

@Database(
    entities = [Popular::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun popularDao(): PopularDao
}