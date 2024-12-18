package com.app.nexttogo.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Race response model to represent a race from the remote data source.
 * @param status The status from the api
 * @param data The data from the api
 * @param message The message from the api
 */

data class RaceResponse(
    val status: Int,
    val data: RaceData,
    val message: String
)

data class RaceData(
    @SerializedName("next_to_go_ids") val nextToGoIds: List<String>,
    @SerializedName("race_summaries") val raceSummaries: Map<String, RaceSummary>,
    @SerializedName("venue_id") val venueId: String,
    @SerializedName("venue_name") val venueName: String,
    @SerializedName("venue_state") val venueState: String,
    @SerializedName("venue_country") val venueCountry: String
)

data class RaceSummary(
    @SerializedName("race_id") val raceId: String,
    @SerializedName("race_name") val raceName: String,
    @SerializedName("race_number") val raceNumber: Int,
    @SerializedName("meeting_id") val meetingId: String,
    @SerializedName("meeting_name") val meetingName: String,
    @SerializedName("category_id") val categoryId: String,
    @SerializedName("advertised_start") val advertisedStart: AdvertisedStart,
    @SerializedName("additional_data") val additionalData: String,
    @SerializedName("generated") val generated: Int,
    @SerializedName("silk_base_url") val silkBaseUrl: String,
    @SerializedName("race_comment_alternative") val raceCommentAlternative: String
)

data class AdvertisedStart(
    val seconds: Long
)
