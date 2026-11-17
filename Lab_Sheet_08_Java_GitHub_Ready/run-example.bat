@echo off
if "%1"=="" (
  echo Usage: run-example.bat fully.qualified.ClassName
  exit /b 1
)
java -cp out %1
