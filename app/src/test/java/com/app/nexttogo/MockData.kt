package com.app.nexttogo

import com.app.nexttogo.data.local.entity.RaceEntity
import com.app.nexttogo.data.remote.model.AdvertisedStart
import com.app.nexttogo.data.remote.model.RaceData
import com.app.nexttogo.data.remote.model.RaceResponse
import com.app.nexttogo.data.remote.model.RaceSummary

object MockData {

    val remoteData = RaceResponse(
        status = 200,  // Simulate a successful response status
        data = RaceData(
            nextToGoIds = listOf("id1", "id2", "id3"),
            raceSummaries = mapOf(
                "race1" to RaceSummary(
                    raceId = "race1",
                    raceName = "Race 1",
                    raceNumber = 1,
                    meetingId = "meeting1",
                    meetingName = "Meeting 1",
                    categoryId = "cat1",
                    advertisedStart = AdvertisedStart(seconds = 1638361200),
                    additionalData = "Some additional data",
                    generated = 1,
                    silkBaseUrl = "http://example.com/silk/race1",
                    raceCommentAlternative = "Race comment"
                ),
                "race2" to RaceSummary(
                    raceId = "race2",
                    raceName = "Race 2",
                    raceNumber = 2,
                    meetingId = "meeting2",
                    meetingName = "Meeting 2",
                    categoryId = "cat2",
                    advertisedStart = AdvertisedStart(seconds = 1638361300),
                    additionalData = "Some additional data",
                    generated = 1,
                    silkBaseUrl = "http://example.com/silk/race2",
                    raceCommentAlternative = "Race comment"
                )
            ),
            venueId = "venue1",
            venueName = "Venue 1",
            venueState = "State 1",
            venueCountry = "Country 1"
        ),
        message = "Success"
    )

    val localData = listOf(
        RaceEntity(
            raceId = "race1",
            raceName = "Race 1",
            raceNumber = 1,
            meetingId = "meeting1",
            meetingName = "Meeting 1",
            categoryId = "cat1",
            advertisedStart = 1638361200L
        ),
        RaceEntity(
            raceId = "race2",
            raceName = "Race 2",
            raceNumber = 2,
            meetingId = "meeting2",
            meetingName = "Meeting 2",
            categoryId = "cat2",
            advertisedStart = 1638361300L
        )
    )
}