package com.app.nexttogo.app.network

import com.app.nexttogo.data.remote.model.RaceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /**
     * Function to fetch races from remote
     * @param method Type of races to fetch
     * @param count  The number of races to fetch
     *
     * @return RaceResponse
     */
    @GET("v1/racing/")
    suspend fun getRaces(
        @Query("method") method: String,
        @Query("count") count: Int
    ): RaceResponse
}