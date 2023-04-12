/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import model.usuarios.Encargado;

/**
 * Lugar donde se realizará la práctica.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class Empresa implements Serializable{
    
    private String razonSocial;
    private String cedulaJuridica;
    private String telefono;
    private String direccion;
    private String encargado;

    /**
     * Contruye los objetos de la clase.
     * 
     * @param razonSocial de la empresa
     * @param cedulaJuridica de la empresa
     * @param direccion ubicacion geografica de la empresa
     * @param pSupervisor persona a cargo del practicante dentro de la empresa
     * @param pTelefono de la empresa
     */
    public Empresa(String razonSocial, String cedulaJuridica, String direccion, String pSupervisor, String pTelefono) {
        this.razonSocial = razonSocial;
        this.cedulaJuridica = cedulaJuridica;
        this.direccion = direccion;
        this.encargado = pSupervisor;
        this.telefono = pTelefono;
    }
    
    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCedulaJuridica() {
        return cedulaJuridica;
    }

    public void setCedulaJuridica(String cedulaJuridica) {
        this.cedulaJuridica = cedulaJuridica;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /** Devuelve una cadena con la información del objeto. */
    @Override
    public String toString() {
        return "Empresa{" + "razonSocial=" + razonSocial + ", cedulaJuridica=" + cedulaJuridica + ", telefono=" + telefono + ", direccion=" + direccion + ", supervisor=" + encargado + '}';
    }
    
    
    
}
