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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.borshevskiy.fkn_labs.presentation.MainViewModel

@Composable
fun DetailScreen(heroId: Int?, navController: NavController, viewModel: MainViewModel) {

    /** Посчитал не целесообразным повторное обращение к апи для получения информации о герое,
     * т.к. мы можем просто передать его параметры с главного скрина на детейл скрин */

    val currentHero = viewModel.marvelHeroes.observeAsState().value?.data?.firstOrNull { it.id == heroId }
    Box {
        AsyncImage(modifier = Modifier.fillMaxSize(),
            model = currentHero?.imageLink,
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
                Text(text = currentHero?.name ?: "",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp)
                Text(text = if (currentHero!!.description.isNullOrEmpty()) { "This should be some desc" } else { "" },
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp)
            }
        }
    }
}