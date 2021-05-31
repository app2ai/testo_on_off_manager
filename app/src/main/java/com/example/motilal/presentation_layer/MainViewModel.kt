package com.example.motilal.presentation_layer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.motilal.models.RepositoriesItem
import com.example.motilal.repository.ReposRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: ReposRepository) : ViewModel(){
    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress

    private var _repos = MutableLiveData<List<RepositoriesItem>>()
    val repos: LiveData<List<RepositoriesItem>> = _repos

    fun getRepos(){
        _showProgress.value = true
        var data: List<RepositoriesItem>?
        viewModelScope.launch(Dispatchers.IO) {
            data = repository.getAllRepos()
            println("Data from DB: $data")
            withContext(Dispatchers.Main){
                _showProgress.value = false
                _repos.value = data?: emptyList()
            }
        }
    }
}