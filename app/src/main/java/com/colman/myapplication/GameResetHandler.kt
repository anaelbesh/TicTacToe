package com.colman.myapplication

import android.view.View
import android.widget.Button
import android.widget.TextView

/**
 * GameResetHandler manages the "Play Again" functionality.
 * This class is responsible for:
 * - Showing/hiding the Play Again button
 * - Resetting the game board
 * - Resetting the game state
 * - Updating the UI after reset
 */
class GameResetHandler(
    private val gameActivity: GameActivity,
    private val board: Array<Array<Button>>,
    private val playAgainButton: Button,
    private val statusTextView: TextView
) {

    init {
        // Set up the Play Again button click listener
        playAgainButton.setOnClickListener {
            resetGame()
        }
    }

    /**
     * Shows the Play Again button when the game ends
     */
    fun showPlayAgainButton() {
        playAgainButton.visibility = View.VISIBLE
    }

    /**
     * Hides the Play Again button
     */
    private fun hidePlayAgainButton() {
        playAgainButton.visibility = View.GONE
    }

    /**
     * Resets the entire game:
     * - Clears all buttons on the board
     * - Resets game state in GameActivity
     * - Hides the Play Again button
     * - Updates the status text
     */
    private fun resetGame() {
        // Clear all board buttons
        clearBoard()

        // Reset game state variables in GameActivity
        gameActivity.resetGameState()

        // Hide the Play Again button
        hidePlayAgainButton()

        // Update status text to show Player X's turn
        statusTextView.text = gameActivity.getString(R.string.player_x_turn)
    }

    /**
     * Clears all text from the game board buttons
     */
    private fun clearBoard() {
        for (row in board) {
            for (button in row) {
                button.text = ""
            }
        }
    }
}

