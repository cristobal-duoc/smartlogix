# SmartLogix

Sistema de gestión logística para eCommerce PYMEs — **Examen Final Transversal (EFT)**, DSY1106 Desarrollo Fullstack III.
Estudiante: Cristóbal Martínez.

Arquitectura de **microservicios** con API Gateway, BFF y tres dominios de negocio (Inventario, Pedidos y Envíos), cada uno con su propia base de datos (Database per Service).

## Repositorios

| Componente | Repositorio | Descripción |
|------------|-------------|-------------|
| frontend-web | [smartlogix-frontend-web](https://github.com/cristobal-duoc/smartlogix-frontend-web) | Frontend React (Vite) — patrón Observer (useState/useEffect), 3 paneles con alta desde la UI (puerto 3000) |
| api-gateway | [smartlogix-api-gateway](https://github.com/cristobal-duoc/smartlogix-api-gateway) | Spring Cloud Gateway — entrada única, rutea /api/bff/** al BFF (puerto 8080) |
| bff | [smartlogix-bff](https://github.com/cristobal-duoc/smartlogix-bff) | Backend for Frontend — Circuit Breaker (Resilience4j), agrega los 3 microservicios (puerto 8088) |
| ms-inventario | [smartlogix-ms-inventario](https://github.com/cristobal-duoc/smartlogix-ms-inventario) | Microservicio de Inventario — Repository Pattern, JPA, PostgreSQL (puerto 8081) |
| ms-pedidos | [smartlogix-ms-pedidos](https://github.com/cristobal-duoc/smartlogix-ms-pedidos) | Microservicio de Pedidos — Factory Method, Repository Pattern, JPA, PostgreSQL (puerto 8082) |
| ms-envios | [smartlogix-ms-envios](https://github.com/cristobal-duoc/smartlogix-ms-envios) | Microservicio de Envíos — Coordinación de envíos, Repository Pattern, JPA, PostgreSQL (puerto 8083) |

## Arquitectura

```
[frontend-web :3000]
        |
        v
[api-gateway :8080]            (entrada única)
        |
        v
   [BFF :8088]                 ← Circuit Breaker (Resilience4j)
     /    |    \
    v     v     v
[ms-inventario  [ms-pedidos  [ms-envios
    :8081]        :8082]        :8083]
    |               |             |
[inventario_db] [pedidos_db]  [envios_db]
   (PostgreSQL)  (PostgreSQL)  (PostgreSQL)
```

## Patrones de diseño implementados

| Patrón | Dónde | Descripción |
|--------|-------|-------------|
| Repository Pattern | ms-inventario, ms-pedidos, ms-envios | `JpaRepository` abstrae el acceso a datos |
| Factory Method | ms-pedidos/factory/ | `PedidoFactory.crear(tipo, fecha)` crea Normal/Urgente/Programado |
| Circuit Breaker | bff/client/ | `@CircuitBreaker` protege las llamadas a los microservicios (con fallback) |
| Observer | frontend-web | `useState`/`useEffect` reaccionan a cambios de estado en los componentes |

Patrón arquitectónico: **microservicios + API Gateway + BFF**.

## GitHub Flow

Cada funcionalidad se desarrolló en una rama `feature/` y se integró a `main` por Pull Request:

| Repo | Rama de feature | PR |
|------|----------------|----|
| smartlogix-ms-inventario | feature/estructura-base | #1 |
| smartlogix-ms-pedidos | feature/factory-method | #1 |
| smartlogix-bff | feature/circuit-breaker | #1 |
| smartlogix-frontend-web | feature/componentes-react | #1 |

(El API Gateway y el microservicio de Envíos se incorporaron en la etapa final del EFT directamente sobre `main`.)

## Cómo levantar el sistema completo

**Opción A — Docker** (un solo comando):
```bash
docker compose up --build
# luego abrir http://localhost:3000
```

**Opción B — Sin Docker** (solo Java 17, bases H2 en memoria):
```
Ejecutar la carpeta RUN_SIN_DOCKER\SmartLogix_DEMO.bat  (ver LEEME.txt)
# levanta los 5 servicios + el frontend y abre http://localhost:3000
```

## Calidad

- Pruebas unitarias en los 6 componentes (JUnit 5 + Mockito en el backend, Vitest en el frontend).
- Cobertura mínima del 60% verificada con JaCoCo; análisis centralizado en SonarCloud.
- Integración Continua con GitHub Actions en cada push.
