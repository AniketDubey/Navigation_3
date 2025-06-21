package com.example.newnavigationcompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.newnavigationcompose.destinationNav.DestinationScreen
import com.example.newnavigationcompose.screen.EnterMobileNumberScreen
import com.example.newnavigationcompose.screen.EnterMobileOTPScreen

@Composable
fun InitialScreen(modifier: Modifier = Modifier) {
//    val backStack = remember { mutableStateListOf<Any>(Destination.EnterMobileNumberScreen) }
    val backStack =
        rememberNavBackStack<DestinationScreen>(DestinationScreen.EnterMobileNumberScreen)

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<DestinationScreen.EnterMobileNumberScreen> { key ->
                EnterMobileNumberScreen {
                    backStack.add(DestinationScreen.EnterOTPScreen)
                }
            }
            entry<DestinationScreen.EnterOTPScreen> { key ->
                EnterMobileOTPScreen()
            }
        }
    )
}