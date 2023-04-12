/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Actividad académica dentro del calendario de evaluación de un periodo de práctica.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class Evaluacion implements Serializable, Cloneable{
    
    private String descripcion;
    private String descripcionDelEntregable = "Sin entregar";
    private String archivo = "Sin entregar";
    private String comentarios = "Sin entregar";
    private double valorPorcentual;
    private String responsable;
    private boolean entregado;
    private double nota;
    private Fecha fechaEntregaEntregable = new Fecha();
    private Fecha fechaEntrega;
 
    private ArrayList<Rubro> rubros = new ArrayList<Rubro>();
 

    /**
     * Contruye los objetos de la clase.
     * 
     * @param descripcion acerca de en qué consiste la actividad
     * @param fechaEntrega de la evaluacion
     * @param valorPorcentual de la nota final
     * @param responsable de asigar la nota, profesor asesor o de curso
     * @param pRubros lista de requerimientos que debe cumplir el trabajo
     */
    public Evaluacion(String descripcion, Fecha fechaEntrega, double valorPorcentual, String responsable, ArrayList<Rubro> pRubros) {
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.valorPorcentual = valorPorcentual;
        this.responsable = responsable;
        this.rubros = pRubros;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getDescripcionDelEntregable() {
        return descripcionDelEntregable;
    }

    public void setDescripcionDelEntregable(String descripcionDelEntregable) {
        this.descripcionDelEntregable = descripcionDelEntregable;
    }

    public Fecha getFechaEntregaEntregable() {
        return fechaEntregaEntregable;
    }

    public void setFechaEntregaEntregable(Fecha fechaEntregaEntregable) {
        this.fechaEntregaEntregable = fechaEntregaEntregable;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public ArrayList<Rubro> getRubros() {
        return rubros;
    }

    public void setRubros(ArrayList<Rubro> rubros) {
        this.rubros = rubros;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Fecha getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Fecha fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public double getValorPorcentual() {
        return valorPorcentual;
    }

    public void setValorPorcentual(double valorPorcentual) {
        this.valorPorcentual = valorPorcentual;
    }

    public String getIdResponsable() {
        return responsable;
    }

    public void setIdResponsable(String idResponsable) {
        this.responsable = idResponsable;
    }
    
    /** Crea otro objeto igual. */
    public Object clone() {
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
    
    /** Retorna una cadena con la información del objeto, */
    @Override
    public String toString() {
        return "Evaluacion{" + "descripcion=" + descripcion + ", fechaEntrega=" + fechaEntrega + ", valorPorcentual=" + valorPorcentual + ", responsable=" + responsable + ", entregado=" + entregado + ", nota=" + nota + ", rubros=" + rubros + ", archivo=" + archivo + '}';
    }
}
