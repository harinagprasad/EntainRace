package com.app.nexttogo.domain.enums

/**
 * Enum class to represent categories of the race
 */
enum class RaceCategory(val categoryId: String, val categoryName: String) {
    GREYHOUND("9daef0d7-bf3c-4f50-921d-8e818c60fe61", "Greyhound Race"),
    HARNESS("161d9be2-e909-4326-8c2c-35ed71fb460b", "Harness Race"),
    HORSE("4a2788f8-e825-4d36-9894-efd4baf1cfae", "Horse Race")
}