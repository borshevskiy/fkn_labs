package com.borshevskiy.fkn_labs.presentation.screens

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
import com.borshevskiy.fkn_labs.domain.MarvelHero

@Composable
fun DetailScreen(marvelHero: MarvelHero, navController: NavController) {

    Box {
        AsyncImage(modifier = Modifier.fillMaxSize(),
            model = marvelHero.imageLink,
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
                Text(text = marvelHero.name,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp)
                Text(text = marvelHero.description.ifEmpty { "This should be some desc" },
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp)
            }
        }
    }
}