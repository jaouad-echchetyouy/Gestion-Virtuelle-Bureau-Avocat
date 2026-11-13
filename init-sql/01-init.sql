-- =============================================================================
-- Script d'initialisation de la base de données
-- Exécuté automatiquement au premier démarrage du conteneur MySQL
-- =============================================================================

-- Créer la base si elle n'existe pas (redondant avec MYSQL_DATABASE mais sûr)
CREATE DATABASE IF NOT EXISTS `abisoft` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `abisoft`;

-- Créer l'utilisateur applicatif avec droits limités
CREATE USER IF NOT EXISTS 'abisoft'@'%' IDENTIFIED BY 'abisoft123';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, INDEX, ALTER, REFERENCES ON `abisoft`.* TO 'abisoft'@'%';
FLUSH PRIVILEGES;

-- Configuration MySQL pour performance
SET GLOBAL innodb_buffer_pool_size = 268435456; -- 256MB
SET GLOBAL max_connections = 200;