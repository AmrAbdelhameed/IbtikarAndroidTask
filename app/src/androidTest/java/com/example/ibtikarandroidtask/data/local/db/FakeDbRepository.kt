package com.example.ibtikarandroidtask.data.local.db

import androidx.lifecycle.LiveData
import com.example.ibtikarandroidtask.data.local.db.DbDataSource
import com.example.ibtikarandroidtask.data.model.Result
import com.example.ibtikarandroidtask.data.model.db.Popular

class FakeDbRepository : DbDataSource {
    override suspend fun insertPopular(popular: Popular) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePopular(popular: Popular) {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: Int): Result<Popular> {
        TODO("Not yet implemented")
    }

    override fun allPopular(): LiveData<List<Popular>> {
        TODO("Not yet implemented")
    }

}