package com.example.tweeter.api

import com.example.tweeter.model.Tweet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweeterApi {
    @GET("/v3/b/65d58597266cfc3fde8d1ddb?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category : String) : Response<List<Tweet>>   // for dynamic

    @GET("/v3/b/65d58597266cfc3fde8d1ddb?meta=false")
    @Headers("X-JSON-Path: tweets..category")   // for static
    suspend fun getCategories(): Response<List<String>>
}