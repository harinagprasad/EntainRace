package com.app.nexttogo.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Destination,
    route: String? = null,
    builder: NavGraphBuilder.() -> Unit = {},
) {
  NavHost(
    navController = navController,
    startDestination = startDestination.route,
    modifier = modifier,
    route = route,
    builder = builder,
    enterTransition = { EnterTransition.None },
    exitTransition = { ExitTransition.None },
  )
}

fun NavGraphBuilder.composable(
    destination: Destination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
  composable(
    route = destination.route,
    arguments = arguments,
    deepLinks = deepLinks,
    content = content,
  )
}
