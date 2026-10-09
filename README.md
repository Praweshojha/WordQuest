# WordQuest — Java Word Puzzle Game

## Description
WordQuest is a polished Java desktop application created as a university mini-project. It offers multiple game modes focused on vocabulary and word puzzles, built using Java Swing, JDBC, and standard file handling techniques.

## Features
* 5 Unique Game Modes
* 3 Difficulty Levels
* Real-time Scoring System
* Persistent Leaderboard and Game History (SQLite)
* File loading for word and question datasets
* Export history to a text file

## Game Modes
1. **Unscramble**: Re-arrange scrambled letters to form the correct word.
2. **Word Guess**: A Hangman-style game where you guess the word letter by letter with limited attempts.
3. **Word Search**: Find hidden words in a randomly generated grid.
4. **Word Quiz**: Answer multiple-choice vocabulary questions.
5. **Timed Challenge**: A fast-paced version of Unscramble with a 30-second timer.

## Technologies Used
* **Java 21 LTS**: Core logic, OOP principles.
* **Java Swing**: Graphical User Interface.
* **JDBC & SQLite**: Database connectivity and data storage.
* **Java I/O**: File reading and writing.

## Project Structure
* `src/main/java/com/wordquest/` - Source code grouped by package (`model`, `game`, `database`, `gui`, `file`, `util`).
* `data/` - Text files containing words and questions.
* `database/` - SQLite database file (auto-generated).
* `scores/` - Directory for exported score history.
* `lib/` - External libraries (sqlite-jdbc).

## How to Run
1. Make sure Java (`javac` and `java`) is installed and added to your system PATH.
2. Run the included `build_and_run.bat` script.
3. The game window will open automatically.

## How to Play
1. Enter your player name on the start screen.
2. Choose a Game Mode and Difficulty.
3. Play the game and earn points!
4. Review your results, check the Leaderboard, or view your History.

## Future Improvements
* Multiplayer mode
* Online leaderboard synchronization
* Sound effects and background music
* Additional languages support
* Larger word database with categories
* Difficulty-based AI for generated puzzles
