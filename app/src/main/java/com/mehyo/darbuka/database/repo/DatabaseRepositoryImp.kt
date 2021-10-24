package com.mehyo.darbuka.database.repo

import com.mehyo.darbuka.database.SquareReposDB
import com.mehyo.darbuka.model.SquareRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DatabaseRepositoryImp(private val db:SquareReposDB):DatabaseRepository {

    override suspend fun addRepo(item: SquareRepos) = withContext(Dispatchers.IO){
        db.squareReposDAO().addRepo(item)
    }

    override suspend fun addRepos(items: List<SquareRepos>) = withContext(Dispatchers.IO){
        db.squareReposDAO().addRepos(items)
    }

    override suspend fun updateRepo(item: SquareRepos) = withContext(Dispatchers.IO){
        db.squareReposDAO().updateRepo(item)
    }

    override suspend fun deleteRepo(item: SquareRepos) = withContext(Dispatchers.IO){
        db.squareReposDAO().deleteRepo(item)
    }

    override suspend fun deleteALLRepos() = withContext(Dispatchers.IO){
        db.squareReposDAO().deleteALLRepos()
    }

    override suspend fun deleteALLBookmarkedRepos() = withContext(Dispatchers.IO){
        db.squareReposDAO().deleteALLBookmarkedRepos()
    }

    override suspend fun getRepoByID(ID: Int): SquareRepos = withContext(Dispatchers.IO){ db.squareReposDAO().getRepoByID(ID)}

    override fun getBookmarkedRepos(): Flow<List<SquareRepos>> = db.squareReposDAO().getBookmarkedRepos()

    override fun getRepos(): Flow<List<SquareRepos>> =  db.squareReposDAO().getRepos()
}