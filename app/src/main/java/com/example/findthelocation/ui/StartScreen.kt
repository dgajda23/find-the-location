/*
    David Gajda
    OSU
    CS492
 */

package com.example.findthelocation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findthelocation.R
import com.example.findthelocation.data.DataSource

@Composable
fun StartScreen(
    nextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
            )

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                RulesList(
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }

            StartButton(
                nextClick = nextClick,
                Modifier
                    .padding(bottom = 130.dp, top = 16.dp)
                    .size(width = 250.dp, height = 80.dp)
                    .padding(horizontal = 16.dp, vertical = 2.dp)
            )
        }
    }
}

@Composable
fun RulesList(
    modifier: Modifier = Modifier
) {
    val rules = DataSource.loadRules()
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(rules) { _, rule ->
            Text(
                text = stringResource(id = rule.ruleTextID),
                modifier = Modifier.padding(bottom = 8.dp),
                style = TextStyle(fontSize = 25.sp)
            )
        }
    }
}

@Composable
fun StartButton(
    nextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = (nextClick),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.start),
            style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold)
        )
    }
}