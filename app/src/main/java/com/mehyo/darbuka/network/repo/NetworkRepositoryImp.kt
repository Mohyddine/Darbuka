package com.mehyo.darbuka.network.repo

import com.mehyo.darbuka.model.SquareModelItem
import com.mehyo.darbuka.network.APIRequests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class NetworkRepositoryImp(private val api:APIRequests):NetworkRepository {

    override suspend fun getAPIResult(page: Int?, per_page: Int?)
    : List<SquareModelItem> = withContext(Dispatchers.IO){
        api.getAPIResult(page,per_page)
    }
}