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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.borshevskiy.fkn_labs.presentation.LoadHeroInfoFromApiEvent
import com.borshevskiy.fkn_labs.presentation.MainViewModel

@Composable
fun DetailScreen(heroId: Int, navController: NavController, viewModel: MainViewModel) {

    val state = viewModel.state.collectAsState().value
    if (state.heroInfo == null || state.heroInfo.id != heroId) {
        viewModel.obtainEvent(LoadHeroInfoFromApiEvent(heroId))
    }

    state.heroInfo?.let {
        Box {
            AsyncImage(modifier = Modifier.fillMaxSize(),
                model = it.imageLink,
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
                    Text(text = it.name,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 30.sp)
                    Text(text = it.description.ifEmpty { "This should be some desc" },
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 30.sp)
                }
            }
        }
    }
}