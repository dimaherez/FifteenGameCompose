package com.example.fifteengamecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.fifteengamecompose.views.ResponsiveLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FifteenGridStateHolder()
        }
    }
}

@Composable
fun FifteenGridStateHolder(engine: FifteenEngine = FifteenEngine) {
    var state by rememberSaveable { mutableStateOf(engine.getInitialState()) }
    var movesCounter by rememberSaveable { mutableIntStateOf(0) }

    val onCellClick: (Byte) -> Unit = { number ->
        val newState = engine.transitionState(state, number)
        if (!newState.contentEquals(state)) {
            state = newState
            movesCounter += 1
        }
    }

    val onReset: () -> Unit = {
        state = engine.getInitialState()
        movesCounter = 0
    }

    FifteenGrid(
        state = state,
        isVictory = engine.isWin(state),
        movesCounter = movesCounter,
        formatText = FifteenEngine::formatCell,
        onCellClick = onCellClick,
        onReset = onReset
    )
}

@Composable
fun FifteenGrid(
    state: ByteArray = byteArrayOf(),
    isVictory: Boolean = false,
    movesCounter: Int = 0,
    formatText: (Byte) -> String = { it.toString() },
    onCellClick: (Byte) -> Unit,
    onReset: () -> Unit,
) {
    Box(
        modifier = with(Modifier) {
            fillMaxSize()
            paint(
                painterResource(id = R.drawable.bg),
                contentScale = ContentScale.FillBounds
            )
        }
    ) {
        ResponsiveLayout(state, isVictory, movesCounter, formatText, onCellClick, onReset)
    }
}


@Preview(showSystemUi = true)
@Composable
fun GridPreview() {
    FifteenGridStateHolder(
        engine = object : FifteenEngine by FifteenEngine.Companion {
            override fun getInitialState(): ByteArray = byteArrayOf(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 16, 15
            )
        }
    )
}

