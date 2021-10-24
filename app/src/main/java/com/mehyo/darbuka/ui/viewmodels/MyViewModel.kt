package com.mehyo.darbuka.ui.viewmodels

import androidx.lifecycle.*
import com.mehyo.darbuka.main_repository.MainRepository
import com.mehyo.darbuka.model.SquareRepos

class MyViewModel(private val repository: MainRepository): ViewModel() {

    val repos= repository.getAllData().asLiveData()
    val bookedRepos= repository.getAllBookmarkedData().asLiveData()

    suspend fun getRepoByID(ID: Int) = repository.getRepoByID(ID)

    suspend fun updateRepo(item: SquareRepos)=repository.updateRepo(item)
}