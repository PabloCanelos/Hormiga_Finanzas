/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package io.github.pablo.hormiga;
import io.github.pablo.hormiga.config.ConexionDB;
/**
 *
 * @author Pavilion X360
 */
public class Hormiga_Finanzas {

    public static void main(String[] args) {
        ConexionDB.getInstancia().getConexion();
    }
}
