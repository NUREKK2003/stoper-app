package com.lexmasterteam.stoperappv2.presentation.states

data class StoperState(
    var isPaused: Boolean = true,
    var seconds: Int=0,
    var time: String="00:00:00",
    var isAboutToReset: Boolean = false,
)