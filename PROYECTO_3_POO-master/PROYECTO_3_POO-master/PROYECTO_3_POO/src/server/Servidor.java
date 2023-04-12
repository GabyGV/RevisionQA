/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dao.xml.EmpresaDAOxml;
import dao.xml.EncargadoDAOxml;
import dao.xml.PeriodoPracticaDAOxml;
import dao.xml.PracticanteDAOxml;
import dao.xml.ProfesorDAOxml;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import model.CalendarioEvaluacion;
import model.Empresa;
import model.Evaluacion;
import model.Fecha;
import model.Minuta;
import model.PeriodoPractica;
import model.Reunion;
import model.usuarios.Encargado;
import model.usuarios.Practicante;
import model.usuarios.Profesor;

/**
 * Modelo del modelo MVC, funciones de manejo de datos.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class Servidor {
    
    private final static Servidor instance = new Servidor();
    
    //private static ServerSocket ss;
    //private static Socket s;
    private ArrayList<Profesor> profesores;
    private ArrayList<Encargado> encargados;
    private ArrayList<Empresa> empresas;
    private ArrayList<PeriodoPractica> periodos;
    private ArrayList<Practicante> practicantes;
    private ArrayList<Minuta> minutas;
    private ArrayList<CalendarioEvaluacion> calendarios;
    
    public EncargadoDAOxml daoEncargado;
    public PracticanteDAOxml daoPracticante;
    public ProfesorDAOxml daoProfesor;
    public EmpresaDAOxml daoEmpresa;
    public PeriodoPracticaDAOxml daoPeriodo;
    
    /** Guarda los profesores en la base de datos. */
    private void guardarProfesores() {
        setProfesores(daoProfesor.obtenerTodos());
    }
    
    /** Guarda las empresas en la base de datos. */
    private void guardarEmpresas() {
        setEmpresas(daoEmpresa.obtenerTodos());
    }

    /** Guarda los practicantes en la base de datos. */
    private void guardarPracticantes() {
        setPracticantes(daoPracticante.obtenerTodos());
    }
    
    /** Guarda los encargadoss en la base de datos. */
    private void guardarEncargados() {
        setEncargados(daoEncargado.obtenerTodos());
    }

    /** Guarda los periodos en la base de datos. */
    private void guardarPeriodos() {
        setPeriodos(daoPeriodo.obtenerTodos());
    }

    /** Recupera los datos del servidor. */
    private void cargarElServidor() {
        guardarProfesores();
        guardarEmpresas();
        guardarPracticantes();
        guardarEncargados();
        guardarPeriodos();
    }
    
    /*
    public static void main(String[] args) throws IOException{
        ss = new ServerSocket(2999);
        while(true){
            s = ss.accept();
            System.out.println("Cliente conectado");
        }
    }
    */
    
    /** Construye los objetos de la clase. */
    private Servidor(){        //Aqui usar las interfaces dao para cargar las arraylist
        daoPracticante = new PracticanteDAOxml();
        daoEncargado = new EncargadoDAOxml();
        daoProfesor = new ProfesorDAOxml();
        daoEmpresa = new EmpresaDAOxml();
        daoPeriodo = new PeriodoPracticaDAOxml();
        cargarElServidor();
    }
    
    /** Imprime en consola la informacion de los practicantes. */
    public void leerPracticantes(){
        
        for(int i = 0; i < practicantes.size(); i++){
            
            System.out.println("Nombre: " + practicantes.get(i).getNombre());
            System.out.println("Carnet: " + practicantes.get(i).getCarnet());
            System.out.println("Cedula: " + practicantes.get(i).getCedula());
            System.out.println("Telefono: " + practicantes.get(i).getTelefono());
            System.out.println("Nacimiento: " + practicantes.get(i).getNacimiento());
            
            System.out.println("Direccion: " + practicantes.get(i).getDireccion());
            System.out.println("Email: " + practicantes.get(i).getEmail());
            System.out.println("Cedula: " + practicantes.get(i).getCedula());
            System.out.println("Password: " + practicantes.get(i).getPassword());
            System.out.println("Periodo: " + practicantes.get(i).getPeriodo().getNumeroSemestre() + " " + practicantes.get(i).getPeriodo().getAgno());
            
            System.out.println("Profesor asesor: " + practicantes.get(i).getProfesorAsesor());
            System.out.println("Profesor curso: " + practicantes.get(i).getProfesorCurso());
            System.out.println("Nota final: " + practicantes.get(i).getNotaFinal());
            System.out.println("Empresa: " + practicantes.get(i).getEmpresa());
            System.out.println("Calendario: ");
            for(int e = 0; e < practicantes.get(i).getCalendario().getEvaluaciones().size(); e++){
                System.out.println("Descripcion: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).getDescripcion());
                System.out.println("Descripcion entregable: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).getDescripcionDelEntregable());
                System.out.println("Archivo: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).getArchivo());
                System.out.println("Comentarios: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).getComentarios());
                System.out.println("Valor porcentual: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).getValorPorcentual());
                System.out.println("Responsable: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).getResponsable());
                System.out.println("Entregado: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).isEntregado());
                System.out.println("Nota: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).getNota());
                System.out.println("Fecha entrega: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).getFechaEntrega().toString());
                System.out.println("Fecha entregado: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).getFechaEntregaEntregable().toString());
                for(int j = 0; j < practicantes.get(i).getCalendario().getEvaluaciones().get(e).getRubros().size(); j++){
                    
                    System.out.println("Rubro:");
                    System.out.println("Detalle: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).getRubros().get(j).getDetalle());
                    System.out.println("Porcentaje: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).getRubros().get(j).getPorcentaje());
                    System.out.println("Nota: " + practicantes.get(i).getCalendario().getEvaluaciones().get(e).getRubros().get(j).getNota());
                }
            }
            System.out.println("Reuniones:");
            for(int e = 0; e < practicantes.get(i).getReuniones().size(); e++){
                
                System.out.println("Fecha: " + practicantes.get(i).getReuniones().get(e).getFecha().toString());
                System.out.println("Tema: " + practicantes.get(i).getReuniones().get(e).getTema());
                System.out.println("Lugar: " + practicantes.get(i).getReuniones().get(e).getLugar());
                System.out.println("Agenda: " + practicantes.get(i).getReuniones().get(e).getAgenda());
                System.out.println("Minutas:");
                for(int j = 0; j < practicantes.get(i).getReuniones().get(e).getMinutas().size(); j++){
                    
                    System.out.println("Titulo: " + practicantes.get(i).getReuniones().get(e).getMinutas().get(j).getTitulo());
                    System.out.println("Participantes:");
                    for(int h = 0; h < practicantes.get(i).getReuniones().get(e).getMinutas().get(j).getListaParticipantes().size(); h++){
                        
                        System.out.println(practicantes.get(i).getReuniones().get(e).getMinutas().get(j).getListaParticipantes().get(h));
                        
                    }
                    
                    System.out.println("Titulo: " + practicantes.get(i).getReuniones().get(e).getMinutas().get(j).getFechaInicio().toString());
                    System.out.println("Titulo: " + practicantes.get(i).getReuniones().get(e).getMinutas().get(j).getFechaFinal().toString());
                    
                    System.out.println("Temas:");
                    for(int h = 0; h < practicantes.get(i).getReuniones().get(e).getMinutas().get(j).getTemas().size(); h++){
                        
                        System.out.println(practicantes.get(i).getReuniones().get(e).getMinutas().get(j).getTemas().get(h));
                        
                    }
                    
                    System.out.println("Lugar: " + practicantes.get(i).getReuniones().get(e).getMinutas().get(j).getLugar());
                    System.out.println("Archivo: " + practicantes.get(i).getReuniones().get(e).getMinutas().get(j).getArchivo());
                    
                }
            }
            
        }
        
    }
    
    /** Retorna la única instancia de la clase. */
    public static Servidor getInstance(){
        return instance;
    }

    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }

    public ArrayList<Encargado> getEncargados() {
        return encargados;
    }

    public ArrayList<Empresa> getEmpresas() {
        return empresas;
    }

    public ArrayList<PeriodoPractica> getPeriodos() {
        return periodos;
    }

    public ArrayList<Practicante> getPracticantes() {
        return practicantes;
    }

    public ArrayList<Minuta> getMinutas() {
        return minutas;
    }


    public ArrayList<CalendarioEvaluacion> getCalendarios() {
        return calendarios;
    }

    /** Imprime los practicantes. */
    public void toStringPracticantes(){
        System.out.println("Practicantes:");
        for(int i = 0; i < practicantes.size(); i++){
            System.out.println(practicantes.get(i).toString());
        }
        
    }
    
    /** Imprime los profesores. */
    public void toStringProfesor(){
        System.out.println("Profesores:");
        for(int i = 0; i < profesores.size(); i++){
            System.out.println(profesores.get(i).toString());
        }
        
    }
    
    /** Imprime los encargados. */
    public void toStringEncargado(){
        System.out.println("Encargados:");
        for(int i = 0; i < encargados.size(); i++){
            System.out.println(encargados.get(i).toString());
        }
    }
    
    /** Imprime los periodos de práctica. */
    public void toStringPeriodoPractica(){
        System.out.println("Periodos:");
        for(int i = 0; i < periodos.size(); i++){
            System.out.println(periodos.get(i).toString());
        }
    }
    
    /** Imprime los empresas. */
    public void toStringEmpresa(){
        System.out.println("Empresas::");
        for(int i = 0; i < empresas.size(); i++){
            System.out.println(empresas.get(i).toString());
        }
    }
    
    /** Retorna el practicante solicitado, si existe. */
    public Practicante buscarPracticante(String pId){
        for(int i = 0; i < practicantes.size(); i++){
            if(practicantes.get(i).getCarnet().equals(pId)){   
                return practicantes.get(i);   
            }
        }
        return null;
    }
    
    /** Retorna el encargado solicitado, si existe. */
    public Encargado buscarEncargado(String pId){
        for(int i = 0; i < encargados.size(); i++){
            if(encargados.get(i).getId().equals(pId)){   
                return encargados.get(i);   
            }
        }
        return null;
    }
    
    /** Retorna el profesor solicitado, si existe. */
    public Profesor buscarProfesor(String pId){
        for(int i = 0; i < profesores.size(); i++){
            if(profesores.get(i).getId().equals(pId)){   
                return profesores.get(i);   
            }
        }
        return null;
    }
        
    /** Retorna la empresa solicitada, si existe. */
    public Empresa buscarEmpresa(String pId){
        for(int i = 0; i < empresas.size(); i++){
            if(empresas.get(i).getCedulaJuridica().equals(pId)){   
                return empresas.get(i);   
            }
        }
        return null;
    }
            
    /** Retorna el periodo solicitado, si existe. */
    public PeriodoPractica buscarPeriodo(int semestre, int agno){
        for(int i = 0; i < periodos.size(); i++){
            if(periodos.get(i).getAgno() == agno && periodos.get(i).getNumeroSemestre() == semestre){   
                return periodos.get(i);   
            }
        }
        return null;
    }
    
    /** Guarda un encargado en la base de datos. */
    public void registrarEncargado(String pNombre, String pCargo, String pTelefono, String pEmail, String pId, String pPassword){
        encargados.add(new Encargado(pNombre, pCargo, pTelefono, pEmail, pId, pPassword));
        daoEncargado.guardar(encargados);
    }
    
    /** Actualiza el calendario del periodo dado. */
    public void actualizarCalendarios(PeriodoPractica pPeriodo){
        
        for (int i = 0; i < practicantes.size(); i++) {
            if (practicantes.get(i).getPeriodo().getAgno() == pPeriodo.getAgno() && practicantes.get(i).getPeriodo().getNumeroSemestre() == pPeriodo.getNumeroSemestre()) {

                practicantes.get(i).setCalendario((CalendarioEvaluacion) pPeriodo.getCalendario().clone());
            }
        }
        this.daoPracticante.guardar(practicantes);
    }
    
    /** Guarda las evaluaciones del practicante dado. */
    public void guardarEvaluacionesPracticante(Practicante p){
        
        System.out.println(p.getCarnet());
        
        for(int i = 0; i < practicantes.size(); i++){
            
            if(practicantes.get(i).getCarnet().equals(p.getCarnet())){
                
                for(int e = 0; e < p.getCalendario().getEvaluaciones().size(); e++){
                    
                    for(int a = 0; a < p.getCalendario().getEvaluaciones().get(e).getRubros().size();a++){
                        practicantes.get(i).getCalendario().getEvaluaciones().get(e).getRubros().get(a).setNota(p.getCalendario().getEvaluaciones().get(e).getRubros().get(a).getNota());
                        System.out.println(p.getCalendario().getEvaluaciones().get(e).getRubros().get(a).getDetalle() + " " +p.getCalendario().getEvaluaciones().get(e).getRubros().get(a).getPorcentaje() + " : " + p.getCalendario().getEvaluaciones().get(e).getRubros().get(a).getNota());
                        
                    }
                    
                }
                
                practicantes.get(i).getCalendario().setEvaluaciones((ArrayList<Evaluacion>)p.getCalendario().getEvaluaciones().clone());
            }
            
        }
        daoPracticante.guardar(practicantes);
        
    }
    
    
    //Req 1
    /**
     * Registra un archivo entregable a un practicante
     * 
     * @param idPracticante al que se le registrara el archivo
     * @param pDescripcion del entregable
     * @param pArchivo a registrar
     * @return si se registro
     */
    public boolean registrarEntregable(String idPracticante, String pDescripcion, String pArchivo){
        
        Practicante p = buscarPracticante(idPracticante);
                
        for(int i = 0; i < p.getCalendario().getEvaluaciones().size(); i++){
            
            if(p.getCalendario().getEvaluaciones().get(i).getDescripcion().equals(pDescripcion)){
                p.getCalendario().getEvaluaciones().get(i).setArchivo(pArchivo);
                p.getCalendario().getEvaluaciones().get(i).setEntregado(true);
                
                Calendar c = Calendar.getInstance();
                int dia = c.get(Calendar.DATE);
                int mes = c.get(Calendar.MONTH);
                int annio = c.get(Calendar.YEAR);
                int hora = c.get(Calendar.HOUR);
                int minutos = c.get(Calendar.MINUTE);
                int segundos = c.get(Calendar.SECOND);
                p.getCalendario().getEvaluaciones().get(i).setFechaEntregaEntregable(new Fecha(dia,mes,annio,hora,minutos,segundos));
                
                daoPracticante.guardar(practicantes);
                
                return true;
            }
            
        }
        return false;
        
    }
    //Req 2
    /**
     * Retorna las evaluaciones de un practicante
     * 
     * @param pPracticante cuyas evaluaciones se requieren
     * @return las evaluaciones del practicante
     */
    public ArrayList<Evaluacion> consultarNotas(Practicante pPracticante){
        
        return pPracticante.getCalendario().getEvaluaciones();
        
    }
    //Req 3
    /**
     * Crea una nueva minuta.
     * 
     * @param pIdPracticante que hace la minuta
     * @param pMinuta la minuta
     * @param pReunion tema de reunion
     * @return si se creo
     */
    public boolean crearMinuta(String pIdPracticante, Minuta pMinuta, String pReunion){
        Practicante p = buscarPracticante(pIdPracticante);
        for(int i = 0; i < p.getReuniones().size(); i++){
            if(p.getReuniones().get(i).getTema().equals(pReunion)){
                p.getReuniones().get(i).getMinutas().add(pMinuta);
                daoPracticante.guardar(practicantes);
                return true;
            }
        }
        return false;
    }
    //Req 4
    /** Retorna el calendario de evaluacion de un periodo de practica. */
    public ArrayList<Evaluacion> consultarCalendario(PeriodoPractica pPeriodo){
        /*
        for(int i = 0; i < calendarios.size(); i++){
            if(calendarios.get(i).getPeriodo().getAgno() == pPeriodo.getAgno() && calendarios.get(i).getPeriodo().getNumeroSemestre() == pPeriodo.getNumeroSemestre()){
                
                return calendarios.get(i).getEvaluaciones();
                
            }
        }
        return null;*/
        return null;
        
    }
    
    //Req 5
    /** Asigna una nota a un practicante. */
    public boolean asignarNota(Practicante p){
        guardarEvaluacionesPracticante(p);
        daoPracticante.guardar(practicantes);
        return true;
    }
    /*
    //Req 6
    public ArrayList<Evaluacion> consultarEntregable(PeriodoPractica pPeriodo, Practicante pPracticante){
        
        ArrayList<Evaluacion> e = new ArrayList<Evaluacion>();
        
        for(int i = 0; i < evaluaciones.size(); i++){
            if(evaluaciones.get(i).getPeriodo().getAgno() == pPeriodo.getAgno() && evaluaciones.get(i).getPeriodo().getNumeroSemestre() == pPeriodo.getNumeroSemestre()){
                
                if(evaluaciones.get(i).getPracticante().getCarnet().equals(pPracticante.getCarnet())){
                    e.add(evaluaciones.get(i));
                }
            }
        }
        
        return e;
    }*/
    //Req 7
    /** Solicita una reunion a un estudiante. */
    public void solicitarReunionPracticante(String pIdPracticante, Reunion pReunion){
        System.out.println(pIdPracticante);
        Practicante p = buscarPracticante(pIdPracticante);
        p.getReuniones().add(pReunion);
        String sub = "Solicitud de Reunion";
        String msj = "El profesor " + buscarProfesor(p.getProfesorAsesor()) + " le ha solicitado una renion\n"
                + "Se ha solicitado una reunion con el practicante: " + p.getNombre();
        EmailSend.enviar(p.getEmail(),sub, msj);
        EmailSend.enviar(buscarProfesor(p.getProfesorAsesor()).getEmail(),sub, msj);
        daoPracticante.guardar(practicantes);
        /*
        Enviar al practicante y al profesor los siguientes mensajes:
        "El profesor " + p.getProfesorAsesor().getNombre() + " le ha solicitado una renion"
        "Se ha solicitado una reunion con el practicante: " + p.getNombre()
        */

    }/*
    //Req 8
    public ArrayList<Minuta> consultarMinutas(Practicante pPracticante){
        ArrayList<Minuta> m = new ArrayList<Minuta>();
        
        for(int i = 0; i < minutas.size(); i++){
            for(int e = 0; e < minutas.get(i).getListaParticipantes().size(); e++){
                if(minutas.get(i).getPracticante().getCarnet().equals(pPracticante.getCarnet())){
                    m.add(minutas.get(i));
                }
            }
        }
        return m;
    }*/
    //Req 9
    /** Registra un nuevo profesor. */
    public void registrarProfesor(String pNombre, String pTelefono, String pEmail, String pPassword, String pId){
        profesores.add(new Profesor(pNombre, pTelefono, pEmail, pPassword, pId));
        daoProfesor.guardar(profesores);
        //guardar los profesores en dao
    }
    //Req 10
    /** Registra una nueva empresa. */
    public void registrarEmpresa(String razonSocial, String cedulaJuridica, String direccion, String pSupervisor, String pTelefono){
        empresas.add(new Empresa(razonSocial, cedulaJuridica, direccion, pSupervisor, pTelefono));
        daoEmpresa.guardar(empresas);
//guardar empresas en dao
    }
    //Req 11
    /** Registra un nuevo periodo de práctica. */
    public void registrarPeriodoPractica(int pNumeroSemestre, int pAgno){
        periodos.add(new PeriodoPractica(pNumeroSemestre, pAgno));
        daoPeriodo.guardar(periodos);
        //guardar periodos en dao
    }
    //Req 12
    /** Registra un nuevo practicante. */
    public void registrarPracticante(String nombre, String carnet, String cedula, String telefono, String nacimiento, String direccion, String email, String password, PeriodoPractica periodo, String profesorAsesor, String profesorCurso, String empresa){
        practicantes.add(new Practicante(nombre, carnet, cedula, telefono, nacimiento, direccion, email, password, periodo, profesorAsesor, profesorCurso, empresa));
        daoPracticante.guardar(practicantes);
//guardar practicantes en dao
    }
    //Req 13
    /** Crea un nuevo calendario de evaluacion para el periodo dado. */
    public void crearCalendarioEvaluacion(PeriodoPractica pPeriodo, CalendarioEvaluacion pCalendario){
        for(int i = 0; i < periodos.size(); i++){
            if(periodos.get(i).getAgno() == pPeriodo.getAgno() && periodos.get(i).getNumeroSemestre() == pPeriodo.getNumeroSemestre()){
                periodos.get(i).setCalendario(pCalendario);
                //actualizarCalendarioEstudiantes(pPeriodo, pCalendario);
            }
        }
        daoPeriodo.guardar(periodos);
        daoPracticante.guardar(practicantes);
        //guardar evaluaciones en dao
    }/*
    //Req 14
    public ArrayList<String> consultarReporteFinalNotas(PeriodoPractica pPeriodo){
        
        ArrayList<String> notas = new ArrayList<String>();
        actualizarNotasFinales();
        
        for(int i = 0; i < practicantes.size(); i++){
            
            if(practicantes.get(i).getPeriodo().getNumeroSemestre() == pPeriodo.getNumeroSemestre() && practicantes.get(i).getPeriodo().getAgno() == pPeriodo.getAgno()){
                notas.add(practicantes.get(i).getNombre() + " " + practicantes.get(i).getCarnet() + " Nota final: " + practicantes.get(i).getNotaFinal());
            }
            
        }
        return notas;
    }
    //Req 15
    public Practicante consultarDatosPracticante(PeriodoPractica pPeriodo, String pCarnet){
        
        for(int i = 0; i < practicantes.size(); i++){
            
            if(practicantes.get(i).getPeriodo().getNumeroSemestre() == practicantes.get(i).getPeriodo().getNumeroSemestre() && practicantes.get(i).getPeriodo().getAgno() == practicantes.get(i).getPeriodo().getAgno() && practicantes.get(i).getCarnet() == pCarnet){
                return practicantes.get(i);
            }
            
        }
        return null;
    }
    
    private void actualizarNotasFinales(){
        
        for(int i = 0; i < practicantes.size(); i++){
            
            for(int e = 0; e < evaluaciones.size(); e++){
            
                if(practicantes.get(i).getCarnet().equals(evaluaciones.get(e).getPracticante().getCarnet())){
                    practicantes.get(i).setNotaFinal(practicantes.get(i).getNotaFinal() + ((evaluaciones.get(e).getNota() * 0.1) * evaluaciones.get(e).getValorPorcentual()));
                }
                
            }
            
        }
        
    }
    */
    
    public void setProfesores(ArrayList<Profesor> profesores) {
        this.profesores = profesores;
    }

    public void setEmpresas(ArrayList<Empresa> empresas) {
        this.empresas = empresas;
    }

    public void setPeriodos(ArrayList<PeriodoPractica> periodos) {
        this.periodos = periodos;
    }

    public void setPracticantes(ArrayList<Practicante> practicantes) {
        this.practicantes = practicantes;
    }

    public void setMinutas(ArrayList<Minuta> minutas) {
        this.minutas = minutas;
    }

    public void setCalendarios(ArrayList<CalendarioEvaluacion> calendarios) {
        this.calendarios = calendarios;
    }
    
    public void setEncargados(ArrayList<Encargado> encargados) {
        this.encargados = encargados;
    }
    
}
