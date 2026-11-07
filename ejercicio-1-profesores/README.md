# API de Profesores (Java + Spring Boot)

## Requisitos
- JDK 17+
- Maven 3.8+ (o usar la extension "Extension Pack for Java" + "Spring Boot Extension Pack" en VS Code)

## Ejecutar
```bash
mvn spring-boot:run
```
La API queda en `http://localhost:8081`.

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
| POST   | /api/profesores                       | Crear profesor                   | 201, 400, 401  |
| GET    | /api/profesores                       | Listar profesores                | 200, 401       |
| GET    | /api/profesores?especialidad=Fisica   | Filtrar por especialidad         | 200, 401       |
| GET    | /api/profesores/{id}                  | Consultar por id                 | 200, 404, 401  |

## Ejemplos con curl

Crear:
```bash
curl -X POST http://localhost:8081/api/profesores \
  -H "Content-Type: application/json" \
  -H "X-API-KEY: mi-api-key-secreta-123" \
  -d '{"nombre":"Ana","apellido":"Lopez","correo":"ana@correo.com","especialidad":"Matematicas","estado":"ACTIVO"}'
```

Listar filtrando:
```bash
curl -H "X-API-KEY: mi-api-key-secreta-123" "http://localhost:8081/api/profesores?especialidad=Matematicas"
```

Consultar inexistente (devuelve 404):
```bash
curl -i -H "X-API-KEY: mi-api-key-secreta-123" http://localhost:8081/api/profesores/99
```
