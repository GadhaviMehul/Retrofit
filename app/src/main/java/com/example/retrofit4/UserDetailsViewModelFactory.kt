package com.example.retrofit4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserDetailsViewModelFactory(private val repository: UserDetailRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserDetailsViewModel(repository) as T
    }
}