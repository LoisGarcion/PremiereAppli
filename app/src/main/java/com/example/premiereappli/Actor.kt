package com.example.premiereappli

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ActorList(windowSizeClass: WindowSizeClass, navController : NavController, searchValue : String) {
    val mainViewModel : MainViewModel = viewModel()
    if(searchValue != "")
    {
        LaunchedEffect(key1 = true) {
            mainViewModel.getActorsBySearch(searchValue)
        }
    }
    else
    {
        LaunchedEffect(key1 = true) {
            mainViewModel.getActors()
        }
    }
    val actors by mainViewModel.actors.collectAsState()

    when(windowSizeClass.widthSizeClass){
        WindowWidthSizeClass.Compact ->
        {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 60.dp, bottom = 80.dp)
            ){
                items(actors.results.size) { index ->
                    ActorBox(actors.results[index], windowSizeClass, navController)
                }
            }
        }
        else ->
        {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.padding(start = 100.dp, end = 20.dp, top = 60.dp, bottom = 0.dp),
            ){
                items(actors.results.size) { index ->
                    ActorBox(actors.results[index], windowSizeClass, navController)
                }
            }
        }
    }
}

@Composable
fun ActorBox(actor : Actor, windowSizeClass: WindowSizeClass, navController: NavController) {
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Card(
                modifier = Modifier.padding(10.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.primary

                )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = "https://image.tmdb.org/t/p/w780" + actor.profile_path)
                                .apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    size(
                                        650,
                                        650
                                    )
                                }).build()
                        ),
                        contentDescription = "Image film ${actor.name}",
                        Modifier.padding(15.dp)
                    )
                    Text(
                        text = actor.name,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        else -> {
            Card(
                modifier = Modifier.padding(8.dp).height(260.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.primary

                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                )
                {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = "https://image.tmdb.org/t/p/w780" + actor.profile_path)
                                .apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    size(
                                        650,
                                        650
                                    )
                                }).build()
                        ),
                        contentDescription = "Image film ${actor.name}",
                        Modifier.padding(15.dp)
                    )
                    Text(
                        text = actor.name,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun DetailActor(windowSizeClass: WindowSizeClass, navController : NavController, actorId : String){

}

fun castToActor(cast : Cast) : Actor{
    print("le profile path : " + cast.profile_path)
    //APPAREMMENT LE PROFIL PATH DE MON CAST EST NULL
    return Actor(cast.adult,cast.gender,cast.id, listOf(),cast.known_for_department, "", cast.name, cast.original_name, cast.popularity, cast.profile_path)
}

