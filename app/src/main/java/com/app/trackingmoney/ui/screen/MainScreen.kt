package com.app.trackingmoney.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.trackingmoney.ui.common.BottomNavigationBar
import com.app.trackingmoney.ui.navigation.Screen
import com.app.trackingmoney.ui.screen.addAmount.AddAmountScreen
import com.app.trackingmoney.ui.screen.home.HomeScreen
import com.app.trackingmoney.ui.screen.summary.SummaryScreen

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Determine the current route.
    val currentRoute = navBackStackEntry?.destination?.route

    val noBottomBarRoutes = listOf(Screen.OnboardingPage.route, Screen.AddTransactionPage.route)

    Scaffold(
        bottomBar = {
            if (currentRoute !in noBottomBarRoutes) {
                BottomNavigationBar(navController = navController)
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.OnboardingPage.route
        ) {
            composable(Screen.OnboardingPage.route) {
                OnBoardingPage (
                    onStartClick = {
                        navController.navigate(Screen.HomePage.route) {
                            popUpTo(Screen.OnboardingPage.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.HomePage.route){
                HomeScreen(
                    innerPadding = innerPadding,
                    onClickAction = {
                        navController.navigate(Screen.AddTransactionPage.route)
                    }
                )
            }
            composable(Screen.AddTransactionPage.route){
                AddAmountScreen(
                    innerPadding = innerPadding,
                    navController = navController
                )
            }
            composable(Screen.SummaryPage.route){
                SummaryScreen(
                    innerPadding = innerPadding,
                    onClickAction = {
                        navController.navigate(Screen.AddTransactionPage.route)
                    }
                )
            }
        }
    }
}