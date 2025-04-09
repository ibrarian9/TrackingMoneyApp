package com.app.trackingmoney.ui.navigation

sealed class Screen(val route: String) {
    data object HomePage: Screen("home")
    data object OnboardingPage: Screen("onboarding")
    data object SummaryPage: Screen("summary")
    data object AddTransactionPage: Screen("transaction")
}