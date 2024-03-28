/*
    David Gajda
    OSU
    CS492
 */

package com.example.findthelocation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findthelocation.data.AppUiState
import com.example.findthelocation.data.DataSource
import com.example.findthelocation.model.Clue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CluesViewModel : ViewModel() {

    private var isTimerRunning = false

    private val _timerState = MutableStateFlow(0L)
    val timerState: StateFlow<Long> = _timerState

    private val _uiState = MutableStateFlow(
        AppUiState(
            currentClue = DataSource.defaultClue,
            currentScreen = 0
        )
    )
    val uiState: StateFlow<AppUiState> = _uiState

    fun updateCurrentClue(selectedClue: Clue) {
        _uiState.update {
            it.copy(currentClue = selectedClue)
        }
    }

    fun navigateToNextPage() {
        _uiState.update {
            it.copy(currentScreen = it.currentScreen + 1)
        }
    }

    fun navigateToPreviousPage() {
        _uiState.update {
            it.copy(currentScreen = it.currentScreen - 1)
        }
    }

    fun navigateToHomePage() {
        _uiState.update {
            it.copy(currentScreen = 0)
        }
    }

    fun startTimerFromCurrentValue() {
        if (!isTimerRunning) {
            isTimerRunning = true
            viewModelScope.launch {
                flow {
                    var currentTime = _timerState.value
                    while (isTimerRunning) {
                        emit(currentTime)
                        delay(1000)
                        currentTime += 1
                    }
                }
                    .onStart {}
                    .collect {
                        _timerState.value = it
                    }
            }
        }
    }

    fun stopTimer() {
        isTimerRunning = false
    }

    fun resetTimer() {
        _timerState.value = 0L
    }
}