package com.example.ibtikarandroidtask.data.local.db

import androidx.lifecycle.LiveData
import com.example.ibtikarandroidtask.data.model.Result
import com.example.ibtikarandroidtask.data.model.db.Popular
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbRepository @Inject constructor(private val mAppDatabase: AppDatabase) : DbDataSource {
    override suspend fun insert(popular: Popular) = mAppDatabase.popularDao().insert(popular)
    override suspend fun delete(popular: Popular) = mAppDatabase.popularDao().delete(popular)
    override suspend fun getFavoriteById(id: Int): Result<Popular> {
        return try {
            Result.Success(mAppDatabase.popularDao().getFavoriteById(id))
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }
    override fun getAllFavorites(): LiveData<List<Popular>> = mAppDatabase.popularDao().getAllFavorites()
}