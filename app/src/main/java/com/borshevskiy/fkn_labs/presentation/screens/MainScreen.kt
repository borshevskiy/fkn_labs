package com.borshevskiy.fkn_labs.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.borshevskiy.fkn_labs.presentation.MainViewModel
import com.borshevskiy.fkn_labs.data.network.model.MarvelResponseDto
import com.borshevskiy.fkn_labs.domain.MarvelHero
import com.borshevskiy.fkn_labs.presentation.navigation.Screen
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@SuppressLint("UnrememberedMutableState")
@ExperimentalSnapperApi
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val allHeroes = viewModel.marvelHeroes.observeAsState().value
    allHeroes?.forEach { Log.d("TEST", "HeroID: ${it.id} HeroName: ${it.name} HeroImage: ${it.imageLink}") }
    val backGroundState = mutableStateOf(Color.White)
    Surface(modifier = Modifier.fillMaxSize(), color = Color.DarkGray) {
        Box(modifier = Modifier
            .padding(top = 250.dp)
            .clip(shape = CutCornerShape(topStart = 450.dp))
            .background(backGroundState.value))
        Box {
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(modifier = Modifier
                    .width(200.dp)
                    .padding(25.dp),
                    painter = painterResource(id = com.borshevskiy.fkn_labs.R.drawable.marvel),
                    contentDescription = null)
                Text(text = stringResource(com.borshevskiy.fkn_labs.R.string.choose_your_hero),
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp)
                HeroList(allHeroes, backGroundState, navController)
            }
        }
    }
}


@ExperimentalSnapperApi
@Composable
fun HeroList(heroList: List<MarvelHero>?, backGroundState: MutableState<Color>, navController: NavController) {
    val lazyListState = rememberLazyListState()
    val layoutInfo =
        rememberLazyListSnapperLayoutInfo(lazyListState)

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            backGroundState.value = Color(kotlin.random.Random.nextLong(0xFFFFFFFF))
        }
    }

    LazyRow(state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(lazyListState)
    ) {
        heroList?.let {
            items(it.take(20)) {
                if (!it.imageLink.contains("image_not_available")) {
                    HeroCard(heroImage = it.imageLink, heroId = it.id, heroName = it.name, navController = navController)
                }
            }
        }
    }
}

@Composable
fun HeroCard(heroImage: String, heroName: String, heroId: Int, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(modifier = Modifier
            .fillMaxHeight()
            .width(400.dp)
            .padding(40.dp)
            .clickable { navController.navigate(Screen.DetailScreen.withArgs(heroId)) },
            shape = RoundedCornerShape(16.dp)
        ) {
            Box {
                AsyncImage(modifier = Modifier.fillMaxSize(),
                    model = heroImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop)
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                    contentAlignment = Alignment.BottomStart) {
                    Text(text = heroName,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 30.sp)
                }
            }
        }
    }
}