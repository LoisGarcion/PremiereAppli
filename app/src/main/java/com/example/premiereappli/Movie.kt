package com.example.premiereappli

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
fun MovieList(windowSizeClass: WindowSizeClass, navController : NavController, searchValue : String) {
    val mainViewModel = MainViewModel()
    mainViewModel.getMovies()
    val movies by mainViewModel.movies.collectAsState()
    LazyVerticalGrid(columns = GridCells.Fixed(2)){
        items(movies.results.size) { index ->
            Text(text = movies.results[index].title)
        }
    }
}
