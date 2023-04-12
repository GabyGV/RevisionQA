/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Momento en el año lectivo que se realiza la práctica.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class PeriodoPractica implements Serializable{
    
    private int numeroSemestre = 0;
    private int agno = 0;
    private CalendarioEvaluacion calendario = new CalendarioEvaluacion(new ArrayList<Evaluacion>());
    
    /**
     * Construye los objetos de la clase.
     * 
     * @param numeroSemestre que se desarrolla la practica
     * @param agno que se desarrolla la practica
     */
    public PeriodoPractica(int numeroSemestre, int agno) {
        this.numeroSemestre = numeroSemestre;
        this.agno = agno;
    }

    public CalendarioEvaluacion getCalendario() {
        return calendario;
    }

    public void setCalendario(CalendarioEvaluacion calendario) {
        this.calendario = calendario;
    }

    public int getNumeroSemestre() {
        return numeroSemestre;
    }

    public void setNumeroSemestre(int numeroSemestre) {
        this.numeroSemestre = numeroSemestre;
    }

    public int getAgno() {
        return agno;
    }

    public void setAgno(int agno) {
        this.agno = agno;
    }

    /** Devuelve una cadena con la informacion del objeto. */
    @Override
    public String toString() {
        
        String s;
        
        if(calendario != null && !calendario.evaluaciones.isEmpty()){
            
            s = "Evaluaciones: \n";
            for(int i = 0; i < calendario.evaluaciones.size(); i++){
                s += calendario.evaluaciones.get(i).toString() + "\n";
            }
            
        }else{
            s = "No existe calendario";
        }

        
        return "PeriodoPractica{" + "numeroSemestre=" + numeroSemestre + ", agno=" + agno + ", calendario=" + s + '}';
    }

    
    
}
