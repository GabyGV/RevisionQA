/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.usuarios;

import java.io.Serializable;

/**
 * Persona a cargo del practicante dentro de la empresa.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class Encargado implements Serializable{
    
    private String nombre;
    private String puesto;
    private String telefono;
    private String email;
    private String id;
    private String password;
    
    /**
     * Construye los objetos de la clase.
     * 
     * @param nombre del encargado
     * @param puesto del encargado dentro de la empresa
     * @param telefono del encargado
     * @param pEmail direccion de correo electronico del encargado
     * @param pId identificacion para ingresar al sistema
     * @param pPassword contrasegna para ingresar al sistema
     */
    public Encargado(String nombre, String puesto, String telefono, String pEmail, String pId, String pPassword) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.telefono = telefono;
        this.email = pEmail;
        this.id = pId;
        this.password = pPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String pEmail) {
        this.email = pEmail;
    }

    /** Retorna una cadena con la informacion del objeto. */
    @Override
    public String toString() {
        return "Encargado{" + "nombre=" + nombre + ", puesto=" + puesto + ", telefono=" + telefono + ", email=" + email + ", id=" + id + ", password=" + password + '}';
    }
    
}
