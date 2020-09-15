package com.example.ibtikarandroidtask.data

import com.example.ibtikarandroidtask.data.local.db.DbRepository
import com.example.ibtikarandroidtask.data.local.prefs.PreferencesRepository
import com.example.ibtikarandroidtask.data.remote.ApiRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dbRepository: DbRepository,
    private val preferencesRepository: PreferencesRepository
) : AppDataSource {
    override fun getApiRepository(): ApiRepository = apiRepository
    override fun getDbRepository(): DbRepository = dbRepository
    override fun getPreferencesRepository(): PreferencesRepository = preferencesRepository
}
