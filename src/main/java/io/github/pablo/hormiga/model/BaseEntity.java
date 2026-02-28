/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.pablo.hormiga.model;

/**
 *
 * @author Pavilion X360
 */
public abstract class BaseEntity {
    
    // esta es la clase base MADRE ya que todas las clases tendran un id, esta clase se las heredara
    protected int id;
    protected String Created_At;

    public BaseEntity() {
    }

    public BaseEntity(String created_at) {
        this.Created_At = created_at;
    }

    public BaseEntity(int id, String created_at) {
    this.id = id;
    this.Created_At = created_at;
    }
    public BaseEntity(int id) {
        this.id = id;
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
