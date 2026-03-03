/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.pablo.hormiga.model;

/**
 * Entidad Final: Usuario.
 * Reúne el ID (Abuela), el Nombre (Madre) y añade el Email.
 */
public class User extends NamedEntity{
    private  String email;
    private String password;
    
    

    public User(String email, String password, String name) {
        super(name);
        this.email = email;
        this.password = password;
    }
//constructor para el dao
    public User(String email, String password, String name, int id, String created_at) {
        super(name, id, created_at);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
   
    
   
    

    
    
    
}
