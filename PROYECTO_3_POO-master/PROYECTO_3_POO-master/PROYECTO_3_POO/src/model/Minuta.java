/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Instrumento de seguimiento para una evaluación. Son creadas posterior a una solicitud de reunion
 * hecha por el profesor asesor.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class Minuta implements Serializable{
    
    private String titulo;
    private ArrayList<String> listaParticipantes;
    private Fecha fechaInicio;
    private Fecha fechaFinal;
    private ArrayList<String> temas;
    private String lugar;
    private String archivo;

    /**
     * Construye los objetos de la clase.
     * 
     * @param titulo de la minuta
     * @param listaParticipantes presentes en la actividad
     * @param fechaInicio de la actividad
     * @param fechaFinal de la actividad
     * @param temas tratados
     * @param lugar de reunion
     * @param pArchivo foto de la minuta, si aplica
     */
    public Minuta(String titulo, ArrayList<String> listaParticipantes, Fecha fechaInicio, Fecha fechaFinal, ArrayList<String> temas, String lugar, String pArchivo) {
        this.titulo = titulo;
        this.listaParticipantes = listaParticipantes;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.temas = temas;
        this.lugar = lugar;
        this.archivo = pArchivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getListaParticipantes() {
        return listaParticipantes;
    }

    public void setListaParticipantes(ArrayList<String> listaParticipantes) {
        this.listaParticipantes = listaParticipantes;
    }

    public Fecha getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Fecha fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Fecha getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Fecha fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public ArrayList<String> getTemas() {
        return temas;
    }

    public void setTemas(ArrayList<String> temas) {
        this.temas = temas;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    
}
