# Tic Tac Toe Android Game

A classic Tic Tac Toe game built for Android using Kotlin and Material Design principles. This project demonstrates modern Android development practices, clean architecture, and an intuitive user interface.

---
## authors: Ofir Shviro and Anael Ben Shabat

## 📱 Overview

This Android application implements the traditional Tic Tac Toe game (also known as Noughts and Crosses) where two players take turns marking spaces in a 3×3 grid. The player who succeeds in placing three of their marks in a horizontal, vertical, or diagonal row wins the game.

### Features

- ✨ **Clean Material Design UI** with a pink color scheme
- 🎮 **Two-player local gameplay** (Player X vs Player O)
- 🔄 **Play Again functionality** to restart the game without leaving the screen
- 📊 **Real-time status updates** showing current player turn and game results
- ♿ **Accessibility support** with content descriptions for screen readers
- 🎨 **Modern UI components** using Material Button with rounded corners and stroke styling

---

## 🏗️ Project Structure

### Package Structure

```
com.colman.myapplication/
├── MainActivity.kt          # Entry point - displays start screen
├── GameActivity.kt          # Main game logic and board management
└── GameResetHandler.kt      # Handles game reset functionality
```

### Resource Structure

```
res/
├── layout/
│   ├── activity_main.xml    # Start screen layout
│   └── game_activity.xml    # Game board layout
├── values/
│   ├── strings.xml          # String resources for UI text
│   ├── colors.xml           # Color definitions
│   └── themes.xml           # App theme configuration
└── drawable/                # App icons and graphics
```

---

## 🎯 Core Components

### 1. MainActivity
**Location:** `app/src/main/java/com/colman/myapplication/MainActivity.kt`

The launch activity that displays the welcome screen.

**Responsibilities:**
- Display the app title and start button
- Handle edge-to-edge display with proper window insets
- Navigate to the game screen when "Start Game" is clicked

**Key Features:**
- Uses ConstraintLayout for responsive design
- Implements edge-to-edge UI with system bars handling
- Simple Intent navigation to GameActivity

---

### 2. GameActivity
**Location:** `app/src/main/java/com/colman/myapplication/GameActivity.kt`

The main game controller that manages game logic and user interactions.

**State Variables:**
- `board`: 2D array of Buttons representing the 3×3 game grid
- `playerX`: Boolean flag tracking current player (true = X, false = O)
- `turnCount`: Counter for tracking number of moves made
- `gameEnded`: Boolean flag indicating if the game has concluded
- `statusTextView`: TextView displaying game status messages
- `playAgainButton`: Button to restart the game
- `gameResetHandler`: Handler object for reset functionality

**Key Methods:**

#### `onCreate(savedInstanceState: Bundle?)`
Initializes the game when the activity is created:
- Sets up the UI components
- Finds and stores references to all 9 board buttons using tags ("00" to "22")
- Attaches click listeners to each button
- Initializes the GameResetHandler
- Displays the initial game status

