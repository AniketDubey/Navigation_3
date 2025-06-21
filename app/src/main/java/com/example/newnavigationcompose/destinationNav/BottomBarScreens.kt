package com.example.newnavigationcompose.destinationNav

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomBarScreens : NavKey {

    @Serializable
    data object BottomBarEvent : BottomBarScreens()

    @Serializable
    data object BottomBarPeople : BottomBarScreens()

    @Serializable
    data object BottomBarSportsBar : BottomBarScreens()
}
