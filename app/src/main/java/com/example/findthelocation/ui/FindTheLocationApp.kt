/*
    David Gajda
    OSU
    CS492
 */

package com.example.findthelocation.ui

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.findthelocation.data.DataSource
import kotlin.math.absoluteValue

@Composable
fun FindTheLocationApp(
    currentLongitudeState: MutableState<Double>,
    currentLatitudeState: MutableState<Double>
) {
    val viewModel: CluesViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val timerState by viewModel.timerState.collectAsState()

    Scaffold(
        content = { innerPadding ->
            val context = LocalContext.current
            when (uiState.currentScreen) {
                1 -> ClueScreen(
                    foundClick = {
                        // Check location here
                        if (ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            //Check location
                            val distance = haversine(
                                userLat = currentLatitudeState.value.absoluteValue,
                                userLong = currentLongitudeState.value.absoluteValue,
                                targetLat = uiState.currentClue.locationLat,
                                targetLong = uiState.currentClue.locationLon
                            )
                            val thresholdDistance = 0.005 // 5 meters

                            if (distance <= thresholdDistance) {
                                // User is within the target location
                                viewModel.stopTimer()
                                viewModel.navigateToNextPage()
                            } else {
                                val alertDialogBuilder = AlertDialog.Builder(context)
                                alertDialogBuilder.setMessage("You're not in the right location! Move to the correct location and try again.")
                                alertDialogBuilder.setPositiveButton("Dismiss") { dialog, _ ->
                                    dialog.dismiss()
                                }
                                val alertDialog = alertDialogBuilder.create()
                                alertDialog.show()
                            }
                        } else {
                            // User doesn't have location services enabled
                            val alertDialogBuilder = AlertDialog.Builder(context)
                            alertDialogBuilder.setMessage("Enable location services to play the game.")
                            alertDialogBuilder.setPositiveButton("Dismiss") { dialog, _ ->
                                dialog.dismiss()
                            }
                            val alertDialog = alertDialogBuilder.create()
                            alertDialog.show()
                        }
                    },
                    backClick = {
                        viewModel.updateCurrentClue(DataSource.defaultClue)
                        viewModel.stopTimer()
                        viewModel.resetTimer()
                        viewModel.navigateToHomePage()
                    },
                    timer = timerState,
                    clue = uiState.currentClue,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = innerPadding,
                )

                2 -> {
                    FoundScreen(
                        nextClick = {
                            val cluesList = DataSource.loadClues()
                            if (cluesList.size == uiState.currentClue.clueID) {
                                // Move to congrats
                                viewModel.navigateToNextPage()
                            } else {
                                // Get the next clue in the list
                                viewModel.startTimerFromCurrentValue()
                                viewModel.updateCurrentClue(cluesList[uiState.currentClue.clueID])
                                viewModel.navigateToPreviousPage()
                            }
                        },
                        timer = timerState,
                        clue = uiState.currentClue,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                3 -> {
                    CongratsScreen(
                        onHomeClick = {
                            viewModel.resetTimer()
                            viewModel.updateCurrentClue(DataSource.defaultClue)
                            viewModel.navigateToHomePage()
                        },
                        timer = timerState,
                        clue = uiState.currentClue,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                else ->
                    StartScreen(
                        nextClick = {
                            viewModel.startTimerFromCurrentValue()
                            viewModel.navigateToNextPage()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
            }
        }
    )
}

fun haversine(
    userLat: Double,
    userLong: Double,
    targetLat: Double,
    targetLong: Double
): Double {
    val earthRadiusKm = 6372.8

    val dLat = Math.toRadians(targetLat - userLat);
    val dLon = Math.toRadians(targetLong - userLong);
    val originLat = Math.toRadians(userLat);
    val destinationLat = Math.toRadians(targetLat);

    val a = Math.pow(Math.sin(dLat / 2), 2.toDouble()) + Math.pow(
        Math.sin(dLon / 2),
        2.toDouble()
    ) * Math.cos(originLat) * Math.cos(destinationLat);
    val c = 2 * Math.asin(Math.sqrt(a));
    return earthRadiusKm * c;
}



