package com.smartlogix.arquetipo.entity;

import jakarta.persistence.*;

// Plantilla de entidad JPA
// Renombrar a {NombreEntidad}.java y agregar los campos específicos del dominio
// Ejemplo: Producto.java en ms-inventario, PedidoEntity.java en ms-pedidos

@Entity
@Table(name = "tabla_base")  // Reemplazar con el nombre real de la tabla
public class BaseEntity {

    // Clave primaria con generación automática por PostgreSQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Constructor vacío requerido por JPA para reconstruir entidades desde la BD
    public BaseEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Agregar aquí los campos específicos del dominio con sus @Column
    // Ejemplo:
    // @Column(nullable = false)
    // private String nombre;
}
