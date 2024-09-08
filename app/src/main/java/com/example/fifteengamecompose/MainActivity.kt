package com.example.fifteengamecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fifteengamecompose.views.ResponsiveLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FifteenGame()
        }
    }
}


@Composable
fun FifteenGame(
    fifteenViewModel: FifteenViewModel = viewModel<FifteenViewModel>()
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
        ResponsiveLayout(state = fifteenViewModel.state, onAction = fifteenViewModel::processIntent)
    }
}

data class FifteenState(
    val grid: MutableList<Int>,
    val isVictory: Boolean = false,
    val movesCounter: Int = 0
)

sealed interface FifteenIntent {
    data class CellClick(val number: Int) : FifteenIntent
    data object Reset : FifteenIntent
}

class FifteenViewModel(private val engine: FifteenEngine = FifteenEngine) : ViewModel() {
    var state by mutableStateOf(FifteenState(grid = engine.getInitialGrid()))

    fun processIntent(intent: FifteenIntent) {
        state = when (intent) {
            is FifteenIntent.CellClick -> {
                with(state) {
                    val oldGrid = grid.toMutableList()
                    engine.transitionState(grid, intent.number)
                    copy(
                        movesCounter = if (oldGrid == grid) movesCounter else movesCounter + 1,
                        isVictory = engine.isWin(grid)
                    )
                }

            }

            is FifteenIntent.Reset -> {
                state.copy(grid = engine.getInitialGrid(), isVictory = false, movesCounter = 0)
            }
        }
    }
}

