/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Conjunto de evaluaciones para practicantes de un periodo determinado.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class CalendarioEvaluacion implements Serializable, Cloneable{
    
    ArrayList<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();

    /** Contruye los objetos de la clase. */
     public CalendarioEvaluacion(){
         
     }
    
    /**
     * Construye los objetos de la clase.
     * 
     * @param evaluaciones del calendario
     */
    public CalendarioEvaluacion(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
    
    /** Crea otro objeto con los mismos valores. */
    public Object clone()
    {
        Object clone = null;
        try
        {
            clone = super.clone();
        } 
        catch(CloneNotSupportedException e)
        {
            // No deberia suceder
        }
        return clone;
    }
}
