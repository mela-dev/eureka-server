# Eureka service

## Build

```bash
mvn clean package
```

## Run

```bash
docker compose up --build
```

## Test

```bash
curl -I GET http://localhost:8080/api/public/test 
```
```bash
curl -I -u user:userpass GET http://localhost:8080/api/user/test 
```
```bash
curl -I -u admin:adminpass GET http://localhost:8080/api/admin/test 
```

