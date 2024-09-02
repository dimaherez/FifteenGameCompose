package com.example.fifteengamecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import com.example.fifteengamecompose.views.ResponsiveLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: FifteenViewModel by viewModels()
            FifteenGame(state = viewModel.state, onAction = viewModel::processIntent)
        }
    }
}


@Composable
fun FifteenGame(
    state: FifteenState,
    onAction: (FifteenIntent) -> Unit
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
        ResponsiveLayout(state, onAction)
    }
}

data class FifteenState(
    val grid: ByteArray = byteArrayOf(),
    val isVictory: Boolean = false,
    val movesCounter: Int = 0,
    val formatText: (Byte) -> String = { if (it.toInt() == 16) " " else it.toString() },
)

sealed interface FifteenIntent {
    data class CellClick(val number: Byte) : FifteenIntent
    data object Reset : FifteenIntent
}

class FifteenViewModel(private val engine: FifteenEngine = FifteenEngine) : ViewModel() {
    var state by mutableStateOf(FifteenState())

    init {
        state = state.copy(grid = engine.getInitialGrid())
    }

    fun processIntent(intent: FifteenIntent) {
        state = when (intent) {
            is FifteenIntent.CellClick -> {
                with(state) {
                    val newGrid = engine.transitionState(grid, intent.number)
                    copy(
                        grid = newGrid,
                        movesCounter = if (newGrid.contentEquals(grid)) movesCounter else movesCounter + 1,
                        isVictory = engine.isWin(newGrid)
                    )
                }

            }

            is FifteenIntent.Reset -> {
                state.copy(grid = engine.getInitialGrid(), isVictory = false, movesCounter = 0)
            }
        }
    }
}

