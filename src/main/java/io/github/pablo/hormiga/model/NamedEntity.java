/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.pablo.hormiga.model;

/**
 * Nivel 2 de Normalización: Entidad con Nombre.
 * Hereda el ID de BaseEntity y añade la capacidad de tener un nombre.
 */
public abstract class NamedEntity extends BaseEntity{
    //protected para que los hijos finales lo usen
    protected String name;
 
    
    
    // CONSTRUCTOR SOLO PARAVTESTING EN MAIN
    // Constructor para Entidades NUEVAS (Solo nombre)
public NamedEntity(String name) {
    super(); // Llama al constructor vacío de BaseEntity (la abuela)
    this.name = name;
}
    
    public NamedEntity(String name, int id, String created_at) {
        super(id, created_at);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_At() {
        return Created_At;
    }

    public void setCreated_At(String Created_At) {
        this.Created_At = Created_At;
    }

   
    
}
