package com.smartlogix.arquetipo.controller;

import com.smartlogix.arquetipo.entity.BaseEntity;
import com.smartlogix.arquetipo.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Plantilla del controller REST
// Renombrar a {Nombre}Controller.java y cambiar la ruta base en @RequestMapping
// El controller solo recibe peticiones HTTP y delega al service — no contiene lógica de negocio

@RestController
@RequestMapping("/api/base")  // Reemplazar 'base' con el recurso real (ej: /api/productos)
public class BaseController {

    private final BaseService baseService;

    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }

    // GET /api/base → lista todos los recursos
    @GetMapping
    public List<BaseEntity> listarTodos() {
        return baseService.listarTodos();
    }

    // GET /api/base/{id} → busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseEntity> buscarPorId(@PathVariable Long id) {
        Optional<BaseEntity> entidad = baseService.buscarPorId(id);
        return entidad.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/base → crea un nuevo recurso
    @PostMapping
    public ResponseEntity<BaseEntity> crear(@RequestBody BaseEntity entidad) {
        BaseEntity guardado = baseService.guardar(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // DELETE /api/base/{id} → elimina un recurso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        baseService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
