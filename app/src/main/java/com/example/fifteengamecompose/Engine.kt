package com.example.fifteengamecompose

import kotlin.math.abs

val INITIAL_STATE = List(16) { it + 1 }
// val TEST_STATE = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15)


interface FifteenEngine {
    fun transitionState(oldGameBoard: List<Int>, cell: Int): List<Int>
    fun isWin(gameBoard: List<Int>): Boolean
    fun getInitialGameBoard(): List<Int>

    companion object : FifteenEngine {
        private const val EMPTY: Int = 16
        const val DIM = 4
        private fun row(ix: Int) = ix / DIM
        private fun col(ix: Int) = ix % DIM

        override fun transitionState(oldGameBoard: List<Int>, cell: Int): List<Int> {
            val ixCell = oldGameBoard.indexOf(cell)
            val ixEmpty = oldGameBoard.indexOf(EMPTY)

            return if (areAdjacent(ixCell, ixEmpty)) {
                withSwapped(oldGameBoard, ixCell, ixEmpty)
            } else oldGameBoard
        }

        private fun withSwapped(gameBoard: List<Int>, ix1: Int, ix2: Int): List<Int> {
            if (ix1 == ix2) return gameBoard
            return gameBoard.toMutableList()
                .apply { this[ix1] = this[ix2].also { this[ix2] = this[ix1] } }
        }

        private fun areAdjacent(ix1: Int, ix2: Int): Boolean {
            val row1 = row(ix1)
            val col1 = col(ix1)
            val row2 = row(ix2)
            val col2 = col(ix2)
            return (row1 == row2 && abs(col1 - col2) == 1 ||
                    col1 == col2 && abs(row1 - row2) == 1)
        }

        override fun isWin(gameBoard: List<Int>): Boolean =
            gameBoard == INITIAL_STATE

        private fun countInversions(gameBoard: List<Int>): Int {
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

        private fun isFeasibleSolution(gameBoard: List<Int>): Boolean =
            countInversions(gameBoard) % 2 == 1

        override fun getInitialGameBoard(): List<Int> {
            var res = INITIAL_STATE.toList()
            do {
                res = res.shuffled()
            } while (!isFeasibleSolution(res))
            return res
        }
    }
}
