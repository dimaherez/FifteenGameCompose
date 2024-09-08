package com.example.fifteengamecompose

import kotlin.math.abs

val INITIAL_STATE = MutableList(16) { it + 1 }
val TEST_STATE = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15)

interface FifteenEngine {
    fun transitionState(oldGrid: MutableList<Int>, cell: Int)
    fun isWin(grid: MutableList<Int>): Boolean
    fun getInitialGrid(): MutableList<Int>

    companion object : FifteenEngine {
        private const val EMPTY: Int = 16
        const val DIM = 4
        private fun row(ix: Int) = ix / DIM
        private fun col(ix: Int) = ix % DIM

        override fun transitionState(oldGrid: MutableList<Int>, cell: Int) {
            val ixCell = oldGrid.indexOf(cell)
            val ixEmpty = oldGrid.indexOf(EMPTY)

            if (areAdjacent(ixCell, ixEmpty)) {
                swapAdjacentCells(oldGrid, ixCell, ixEmpty)
            }
        }

        private fun swapAdjacentCells(grid: MutableList<Int>, ix1: Int, ix2: Int) {
            if (ix1 != ix2) grid[ix1] = grid[ix2].also { grid[ix2] = grid[ix1] }
        }

        private fun areAdjacent(ix1: Int, ix2: Int): Boolean {
            val row1 = row(ix1)
            val col1 = col(ix1)
            val row2 = row(ix2)
            val col2 = col(ix2)
            return (row1 == row2 && abs(col1 - col2) == 1 ||
                    col1 == col2 && abs(row1 - row2) == 1)
        }

        override fun isWin(grid: MutableList<Int>): Boolean =
            grid == INITIAL_STATE

        private fun countInversions(grid: MutableList<Int>): Int {
            val rowOfEmptyCell = row(grid.indexOf(EMPTY))
            var inversions = rowOfEmptyCell
            repeat(grid.size) {
                if (grid[it] != EMPTY)
                    for (j in it + 1..<grid.size) {
                        if (grid[j] != EMPTY && grid[it] > grid[j]) inversions++
                    }
            }
            return inversions
        }

        private fun isFeasibleSolution(grid: MutableList<Int>): Boolean = countInversions(grid) % 2 == 1

        override fun getInitialGrid(): MutableList<Int> {
            val res = INITIAL_STATE.toMutableList()
            do {
                res.shuffle()
            } while (!isFeasibleSolution(res))
            return res
        }
    }
}
