package com.app.nexttogo.domain.repository

import com.app.nexttogo.data.local.entity.RaceEntity
import com.app.nexttogo.utils.states.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Races repository interface to interact with the data layer.
 */
interface RaceRepository {
    suspend fun fetchRaces(
        method: String,
        count: Int
    ): Flow<Resource<List<RaceEntity>>>
}