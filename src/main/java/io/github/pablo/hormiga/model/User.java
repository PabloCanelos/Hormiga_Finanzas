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
    private String createAt;// resgistrara hora del movimiento o transaccion

    public User() {
        super();
    }
    
    // constructor para nuevos usuarios(Lo que envia docker)
    public User(String userName, String email, String password) {
        super(userName);
        this.email = email;
        this.password = password;
    }

    // constructor para usuarios existentes(Los que traigo de docker)
    public User(int id,String userName, String email, String password, String createAt) {
        super(id, userName);
        this.email = email;
        this.password = password;
        this.createAt = createAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getCreatedAt() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
    
    
}
