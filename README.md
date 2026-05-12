# SmartLogix

Sistema de gestión logística para eCommerce PYMEs — Sección 2, DSY1106 Desarrollo Fullstack III.

## Repositorios

| Componente | Repositorio | Descripción |
|------------|-------------|-------------|
| ms-inventario | [smartlogix-ms-inventario](https://github.com/cristobal-duoc/smartlogix-ms-inventario) | Microservicio de inventario — Repository Pattern, JPA, PostgreSQL (puerto 8081) |
| ms-pedidos | [smartlogix-ms-pedidos](https://github.com/cristobal-duoc/smartlogix-ms-pedidos) | Microservicio de pedidos — Factory Method, Repository Pattern, JPA, PostgreSQL (puerto 8082) |
| bff | [smartlogix-bff](https://github.com/cristobal-duoc/smartlogix-bff) | Backend for Frontend — Circuit Breaker con Resilience4j (puerto 8080) |
| frontend-web | [smartlogix-frontend-web](https://github.com/cristobal-duoc/smartlogix-frontend-web) | Frontend React — Observer pattern con useState/useEffect, NPM modules (puerto 3000) |

## Arquitectura

```
[frontend-web :3000]
        |
        v
    [BFF :8080]   ← Circuit Breaker (Resilience4j)
       /    \
      v      v
[ms-inventario  [ms-pedidos
    :8081]          :8082]
      |                |
[inventario_db]  [pedidos_db]
  (PostgreSQL)   (PostgreSQL)
```

## Patrones de diseño implementados

| Patrón | Dónde | Descripción |
|--------|-------|-------------|
| Repository Pattern | ms-inventario, ms-pedidos | `JpaRepository` abstrae el acceso a datos |
| Factory Method | ms-pedidos/factory/ | `PedidoFactory.crear(tipo, fecha)` crea Normal/Urgente/Programado |
| Circuit Breaker | bff/client/ | `@CircuitBreaker` protege las llamadas a los microservicios |
| Observer | frontend-web | `useState`/`useEffect` reaccionan a cambios de estado en los componentes |

## GitHub Flow

Cada repositorio sigue el mismo flujo:

```
main
  └── feature/{nombre}  ← desarrollo aquí
        └── PR → merge a main
```

| Repo | Rama de feature |
|------|----------------|
| smartlogix-ms-inventario | feature/estructura-base |
| smartlogix-ms-pedidos | feature/factory-method |
| smartlogix-bff | feature/circuit-breaker |
| smartlogix-frontend-web | feature/componentes-react |

## Instrucciones para ejecutar

1. Crear bases de datos en PostgreSQL:
   ```sql
   CREATE DATABASE inventario_db;
   CREATE DATABASE pedidos_db;
   ```

2. Exportar variable de entorno:
   ```bash
   export DB_PASSWORD=tu_password
   ```

3. Arrancar en orden:
   ```bash
   cd ms-inventario && mvn spring-boot:run   # puerto 8081
   cd ms-pedidos    && mvn spring-boot:run   # puerto 8082
   cd bff           && mvn spring-boot:run   # puerto 8080
   cd frontend-web  && npm install && npm run dev  # puerto 3000
   ```
