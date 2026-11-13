-- Database initialization script for MySQL
-- This script runs automatically when the MySQL container starts for the first time

-- Create database if not exists (handled by MYSQL_DATABASE env var, but kept for clarity)
CREATE DATABASE IF NOT EXISTS avocat_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Use the database
USE avocat_db;

-- Create user if not exists (handled by MYSQL_USER env var, but kept for clarity)
CREATE USER IF NOT EXISTS 'avocat_user'@'%' IDENTIFIED BY 'avocat_password';

-- Grant privileges
GRANT ALL PRIVILEGES ON avocat_db.* TO 'avocat_user'@'%';
FLUSH PRIVILEGES;

-- Set timezone
SET GLOBAL time_zone = '+00:00';

-- The actual schema will be created by Hibernate/JPA based on entity classes
-- when the Spring Boot application starts with spring.jpa.hibernate.ddl-auto=update