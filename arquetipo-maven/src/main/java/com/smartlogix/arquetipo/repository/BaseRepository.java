package com.smartlogix.arquetipo.repository;

import com.smartlogix.arquetipo.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Plantilla del Repository — Patrón Repository
// JpaRepository<Entidad, TipoClavePrimaria> proporciona:
//   findAll(), findById(), save(), deleteById() y más
// Spring Data JPA genera la implementación automáticamente en tiempo de ejecución
// No es necesario escribir SQL ni implementar los métodos CRUD manualmente

// Renombrar a {Nombre}Repository.java y cambiar BaseEntity por la entidad concreta
@Repository
public interface BaseRepository extends JpaRepository<BaseEntity, Long> {

    // Agregar métodos de consulta personalizados según las necesidades del dominio
    // Spring Data JPA los implementa automáticamente interpretando el nombre del método
    // Ejemplo:
    // List<BaseEntity> findByNombre(String nombre);
    // List<BaseEntity> findByEstado(String estado);
}
