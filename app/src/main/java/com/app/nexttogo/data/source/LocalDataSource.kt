package com.app.nexttogo.data.source

import com.app.nexttogo.data.local.entity.RaceEntity
import kotlinx.coroutines.flow.Flow

/**
 * Local data source interface to interact with the local data source.
 */
interface LocalDataSource {
    suspend fun getRaces(): Flow<List<RaceEntity>>
    suspend fun insertRaces(races : List<RaceEntity>)
    suspend fun deleteAllRaces()
}