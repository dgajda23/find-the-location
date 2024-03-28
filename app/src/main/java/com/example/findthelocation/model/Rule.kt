package com.example.findthelocation.model

import androidx.annotation.StringRes

data class Rule(
    val ruleID: Int,
    @StringRes val ruleTextID: Int
)