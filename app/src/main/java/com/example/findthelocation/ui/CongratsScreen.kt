/*
    David Gajda
    OSU
    CS492
 */

package com.example.findthelocation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.findthelocation.model.Clue

@Composable
fun CongratsScreen(
    onHomeClick: () -> Unit,
    timer: Long,
    clue: Clue,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.you_made),
                style = TextStyle(fontSize = 70.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = stringResource(id = R.string.it),
                style = TextStyle(fontSize = 70.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = stringResource(id = R.string.it_took),
                style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                text = "$timer " + stringResource(id = R.string.s),
                style = TextStyle(fontSize = 70.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(id = clue.locationInfoID),
                style = TextStyle(fontSize = 22.sp)
            )
            Button(
                onClick = (onHomeClick),
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 120.dp)
                    .size(width = 300.dp, height = 120.dp)
                    .padding(horizontal = 16.dp, vertical = 2.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.home),
                    fontSize = 45.sp, fontWeight = FontWeight.Bold
                )
            }
        }
    }
}