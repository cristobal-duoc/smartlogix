# arquetipo-maven

Plantilla base (arquetipo Maven) compartida por los microservicios de SmartLogix.

## ¿Qué es un arquetipo Maven?

Un arquetipo Maven es una plantilla de proyecto reutilizable que define:
- La estructura de paquetes estándar
- Las dependencias comunes
- Los archivos de configuración base

En SmartLogix, este arquetipo documenta la estructura que siguen `ms-inventario` y `ms-pedidos`.

## Estructura del arquetipo

```
arquetipo-maven/
├── pom.xml                                         ← Dependencias comunes
├── src/main/java/com/smartlogix/arquetipo/
│   ├── controller/BaseController.java              ← Plantilla del controller REST
│   ├── service/BaseService.java                    ← Plantilla del service
│   ├── repository/BaseRepository.java              ← Plantilla del repository (JpaRepository)
│   └── entity/BaseEntity.java                      ← Plantilla de la entidad JPA
└── src/main/resources/
    └── application.properties.template             ← Configuración base
```

## Patrón de capas

Todos los microservicios siguen la misma arquitectura en capas:

```
Controller → Service → Repository → Entity → Base de datos
```

| Capa | Responsabilidad |
|------|----------------|
| Controller | Recibe peticiones HTTP, retorna JSON, delega al Service |
| Service | Contiene la lógica de negocio del dominio |
| Repository | Abstrae el acceso a datos (Repository Pattern) |
| Entity | Representa una tabla en PostgreSQL |

## Cómo crear un nuevo microservicio

1. Copiar esta carpeta
2. En `pom.xml`: cambiar `artifactId`, `name` y `description`
3. Renombrar el paquete `com.smartlogix.arquetipo` a `com.smartlogix.{nombre}`
4. Renombrar las clases Base* por el nombre del dominio
5. Agregar los campos específicos en la entidad
6. Implementar la lógica de negocio en el service
7. Crear la base de datos en PostgreSQL
8. Configurar `application.properties` con el puerto y nombre de BD correctos

## Dependencias incluidas

- `spring-boot-starter-web` — API REST
- `spring-boot-starter-data-jpa` — Mapeo objeto-relacional con Hibernate
- `postgresql` — Driver de conexión a PostgreSQL
- `spring-boot-starter-test` — JUnit 5 + Mockito para pruebas unitarias
