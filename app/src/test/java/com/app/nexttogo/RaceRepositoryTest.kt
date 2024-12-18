package com.app.nexttogo

import com.app.nexttogo.data.repository.RaceRepositoryImpl
import com.app.nexttogo.data.source.LocalDataSource
import com.app.nexttogo.data.source.RemoteDataSource
import com.app.nexttogo.domain.repository.RaceRepository
import com.app.nexttogo.utils.states.Resource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RaceRepositoryTest {

    private val remoteDataSource: RemoteDataSource = mock()
    private val localDataSource: LocalDataSource = mock()
    private val raceRepository: RaceRepository =
        RaceRepositoryImpl(remoteDataSource, localDataSource)

    @Test
    fun `fetchRaces returns Success with local and remote data`() = runTest {
        // Mock local data
        val localData = MockData.localData
        whenever(localDataSource.getRaces()).thenReturn(flowOf(localData))
        // Mock remote data
        val remoteData = MockData.remoteData
        whenever(remoteDataSource.fetchRaces(any(), any())).thenReturn(remoteData)
        val result = raceRepository.fetchRaces("method", 5).toList()
        assertTrue(result[0] is Resource.Loading)  // First emission is Loading
        assertTrue(result[1] is Resource.Success)  // Second emission is Success
        // Verify interactions
        verify(localDataSource).getRaces()
        verify(remoteDataSource).fetchRaces("method", 5)
        // Verify result content
        val successState = result[1] as Resource.Success
        assertEquals(localData, successState.data)  // Ensure the data is what we expect
    }
}

