package com.app.nexttogo.data.repository

import com.app.nexttogo.data.local.entity.toRaceEntityList
import com.app.nexttogo.data.source.LocalDataSource
import com.app.nexttogo.data.source.RemoteDataSource
import com.app.nexttogo.domain.repository.RaceRepository
import com.app.nexttogo.utils.parseError
import com.app.nexttogo.utils.states.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * Next Race repository implementation to interact with the data layer.
 * @see RaceRepository
 */
class RaceRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : RaceRepository {

    /**
     * Function to fetch races from remote
     * @param method Type of races to fetch
     * @param count The number of races to fetch
     * @return Flow<Resource<List<RaceEntity>>>
     */
    override suspend fun fetchRaces(
        method: String,
        count: Int,
    ) = flow {
        emit(Resource.Loading)
        val localData = localDataSource.getRaces().first()
        emit(Resource.Success(localData))
        val races = remoteDataSource.fetchRaces(method = method, count = count)
        localDataSource.deleteAllRaces()
        localDataSource.insertRaces(races.data.raceSummaries.values.toList().toRaceEntityList())
        emit(Resource.Success(races.data.raceSummaries.values.toList().toRaceEntityList()))
    }.catch { e ->
        emit(Resource.Error(e.parseError()))
    }
}