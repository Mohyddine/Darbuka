package com.mehyo.darbuka.main_repository

import com.mehyo.darbuka.database.repo.DatabaseRepository
import com.mehyo.darbuka.model.SquareRepos
import com.mehyo.darbuka.network.repo.NetworkRepository
import com.mehyo.darbuka.util.Resource
import kotlinx.coroutines.flow.Flow

interface MainRepository:NetworkRepository,DatabaseRepository{
    fun getAllData(): Flow<Resource<List<SquareRepos>>>
    fun getAllBookmarkedData(): Flow<Resource<List<SquareRepos>>>
}