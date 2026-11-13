#!/bin/bash

# Startup script for Gestion D'avocat application
# This script builds and starts the application with Docker Compose

set -e

echo "🚀 Starting Gestion D'avocat application..."

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker is not running. Please start Docker Desktop and try again."
    exit 1
fi

# Check if docker-compose is available
if ! command -v docker-compose &> /dev/null; then
    echo "❌ docker-compose not found. Please install Docker Compose."
    exit 1
fi

# Create logs directory if it doesn't exist
mkdir -p logs

# Build and start the containers
echo "🔨 Building and starting containers..."
docker-compose up --build -d

# Wait for services to be healthy
echo "⏳ Waiting for services to be ready..."
sleep 10

# Check if services are running
if docker-compose ps | grep -q "Up"; then
    echo "✅ Application started successfully!"
    echo ""
    echo "📋 Service URLs:"
    echo "   - Application: http://localhost:8081"
    echo "   - Database: localhost:3306"
    echo ""
    echo "📝 Useful commands:"
    echo "   - View logs: docker-compose logs -f"
    echo "   - Stop application: docker-compose down"
    echo "   - Stop and remove volumes: docker-compose down -v"
    echo "   - Restart: docker-compose restart"
else
    echo "❌ Failed to start services. Check logs with: docker-compose logs"
    exit 1
fi