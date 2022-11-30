package com.borshevskiy.fkn_labs.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.borshevskiy.fkn_labs.utils.Hero

@Composable
fun DetailScreen(heroName: String?, navController: NavController) {
    var hero = Hero.DEADPOOL
    when(heroName) {
        Hero.IRONMAN.heroName -> hero = Hero.IRONMAN
        Hero.CAPTAINAMERICA.heroName -> hero = Hero.CAPTAINAMERICA
        Hero.SPIDERMAN.heroName -> hero = Hero.SPIDERMAN
        Hero.DOCTORSTRANGE.heroName -> hero = Hero.DOCTORSTRANGE
        Hero.THOR.heroName -> hero = Hero.THOR
        Hero.THANOS.heroName -> hero = Hero.THANOS
    }
    Box {
        AsyncImage(modifier = Modifier.fillMaxSize(),
            model = hero.heroImageLink,
            contentDescription = null,
            contentScale = ContentScale.Crop)
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null, tint = Color.White)
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
            contentAlignment = Alignment.BottomStart) {
            Column {
                Text(text = hero.heroName,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp)
                Text(text = hero.heroDesc,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp)
            }
        }
    }
}