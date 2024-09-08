package com.example.fifteengamecompose

import kotlin.math.abs

val INITIAL_STATE = ByteArray(16) { (it + 1).toByte() }
val TEST_STATE = byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15)

interface FifteenEngine {
    fun transitionState(oldState: ByteArray, cell: Byte): ByteArray
    fun isWin(state: ByteArray): Boolean
    fun getInitialGrid(): ByteArray

    companion object : FifteenEngine {
        private const val EMPTY: Byte = 16
        const val DIM = 4
        private fun row(ix: Int) = ix / DIM
        private fun col(ix: Int) = ix % DIM

        override fun transitionState(oldState: ByteArray, cell: Byte): ByteArray {
            val ixCell = oldState.indexOf(cell)
            val ixEmpty = oldState.indexOf(EMPTY)

            return if (areAdjacent(ixCell, ixEmpty)) {
                withSwapped(oldState, ixCell, ixEmpty)
            }
            else oldState
        }

        private fun withSwapped(arr: ByteArray, ix1: Int, ix2: Int): ByteArray {
            if (ix1 == ix2) return arr
            val res = arr.clone()
            res[ix1] = res[ix2].also { res[ix2] = res[ix1] }
            return res
        }

        private fun areAdjacent(ix1: Int, ix2: Int): Boolean {
            val row1 = row(ix1)
            val col1 = col(ix1)
            val row2 = row(ix2)
            val col2 = col(ix2)
            return (row1 == row2 && abs(col1 - col2) == 1 ||
                    col1 == col2 && abs(row1 - row2) == 1)
        }

        override fun isWin(state: ByteArray): Boolean =
            state.contentEquals(INITIAL_STATE)

        private fun countInversions(state: ByteArray): Int {
            val rowOfEmptyCell = row(state.indexOf(EMPTY))
            var inversions = rowOfEmptyCell
            repeat(state.size) {
                if (state[it] != EMPTY)
                    for (j in it + 1..<state.size) {
                        if (state[j] != EMPTY && state[it] > state[j]) inversions++
                    }
            }
            return inversions
        }

        private fun isFeasibleSolution(state: ByteArray): Boolean = countInversions(state) % 2 == 1

        override fun getInitialGrid(): ByteArray {
            val res = INITIAL_STATE.clone()
            do {
                res.shuffle()
            } while (!isFeasibleSolution(res))
            return res
        }
    }
}
