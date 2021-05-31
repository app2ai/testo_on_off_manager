package com.example.motilal.presentation_layer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.motilal.repository.ReposRepository

class MainViewModelFactory(private val repository: ReposRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model")
    }
}
