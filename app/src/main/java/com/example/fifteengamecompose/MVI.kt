package com.example.fifteengamecompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class FifteenState(
    val gameBoard: List<Int>,
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
                    val newGameBoard = engine.transitionState(gameBoard, intent.number)
                    copy(
                        gameBoard = newGameBoard,
                        movesCounter = if (newGameBoard == gameBoard) movesCounter else movesCounter + 1,
                        isVictory = engine.isWin(newGameBoard)
                    )
                }

            }
            is FifteenIntent.Reset -> {
                state.copy(gameBoard = engine.getInitialGameBoard(), isVictory = false, movesCounter = 0)
            }
        }
    }
}