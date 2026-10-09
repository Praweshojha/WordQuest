@echo off
echo Building WordQuest...
if not exist out mkdir out
javac -d out -cp "lib/sqlite-jdbc.jar" src/main/java/com/wordquest/**/*.java src/main/java/com/wordquest/*.java
if %errorlevel% neq 0 (
    echo Build failed!
    pause
    exit /b %errorlevel%
)
echo Build successful! Starting game...
java -cp "out;lib/sqlite-jdbc.jar" com.wordquest.Main
pause
