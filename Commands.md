# Docker & Docker Compose Commands

## Build Docker Images

```
docker compose build
```

## Start All Services (Detached)

```
docker compose up -d
```

## Stop and Remove All Containers, Networks, Volumes

```
docker compose down
```

## View Running Containers

```
docker ps
```

## View All Containers (including stopped)

```
docker ps -a
```

## View Docker Images

```
docker images
```

## Remove Unused Images

```
docker image prune -a
```

## Run Tests (example: UserService with Maven)

```
docker compose exec userservice ./mvnw test
```

- Replace `userservice` with your service name as needed.
- For frontend tests, use the appropriate npm/yarn command inside the container.

## Build & Run Without Compose (Single Service Example)

```
docker build -t my-image ./FrontendService

docker run -p 3000:80 my-image
```

## Troubleshooting

### Fixed Issues

#### UserService Build Compilation Error
If you encounter compilation errors when building UserService, the issue may be related to incorrect package imports in test files.

**Error Symptoms:**
- `package therap.dhatree.UserService.entity does not exist`
- `package therap.dhatree.UserService.web.dto does not exist`
- Constructor parameter mismatch in AuthService

**Solution Applied:**
1. Fixed import statements in `AuthServiceTest.java`:
   - Changed `therap.dhatree.UserService.entity.*` to `therap.dhatree.UserService.model.*`
   - Changed `therap.dhatree.UserService.web.dto.AuthDtos` to `therap.dhatree.UserService.dto.AuthDtos`
2. Fixed constructor call to include all required parameters including `DoctorRepository`

The build should now complete successfully with `docker compose build`.


