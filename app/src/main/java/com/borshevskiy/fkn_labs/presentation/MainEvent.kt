package com.borshevskiy.fkn_labs.presentation

interface MainEvent

class LoadFromApiEvent(): MainEvent

class GetCacheFromDBEvent(): MainEvent