# Project Guide — Java Concepts Implemented

This document explains where and how core Java concepts were utilized in the WordQuest project. This is designed to help you understand the architecture for your university mini-project submission.

## Object-Oriented Programming (OOP) Concepts

| Concept | Location | Explanation |
|---|---|---|
| **Class** | All `.java` files | Blueprint for all objects (e.g., `Player.java`, `Word.java`, `GameMode.java`). |
| **Object** | Throughout the code | Instances of classes, like creating a new `Player` object when a user logs in. |
| **Constructor** | Model and GUI classes | Used to initialize state. E.g., `public Player(String name)` initializes a player. |
| **Encapsulation** | `model` package | Using `private` fields and public `getters`/`setters` (e.g., `score`, `playerName`). |
| **Inheritance** | `game` package | `UnscrambleGame`, `WordGuessGame`, etc. `extend` the abstract `GameMode` class to inherit `score` and `difficulty` logic. |
| **Polymorphism** | `GameSelectionPanel.java` | The `startGame` method treats all game modes uniformly through the `GameMode` abstract reference. |
| **Abstraction** | `GameMode.java` | Declares `abstract` methods like `start()` and `isGameOver()` that subclasses must implement. |

## Core Java Concepts

| Concept | Location | Explanation |
|---|---|---|
| **Variables & Types** | Everywhere | Used primitive types (`int`, `double`, `boolean`) and reference types (`String`, `List`). |
| **Operators** | Game logic | Math operators for scoring (`+`, `*`), relational operators for comparisons (`>=`, `==`). |
| **If/Else / Switch** | `GameUtil.java`, `GameSelectionPanel.java` | Using `switch` to determine score multipliers based on difficulty. |
| **Loops** | `WordSearchGame.java`, `WordFileManager.java` | `for` loops to generate grids, `while` loops to read lines from files. |
| **Arrays** | `WordSearchGame.java`, GUI | 2D `char[][]` array used for the Word Search grid. |
| **Strings** | Everywhere | Extensively used for word manipulation, formatting queries, and UI text. |
| **Methods** | Everywhere | Reusable blocks of code (e.g., `GameUtil.shuffleWord()`). |
| **Exception Handling** | `DatabaseConnection.java`, `WordFileManager.java` | `try-catch` blocks handling `SQLException` and `IOException` for safe database and file access. |
| **Collections (List, Set)** | `WordGuessGame.java`, `ScoreDAO.java` | `ArrayList` for words/questions, `HashSet` for tracking guessed letters uniquely. |
| **File Handling (I/O)** | `file` package | `BufferedReader`/`FileReader` to load data, `BufferedWriter`/`FileWriter` to export scores. |
| **JDBC (Database)** | `database` package | Using `Connection`, `PreparedStatement`, and `ResultSet` to interact with SQLite. |
| **GUI (Swing)** | `gui` package | Using `JFrame`, `JPanel`, `JButton`, `CardLayout`, etc., to build the visual interface. |
