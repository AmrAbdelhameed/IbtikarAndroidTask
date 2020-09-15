package com.example.ibtikarandroidtask.data

import com.example.ibtikarandroidtask.data.local.db.DbRepository
import com.example.ibtikarandroidtask.data.local.prefs.PreferencesRepository
import com.example.ibtikarandroidtask.data.remote.ApiRepository

interface AppDataSource {
    fun getApiRepository(): ApiRepository
    fun getDbRepository(): DbRepository
    fun getPreferencesRepository(): PreferencesRepository
}