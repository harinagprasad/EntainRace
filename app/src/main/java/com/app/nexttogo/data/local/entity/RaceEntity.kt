package com.app.nexttogo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.nexttogo.data.remote.model.RaceSummary
import com.app.nexttogo.domain.model.Race

/**
 * Entity class for the race table.
 *
 * @param raceId The id of the race
 * @param raceName The name of the race
 * @param raceNumber The number of the race
 * @param meetingId The id of the meeting
 * @param meetingName The name of the meeting
 * @param categoryId The id of the category
 * @param advertisedStart The start time of the race
 */
@Entity(tableName = "race")
data class RaceEntity(
    @PrimaryKey
    val raceId: String,
    val raceName: String,
    val raceNumber: Int,
    val meetingId: String,
    val meetingName: String,
    val categoryId: String,
    val advertisedStart: Long
)

fun RaceEntity.toRace(): Race {
    return Race(
        raceId = raceId,
        raceName = raceName,
        raceNumber = raceNumber,
        meetingId = meetingId,
        meetingName = meetingName,
        categoryId = categoryId,
        advertisedStart = advertisedStart
    )
}

fun RaceSummary.toRaceEntity(): RaceEntity {
    return RaceEntity(
        raceId = this.raceId,
        raceName = this.raceName,
        raceNumber = this.raceNumber,
        meetingId = this.meetingId,
        meetingName = this.meetingName,
        categoryId = this.categoryId,
        advertisedStart = this.advertisedStart.seconds
    )
}


fun List<RaceEntity>.toRaceList(): List<Race> {
    return map { it.toRace() }
}

fun List<RaceSummary>.toRaceEntityList(): List<RaceEntity> {
    return map { it.toRaceEntity() }
}

