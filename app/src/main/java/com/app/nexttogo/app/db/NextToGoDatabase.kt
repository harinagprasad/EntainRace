package com.app.nexttogo.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.nexttogo.data.local.dao.RaceDao
import com.app.nexttogo.data.local.entity.RaceEntity

/**
 * Room database class for the Next To Go app.
 */
@Database(
    entities = [RaceEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NextToGoDatabase : RoomDatabase() {
    abstract fun raceDao(): RaceDao
}