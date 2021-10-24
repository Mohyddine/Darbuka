package com.mehyo.darbuka.main_repository

import com.mehyo.darbuka.database.repo.DatabaseRepository
import com.mehyo.darbuka.model.SquareRepos
import com.mehyo.darbuka.model.toSquareRepos
import com.mehyo.darbuka.network.repo.NetworkRepository
import com.mehyo.darbuka.util.networkBoundResource

class MainRepositoryImp(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository
):MainRepository {

    override suspend fun getAPIResult(page: Int?, per_page: Int?) =
        networkRepository.getAPIResult(page,per_page)

    override suspend fun addRepo(item: SquareRepos) = databaseRepository.addRepo(item)

    override suspend fun addRepos(items: List<SquareRepos>) = databaseRepository.addRepos(items)

    override suspend fun updateRepo(item: SquareRepos) = databaseRepository.updateRepo(item)

    override suspend fun deleteRepo(item: SquareRepos) = databaseRepository.deleteRepo(item)

    override suspend fun deleteALLRepos() = databaseRepository.deleteALLRepos()

    override suspend fun deleteALLBookmarkedRepos() = databaseRepository.deleteALLBookmarkedRepos()

    override suspend fun getRepoByID(ID: Int) = databaseRepository.getRepoByID(ID)

    override fun getBookmarkedRepos() = databaseRepository.getBookmarkedRepos()

    override fun getRepos() = databaseRepository.getRepos()

    override fun getAllData() = networkBoundResource(
        query = { getRepos()
        },
        fetch = {
            getAPIResult(1,20)
        },
        saveFetchResult = { repos->
            val items:ArrayList<SquareRepos> = ArrayList()
            repos.forEach {
                items.add(it.toSquareRepos())
            }
            deleteALLRepos()
            addRepos(items = items)
        }
    )

    override fun getAllBookmarkedData() = networkBoundResource(
        query = { getBookmarkedRepos()
        },
        fetch = {
            getAPIResult(1,20)
        },
        saveFetchResult = { repos->
            val items:ArrayList<SquareRepos> = ArrayList()
            repos.forEach {
                items.add(it.toSquareRepos())
            }
            deleteALLRepos()
            addRepos(items = items)
        }
    )
}