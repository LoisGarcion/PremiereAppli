package com.example.premiereappli

import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ActorList(windowSizeClass: WindowSizeClass, navController : NavController, searchValue : String) {
    Text(text = searchValue)
}
