package com.abdx79.cryptotext

import androidx.annotation.DrawableRes

sealed class ScreenRoutes(val route: String, @DrawableRes val icon: Int) {

    data object Home : ScreenRoutes("home", R.drawable.rounded_home_24)
    data object History : ScreenRoutes("history", R.drawable.rounded_history_24)

    companion object {
        fun getRoutes(): List<ScreenRoutes> {
            return listOf(Home, History)
        }
    }
}
