package com.example.ibtikarandroidtask.data

import com.example.ibtikarandroidtask.data.local.db.FakeDbRepository
import com.example.ibtikarandroidtask.data.local.prefs.FakePreferenceRepository
import com.example.ibtikarandroidtask.data.remote.FakeApiRepository

interface FakeAppDataSource {
    fun getFakeApiRepository(): FakeApiRepository
    fun getFakeDbRepository(): FakeDbRepository
    fun getFakePreferencesRepository(): FakePreferenceRepository
}