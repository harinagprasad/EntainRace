package com.app.nexttogo

import com.app.nexttogo.app.db.NextToGoDatabase
import com.app.nexttogo.data.local.LocalDataSourceImpl
import com.app.nexttogo.data.local.dao.RaceDao
import com.app.nexttogo.data.local.entity.RaceEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class LocalDataSourceTest {

    private lateinit var localDataSource: LocalDataSourceImpl
    private lateinit var raceDao: RaceDao
    private lateinit var nextToGoDatabase: NextToGoDatabase

    @Before
    fun setUp() {
        // Mock the dependencies
        raceDao = mock()
        nextToGoDatabase = mock()
        // Set up LocalDataSourceImpl
        whenever(nextToGoDatabase.raceDao()).thenReturn(raceDao)
        localDataSource = LocalDataSourceImpl(nextToGoDatabase)
    }

    @Test
    fun `getRaces returns list of RaceEntity`() = runTest {
        // Prepare mock data
        val races = MockData.localData
        // Mock the DAO method
        whenever(raceDao.getRaces()).thenReturn(flowOf(races))
        // Call the method
        val result = localDataSource.getRaces()
        // Collect the result and verify
        result.collect {
            assertEquals(races, it)
        }
        // Verify that the DAO method was called
        verify(raceDao).getRaces()
    }

    @Test
    fun `insertRaces calls insertRaces in the DAO`() = runTest {
        // Prepare mock data
        val races = listOf(
            RaceEntity(
                raceId = "race1",
                raceName = "Race 1",
                raceNumber = 1,
                meetingId = "meeting1",
                meetingName = "Meeting 1",
                categoryId = "cat1",
                advertisedStart = 1638361200L
            )
        )
        // Call the method
        localDataSource.insertRaces(races)
        // Verify that the insertRaces method was called on the DAO
        verify(raceDao).insertRaces(races)
    }

    @Test
    fun `deleteAllRaces calls deleteAllRaces in the DAO`() = runTest {
        // Call the method
        localDataSource.deleteAllRaces()
        // Verify that the deleteAllRaces method was called on the DAO
        verify(raceDao).deleteAllRaces()
    }
}