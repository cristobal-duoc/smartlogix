package com.smartlogix.arquetipo.service;

import com.smartlogix.arquetipo.entity.BaseEntity;
import com.smartlogix.arquetipo.repository.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Plantilla de la capa de servicio
// Renombrar a {Nombre}Service.java y reemplazar BaseEntity/BaseRepository por los concretos
// La lógica de negocio va aquí — el controller delega, el repository persiste

@Service
public class BaseService {

    // Inyección por constructor: práctica recomendada en Spring Boot
    // Facilita los tests unitarios (se puede pasar un mock sin levantar Spring)
    private final BaseRepository baseRepository;

    public BaseService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    // Retorna todos los registros
    public List<BaseEntity> listarTodos() {
        return baseRepository.findAll();
    }

    // Busca por ID — retorna Optional para manejar el caso de no encontrar
    public Optional<BaseEntity> buscarPorId(Long id) {
        return baseRepository.findById(id);
    }

    // Guarda o actualiza un registro
    public BaseEntity guardar(BaseEntity entidad) {
        return baseRepository.save(entidad);
    }

    // Elimina por ID
    public void eliminar(Long id) {
        baseRepository.deleteById(id);
    }

    // Agregar métodos de negocio específicos del dominio aquí
}
