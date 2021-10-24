package com.mehyo.darbuka.database.repo

import com.mehyo.darbuka.model.SquareRepos
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    suspend fun addRepo(item: SquareRepos)

    suspend fun addRepos(items:List<SquareRepos>)

    suspend fun updateRepo(item: SquareRepos)

    suspend fun deleteRepo(item: SquareRepos)

    suspend fun deleteALLRepos()

    suspend fun deleteALLBookmarkedRepos()

    suspend fun getRepoByID(ID:Int): SquareRepos

    fun getBookmarkedRepos(): Flow<List<SquareRepos>>

    fun getRepos(): Flow<List<SquareRepos>>
}