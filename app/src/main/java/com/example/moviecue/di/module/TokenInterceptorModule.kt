package com.example.moviecue.di.module

import com.example.moviecue.myutils.MyConstant.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Observes, modifies, and potentially short-circuits requests
 * going out and the corresponding responses coming back in.
 * Typically interceptors add, remove, or transform headers
 * on the request or response.
 *
 * @TokenInterceptorModule modifies each request by adding query parameter i.e
 * api key. So we do not require to add each time api key as a query param manually
 
 Interceptors, according to the documentation, are a powerful mechanism that can monitor, 
 rewrite, and retry the API call. So, when we make an API call, we can either monitor it or perform some tasks
 *
 */
class TokenInterceptorModule :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val token = API_KEY
        val url = original.url.newBuilder().addQueryParameter("apikey", token).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}
