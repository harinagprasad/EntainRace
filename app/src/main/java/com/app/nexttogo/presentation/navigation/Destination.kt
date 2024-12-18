package com.app.nexttogo.presentation.navigation

sealed class Destination(val route: String) {
    data object RaceList : Destination("race_list")
}
