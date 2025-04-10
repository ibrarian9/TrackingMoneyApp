package com.app.trackingmoney.ui.common

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.trackingmoney.ui.navigation.NavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavController
){
    val screens = listOf(
        NavigationItem.Home,
        NavigationItem.Summary
    )

    val currentDestionation = navController.currentBackStackEntryAsState().value?.destination

    NavigationBar(
        containerColor = Color.White
    ) {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title, tint = Color.Black) },
                label = { Text(text = screen.title, color = Color.Black) },
                selected = currentDestionation?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}