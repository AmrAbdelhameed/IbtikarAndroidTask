package com.example.ibtikarandroidtask.utils

import com.example.ibtikarandroidtask.domain.dto.api.Result
import com.google.gson.Gson


class TestUtils {
    companion object {
        fun parsePopular(json: String): Result {
            return Gson().fromJson(json, Result::class.java)
        }
    }
}