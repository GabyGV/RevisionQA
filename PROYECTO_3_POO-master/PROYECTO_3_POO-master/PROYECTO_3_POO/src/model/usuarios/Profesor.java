/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.usuarios;

import java.io.Serializable;

/**
 * Profesor a cargo de revisar los trabajos entregados por el estudiante y determinar la nota final.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class Profesor implements Serializable{
    
    private String nombre;
    private String telefono;
    private String email;
    private String password;
    private String id;
    
    /**
     * Construye los objetos de la clase.
     * 
     * @param pName del profesor
     * @param pTelefono del profesor
     * @param pEmail direccion de correo electronico del profesor
     * @param pPassword contrasegna para ingresar al sistema
     * @param pId identificacion dentro del sistema
     */
    public Profesor(String pName, String pTelefono, String pEmail, String pPassword, String pId){
        this.nombre = pName;
        this.telefono = pTelefono;
        this.email = pEmail;
        this.password = pPassword;
        this.id = pId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    public String getTelefono(){
        return this.telefono;
    }
    public String getEmail(){
        return this.email;
    }
    
    public void setNombre(String pName){
        this.nombre = pName;
    }
    public void setTelefono(String pTelefono){
        this.telefono = pTelefono;
    }
    public void setEmail(String pEmail){
        this.email = pEmail;
    }

    /** Retorna una cadena con la informacion del objeto. */
    @Override
    public String toString() {
        return "Profesor{" + "nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + ", password=" + password + ", id=" + id + '}';
    }

}
