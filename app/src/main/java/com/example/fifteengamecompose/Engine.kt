package com.example.fifteengamecompose

import kotlin.math.abs

val INITIAL_STATE = MutableList(16) { it + 1 }
// val TEST_STATE = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15)

typealias GameBoard = MutableList<Int>

interface FifteenEngine {
    fun transitionState(oldGameBoard: GameBoard, cell: Int)
    fun isWin(gameBoard: GameBoard): Boolean
    fun getInitialGameBoard(): GameBoard

    companion object : FifteenEngine {
        private const val EMPTY: Int = 16
        const val DIM = 4
        private fun row(ix: Int) = ix / DIM
        private fun col(ix: Int) = ix % DIM

        override fun transitionState(oldGameBoard: GameBoard, cell: Int) {
            val ixCell = oldGameBoard.indexOf(cell)
            val ixEmpty = oldGameBoard.indexOf(EMPTY)

            if (areAdjacent(ixCell, ixEmpty)) {
                swapAdjacentCells(oldGameBoard, ixCell, ixEmpty)
            }
        }

        private fun swapAdjacentCells(gameBoard: GameBoard, ix1: Int, ix2: Int) {
            if (ix1 != ix2) gameBoard[ix1] = gameBoard[ix2].also { gameBoard[ix2] = gameBoard[ix1] }
        }

        private fun areAdjacent(ix1: Int, ix2: Int): Boolean {
            val row1 = row(ix1)
            val col1 = col(ix1)
            val row2 = row(ix2)
            val col2 = col(ix2)
            return (row1 == row2 && abs(col1 - col2) == 1 ||
                    col1 == col2 && abs(row1 - row2) == 1)
        }

        override fun isWin(gameBoard: GameBoard): Boolean =
            gameBoard == INITIAL_STATE

        private fun countInversions(gameBoard: GameBoard): Int {
            val rowOfEmptyCell = row(gameBoard.indexOf(EMPTY))
            var inversions = rowOfEmptyCell
            repeat(gameBoard.size) {
                if (gameBoard[it] != EMPTY)
                    for (j in it + 1..<gameBoard.size) {
                        if (gameBoard[j] != EMPTY && gameBoard[it] > gameBoard[j]) inversions++
                    }
            }
            return inversions
        }

        private fun isFeasibleSolution(gameBoard: GameBoard): Boolean = countInversions(gameBoard) % 2 == 1

        override fun getInitialGameBoard(): GameBoard {
            val res = INITIAL_STATE.toMutableList()
            do {
                res.shuffle()
            } while (!isFeasibleSolution(res))
            return res
        }
    }
}
