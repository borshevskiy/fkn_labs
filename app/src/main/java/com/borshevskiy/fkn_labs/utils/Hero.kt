package com.borshevskiy.fkn_labs.utils

import androidx.compose.ui.graphics.Color
import com.borshevskiy.fkn_labs.R
import com.borshevskiy.fkn_labs.ui.theme.*

enum class Hero(val heroImage: Int, val heroName: String, val backGround: Color, val heroImageLink: String, val heroDesc: String) {
    DEADPOOL(R.drawable.deadpool, "Deadpool", Deadpool, "https://shutniks.com/wp-content/uploads/2019/12/kartinki_Daedpula_dlya_srisovki_7_01112329.jpg", "Please don't make the super suit green...or animated!"),
    IRONMAN(R.drawable.ironman, "Iron Man", Ironman, "https://crops.giga.de/68/9e/b5/d0b6fda9a24ddb9ab7edfed636_YyAzMDMyeDE3MDYrMjMrNTYCcmUgODQwIDQ3MgM2ZWQ2YTJkYWVlMw==.jpg", "I AM IRON MAN"),
    CAPTAINAMERICA(R.drawable.captainamerica, "Captain America", CaptainAmerica, "https://cdn.britannica.com/30/182830-050-96F2ED76/Chris-Evans-title-character-Joe-Johnston-Captain.jpg", "I don't like bullies; I don't care where they're from."),
    SPIDERMAN(R.drawable.spiderman, "Spiderman", SpiderMan, "https://preview.redd.it/4bb4w3s3vno81.jpg?width=640&crop=smart&auto=webp&s=c266554a81ba868fb949ddbcd8b1997d8aabc77a", "I've just got to do this on my own."),
    DOCTORSTRANGE(R.drawable.doctorstrange, "Doctor Strange", DoctorStrange, "https://duet-cdn.vox-cdn.com/thumbor/0x53:1688x1201/640x640/filters:focal(844x601:845x602):format(webp)/cdn.vox-cdn.com/uploads/chorus_asset/file/23434598/DrStrange2_Payoff_1_Sht_v6_Lg.jpeg","No man can win every battle, but no man should fall without a struggle."),
    THOR(R.drawable.thor, "Thor", Thor, "https://static.wikia.nocookie.net/marvelcinematicuniverse/images/2/22/Thor_in_LoveAndThunder_Poster.jpg/revision/latest/scale-to-width-down/1000?cb=20220615195810", "What more could I lose?"),
    THANOS(R.drawable.thanos, "Thanos", Thanos, "https://cdn3.whatculture.com/images/2020/08/7623cebe8184d315-600x338.jpg", "No man can win every battle, but no man should fall without a struggle.")
}