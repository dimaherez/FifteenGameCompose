package com.example.fifteengamecompose.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

    Column(
        modifier = Modifier.fillMaxSize(),
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

        ControlPanelView(movesCounter = movesCounter, onReset)
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

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (iCol in 0 until DIM) {
            Column {
                for (iRow in 0 until DIM) {
                    val id = ix(iRow, iCol)
                    Cell(formatText(state[id]), onClick = { onCellClick(state[id]) })
                }
            }
        }

        ControlPanelView(movesCounter = movesCounter, onReset)
    }

    if (isVictory) {
        LandscapeVictoryLayout(movesCounter = movesCounter, onReset)
    }
}