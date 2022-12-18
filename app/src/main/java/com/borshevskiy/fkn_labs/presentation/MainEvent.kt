package com.borshevskiy.fkn_labs.presentation

sealed interface MainEvent

object LoadHeroListFromApiEvent : MainEvent

class LoadHeroInfoFromApiEvent(val heroId: Int) : MainEvent

object GetCacheFromDBEvent : MainEvent