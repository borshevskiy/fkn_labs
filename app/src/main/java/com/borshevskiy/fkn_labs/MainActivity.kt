package com.borshevskiy.fkn_labs

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.borshevskiy.fkn_labs.ui.theme.*
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@ExperimentalSnapperApi
class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Fkn_labsTheme {
                val backGroundState = mutableStateOf(Deadpool)
                Surface(modifier = Modifier.fillMaxSize(), color = Color.DarkGray) {
                    Box(modifier = Modifier
                        .padding(top = 250.dp)
                        .clip(shape = CutCornerShape(topStart = 450.dp))
                        .background(backGroundState.value))
                    Box {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(modifier = Modifier
                                .width(200.dp)
                                .padding(25.dp),
                                painter = painterResource(id = R.drawable.marvel),
                                contentDescription = null)
                            Text(text = getString(R.string.choose_your_hero),
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 30.sp)
                            HeroList(backGroundState)
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalSnapperApi
@Composable
fun HeroList(backGroundState: MutableState<Color>) {
    val lazyListState = rememberLazyListState()
    val layoutInfo =
        rememberLazyListSnapperLayoutInfo(lazyListState)

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            when (layoutInfo.currentItem?.index) {
                0 -> backGroundState.value = Deadpool
                1 -> backGroundState.value = Ironman
                2 -> backGroundState.value = CaptainAmerica
                3 -> backGroundState.value = SpiderMan
                4 -> backGroundState.value = DoctorStrange
                5 -> backGroundState.value = Thor
                6 -> backGroundState.value = Thanos
            }
            if (layoutInfo.currentItem?.offset == -1020) backGroundState.value = Thanos
        }
    }

    LazyRow(state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(lazyListState)) {
        items(listOf(Hero.DEADPOOL,
            Hero.IRONMAN,
            Hero.CAPTAINAMERICA,
            Hero.SPIDERMAN,
            Hero.DOCTORSTRANGE,
            Hero.THOR,
            Hero.THANOS)) {
            HeroCard(heroImage = it.heroImage, heroName = it.heroName)
        }
    }
}

@Composable
fun HeroCard(heroImage: Int, heroName: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Card(modifier = Modifier
            .fillMaxHeight()
            .width(400.dp)
            .padding(40.dp),
            shape = RoundedCornerShape(16.dp)) {
            Box {
                Image(modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = heroImage),
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