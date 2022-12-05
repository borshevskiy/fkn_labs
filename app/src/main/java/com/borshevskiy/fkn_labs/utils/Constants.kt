package com.borshevskiy.fkn_labs.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object {
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "30bf94833fc9a2b0eb1f2dfe3f74ba4a"
        const val PRIVATE_KEY = "e638599ff3e742aa4be041505e7c583619f4e115"
        const val limit = "100"
        fun hash():String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }

    object Screens {
        const val MAIN_SCREEN = "main_screen"
        const val DETAIL_SCREEN = "detail_screen"
    }

    object Key {
        const val HERO_ID = "heroId"
    }
}