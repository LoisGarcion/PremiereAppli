package com.example.premiereappli

import android.annotation.SuppressLint
import android.inputmethodservice.Keyboard
import android.widget.FrameLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun GenericPage(windowSizeClass: WindowSizeClass, navController: NavController, content: @Composable (String) -> Unit, searchBar: Boolean) {
    var text by remember { mutableStateOf("") }
    var textSearched by remember { mutableStateOf("") }
    var search by remember { mutableStateOf(false) }
   Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Le super site de Loïs", fontSize = 15.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 10.dp, start = when(windowSizeClass.widthSizeClass){ WindowWidthSizeClass.Compact -> 10.dp else -> 100.dp} )) },
                actions = {
                    if (searchBar) {
                        DockedSearchBar(
                            query = text,
                            onQueryChange = {text = it},
                            onSearch = { search = false; textSearched = it },
                            active = search,
                            onActiveChange = {
                                search = it
                            },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "search") },
                            modifier = Modifier
                                .width(
                                    when (windowSizeClass.widthSizeClass) {
                                        WindowWidthSizeClass.Compact -> 200.dp
                                        else -> 400.dp
                                    }
                                )
                                .height(45.dp)
                            )
                        {
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            when(windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact ->
                    BottomAppBar(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.primary,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    painter = painterResource(id = R.drawable.movie),
                                    contentDescription = "Movie",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clickable {
                                            navController.navigate("movieList")
                                        }
                                )
                                Text(text = "Movies", fontSize = 15.sp)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    painter = painterResource(id = R.drawable.serie),
                                    contentDescription = "Serie",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clickable {
                                            navController.navigate("serieList")
                                        }
                                )
                                Text(text = "Series", fontSize = 15.sp)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    painter = painterResource(id = R.drawable.person),
                                    contentDescription = "Actor",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clickable {
                                            navController.navigate("actorList")
                                        }
                                )
                                Text(text = "Actors", fontSize = 15.sp)
                            }
                        }
                    }
                else -> {
                    Surface(modifier = Modifier
                        .fillMaxHeight()
                        .width(75.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                        Column(verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    painter = painterResource(id = R.drawable.movie),
                                    contentDescription = "Movie",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clickable {
                                            navController.navigate("movieList")
                                        }
                                )
                                Text(text = "Movies", fontSize = 15.sp)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    painter = painterResource(id = R.drawable.serie),
                                    contentDescription = "Serie",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clickable {
                                            navController.navigate("serieList")
                                        }
                                )
                                Text(text = "Series", fontSize = 15.sp)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    painter = painterResource(id = R.drawable.person),
                                    contentDescription = "Actor",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clickable {
                                            navController.navigate("actorList")
                                        }
                                )
                                Text(text = "Actors", fontSize = 15.sp)
                            }
                        }
                    }
                }
            }
        }
   )
   {
       content(textSearched)
   }
}