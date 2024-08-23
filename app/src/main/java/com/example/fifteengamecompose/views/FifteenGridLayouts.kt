package com.example.fifteengamecompose.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.fifteengamecompose.FifteenEngine.Companion.DIM

@Composable
fun ResponsiveLayout(
    state: ByteArray,
    isVictory: Boolean,
    movesCounter: Int,
    formatText: (Byte) -> String,
    onCellClick: (Byte) -> Unit,
    onReset: () -> Unit
) {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LandscapeFifteenGrid(state, movesCounter, isVictory, formatText, onCellClick, onReset)
        }

        else -> {
            PortraitFifteenGrid(state, movesCounter,isVictory, formatText, onCellClick, onReset)
        }
    }
}

@Composable
fun PortraitFifteenGrid(
    state: ByteArray,
    movesCounter: Int,
    isVictory: Boolean,
    formatText: (Byte) -> String,
    onCellClick: (Byte) -> Unit,
    onReset: () -> Unit
) {
    fun ix(iRow: Int, iCol: Int) = iRow * DIM + iCol

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (iRow in 0 until DIM) {
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    for (iCol in 0 until DIM) {
                        val id = ix(iRow, iCol)
                        Cell(formatText(state[id]), onClick = { onCellClick(state[id]) })
                    }
                }
            }
        }

        if (!isVictory) {
            ControlPanelView(movesCounter = movesCounter, onReset)
        } else {
            Spacer(modifier = Modifier.size(250.dp))
        }

    }

    if (isVictory) {
        PortraitVictoryLayout(movesCounter = movesCounter, onReset)
    }
}

@Composable
fun LandscapeFifteenGrid(
    state: ByteArray,
    movesCounter: Int,
    isVictory: Boolean,
    formatText: (Byte) -> String,
    onCellClick: (Byte) -> Unit,
    onReset: () -> Unit
) {
    fun ix(iRow: Int, iCol: Int) = iRow * DIM + iCol

    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (iRow in 0 until DIM) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (iCol in 0 until DIM) {
                        val id = ix(iRow, iCol)
                        Cell(formatText(state[id]), onClick = { onCellClick(state[id]) })
                    }
                }
            }
        }

        if (!isVictory) {
            ControlPanelView(movesCounter = movesCounter, onReset)
        } else {
            Spacer(modifier = Modifier.size(250.dp))
        }
    }

    if (isVictory) {
        LandscapeVictoryLayout(movesCounter = movesCounter, onReset)
    }
}