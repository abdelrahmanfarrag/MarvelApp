package com.abdelrahman.shared_domain.utils

fun String?.defaultString(alt: String = Constants.DASH_STRING): String {
    return this ?: alt
}