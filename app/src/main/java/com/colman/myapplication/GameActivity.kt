package com.colman.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout

class GameActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var board: Array<Array<Button>>
    private var playerX = true // true for player X, false for player O
    private var turnCount = 0
    private var gameEnded = false
    private lateinit var statusTextView: TextView
    private lateinit var playAgainButton: Button
    private lateinit var gameResetHandler: GameResetHandler

    /**
     * Called when the activity is first created.
     * Initializes the game board, sets click listeners, and displays the initial status.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        statusTextView = findViewById(R.id.statusTextView)
        playAgainButton = findViewById(R.id.playAgainButton)

        val gridLayout = findViewById<GridLayout>(R.id.gameBoard)
        board = Array(3) { r ->
            Array(3) { c ->
                val button = gridLayout.findViewWithTag<Button>("${r}${c}")
                button.setOnClickListener(this)
                button
            }
        }

        // Initialize the reset handler
        gameResetHandler = GameResetHandler(this, board, playAgainButton, statusTextView)

        updateStatus()
    }

    /**
     * Handles click events on the game board buttons.
     * Places an 'X' or 'O', checks for a win or draw, and updates the game status.
     */
    override fun onClick(v: View?) {
        if (v !is Button || gameEnded) {
            return
        }

        if (v.text.toString() != "") {
            return
        }

        if (playerX) {
            v.text = "X"
        } else {
            v.text = "O"
        }

        turnCount++

        if (checkForWin()) {
            gameEnded = true
            updateStatus()
            gameResetHandler.showPlayAgainButton()
        } else if (turnCount == 9) {
            gameEnded = true
            updateStatus()
            gameResetHandler.showPlayAgainButton()
        } else {
            playerX = !playerX
            updateStatus()
        }
    }

    /**
     * Updates the on-screen status TextView to reflect the current state of the game
     * (e.g., whose turn it is, if a player has won, or if it's a draw).
     */
    private fun updateStatus() {
        val message = if (gameEnded) {
            if (checkForWin()) {
                getString(if (playerX) R.string.player_x_wins else R.string.player_o_wins)
            } else {
                getString(R.string.draw)
            }
        } else {
            getString(if (playerX) R.string.player_x_turn else R.string.player_o_turn)
        }
        statusTextView.text = message
    }

    /**
     * Checks the board for a winning condition (three in a row, column, or diagonal).
     * @return True if a player has won, false otherwise.
     */
    private fun checkForWin(): Boolean {
        val boardValues = Array(3) { r ->
            Array(3) { c ->
                board[r][c].text.toString()
            }
        }

        // Check rows
        for (i in 0..2) {
            if (boardValues[i][0] == boardValues[i][1] && boardValues[i][0] == boardValues[i][2] && boardValues[i][0] != "") {
                return true
            }
        }

        // Check columns
        for (i in 0..2) {
            if (boardValues[0][i] == boardValues[1][i] && boardValues[0][i] == boardValues[2][i] && boardValues[0][i] != "") {
                return true
            }
        }

        // Check diagonals
        if (boardValues[0][0] == boardValues[1][1] && boardValues[0][0] == boardValues[2][2] && boardValues[0][0] != "") {
            return true
        }
        if (boardValues[0][2] == boardValues[1][1] && boardValues[0][2] == boardValues[2][0] && boardValues[0][2] != "") {
            return true
        }

        return false
    }

    /**
     * Reset game state variables (called by GameResetHandler)
     */
    fun resetGameState() {
        playerX = true
        turnCount = 0
        gameEnded = false
    }
}
