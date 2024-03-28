/*
    David Gajda
    OSU
    CS492
 */

package com.example.findthelocation.data

import com.example.findthelocation.model.Clue

data class AppUiState(
    val currentClue: Clue = DataSource.defaultClue,
    val currentScreen: Int = 0
)