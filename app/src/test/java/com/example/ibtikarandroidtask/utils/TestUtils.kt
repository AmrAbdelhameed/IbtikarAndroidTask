package com.example.ibtikarandroidtask.utils

import com.example.ibtikarandroidtask.data.model.api.Result
import com.example.ibtikarandroidtask.ui.main.popular.PopularDataItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

const val jsonResponseFileName = "popular_response.json"

class TestUtils {

    companion object {
        fun getJson(path: String): String {
            val uri = this.javaClass.classLoader!!.getResource(path)
            val file = File(uri.path)
            return String(file.readBytes())
        }

        fun getPopularTestObject(): List<PopularDataItem> {
            val json = getJson(jsonResponseFileName)
            return parsePopularList(json)
        }

        private fun parsePopularList(json: String): List<PopularDataItem> {
            return Gson().fromJson(
                json, object : TypeToken<List<PopularDataItem>>() {}.type
            )
        }

        fun parsePopular(json: String): Result {
            return Gson().fromJson(json, Result::class.java)
        }

    }
}