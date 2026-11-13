@echo off
REM Startup script for Gestion D'avocat application (Windows)
REM This script builds and starts the application with Docker Compose

echo 🚀 Starting Gestion D'avocat application...

REM Check if Docker is running
docker info >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Docker is not running. Please start Docker Desktop and try again.
    exit /b 1
)

REM Create logs directory if it doesn't exist
if not exist logs mkdir logs

REM Build and start the containers
echo 🔨 Building and starting containers...
docker-compose up --build -d

REM Wait for services to be ready
echo ⏳ Waiting for services to be ready...
timeout /t 10 /nobreak >nul

REM Check if services are running
docker-compose ps | find "Up" >nul
if %errorlevel% equ 0 (
    echo ✅ Application started successfully!
    echo.
    echo 📋 Service URLs:
    echo    - Application: http://localhost:8081
    echo    - Database: localhost:3306
    echo.
    echo 📝 Useful commands:
    echo    - View logs: docker-compose logs -f
    echo    - Stop application: docker-compose down
    echo    - Stop and remove volumes: docker-compose down -v
    echo    - Restart: docker-compose restart
) else (
    echo ❌ Failed to start services. Check logs with: docker-compose logs
    exit /b 1
)