package com.mehyo.darbuka.network

import com.mehyo.darbuka.model.SquareModelItem
import retrofit2.http.GET
import retrofit2.http.Query

interface APIRequests {

    //suspending function for GET network calls
    @GET("repos")
    suspend fun getAPIResult(
        @Query("page") page:Int?,
        @Query("per_page") per_page:Int?
    ): List<SquareModelItem>
}
