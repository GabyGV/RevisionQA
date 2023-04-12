/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 * Representación de una fecha para la entrega de evaluaciones.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class Fecha implements Serializable{
    
    private int dia;
    private int mes;
    private int agno;
    private int hora;
    private int minutos;
    private int segundos;

    /** Construye los objetos de la clase. */
    public Fecha() {
    this.dia = 0;
    this.mes = 0;
    this.agno = 0;
    this.hora = 0;
    this.minutos = 0;
    this.segundos = 0;
    }
    
    /** Construye los objetos de la clase. */
    public Fecha(int dia, int mes, int agno, int hora, int minutos, int segundos) {
        this.dia = dia;
        this.mes = mes;
        this.agno = agno;
        this.hora = hora;
        this.minutos = minutos;
        this.segundos = segundos;
    }
    
    public int getDia() {
        return dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAgno() {
        return agno;
    }

    public void setAgno(int agno) {
        this.agno = agno;
    }

    /** Retorna la fecha en un formato legible. */
    @Override
    public String toString() {
        return dia + "/" + mes + "/" + agno + " " + hora + ":" + minutos + ":" + segundos;
    }


    
    
}
