package com.example.fifteengamecompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class FifteenState(
    val gameBoard: GameBoard,
    val isVictory: Boolean = false,
    val movesCounter: Int = 0
)

sealed interface FifteenIntent {
    data class CellClick(val number: Int) : FifteenIntent
    data object Reset : FifteenIntent
}

class FifteenViewModel(private val engine: FifteenEngine = FifteenEngine) : ViewModel() {
    var state by mutableStateOf(FifteenState(gameBoard = engine.getInitialGameBoard()))

    fun processIntent(intent: FifteenIntent) {
        state = when (intent) {
            is FifteenIntent.CellClick -> {
                with(state) {
                    val oldGameBoard = gameBoard.toMutableList()
                    engine.transitionState(gameBoard, intent.number)
                    copy(
                        movesCounter = if (oldGameBoard == gameBoard) movesCounter else movesCounter + 1,
                        isVictory = engine.isWin(gameBoard)
                    )
                }

            }
            is FifteenIntent.Reset -> {
                state.copy(gameBoard = engine.getInitialGameBoard(), isVictory = false, movesCounter = 0)
            }
        }
    }
}