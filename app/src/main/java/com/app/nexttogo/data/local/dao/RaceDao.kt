package com.app.nexttogo.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.app.nexttogo.data.local.entity.RaceEntity
import kotlinx.coroutines.flow.Flow

/**
 * Next Race data access object to interact with the next_race table.
 */
@Dao
interface RaceDao {

    @Query("SELECT * FROM race WHERE (advertisedStart - strftime('%s', 'now')) > -59 ORDER BY advertisedStart ASC LIMIT 5")
    fun getRaces(): Flow<List<RaceEntity>>

    @Upsert
    suspend fun insertRaces(nextRaces: List<RaceEntity>)

    @Query("DELETE FROM race")
    suspend fun deleteAllRaces()
}