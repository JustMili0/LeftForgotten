@echo off

cd /D %~dp0
cd /D ../

powershell -c "./gradlew :fabric:publishModrinth"
powershell -c "./gradlew :forge:publishModrinth"

pause >nul