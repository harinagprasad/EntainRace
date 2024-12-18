package com.app.nexttogo.data.remote

import com.app.nexttogo.app.network.ApiService
import com.app.nexttogo.data.remote.model.RaceResponse
import com.app.nexttogo.data.source.RemoteDataSource
import javax.inject.Inject

/**
 * Remote data source implementation to interact with the remote data source.
 * @see RemoteDataSource
 */
class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {

    /**
     * Function to fetch races from remote
     * @param method Type of races to fetch
     * @param count The number of races to fetch
     *
     * @return RaceResponse
     */
    override suspend fun fetchRaces(method: String, count: Int): RaceResponse {
        return apiService.getRaces(method = method, count = count)
    }
}