package com.borshevskiy.fkn_labs.presentation

sealed interface MainEvent

class LoadHeroListFromApiEvent : MainEvent

class LoadHeroInfoFromApiEvent(val heroId: Int) : MainEvent

class GetCacheFromDBEvent : MainEvent