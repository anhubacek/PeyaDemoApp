package com.example.peyademoapp.utils

import org.json.JSONObject

fun parseErrorMessage(json: String?): String? {
    return try {
        val obj = json?.let { JSONObject(it) }
        obj?.getString("message")
    } catch (e: Exception) {
        null
    }
}
