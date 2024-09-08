package com.example.fifteengamecompose.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.fifteengamecompose.FifteenEngine.Companion.DIM
import com.example.fifteengamecompose.FifteenIntent
import com.example.fifteengamecompose.FifteenState

@Composable
fun ResponsiveLayout(
    state: FifteenState,
    onAction: (FifteenIntent) -> Unit
) {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LandscapeFifteenGrid(state, onAction)
        }
        else -> {
            PortraitFifteenGrid(state, onAction)
        }
    }
}

@Composable
fun PortraitFifteenGrid(
    state: FifteenState,
    onAction: (FifteenIntent) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Grid(state = state, onAction)

        if (!state.isVictory) {
            ControlPanelView(movesCounter = state.movesCounter) { onAction(FifteenIntent.Reset) }
        } else {
            Spacer(modifier = Modifier.size(250.dp))
        }

    }

    if (state.isVictory) {
        PortraitVictoryLayout(movesCounter = state.movesCounter) { onAction(FifteenIntent.Reset) }
    }
}

@Composable
fun LandscapeFifteenGrid(
    state: FifteenState,
    onAction: (FifteenIntent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Grid(state = state, onAction)

        if (!state.isVictory) {
            ControlPanelView(movesCounter = state.movesCounter) { onAction(FifteenIntent.Reset) }
        } else {
            Spacer(modifier = Modifier.size(250.dp))
        }
    }

    if (state.isVictory) {
        LandscapeVictoryLayout(movesCounter = state.movesCounter) { onAction(FifteenIntent.Reset) }
    }
}


@Composable
fun Grid(
    state: FifteenState,
    onAction: (FifteenIntent) -> Unit
) {
    fun ix(iRow: Int, iCol: Int) = iRow * DIM + iCol

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
                    Cell(state.formatText(state.grid[id]), onClick = {
                        onAction(
                            FifteenIntent.CellClick(
                                state.grid[id]
                            )
                        )
                    }
                    )
                }
            }
        }
    }
}