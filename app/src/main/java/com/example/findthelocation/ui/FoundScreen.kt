/*
    David Gajda
    OSU
    CS492
 */

package com.example.findthelocation.ui

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
fun FoundScreen(
    nextClick: () -> Unit,
    timer: Long,
    clue: Clue,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = clue.locationNameID),
                    style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = clue.locationInfoID),
                style = TextStyle(fontSize = 25.sp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 300.dp, bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = (nextClick),
                modifier = Modifier
                    .padding(bottom = 130.dp, top = 16.dp)
                    .size(width = 300.dp, height = 120.dp)
                    .padding(horizontal = 16.dp, vertical = 2.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.continueString),
                    fontSize = 45.sp, fontWeight = FontWeight.Bold
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 570.dp, bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "$timer " + stringResource(id = R.string.s),
                style = TextStyle(fontSize = 70.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}