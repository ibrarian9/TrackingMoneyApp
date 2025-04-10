package com.app.trackingmoney.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val title: String,
    val route: String,
    val icon: ImageVector
){
    object Home : NavigationItem("Home", "Home", Icons.Default.Home)
    object Summary : NavigationItem("Summary", "Summary", Icons.AutoMirrored.Default.List)
}
