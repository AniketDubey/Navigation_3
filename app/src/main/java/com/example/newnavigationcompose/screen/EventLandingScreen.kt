package com.example.newnavigationcompose.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.newnavigationcompose.destinationNav.BottomBarItem
import com.example.newnavigationcompose.destinationNav.BottomBarScreens
import com.example.newnavigationcompose.screen.BottomBarScreen.BottomBarEvent
import com.example.newnavigationcompose.screen.BottomBarScreen.BottomBarPeople
import com.example.newnavigationcompose.screen.BottomBarScreen.BottomBarSports

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventLandingScreen(navigateToSettingScreen: () -> Unit) {
    val bottomBarItemList = getBottomBarItemList()
    val curSelectedBottomBarItemIndex = rememberSaveable { mutableIntStateOf(0) }
    val eventNavBackStack =
        rememberNavBackStack<BottomBarScreens>(BottomBarScreens.BottomBarEvent)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nested Graph") },
                actions = {
                    IconButton(
                        onClick = { navigateToSettingScreen() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings icon"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                bottomBarItemList.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = index == curSelectedBottomBarItemIndex.intValue,
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = "$destination icon"
                            )
                        },
                        onClick = {
                            /*if (backStack.lastOrNull() != destination) {
                                if (backStack.lastOrNull() in bottomBarItemList) {
                                    backStack.removeAt(backStack.lastIndex)
                                }
                                backStack.add(destination)
                                currentBottomBarScreen = destination
                            }*/
                            curSelectedBottomBarItemIndex.intValue = index
                            when (curSelectedBottomBarItemIndex.intValue) {
                                0 -> eventNavBackStack.add(BottomBarScreens.BottomBarEvent)
                                1 -> eventNavBackStack.add(BottomBarScreens.BottomBarPeople)
                                2 -> eventNavBackStack.add(BottomBarScreens.BottomBarSportsBar)
                            }
                        }
                    )
                }
            }
        }
    ) { innerpadding ->
        NavDisplay(
            modifier = Modifier.padding(innerpadding),
            backStack = eventNavBackStack,
            onBack = { eventNavBackStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
//                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<BottomBarScreens.BottomBarEvent> {
                    BottomBarEvent()
                }
                entry<BottomBarScreens.BottomBarPeople> {
                    BottomBarPeople()
                }
                entry<BottomBarScreens.BottomBarSportsBar> {
                    BottomBarSports()
                }
            }
        )
    }
}

fun getBottomBarItemList(): List<BottomBarItem> {
    return listOf(
        BottomBarItem(
            title = "Event",
            icon = Icons.Default.Event
        ),
        BottomBarItem(
            title = "People",
            icon = Icons.Default.People
        ),
        BottomBarItem(
            title = "SportsBar",
            icon = Icons.Default.SportsBar
        )
    )
}
