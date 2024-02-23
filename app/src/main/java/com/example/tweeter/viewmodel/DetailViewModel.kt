package com.example.tweeter.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweeter.model.Tweet
import com.example.tweeter.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel@Inject constructor(private val repository: TweetRepository, private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val tweets: StateFlow<List<Tweet>>
        get() = repository.tweets

    init {
        viewModelScope.launch{
            val category = savedStateHandle.get<String>("category")?:"facts"
            repository.getTweets(category)
        }
    }
}