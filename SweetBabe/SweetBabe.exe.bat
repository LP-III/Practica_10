@echo off
echo ================================
echo   COMPILANDO EL JUEGO.
echo ================================
echo ================================
echo   COMPILANDO EL JUEGO..
echo ================================
echo ================================
echo   COMPILANDO EL JUEGO...
echo ================================

javac -cp ".;sqlite-jdbc.jar" *.java

if %errorlevel% neq 0 (
    echo.
    echo ERROR AL COMPILAR!!!!
    pause
    exit /b
)

echo.
echo ================================
echo     EJECUTANDO EL JUEGO.
echo ================================
echo ================================
echo     EJECUTANDO EL JUEGO..
echo ================================
echo ================================
echo     EJECUTANDO EL JUEGO...
echo ================================
echo ================================
echo     BIENVENIDOS ლ(╹◡╹ლ)
echo ================================

java -cp ".;sqlite-jdbc.jar" Exec

echo.
echo ================================
echo     JUEGO FINALIZADO
echo ================================
pause
