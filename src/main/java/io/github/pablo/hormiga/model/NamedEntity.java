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

    public NamedEntity() {
        super();// llama alconstructor de la super clase BadeEntity
    }

    public NamedEntity(String name) {
        this.name = name;
    }

    public NamedEntity(int id, String name) {
        super(id);
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
    
    
    
}
