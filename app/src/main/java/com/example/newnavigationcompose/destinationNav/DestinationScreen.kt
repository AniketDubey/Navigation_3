package com.example.newnavigationcompose.destinationNav

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class DestinationScreen: NavKey {
    @Serializable
    data object EnterMobileNumberScreen: DestinationScreen()

    @Serializable
    data object EnterOTPScreen: DestinationScreen()

    @Serializable
    data object SettingScreen: DestinationScreen()

    @Serializable
    data object EventLandingScreen: DestinationScreen()
}