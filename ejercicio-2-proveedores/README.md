# API de Proveedores (Java + Spring Boot)

## Requisitos
- JDK 17+
- Maven 3.8+ (o usar la extension "Extension Pack for Java" + "Spring Boot Extension Pack" en VS Code)

## Ejecutar
```bash
mvn spring-boot:run
```
La API queda en `http://localhost:8082`.

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
| POST   | /api/proveedores                       | Crear proveedor                   | 201, 400, 401  |
| GET    | /api/proveedores                       | Listar proveedores                | 200, 401       |
| GET    | /api/proveedores?categoria=Alimentos   | Filtrar por categoria         | 200, 401       |
| GET    | /api/proveedores/{id}                  | Consultar por id                 | 200, 404, 401  |

## Ejemplos con curl

Crear:
```bash
curl -X POST http://localhost:8082/api/proveedores \
  -H "Content-Type: application/json" \
  -H "X-API-KEY: mi-api-key-secreta-123" \
  -d '{"nombre":"Ana","apellido":"Lopez","correo":"ana@correo.com","categoria":"Tecnologia","estado":"ACTIVO"}'
```

Listar filtrando:
```bash
curl -H "X-API-KEY: mi-api-key-secreta-123" "http://localhost:8082/api/proveedores?categoria=Tecnologia"
```

Consultar inexistente (devuelve 404):
```bash
curl -i -H "X-API-KEY: mi-api-key-secreta-123" http://localhost:8082/api/proveedores/99
```
