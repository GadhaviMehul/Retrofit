package com.example.retrofit4

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailsViewModel(private val repository: UserDetailRepository) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUserDetails()
        }
    }

    val userDetails: LiveData<MutableList<UserDetails>>
        get() = repository.userDetails
}