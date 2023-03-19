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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ucne.parcial2.ui.navigation.DrawerMenu
import com.ucne.parcial2.ui.navigation.ScreenModule
import com.ucne.parcial2.ui.Tickets.TicketsListScreen
import com.ucne.parcial2.ui.Tickets.TicketsScreen


import com.ucne.parcial2.ui.theme.Parcial2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Parcial2Theme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = ScreenModule.Start.route
                ) {

                    /*******************HOME*********************/
                    composable(ScreenModule.Start.route) {
                        DrawerMenu(navController = navController)
                    }

                    /*****************CONSULTA*********************/
                    composable(route = ScreenModule.TicketsList.route) {
                        TicketsListScreen(onTicketClick = { ticketId ->
                            navController.navigate(ScreenModule.Tickets.route+ "/$ticketId")
                        }, navController = navController)
                    }

                    /*****************REGISTRO*********************/
                    composable(route = ScreenModule.Tickets.route + "/{id}",
                        arguments = listOf(
                            navArgument("id"){type = NavType.IntType}
                        )
                    ){ entrada ->
                        val id = entrada.arguments?.getInt("id") ?: 0
                        TicketsScreen(ticketId = id, navController = navController)
                    }
                }
            }
        }
    }
}



