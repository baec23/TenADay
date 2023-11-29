package com.baec23.tenaday.navigation.navgraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions
import com.baec23.ludwig.component.navbar.BottomNavigationItem
import com.baec23.tenaday.navigation.NavService
import com.baec23.tenaday.ui.screen.main.addword.addWordScreen
import com.baec23.tenaday.ui.screen.main.addword.addWordScreenRoute
import com.baec23.tenaday.ui.screen.main.quiz.quizScreen
import com.baec23.tenaday.ui.screen.main.quiz.quizScreenRoute

const val mainNavGraphRoute = "main_nav_graph_route"
fun NavGraphBuilder.mainNavGraph() {
    navigation(
        startDestination = quizScreenRoute,
        route = mainNavGraphRoute
    ) {
        quizScreen()
        addWordScreen()
    }
}

fun NavService.navigateToMainNavGraph() {
    navController.navigate(
        mainNavGraphRoute,
        navOptions = navOptions {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    )
}

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = quizScreenRoute,
        iconImageVector = Icons.Default.Home,
        label = "Quiz"
    ),
    BottomNavigationItem(
        route = addWordScreenRoute,
        iconImageVector = Icons.Default.Info,
        label = "Add Word"
    )
)
