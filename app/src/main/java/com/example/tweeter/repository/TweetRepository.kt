package com.example.tweeter.repository

import com.example.tweeter.api.TweeterApi
import com.example.tweeter.model.Tweet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweeterApi: TweeterApi) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>>
        get() = _categories

    private val _tweets = MutableStateFlow<List<Tweet>>(emptyList())
    val tweets: StateFlow<List<Tweet>>
        get() = _tweets


    suspend fun getCategories() {
        val response = tweeterApi.getCategories()
        if(response.isSuccessful && response.body() != null) {
            _categories.emit(response.body()!!)
        }
    }
    suspend fun getTweets(category: String) {
        val result = tweeterApi.getTweets("tweets[?(@.category==\"$category\")]")
        if(result.isSuccessful && result.body() != null) {
            _tweets.emit(result.body()!!)
        }
    }
}