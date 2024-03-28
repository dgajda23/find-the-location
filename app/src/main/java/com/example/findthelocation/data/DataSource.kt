/*
    David Gajda
    OSU
    CS492
 */

package com.example.findthelocation.data

import com.example.findthelocation.R
import com.example.findthelocation.model.Clue
import com.example.findthelocation.model.Rule

object DataSource {
    val defaultClue = loadClues()[0]

    fun loadClues(): List<Clue> {
        return listOf(
            Clue(
                clueID = 1,
                clueTextID = R.string.clue1,
                hintTextID = R.string.hint1,
                locationNameID = R.string.location1_name,
                locationInfoID = R.string.info_location1,
                locationLat = 50.2832,
                locationLon = 21.4099,
            ),
            Clue(
                clueID = 2,
                clueTextID = R.string.clue2,
                hintTextID = R.string.hint2,
                locationNameID = R.string.location2_name,
                locationInfoID = R.string.info_location2,
                locationLat = 50.2842,
                locationLon = 21.4109
            )
        )
    }

    fun loadRules(): List<Rule> {
        return listOf(
            Rule(
                ruleID = 1,
                ruleTextID = R.string.rule1
            ),
            Rule(
                ruleID = 2,
                ruleTextID = R.string.rule2
            ),
            Rule(
                ruleID = 3,
                ruleTextID = R.string.rule3
            ),
            Rule(
                ruleID = 4,
                ruleTextID = R.string.rule4
            )
        )
    }
}