/*
    David Gajda
    OSU
    CS492
 */

package com.example.findthelocation.model

import androidx.annotation.StringRes

data class Clue(
    val clueID: Int,
    @StringRes val clueTextID: Int,
    @StringRes val hintTextID: Int,
    @StringRes val locationNameID: Int,
    @StringRes val locationInfoID: Int,
    val locationLat: Double,
    val locationLon: Double
)