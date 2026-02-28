/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.pablo.hormiga.validations;
import io.github.pablo.hormiga.model.User;
import io.github.pablo.hormiga.model.NamedEntity;
/**
 *
 * @author Pavilion X360
 */
public class ValidationSingleton {
    
    private static ValidationSingleton instance;

    //nadie de afuera puede usar new
    public ValidationSingleton() {
    }
    
    public static ValidationSingleton getInstance(){
        if(instance == null){
            instance = new ValidationSingleton();
        }
        return instance;
    }
    
    // --- METODOS DE VALIDACION ---
    /**
     * Valida cualquier entidad que tenga nombre
     */
    
    public boolean validateName(NamedEntity entity){
        if(entity.getName() == null)return false;
        return !entity.getName().trim().isEmpty();
        
    }
    //retulizamos metodo validateName
    //Validaobjeto user completo
    public boolean validateUser(User user){
        if(!validateName(user))return false;       
        //validacion email
        if(user.getEmail() == null || !user.getEmail().contains("@")){
            return false;
        }
        //Validar password
        if(user.getPassword() == null|| user.getPassword().length()<4){
            return false;
        }
        return true;
        
    }
    
    
    
    
}
