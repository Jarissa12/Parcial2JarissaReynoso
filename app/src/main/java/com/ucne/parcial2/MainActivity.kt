package com.ucne.parcial2

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucne.parcial2.ui.Navigation.DrawerMenu
import com.ucne.parcial2.ui.Navigation.ScreenModule
import com.ucne.parcial2.ui.Tickets.TicketsListScreen
import com.ucne.parcial2.ui.Tickets.TicketsScreen


import com.ucne.parcial2.ui.theme.Parcial2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Parcial2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenModule.Start.route
                    ) {
                        composable(ScreenModule.Start.route) {
                            DrawerMenu(navController = navController)
                        }
                        composable(route = ScreenModule.TicketsList.route) {
                            TicketsListScreen(onClickList = {}, navController = navController)
                        }
                        composable(route = ScreenModule.Tickets.route){
                            TicketsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

