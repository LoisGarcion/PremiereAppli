package com.example.premiereappli

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
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
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun SerieList(windowSizeClass: WindowSizeClass, navController : NavController, searchValue : String) {
    val mainViewModel : MainViewModel = viewModel()
    if(searchValue != "")
    {
        LaunchedEffect(key1 = true) {
            mainViewModel.getSeriesBySearch(searchValue)
        }
    }
    else {
        LaunchedEffect(key1 = true) {

            mainViewModel.getSeries()
        }
    }
    val series by mainViewModel.series.collectAsState()

    when(windowSizeClass.widthSizeClass){
        WindowWidthSizeClass.Compact ->
        {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 60.dp, bottom = 80.dp)
            ){
                items(series.results.size) { index ->
                    SerieBox(series.results[index], windowSizeClass, navController)
                }
            }
        }
        else ->
        {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.padding(start = 100.dp, end = 20.dp, top = 60.dp, bottom = 0.dp),
            ){
                items(series.results.size) { index ->
                    SerieBox(series.results[index], windowSizeClass, navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SerieBox(serie : Serie, windowSizeClass: WindowSizeClass, navController : NavController){
    when(windowSizeClass.widthSizeClass){
        WindowWidthSizeClass.Compact ->
        {
            Card(
                onClick = {navController.navigate("serieDetail/${serie.id}")},
                modifier = Modifier.padding(10.dp).height(305.dp),
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
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize())
                {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = "https://image.tmdb.org/t/p/w780" + serie.poster_path).apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    size(
                                        650,
                                        650
                                    )
                                }).build()
                        ),
                        contentDescription = "Image film ${serie.name}",
                        Modifier.padding(15.dp)
                    )
                    Text(text = serie.name, textAlign = TextAlign.Center, modifier = Modifier.padding(start = 5.dp, end = 5.dp), fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = formatDate(serie.first_air_date), textAlign = TextAlign.Center, modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp))
                }
            }
        }
        else ->
        {
            Card(
                onClick = {navController.navigate("serieDetail/${serie.id}")},
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
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize())
                {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current)
                                .data(data = "https://image.tmdb.org/t/p/w780" + serie.poster_path).apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    size(
                                        475,
                                        475
                                    )
                                }).build()
                        ),
                        contentDescription = "Image film ${serie.name}",
                        Modifier.padding(15.dp)
                    )
                    Text(text = serie.name, textAlign = TextAlign.Center, modifier = Modifier.padding(start = 5.dp, end = 5.dp), fontWeight = FontWeight.Bold)
                    Text(text = formatDate(serie.first_air_date), textAlign = TextAlign.Center, modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp))
                }
            }
        }
    }
}

@Composable
fun DetailSerie(windowSizeClass: WindowSizeClass, navController : NavController, serieId : String){
    val mainViewModel : MainViewModel  = viewModel()

    val serie by mainViewModel.serie.collectAsState()
    LaunchedEffect(key1 = true){
        mainViewModel.getSerieDetails(serieId)
    }

    var genreNames = ""
    for(genre in serie.genres)
    {
        genreNames += genre.name + " "
    }

    when(windowSizeClass.widthSizeClass){
        WindowWidthSizeClass.Compact ->
        {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 60.dp, bottom = 80.dp)
            ) {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = (serie.name),
                            fontSize = 30.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = "https://image.tmdb.org/t/p/w1280" + serie.backdrop_path)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                        size(
                                            1280,
                                            1000
                                        )
                                    }).build()
                            ),
                            contentDescription = "Image film ${serie.name}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 5.dp, end = 5.dp)
                        )
                    }
                }
                item{
                    Row(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(50.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = "https://image.tmdb.org/t/p/w780" + serie.poster_path)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                        size(
                                            200,
                                            200
                                        )
                                    }).build()
                            ),
                            contentDescription = "Image film ${serie.name}",
                            modifier = Modifier.size(200.dp)
                        )
                        Column {
                            Text(text = formatDate(serie.first_air_date), fontSize = 15.sp,color = MaterialTheme.colorScheme.primary, fontStyle = FontStyle.Italic)
                            Text(text = genreNames, fontSize = 15.sp,color = MaterialTheme.colorScheme.primary, fontStyle = FontStyle.Italic)
                        }
                    }
                }
                item{
                    Column {
                        Text(text = "Synopsis", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(start = 15.dp))
                        Text(text = serie.overview, fontSize = 12.sp, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp))
                    }
                }
                item{
                    Text(text = "Têtes d'affiche", fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(start = 20.dp))
                    LazyRow {
                        items(serie.credits.cast.take(15)){
                                cast ->
                            val actor = castToActor(cast)
                            ActorBox(
                                actor = actor,
                                windowSizeClass = windowSizeClass,
                                navController = navController,
                                character = cast.character
                            )
                        }
                    }
                }
            }
        }
        else ->
        {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 60.dp, start = 80.dp)
            ) {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = (serie.name),
                            fontSize = 30.sp,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = "https://image.tmdb.org/t/p/w1280" + serie.backdrop_path)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                        size(
                                            1280,
                                            1000
                                        )
                                    }).build()
                            ),
                            contentDescription = "Image film ${serie.name}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 5.dp, end = 5.dp)
                        )
                    }
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
                                    .data(data = "https://image.tmdb.org/t/p/w780" + serie.poster_path)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                        size(
                                            200,
                                            200
                                        )
                                    }).build()
                            ),
                            contentDescription = "Image film ${serie.name}",
                            modifier = Modifier.size(200.dp)
                        )
                        Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                            Text(text = "Synopsis", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(start = 10.dp))
                            Text(text = serie.overview, fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                            Text(text = formatDate(serie.first_air_date), fontSize = 15.sp, color = MaterialTheme.colorScheme.primary, fontStyle = FontStyle.Italic)
                            Text(text = genreNames, fontSize = 15.sp, color = MaterialTheme.colorScheme.primary, fontStyle = FontStyle.Italic)
                        }
                    }
                }
                item{
                    Text(text = "Têtes d'affiche", fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    LazyRow {
                        items(serie.credits.cast.take(15)){
                                cast ->
                            val actor = castToActor(cast)
                            ActorBox(
                                actor = actor,
                                windowSizeClass = windowSizeClass,
                                navController = navController,
                                character = cast.character
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun formatDate(date: String): String {
    if(date == "")
    {
        return ""
    }
    val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE)
    val wantedFormatDate = SimpleDateFormat("dd MMM yyyy", Locale.FRANCE)
    val dateFormatted = formatDate.parse(date)
    return wantedFormatDate.format(dateFormatted)
}
