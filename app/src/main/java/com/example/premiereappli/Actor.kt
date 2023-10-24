package com.example.premiereappli

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
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
                    ActorBox(actors.results[index], windowSizeClass, navController, "")
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
                    ActorBox(actors.results[index], windowSizeClass, navController, "")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActorBox(actor : Actor, windowSizeClass: WindowSizeClass, navController: NavController, character : String) {
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Card(
                onClick = {navController.navigate("actorDetail/${actor.id}")},
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
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center)
                {
                    if(actor.profile_path != "") {
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
                    }
                    Text(
                        text = actor.name,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        fontWeight = FontWeight.Bold
                    )
                    if(character != "") {
                        Text(
                            text = "As $character",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        else -> {
            Card(
                onClick = {navController.navigate("actorDetail/${actor.id}")},
                modifier = Modifier
                    .padding(8.dp)
                    .height(260.dp),
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
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 10.dp)
                )
                {
                    if(actor.profile_path != "") {
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
                            Modifier
                                .padding(15.dp)
                                .height(180.dp)
                        )
                    }
                    Text(
                        text = actor.name,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 5.dp),
                        fontWeight = FontWeight.Bold
                    )
                    if(character != "") {
                        Text(
                            text = "As $character",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailActor(windowSizeClass: WindowSizeClass, navController : NavController, actorId : String){
    val mainViewModel : MainViewModel  = viewModel()

    val actor by mainViewModel.actor.collectAsState()
    LaunchedEffect(key1 = true){
        mainViewModel.getActorDetails(actorId)
    }

    val birthday = formatDate(actor.birthday)

    when(windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            LazyColumn(modifier = Modifier.padding(top = 60.dp)){
                item {
                    Row(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(50.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = "https://image.tmdb.org/t/p/w780" + actor.profile_path)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                        size(
                                            200,
                                            200
                                        )
                                    }).build()
                            ),
                            contentDescription = "Image actor ${actor.name}",
                            modifier = Modifier.size(200.dp)
                        )
                        Column {
                            Text(
                                text = formatDate(actor.birthday),
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontStyle = FontStyle.Italic
                            )
                            Text(
                                text = "Also known for : ${actor.known_for_department}",
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.primary,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier.padding(top = 10.dp)
                            )
                        }
                    }
                }
                item{
                    Column {
                        Text(text = "Biographie", fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(start = 15.dp))
                        Text(text = actor.biography, fontSize = 12.sp, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp))
                    }
                }
            }
        }
        else -> {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 60.dp, start = 80.dp, bottom = 5.dp)
                )
                {
                    item{
                        Text(
                            text = (actor.name),
                            fontSize = 25.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    }
                    item{
                        Row(
                            modifier = Modifier
                                .padding(top = 5.dp, bottom = 20.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(data = "https://image.tmdb.org/t/p/w780" + actor.profile_path)
                                        .apply(block = fun ImageRequest.Builder.() {
                                            crossfade(true)
                                            size(
                                                200,
                                                200
                                            )
                                        }).build()
                                ),
                                contentDescription = "Image actor ${actor.name}",
                                modifier = Modifier.size(180.dp)
                            )
                            Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                                    Text(
                                        text = birthday,
                                        fontSize = 15.sp,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                        modifier = Modifier.padding(top = 10.dp)
                                    )
                                Text(text = "Know for : ${actor.known_for_department}", fontSize = 15.sp, color = MaterialTheme.colorScheme.primary, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                            }
                        }
                    }
                    item{
                        Text(text = "Biographie", fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(start = 10.dp))
                        Text(text = actor.biography, fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                    }
            }
        }
    }
}

fun castToActor(cast : Cast) : Actor{
    Log.d("test","le profile path : " + cast.profile_path)
    if(cast.profile_path == null){
        return Actor(cast.adult,cast.gender,cast.id, listOf(),cast.known_for_department, "", cast.name, cast.original_name, cast.popularity, "", "","")
    }
    return Actor(cast.adult,cast.gender,cast.id, listOf(),cast.known_for_department, "", cast.name, cast.original_name, cast.popularity, cast.profile_path)
}

private fun formatDate(date: String): String {
    var dateFormatted: Date
    if(date == "" || date == "null")
    {
        return "Unknow birthday"
    }
    val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE)
    val wantedFormatDate = SimpleDateFormat("dd MMM yyyy", Locale.FRANCE)
    try {
        dateFormatted = formatDate.parse(date)!!
    } catch (e: Exception) {
        return "Unknow birthday"
    }
    return wantedFormatDate.format(dateFormatted)
}