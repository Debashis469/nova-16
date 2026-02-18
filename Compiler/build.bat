@echo off

REM --------------------------------------------------
REM Always move to the directory of this script
REM --------------------------------------------------
cd /d %~dp0

REM --------------------------------------------------
REM Check if an argument is provided
REM --------------------------------------------------
if "%~1"=="" (
    echo Usage: build ^<file.luma ^| directory^>
    echo Example: build test.luma
    echo Example: build Pong
    exit /b 1
)

REM --------------------------------------------------
REM Compile all Java source files
REM --------------------------------------------------
javac -d out parser\*.java tokenizer\*.java symboltable\*.java util\*.java vm\*.java *.java

if errorlevel 1 (
    echo Java compilation failed.
    exit /b 1
)

REM --------------------------------------------------
REM Run Luma compiler with user argument
REM --------------------------------------------------
java -cp out LumaCompiler %1

pause
