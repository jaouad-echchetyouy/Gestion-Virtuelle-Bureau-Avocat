# Docker Setup for Gestion D'avocat

This document explains how to run the Gestion D'avocat application using Docker.

## Prerequisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed and running
- [Docker Compose](https://docs.docker.com/compose/install/) (included with Docker Desktop)

## Quick Start

### Linux/macOS
```bash
./start.sh
```

### Windows
```cmd
start.bat
```

Or manually with Docker Compose:
```bash
docker-compose up --build -d
```

## Services

The application consists of two services:

1. **Application (avocat-app)**: Spring Boot application running on port 8081
2. **Database (avocat-db)**: MySQL 8.0 database running on port 3306

## Access URLs

- **Application**: http://localhost:8081
- **Database**: localhost:3306
  - Database: `avocat_db`
  - Username: `avocat_user`
  - Password: `avocat_password`
  - Root Password: `rootpassword`

## Configuration

### Environment Variables

You can customize the application by setting these environment variables before running:

```bash
# Email configuration
export MAIL_USERNAME=your-email@gmail.com
export MAIL_PASSWORD=your-app-password
export SUPPORT_EMAIL=support@yourdomain.com

# Recaptcha configuration
export RECAPTCHA_SITE_KEY=your-site-key
export RECAPTCHA_SECRET_KEY=your-secret-key
```

Or create a `.env` file in the project root:
```env
MAIL_USERNAME=your-email@gmail.com
MAIL_PASSWORD=your-app-password
SUPPORT_EMAIL=support@yourdomain.com
RECAPTCHA_SITE_KEY=your-site-key
RECAPTCHA_SECRET_KEY=your-secret-key
```

## Useful Commands

| Command | Description |
|---------|-------------|
| `docker-compose up --build -d` | Build and start services in background |
| `docker-compose logs -f` | View logs (follow) |
| `docker-compose logs -f app` | View only application logs |
| `docker-compose logs -f db` | View only database logs |
| `docker-compose down` | Stop and remove containers |
| `docker-compose down -v` | Stop and remove containers + volumes (⚠️ deletes database data) |
| `docker-compose restart` | Restart services |
| `docker-compose ps` | List running services |
| `docker-compose exec app sh` | Access application container shell |
| `docker-compose exec db mysql -u avocat_user -p avocat_db` | Access MySQL shell |

## Data Persistence

Database data is persisted in a Docker volume named `db_data`. To backup:

```bash
# Backup database
docker-compose exec db mysqldump -u avocat_user -p avocat_db > backup.sql

# Restore database
docker-compose exec -T db mysql -u avocat_user -p avocat_db < backup.sql
```

## Troubleshooting

### Application fails to start

1. Check logs: `docker-compose logs app`
2. Ensure database is healthy: `docker-compose ps`
3. Verify database connection settings in `docker-compose.yml`

### Database connection issues

1. Check database logs: `docker-compose logs db`
2. Verify healthcheck is passing: `docker-compose ps`
3. Ensure database is fully initialized before app starts (handled by `depends_on` with `condition: service_healthy`)

### Port conflicts

If ports 8081 or 3306 are already in use, modify the port mappings in `docker-compose.yml`:

```yaml
ports:
  - "8081:8081"  # Change host port to 8081
```

### Rebuild from scratch

```bash
docker-compose down -v
docker-compose build --no-cache
docker-compose up -d
```

## Development

For development with live reload, you can mount the source code:

```yaml
# Add to app service in docker-compose.yml
volumes:
  - ./src:/app/src
  - ./pom.xml:/app/pom.xml
```

Then run with a development profile that enables hot reload.

## Production Considerations

1. **Change default passwords** in `docker-compose.yml`
2. **Use secrets** for sensitive data (Docker secrets or external secret manager)
3. **Configure proper JVM settings** in Dockerfile `JAVA_OPTS`
4. **Set up monitoring** and logging
5. **Use reverse proxy** (nginx/traefik) for SSL termination
6. **Configure resource limits** in docker-compose.yml:
   ```yaml
   deploy:
     resources:
       limits:
         memory: 2G
       reservations:
         memory: 1G
   ```

## File Structure

```
.
├── Dockerfile              # Multi-stage build for Spring Boot app
├── docker-compose.yml      # Service orchestration
├── .dockerignore           # Files excluded from Docker build
├── init-db.sql            # Database initialization script
├── start.sh               # Linux/macOS startup script
├── start.bat              # Windows startup script
└── DOCKER_README.md       # This file
```