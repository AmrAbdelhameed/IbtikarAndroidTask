package com.example.popularpeople.utils

import com.example.popularpeople.domain.dto.api.Result
import com.google.gson.Gson


class TestUtils {
    companion object {
        fun parsePopular(json: String): Result {
            return Gson().fromJson(json, Result::class.java)
        }
    }
}