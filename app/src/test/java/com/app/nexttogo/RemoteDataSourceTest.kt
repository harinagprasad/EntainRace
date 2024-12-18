package com.app.nexttogo

import com.app.nexttogo.app.network.ApiService
import com.app.nexttogo.data.remote.RemoteDataSourceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

    private lateinit var remoteDataSource: RemoteDataSourceImpl
    private lateinit var apiService: ApiService

    @Before
    fun setUp() {
        apiService = mock()
        remoteDataSource = RemoteDataSourceImpl(apiService)
    }

    @Test
    fun `fetchRaces returns valid data when API call is successful`() = runTest {
        // Prepare mock data
        val raceResponse = MockData.remoteData
        // Mock the API response
        whenever(apiService.getRaces("method", 5)).thenReturn(raceResponse)
        // Call the method
        val result = remoteDataSource.fetchRaces("method", 5)
        // Assert that the result matches the expected mock data
        assertEquals(raceResponse, result)
    }
}