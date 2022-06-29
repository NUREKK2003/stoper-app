package com.lexmasterteam.stoperappv2.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.lexmasterteam.stoperappv2.presentation.states.StoperState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class StoperMainViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow(StoperState())
    val state: MutableStateFlow<StoperState> = _state
    val emitTime = flow{
        while(1==1){
            emit(state.value.time)
            delay(1000L)
        }

    }

    suspend fun startStoper(){
        _state.value.isPaused = false
        while(!_state.value.isPaused){
            _state.value.seconds+=1
            setTime(_state.value.seconds)
            delay(1000L)
        }

    }
    suspend fun stoporStartStoper(){
        _state.value.isPaused = !_state.value.isPaused
        if(!_state.value.isPaused){
            startStoper()
        }
    }
    fun resetStoper(){
        _state.value.isPaused=true
        _state.value.isAboutToReset=true
        if(_state.value.isAboutToReset){
            _state.value.seconds=0
            _state.value.time="00:00:00"
        }
    }
    fun setTime(seconds: Int){
        var sec = seconds
        var hours:Int = sec / 3600
        sec-= hours*3600
        var minutes:Int = sec / 60
        sec -= minutes * 60
        var time = "$hours:$minutes:$sec"
        _state.value.time = time
    }
}