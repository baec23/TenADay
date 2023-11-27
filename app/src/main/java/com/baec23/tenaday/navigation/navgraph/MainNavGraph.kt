package com.baec23.tenaday.navigation.navgraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import androidx.navigation.navOptions
import com.baec23.ludwig.component.navbar.BottomNavigationItem
import com.baec23.tenaday.navigation.NavService
import com.baec23.tenaday.ui.screen.main.addword.addWordScreen
import com.baec23.tenaday.ui.screen.main.addword.addWordScreenRoute
import com.baec23.tenaday.ui.screen.main.first.firstScreen
import com.baec23.tenaday.ui.screen.main.firstdetails.firstDetailsScreen
import com.baec23.tenaday.ui.screen.main.quiz.quizScreen
import com.baec23.tenaday.ui.screen.main.quiz.quizScreenRoute
import com.baec23.tenaday.ui.screen.main.second.secondScreen
import com.baec23.tenaday.ui.screen.main.third.thirdScreen
import com.baec23.tenaday.ui.screen.main.third.thirdScreenRoute

const val mainNavGraphRoute = "main_nav_graph_route"
fun NavGraphBuilder.mainNavGraph() {
    navigation(startDestination = quizScreenRoute, route = mainNavGraphRoute) {
        quizScreen()
        addWordScreen()
        firstScreen()
        secondScreen()
        thirdScreen()
        firstDetailsScreen()
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
    ),
    BottomNavigationItem(
        route = thirdScreenRoute,
        iconImageVector = Icons.Default.Person,
        label = "Third"
    )
)
