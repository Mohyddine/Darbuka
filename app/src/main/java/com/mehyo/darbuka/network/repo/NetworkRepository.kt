package com.mehyo.darbuka.network.repo

import com.mehyo.darbuka.model.SquareModelItem

interface NetworkRepository {

    suspend fun getAPIResult(page:Int?, per_page:Int?): List<SquareModelItem>
}