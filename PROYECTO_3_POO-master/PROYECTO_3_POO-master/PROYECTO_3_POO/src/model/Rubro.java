/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 * Requerimientos de una evaluacion
 * 
 * @author Adrian, Dylan y Roberto
 */
public class Rubro implements Serializable{
    
    String detalle = "";
    double porcentaje = 0;
    double nota = 0;

    /**
     * Construye los objetos de la clase.
     * 
     * @param detalle descripcion del requerimiento
     * @param porcentaje  del total de la evaluacion
     */
    public Rubro(String detalle, double porcentaje) {
        this.detalle = detalle;
        this.porcentaje = porcentaje;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
