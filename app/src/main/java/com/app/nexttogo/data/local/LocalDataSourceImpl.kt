package com.app.nexttogo.data.local

import com.app.nexttogo.app.db.NextToGoDatabase
import com.app.nexttogo.data.local.entity.RaceEntity
import com.app.nexttogo.data.source.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Local data source implementation to interact with the local data source.
 * @see LocalDataSource
 */
class LocalDataSourceImpl @Inject constructor(
    private val nextToGoDatabase: NextToGoDatabase
) : LocalDataSource {

    /**
     * Function to fetch races from local
     * @return Flow<List<RaceEntity>>
     */
    override suspend fun getRaces(): Flow<List<RaceEntity>> {
        return nextToGoDatabase.raceDao().getRaces()
    }

    /**
     * Function to insert races into local
     * @param races List<RaceEntity>
     */
    override suspend fun insertRaces(races: List<RaceEntity>) {
        nextToGoDatabase.raceDao().insertRaces(races)
    }

    /**
     * Function to delete all
     */
    override suspend fun deleteAllRaces() {
        nextToGoDatabase.raceDao().deleteAllRaces()
    }
}