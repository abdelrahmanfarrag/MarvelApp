package com.abdelrahman.api.retrofit.interceptors

import com.abdelrahman.api.BuildConfig
import com.abdelrahman.api.utils.Constants
import com.abdelrahman.api.utils.Constants.MD5_ALGORITHM
import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject

class QueryParamsInterceptor @Inject constructor() : IQueryParamsInterceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val urlBuilder = request.url.newBuilder().apply {
            val timeStamp = System.currentTimeMillis().toString()
            val publicKey = BuildConfig.PUBLIC_KEY
            val privateKey = BuildConfig.PRIVATE_KEY
            addQueryParameter(Constants.TS, timeStamp)
            addQueryParameter(Constants.HASH, md5(timeStamp + privateKey + publicKey))
            addQueryParameter(Constants.API_KEY, BuildConfig.PUBLIC_KEY)
        }.build()
        return chain.proceed(request.newBuilder().url(urlBuilder).build())
    }

    private fun md5(input: String): String {
        val md = MessageDigest.getInstance(MD5_ALGORITHM)
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}
