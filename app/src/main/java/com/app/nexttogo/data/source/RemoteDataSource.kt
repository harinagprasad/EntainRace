package com.app.nexttogo.data.source

import com.app.nexttogo.data.remote.model.RaceResponse

/**
 * Remote data source interface to interact with the remote data source.
 */
interface RemoteDataSource {
    suspend fun fetchRaces(method : String, count: Int): RaceResponse
}