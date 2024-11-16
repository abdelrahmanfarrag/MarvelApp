package com.abdelrahman.shared_presentation.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.gson.Gson

fun Any.toJson(): String {
    return Gson().toJson(this)
}

inline fun <reified D> String?.convertJsonArgumentToClass(): D? {
    return if (this.isNullOrBlank())
        null
    else
        try {
            val gson = Gson()
            val resultGson = this
            gson.fromJson(resultGson, D::class.java)
        } catch (exception: Exception) {
            Log.d("printExcept", "${exception.message}")
            null
        }
}

fun Context.openURLInBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(intent)
}