#### `onClick(v: View?)`
Handles player moves when a board button is clicked:
- Validates the move (checks if button is empty and game hasn't ended)
- Places the current player's mark ('X' or 'O')
- Increments the turn counter
- Checks for win condition
- Checks for draw (all 9 spaces filled)
- Switches to the next player
- Updates the status display

#### `checkForWin(): Boolean`
Determines if a player has won the game:
- Creates a snapshot of the current board state
- Checks all 3 rows for matching marks
- Checks all 3 columns for matching marks
- Checks both diagonals (top-left to bottom-right, top-right to bottom-left)
- Returns `true` if any winning condition is met

#### `updateStatus()`
Updates the status TextView to reflect the current game state:
- Shows whose turn it is during gameplay
- Displays winner message when game ends
- Displays draw message when board is full

#### `resetGameState()`
Resets all game state variables to initial values:
- Sets playerX back to true (X starts)
- Resets turn counter to 0
- Sets gameEnded flag to false

---

### 3. GameResetHandler
**Location:** `app/src/main/java/com/colman/myapplication/GameResetHandler.kt`

A dedicated class for managing the "Play Again" functionality, following the Single Responsibility Principle.

**Constructor Parameters:**
- `gameActivity`: Reference to the GameActivity instance
- `board`: The 2D array of game board buttons
- `playAgainButton`: The button that triggers the reset
- `statusTextView`: Status display to update after reset

**Methods:**

#### `init`
Sets up the Play Again button click listener in the initialization block.

#### `showPlayAgainButton()`
Makes the Play Again button visible when the game ends.

#### `hidePlayAgainButton()`
Hides the Play Again button when starting a new game.

#### `resetGame()`
Complete game reset sequence:
1. Clears all button text from the board
2. Calls GameActivity's `resetGameState()` to reset variables
3. Hides the Play Again button
4. Updates status text to show Player X's turn

#### `clearBoard()`
Iterates through all board buttons and sets their text to empty strings.

---

## 🎨 User Interface

### Start Screen (`activity_main.xml`)

**Layout:** ConstraintLayout with edge-to-edge design

**Components:**
- **Title TextView**: Large, bold "Tic Tac Toe!" text in pink color
- **Start Button**: Dark-colored button with white text to begin the game

**Design Features:**
- Pink background (#F5E6EA)
- Centered vertical layout
- Material Design typography
- Responsive to system bars (status bar and navigation bar)

---

### Game Screen (`game_activity.xml`)

**Layout:** ConstraintLayout with GridLayout for the game board

**Components:**

1. **Title TextView** (`textView2`)
   - Displays app name at the top
   - Large display font in dark pink (#C2185B)
   - Bold styling

2. **Game Board GridLayout** (`gameBoard`)
   - 3×3 grid of MaterialButton components
   - Each button has:
     - Light pink background (#FFCDD2)
     - Dark pink stroke/border (#C2185B, 4dp width)
     - Rounded corners (8dp radius)
     - Large text size (48sp) for X and O marks
     - Unique tag identifier (e.g., "00", "01", "02")
     - Content description for accessibility
   - Buttons are evenly weighted to fill available space
   - 4dp margins between buttons for visual separation

3. **Status TextView** (`statusTextView`)
   - Displays current game status below the board
   - Shows turn information or game results
   - 24sp bold text in dark color
   - Dynamically updated during gameplay

4. **Play Again Button** (`playAgainButton`)
   - Initially hidden (visibility = "gone")
   - Appears only when game ends
   - Dark background with white text
   - Positioned below the status text

**Color Scheme:**
- Background: Light pink (#F5E6EA)
- Button background: Light pink (#FFCDD2 / @color/light_pink)
- Button border: Dark pink (#C2185B)
- Text: Hot pink (#FF69B4) for marks, dark for status

---

## 🔧 Technical Details

### Build Configuration

**Language:** Kotlin  
**Minimum SDK:** API 33 (Android 13)  
**Target SDK:** API 36  
**Compile SDK:** API 36  
**Java Version:** 11

### Dependencies

```gradle
- androidx.core:core-ktx          # Kotlin extensions
- androidx.appcompat:appcompat    # Backward compatibility
- material                         # Material Design components
- androidx.activity:activity      # Activity extensions
- androidx.constraintlayout       # Constraint-based layouts
- androidx.gridlayout             # Grid layout for game board
```

---

## 🎮 Game Logic Flow

### Game Initialization
1. User launches app and sees MainActivity
2. User clicks "Start Game" button
3. GameActivity is launched
4. 3×3 board is initialized with empty buttons
5. Status shows "Player X's turn"

### Gameplay Loop
1. Current player taps an empty cell
2. Cell is marked with 'X' or 'O'
3. Game checks for win condition:
   - All rows checked
   - All columns checked
   - Both diagonals checked
4. If win found:
   - Game ends
   - Winner announced
   - "Play Again" button appears
5. If no win and 9 turns completed:
   - Game declared a draw
   - "Play Again" button appears
6. If no win and turns remain:
   - Switch to other player
   - Update status message
7. Repeat from step 1

### Game Reset
1. User clicks "Play Again" button
2. All board cells cleared
3. Game state variables reset
4. Player X set as current player
5. "Play Again" button hidden
6. Status updated to "Player X's turn"
7. New game begins

---

## 🏆 Win Conditions

The game checks for the following winning patterns:

### Rows
- Row 0: [0,0] [0,1] [0,2]
- Row 1: [1,0] [1,1] [1,2]
- Row 2: [2,0] [2,1] [2,2]

### Columns
- Column 0: [0,0] [1,0] [2,0]
- Column 1: [0,1] [1,1] [2,1]
- Column 2: [0,2] [1,2] [2,2]

### Diagonals
- Main diagonal: [0,0] [1,1] [2,2]
- Anti-diagonal: [0,2] [1,1] [2,0]

A player wins when three of their marks appear in any of these patterns.

---

## 📱 String Resources

The app uses string resources for internationalization support:

**Game Status Messages:**
- `player_x_turn`: "Player X's turn"
- `player_o_turn`: "Player O's turn"
- `player_x_wins`: "Player X wins!"
- `player_o_wins`: "Player O wins!"
- `draw`: "It's a draw!"

**Button Labels:**
- `start_game`: "Start Game"
- `play_again`: "Play Again"

**Accessibility Descriptions:**
- Each of the 9 cells has a descriptive label (e.g., "Top left cell", "Center cell")

---

## ♿ Accessibility

The application includes several accessibility features:

1. **Content Descriptions**: Each game board cell has a meaningful description for screen readers
2. **Large Touch Targets**: Buttons are sized appropriately for easy tapping
3. **High Contrast**: Pink and white color scheme provides good visibility
4. **Text Sizing**: Large, bold text for game marks (48sp) ensures readability
5. **Status Announcements**: Text-based status updates can be read by assistive technologies

---

## 🚀 Getting Started

### Prerequisites
- Android Studio (Arctic Fox or newer)
- Android SDK API 33 or higher
- Gradle 8.0+

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/TicTacToe.git
   ```

2. Open the project in Android Studio

3. Let Gradle sync the project dependencies

4. Connect an Android device or start an emulator (API 33+)

5. Click "Run" or press Shift+F10

### Building the Project

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

---

## 🎓 Architecture and Design Patterns

### Separation of Concerns
- **MainActivity**: Handles navigation and app entry
- **GameActivity**: Manages game state and UI updates
- **GameResetHandler**: Dedicated reset functionality

### Key Design Principles Applied
1. **Single Responsibility**: Each class has one clear purpose
2. **Encapsulation**: Game state managed within GameActivity
3. **Delegation**: Reset logic delegated to GameResetHandler
4. **Event-Driven**: UI responds to user clicks with event listeners

### Android Components Used
- **Activities**: For screen management
- **Views**: Button, TextView for UI elements
- **Layouts**: ConstraintLayout and GridLayout for responsive design
- **Resources**: Externalized strings, colors, and dimensions
- **Material Components**: MaterialButton for modern UI

---

## 🔮 Future Enhancements

Potential features for future versions:
- 🤖 Single-player mode with AI opponent
- 🏅 Score tracking across multiple games
- 🎨 Theme customization options
- 💾 Save game state across app restarts
- 🔊 Sound effects and haptic feedback
- 📊 Game statistics and history
- 🌐 Online multiplayer capability
- ⏱️ Timer for each turn
- 🎯 Different difficulty levels for AI
- 📱 Tablet optimization with larger boards

---

## 👨‍💻 Development

This project was developed as a demonstration of Android development skills using Kotlin and follows Android best practices.

**Key Technologies:**
- Kotlin programming language
- Android SDK
- Material Design 3
- View-based UI (not Jetpack Compose)
- Gradle build system

---

## 📄 License

This project is available for educational purposes.

---

## 🙏 Acknowledgments

- Android documentation and best practices
- Material Design guidelines
- Kotlin programming language

---

**Enjoy playing Tic Tac Toe! 🎮**

