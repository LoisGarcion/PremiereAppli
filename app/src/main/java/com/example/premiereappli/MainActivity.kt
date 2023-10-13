package com.example.premiereappli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    GenericPage(
                        windowSizeClass = windowSizeClass,
                        navController = navController,
                        content = {
                            CV(
                                windowSizeClass = windowSizeClass,
                                navController = navController,
                            )
                        },
                        searchBar = false
                    )
                }
                composable("movieList") {
                    GenericPage(
                        windowSizeClass = windowSizeClass,
                        navController = navController,
                        content = {
                            MovieList(
                                windowSizeClass = windowSizeClass,
                                navController = navController,
                                searchValue = it
                            )
                        },
                        searchBar = true
                    )
                }
                composable("serieList") {
                    GenericPage(
                        windowSizeClass = windowSizeClass,
                        navController = navController,
                        content = {
                            SerieList(
                                windowSizeClass = windowSizeClass,
                                navController = navController,
                                searchValue = it
                            )
                        },
                        searchBar = true
                    )
                }
                composable("actorList") {
                    GenericPage(
                        windowSizeClass = windowSizeClass,
                        navController = navController,
                        content = {
                            ActorList(windowSizeClass = windowSizeClass, navController = navController, searchValue = it)
                        },
                        searchBar = true
                    )
                }
            }
            navController.navigate("home")
        }
    }
}