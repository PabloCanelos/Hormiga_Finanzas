package io.github.pablo.hormiga.model;

/**
 * Entidad categoria.
 * Solo maneja el nonmbre(heredado) porque no tiene Email ni password
 */

/**
 *
 * @author Pavilion X360
 */
public class Category extends NamedEntity{    
    //  CONSTRUCTOR PARA PRUEBA TESTING EN MAIN

    public Category(String name) {
        super(name);
    }
    
    //Fin del constructor para el main
    
    
    //===========Constructor para el dao=================================================
    public Category( String name, int id, String created_at) {
        super(name, id, created_at);
       
    }

    
    // NOTA : no necesitamos get and set porque name ya vice en namedEntity

    

    
    
}
