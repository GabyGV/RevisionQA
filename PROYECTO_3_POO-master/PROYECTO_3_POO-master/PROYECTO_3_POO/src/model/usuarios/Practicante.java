/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import model.CalendarioEvaluacion;
import model.PeriodoPractica;
import model.Reunion;

/**
 * Estudiante del curso Práctica de Especialidad, practicante.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class Practicante implements Serializable{
    
    private String nombre;
    private String carnet;
    private String cedula;
    private String telefono;
    private String nacimiento;
    private String direccion;
    private String email;
    private String password;
    private double notaFinal;
    private PeriodoPractica periodo;
    private String idProfesorAsesor;
    private String idProfesorCurso;
    private String empresa;
    private CalendarioEvaluacion calendario = new CalendarioEvaluacion();
    private ArrayList<Reunion> reuniones = new ArrayList<Reunion>();

    /**
     * Construye los objetos de la clase.
     * 
     * @param nombre del practicante
     * @param carnet del practicante
     * @param cedula del practicante
     * @param telefono del practicante
     * @param nacimiento del practicante, fecha
     * @param direccion ubicacion geografica de la residencia del practicante
     * @param email direccion de correo electronico del practicante
     * @param password contrasegna para ingresar al sistema
     * @param periodo en que hará la práctica
     * @param pProfesorAsesor profesor a cargo de revisar las evaluaciones
     * @param pProfesorCurso profesor a cargo de poner la nota final
     * @param empresa donde hara la practica
     */
    public Practicante(String nombre, String carnet, String cedula, String telefono, String nacimiento, String direccion, String email, String password, PeriodoPractica periodo, String pProfesorAsesor, String pProfesorCurso, String empresa) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.cedula = cedula;
        this.telefono = telefono;
        this.nacimiento = nacimiento;
        this.direccion = direccion;
        this.email = email;
        this.password = password;
        this.periodo = periodo;
        this.idProfesorAsesor = pProfesorAsesor;
        this.idProfesorCurso = pProfesorCurso;
        this.empresa = empresa;
    }

    public ArrayList<Reunion> getReuniones() {
        return reuniones;
    }

    public void setReuniones(ArrayList<Reunion> reuniones) {
        this.reuniones = reuniones;
    }


    public CalendarioEvaluacion getCalendario() {
        return calendario;
    }

    public void setCalendario(CalendarioEvaluacion calendario) {
        this.calendario = calendario;
    }

    public String getProfesorAsesor() {
        return idProfesorAsesor;
    }

    public void setProfesorAsesor(String profesorAsesor) {
        this.idProfesorAsesor = profesorAsesor;
    }

    public String getProfesorCurso() {
        return idProfesorCurso;
    }

    public void setProfesorCurso(String profesorCurso) {
        this.idProfesorCurso = profesorCurso;
    }


    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public PeriodoPractica getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoPractica periodo) {
        this.periodo = periodo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public String getCarnet(){
        return this.carnet;
    }
    public String getCedula(){
        return this.cedula;
    }
    public String getTelefono(){
        return this.telefono;
    }
    public String getNacimiento(){
        return this.nacimiento;
    }
    
    public void setNombre(String pName){
        this.nombre = pName;
    }
    public void setCarnet(String pCarnet){
        this.carnet = pCarnet;
    }
    public void setCedula(String pCedula){
        this.cedula = pCedula;
    }
    public void setTelefono(String pTelefono){
        this.telefono = pTelefono;
    }
    public void setNacimiento(String pNacimiento){
        this.nacimiento = pNacimiento;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}