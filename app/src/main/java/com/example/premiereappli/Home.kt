package com.example.premiereappli

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CV(windowSizeClass: WindowSizeClass, navController : NavController) {
       when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()

            ) {
                ImageRonde()
                TexteIntro()
                Spacer(modifier = Modifier.size(20.dp))
                Contact()
                BoutonDemarrer()
            }
        }
        else -> {
            Row(
                modifier = Modifier.fillMaxSize(),
                Arrangement.SpaceEvenly,
                Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ImageRonde()
                    TexteIntro()
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Contact()
                    Spacer(modifier = Modifier.size(20.dp))
                    BoutonDemarrer()
                }
            }
        }
    }
}

@Composable
fun ImageRonde(){
    Image(
        painter = painterResource(id = R.drawable.sexotik),
        contentDescription = "exotik cv",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(250.dp)
            .clip(CircleShape)
    )
}

@Composable
fun TexteIntro(){
    Text(text = "Garcion Loïs", fontSize = 25.sp)
    Text(text = "Tesla cofounder and CEO", fontSize = 15.sp)
    Text(text = "Ecole d'ingénieur ISIS", fontSize = 15.sp)
}

@Composable
fun Contact(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "lois.garcion34@gmail.com", fontSize = 15.sp)
        Text(text = "https://www.linkedin.com/in/lois-garcion/", fontSize = 15.sp)
    }
}

@Composable
fun BoutonDemarrer() {

}

