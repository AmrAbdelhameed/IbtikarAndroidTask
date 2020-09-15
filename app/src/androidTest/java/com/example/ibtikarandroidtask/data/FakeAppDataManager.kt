package com.example.ibtikarandroidtask.data

import com.example.ibtikarandroidtask.data.local.db.DbRepository
import com.example.ibtikarandroidtask.data.local.db.FakeDbRepository
import com.example.ibtikarandroidtask.data.local.prefs.FakePreferenceRepository
import com.example.ibtikarandroidtask.data.local.prefs.PreferencesRepository
import com.example.ibtikarandroidtask.data.remote.ApiRepository
import com.example.ibtikarandroidtask.data.remote.FakeApiRepository

class FakeAppDataManager(
    private val fakeApiRepository: FakeApiRepository,
    private val fakeDbRepository: FakeDbRepository,
    private val fakePreferencesRepository: FakePreferenceRepository
) : AppDataSource {
    override fun getApiRepository(): ApiRepository {
        TODO("Not yet implemented")
    }

    override fun getDbRepository(): DbRepository {
        TODO("Not yet implemented")
    }

    override fun getPreferencesRepository(): PreferencesRepository {
        TODO("Not yet implemented")
    }
}