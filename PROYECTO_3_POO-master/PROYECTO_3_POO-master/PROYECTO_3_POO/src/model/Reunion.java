/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Reunion solicitada por el profesor asesor.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class Reunion implements Serializable{
    
    private Fecha fecha;
    private String tema;
    private String lugar;
    private String agenda;
    private ArrayList<Minuta> minutas = new ArrayList<Minuta>();
    
    /**
     * Construye los objetos de la clase.
     * 
     * @param fecha de la reunion
     * @param tema de la reunion
     * @param pAgenda puntos a ser tratados durante la reunion
     * @param lugar de la reunion
     */
    public Reunion(Fecha fecha, String tema,String pAgenda, String lugar) {
        this.fecha = fecha;
        this.tema = tema;
        this.lugar = lugar;
        this.agenda = pAgenda;
    }

    public ArrayList<Minuta> getMinutas() {
        return minutas;
    }

    public void setMinutas(ArrayList<Minuta> minutas) {
        this.minutas = minutas;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
    
    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    
}
