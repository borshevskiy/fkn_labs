package com.borshevskiy.fkn_labs

import androidx.compose.ui.graphics.Color
import com.borshevskiy.fkn_labs.ui.theme.*

enum class Hero(val heroImage: Int, val heroName: String, val backGround: Color) {
    DEADPOOL(R.drawable.deadpool, "Deadpool", Deadpool),
    IRONMAN(R.drawable.ironman, "Iron Man", Ironman),
    CAPTAINAMERICA(R.drawable.captainamerica, "Captain America", CaptainAmerica),
    SPIDERMAN(R.drawable.spiderman, "Spiderman", SpiderMan),
    DOCTORSTRANGE(R.drawable.doctorstrange, "Doctor Strange", DoctorStrange),
    THOR(R.drawable.thor, "Thor", Thor),
    THANOS(R.drawable.thanos, "Thanos", Thanos)
}