# API de Empleados (Java + Spring Boot)

## Requisitos
- JDK 17+
- Maven 3.8+ (o usar la extension "Extension Pack for Java" + "Spring Boot Extension Pack" en VS Code)

## Ejecutar
```bash
mvn spring-boot:run
```
La API queda en `http://localhost:8083`.

## Pruebas unitarias
```bash
mvn test
```

## API Key
Todos los endpoints requieren el header `X-API-KEY`.
Valor por defecto: `mi-api-key-secreta-123` (cambiar en `application.properties` o con la variable de entorno `API_KEY`).

## Endpoints

| Metodo | URL                                   | Descripcion                      | Codigos        |
|--------|---------------------------------------|----------------------------------|----------------|
| POST   | /api/empleados                       | Crear empleado                   | 201, 400, 401  |
| GET    | /api/empleados                       | Listar empleados                | 200, 401       |
| GET    | /api/empleados?especialidad=Fisica   | Filtrar por especialidad         | 200, 401       |
| GET    | /api/empleados/{id}                  | Consultar por id                 | 200, 404, 401  |

## Ejemplos con curl

Crear:
```bash
curl -X POST http://localhost:8083/api/empleados \
  -H "Content-Type: application/json" \
  -H "X-API-KEY: mi-api-key-secreta-123" \
  -d '{"nombre":"Ana","apellido":"Lopez","correo":"ana@correo.com","especialidad":"Matematicas","estado":"ACTIVO"}'
```

Listar filtrando:
```bash
curl -H "X-API-KEY: mi-api-key-secreta-123" "http://localhost:8083/api/empleados?especialidad=Matematicas"
```

Consultar inexistente (devuelve 404):
```bash
curl -i -H "X-API-KEY: mi-api-key-secreta-123" http://localhost:8083/api/empleados/99
```
