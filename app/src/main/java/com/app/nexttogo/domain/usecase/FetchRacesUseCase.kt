package com.app.nexttogo.domain.usecase

import com.app.nexttogo.data.local.entity.toRaceList
import com.app.nexttogo.domain.model.Race
import com.app.nexttogo.domain.repository.RaceRepository
import com.app.nexttogo.utils.states.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case class to fetch the list of races
 * @param raceRepository The repository to interact with the data layer
 */
class FetchRacesUseCase @Inject constructor(private val raceRepository: RaceRepository) {
    suspend operator fun invoke(): Flow<Resource<List<Race>>> {
        return raceRepository.fetchRaces(method = "nextraces", count = 10)
            .map { result ->
                when (result) {
                    is Resource.Loading -> result
                    is Resource.Success -> Resource.Success(result.data.toRaceList())
                    is Resource.Error -> result
                }
            }
    }
}