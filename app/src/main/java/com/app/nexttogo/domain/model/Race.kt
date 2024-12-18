package com.app.nexttogo.domain.model

/**
 * Race class to represent a race
 *
 * @param raceId The id of the race
 * @param raceName The name of the race
 * @param raceNumber The number of the race
 * @param meetingId The id of the meeting
 * @param meetingName The name of the meeting
 * @param categoryId The id of the category
 * @param advertisedStart The start time of the race
 */
class Race(
    val raceId: String,
    val raceName: String,
    val raceNumber: Int,
    val meetingId: String,
    val meetingName: String,
    val categoryId: String,
    val advertisedStart: Long
)