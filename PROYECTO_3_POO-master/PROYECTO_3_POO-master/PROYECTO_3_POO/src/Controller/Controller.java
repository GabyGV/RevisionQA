/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.xml.EmpresaDAOxml;
import dao.xml.EncargadoDAOxml;
import dao.xml.PeriodoPracticaDAOxml;
import dao.xml.PracticanteDAOxml;
import dao.xml.ProfesorDAOxml;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import java.io.*;
import java.awt.Desktop;
import javax.swing.*;
import​ java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;
import model.CalendarioEvaluacion;
import model.Empresa;
import model.Evaluacion;
import model.Fecha;
import model.Minuta;
import model.PeriodoPractica;
import model.Reunion;
import model.Rubro;
import model.usuarios.Encargado;
import model.usuarios.Practicante;
import model.usuarios.Profesor;
import server.Servidor;
 import​ view.*;

/**
 * Parte controladora del modelo MVC.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class Controller implements ActionListener {

    public Servidor model;
    
    private static Socket s;

    public InicioSesion viewLogIn;
    public VentanaEncargado viewEncargado;
    public RegisPracticante viewRegistrarPracticante;
    public RegisProf viewRegistrarProfesor;
    public RegisEmpresa viewRegistrarEmpresa;
    public RegistrarPeriodo viewRegistrarPeriodo;
    public CrearCalendario viewCrearCalendario;
    public VentanaPracticante viewPracticante;
    public RegistrarEntregable viewRegistrarEntregable;
    public ConsultaNotas viewNotas;
    public CrearMinuta viewCrearMinuta;
    public VentanaProfesor viewProfesor;
    public ConsultarCalendario viewCalendario;
    public SolicitarReunion viewReunion;
    public VentanaAsignarNota viewAsignarNota;
    public ConsultarEntregable viewConsultarEntregable;
    public ConsultaMinutas viewConsultaMinuta;
    public ConsultarReporteFinal viewConsultarReporte;
    public ConsultarDatosPracticante viewConsultarDatos;


    private ArrayList<Rubro> rubrosTmp;
    private ArrayList<Evaluacion> evaluacionesTmp;
    private double totalRubros;
    private double totalEvaluaciones;
    private String archivoTemporal = "";
    
    private ArrayList<String> participantesMinutaTmp;
    private ArrayList<String> temasMinutaTmp;

    private Practicante usuarioPracticante;
    private Profesor usuarioProfesor;
    
    private String idTemporalPracticante;
    private String evaluacionTmp;
    
    private ArrayList<Minuta> minutasTmp;
    
    /**
     * Funcion principal para la ejecución del programa.
     * 
     * @param args usado solamente si se corre desde consola
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException{
		
        //s = new Socket("localhost", 2999);
        Controller c = new Controller();
        
        
    }

    
    /**
     * Construye los objetos de la clase.
     * 
     * @throws IOException 
     */
    public Controller() throws IOException {

        //s = new Socket("localhost", 4999);
        
        this.model = Servidor.getInstance();

        viewLogIn = new InicioSesion();
        viewEncargado = new VentanaEncargado();
        viewRegistrarPracticante = new RegisPracticante();
        viewRegistrarProfesor = new RegisProf();
        viewRegistrarEmpresa = new RegisEmpresa();
        viewRegistrarPeriodo = new RegistrarPeriodo();
        viewCrearCalendario = new CrearCalendario();
        viewPracticante = new VentanaPracticante();
        viewRegistrarEntregable = new RegistrarEntregable();
        viewNotas = new ConsultaNotas();
        viewCrearMinuta = new CrearMinuta();
        viewProfesor = new VentanaProfesor();
        viewCalendario = new ConsultarCalendario();
        viewReunion = new SolicitarReunion();
        viewAsignarNota = new VentanaAsignarNota();
        viewConsultarEntregable = new ConsultarEntregable();
        viewConsultaMinuta = new ConsultaMinutas();
        viewConsultarReporte = new ConsultarReporteFinal();
        viewConsultarDatos = new ConsultarDatosPracticante();
        
        viewLogIn.setVisible(true);
        viewLogIn.setLocationRelativeTo(null);


        this.viewLogIn.jButtonIniciarSesion.addActionListener(this);

        this.viewEncargado.jButtonCrearCalendario.addActionListener(this);
        this.viewEncargado.jButtonRegistrarEmpresa.addActionListener(this);
        this.viewEncargado.jButtonRegistrarPeriodo.addActionListener(this);
        this.viewEncargado.jButtonRegistrarPracticante.addActionListener(this);
        this.viewEncargado.jButtonRegistrarProfesor.addActionListener(this);
        this.viewEncargado.jButtonVEVolver.addActionListener(this);

        this.viewRegistrarPracticante.jButtonRPAceptar.addActionListener(this);
        this.viewRegistrarPracticante.jButtonRPVolver.addActionListener(this);

        this.viewRegistrarProfesor.jButtonAceptar.addActionListener(this);
        this.viewRegistrarProfesor.jButtonVolver.addActionListener(this);

        this.viewRegistrarEmpresa.jButtonAceptar.addActionListener(this);
        this.viewRegistrarEmpresa.jButtonCancelar.addActionListener(this);
        this.viewRegistrarEmpresa.jButtonRegistrarSuperivsor.addActionListener(this);

        this.viewRegistrarPeriodo.jButtonAceptar.addActionListener(this);
        this.viewRegistrarPeriodo.jButtonVolver.addActionListener(this);

        this.viewCrearCalendario.jButtonAceptarCrearCalendario.addActionListener(this);
        this.viewCrearCalendario.jButtonAceptarPeriodo.addActionListener(this);
        this.viewCrearCalendario.jButtonAgregarRubro.addActionListener(this);
        this.viewCrearCalendario.jButtonVolver.addActionListener(this);
        this.viewCrearCalendario.jButtonAgregarEvaluacion.addActionListener(this);

        this.viewPracticante.jButtonConsultarNotas.addActionListener(this);
        this.viewPracticante.jButtonCrearMinuta.addActionListener(this);
        this.viewPracticante.jButtonRegistrarEntregable.addActionListener(this);
        this.viewPracticante.jButtonVolver.addActionListener(this);

        this.viewRegistrarEntregable.jButtonAceptar.addActionListener(this);
        this.viewRegistrarEntregable.jButtonArchivo.addActionListener(this);
        this.viewRegistrarEntregable.jButtonVolver.addActionListener(this);
        
        this.viewNotas.jButtonVolver.addActionListener(this);
     
        this.viewCrearMinuta.jButtonAgregarFoto.addActionListener(this);
        this.viewCrearMinuta.jButtonAgregarParticipante.addActionListener(this);
        this.viewCrearMinuta.jButtonAgregarTema.addActionListener(this);
        this.viewCrearMinuta.jButtonCrear.addActionListener(this);
        this.viewCrearMinuta.jButtonVolver.addActionListener(this);
        
        this.viewProfesor.jButtonAsginarNotaEntregable.addActionListener(this);
        this.viewProfesor.jButtonConsultarCalendario.addActionListener(this);
        this.viewProfesor.jButtonConsultarDatosPracticante.addActionListener(this);
        this.viewProfesor.jButtonConsultarReporteFinal.addActionListener(this);
        this.viewProfesor.jButtonSolicitarReunion.addActionListener(this);
        this.viewProfesor.jButtonVolver.addActionListener(this);
        this.viewProfesor.jButtonConsultarEntregable.addActionListener(this);
        this.viewProfesor.jButtonConsultarMinutas.addActionListener(this);
        
        this.viewCalendario.jButtonBuscar.addActionListener(this);
        this.viewCalendario.jButtonVolver.addActionListener(this);
        this.viewCalendario.jTextFieldAnio.addActionListener(this);
        this.viewCalendario.jTextFieldPeriodo.addActionListener(this);
     
        this.viewReunion.jButtonEnviar.addActionListener(this);
        this.viewReunion.jButtonVolver.addActionListener(this);
        this.viewReunion.jButtonSeleccionarPeriodo.addActionListener(this);
        
        this.viewAsignarNota.jButtonAceptar.addActionListener(this);
        this.viewAsignarNota.jButtonVolver.addActionListener(this);
        this.viewAsignarNota.jButtonCalificarRubro.addActionListener(this);
        this.viewAsignarNota.jButtonBuscarEvaluaciones.addActionListener(this);
        this.viewAsignarNota.jButtonSeleccionarEvaluacion.addActionListener(this);
        
        this.viewConsultarEntregable.jButtonMostrar.addActionListener(this);
        this.viewConsultarEntregable.jButtonVolver.addActionListener(this);
        this.viewConsultarEntregable.jButtonSeleccionarPeriodo.addActionListener(this);
        this.viewConsultarEntregable.jButtonSeleccionarPracticante.addActionListener(this);
        
        this.viewConsultaMinuta.jButtonAgregarMinuta.addActionListener(this);
        this.viewConsultaMinuta.jButtonEnviar.addActionListener(this);
        this.viewConsultaMinuta.jButtonSeleccionarPracticante.addActionListener(this);
        this.viewConsultaMinuta.jButtonVolver.addActionListener(this);
        
        this.viewConsultarReporte.jButton1.addActionListener(this);
        this.viewConsultarReporte.jButton2.addActionListener(this);
        
        this.viewConsultarDatos.jButton1.addActionListener(this);
        this.viewConsultarDatos.jButton2.addActionListener(this);
        
        //registrarEncargadoRapido();
        
        //registroRapido();
        
        
        
        this.model.leerPracticantes();
        this.model.toStringProfesor();
        this.model.toStringPeriodoPractica();
        this.model.toStringEncargado();
        this.model.toStringEmpresa();

    }
   
    /**
     * Maneja el flujo de eventos. Llama a la función adecuada seguún las acciones del usuario.
     * 
     * @param e evento ocurrido
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "Iniciar sesion":
                iniciarSesion();
                break;

            case "Registrar Practicante":
                this.viewEncargado.setVisible(false);
                this.viewRegistrarPracticante.setVisible(true);
                actualizarComboBoxRegisPract();
                break;

            case "AceptarRegisPract":
                registrarPracticante();
                break;

            case "Registrar Profesor":
                this.viewEncargado.setVisible(false);
                this.viewRegistrarProfesor.setVisible(true);
                break;

            case "AceptarRegisProf":
                registrarProfesor();
                break;

            case "Registrar Empresa":
                this.viewEncargado.setVisible(false);
                this.viewRegistrarEmpresa.setVisible(true);
                actualizarComboBoxRegisEmpresa();
                break;

            case "AceptarRegisEmpresa":
                registrarEmpresa();
                break;

            case "Registrar Periodo de Practica":
                this.viewEncargado.setVisible(false);
                this.viewRegistrarPeriodo.setVisible(true);
                break;

            case "AceptarRegisPeriodo":
                registrarPeriodo();
                break;

            case "Crear Calendario de Evaluacion":
                this.viewEncargado.setVisible(false);
                this.viewCrearCalendario.setVisible(true);
                rubrosTmp = new ArrayList<Rubro>();
                totalRubros = 0;
                evaluacionesTmp = new ArrayList<Evaluacion>();
                totalEvaluaciones = 0;
                actualizarPeriodos();
                break;

            case "Aceptar periodo":
                seleccionarPeriodo();
                break;

            case "Agregar evaluacion":
                agregarEvaluacion();
                break;

            case "AgregarRubro":
                agregarRubro();
                break;

            case "AceptarCrearCalendario":
                agregarCalendario();
                break;

            case "Registrar un entregable":
                this.viewPracticante.setVisible(false);
                this.viewRegistrarEntregable.setVisible(true);
                mostrarEvaluaciones();
                break;
                
            case "Archivo":
                seleccionarArchivo();
                if(!archivoTemporal.isEmpty()){
                    this.viewRegistrarEntregable.jTextFieldArchivo.setText(archivoTemporal);
                }
                break;
            
            case "AceptarRegistrarEntregable":
                registrarEntregable();
                break;
                
            case "Consultar notas y calendario":
              this.viewPracticante.setVisible(false);
              this.viewNotas.setVisible(true);
              consultarNotas();
              break;
            
            case "Crear minuta de reunion":
                this.viewPracticante.setVisible(false);
                this.viewCrearMinuta.setVisible(true);
                participantesMinutaTmp = new ArrayList<String>();
                temasMinutaTmp = new ArrayList<String>();
                actualizarComboBoxMinuta();
                break;
                
            case "Agregar participante":
                agregarParticipante();
                break;
                
            case "Agregar tema":
                agregarTemas();
                break;
                
            case "Archivo fotografia":
                seleccionarArchivo();
                if(!archivoTemporal.isEmpty()){
                    this.viewCrearMinuta.jTextFieldFoto.setText(archivoTemporal);
                }
                break;
                
            case "Solicitar reunion con Practicante":
                this.viewProfesor.setVisible(false);
                this.viewReunion.setVisible(true);
                this.viewReunion.jLabelNombre.setText(usuarioProfesor.getNombre());
                actualizarComboBoxReunion();
                break;
                
            case "Seleccionar periodo Reunion":
                actualizarComboBoxReunionPracticantes();
                break;
                
            case "Enviar":
                solicitarReunion();
                break;
                
            case "CrearMinuta":
                crearMinuta();
                break;
                
            case "Asignar nota a entregable":
                this.viewProfesor.setVisible(false);
                this.viewAsignarNota.setVisible(true);
                actualizarComboBoxPracticantesAsignarNotas();
                break;
            case "Seleccionar practicante":
                actualizarComboBoxEvaluacionesAsignarNotas();
                break;
                
            case "Seleccionar evaluacion":
                actualizarComboBoxRubrosAsignarNotas();
                break;
                
            case "Calificar rubro":
                calificarRubro();
                break;
                
            case "AceptarAsignarNota":
                asignarNota();
                break;
            
            case "Consultar Calendario":
              this.viewProfesor.setVisible(false);
              this.viewCalendario.setVisible(true);
              break;
            
            case "Buscar":
              consultarCalendario();
              break;
          
            case "Consultar Entregable":
                this.viewProfesor.setVisible(false);
                this.viewConsultarEntregable.setVisible(true);
                actualizarComboBoxPeriodoEntregable();
                break;
                
            case "Seleccionar periodo Entregables":
                actualizarComboBoxPracticanteEntregable();
                break;
                
            case "Seleccionar practicante Entregables":
                actualizarComboBoxEntregables();
                break;
                
            case "MostrarEntregable":
                abrirEntregable();
                break;
                
            case "Consultar Minutas":
                this.viewProfesor.setVisible(false);
                this.viewConsultaMinuta.setVisible(true);
                minutasTmp = new ArrayList<Minuta>();
                actualizarComboBoxPracticantesMinutas();
                break;
                
            case "Seleccionar practicante Minuta":
                actualizarComboBoxMinutas();
                break;
                
            case "Agregar minuta Minuta":
                agregarMinutaEnviar();
                break;
                
            case "Enviar Minuta":
                try{
                    enviarMinutas();
                }catch(Exception a){
                    
                }
                
                break;
            
            case "Consultar Reporte Final":
              this.viewProfesor.setVisible(false);
              this.viewConsultarReporte.setVisible(true);
                actualizarReporteFinalNotas();
                break;
             
            case "Mostrar":
              mostrarNotasFinales();
              break;
                
            case "Consultar Datos de un Practicante":
              this.viewProfesor.setVisible(false);
              this.viewConsultarDatos.setVisible(true);
                actualizarDatosPracticante();
                break;
            
            case "Consultar":
              consultarDatosEstudiante();
              break;
            case "Volver":
              if (this.viewPracticante.isVisible()) {
                this.viewPracticante.setVisible(false);
                this.viewLogIn.setVisible(true);
              } else if (this.viewRegistrarEntregable.isVisible()) {
                this.viewRegistrarEntregable.setVisible(false);
                this.viewPracticante.setVisible(true);
              } else if (this.viewNotas.isVisible()) {
                this.viewNotas.setVisible(false);
                this.viewPracticante.setVisible(true);
              } else if (this.viewCrearMinuta.isVisible()) {
                this.viewCrearMinuta.setVisible(false);
                this.viewPracticante.setVisible(true);
              } else if (this.viewProfesor.isVisible()) {
                this.viewProfesor.setVisible(false);
                this.viewLogIn.setVisible(true);
              } else if (this.viewCalendario.isVisible()) {
                this.viewCalendario.setVisible(false);
                this.viewProfesor.setVisible(true);
              } else if (this.viewConsultarEntregable.isVisible()) {
                this.viewConsultarEntregable.setVisible(false);
                this.viewProfesor.setVisible(true);
              } else if (this.viewConsultarReporte.isVisible()) {
                this.viewConsultarReporte.setVisible(false);
                this.viewProfesor.setVisible(true);
              } else if (this.viewConsultarDatos.isVisible()) {
                this.viewConsultarDatos.setVisible(false);
                this.viewProfesor.setVisible(true);
              } else if (this.viewAsignarNota.isVisible()) {
                this.viewAsignarNota.setVisible(false);
                this.viewProfesor.setVisible(true);
              } else if (this.viewReunion.isVisible()) {
                this.viewReunion.setVisible(false);
                this.viewProfesor.setVisible(true);
              } else if (this.viewConsultaMinuta.isVisible()) {
                this.viewConsultaMinuta.setVisible(false);
                this.viewProfesor.setVisible(true);
              } else if (this.viewEncargado.isVisible()) {
                this.viewEncargado.setVisible(false);
                this.viewLogIn.setVisible(true);
              } else if (this.viewRegistrarProfesor.isVisible()) {
                this.viewRegistrarProfesor.setVisible(false);
                this.viewEncargado.setVisible(true);
              } else if (this.viewRegistrarPeriodo.isVisible()) {
                this.viewRegistrarPeriodo.setVisible(false);
                this.viewEncargado.setVisible(true);
              } else if (this.viewCrearCalendario.isVisible()) {
                this.viewCrearCalendario.setVisible(false);
                this.viewEncargado.setVisible(true);
              } else if (this.viewRegistrarEmpresa.isVisible()) {
                this.viewRegistrarEmpresa.setVisible(false);
                this.viewEncargado.setVisible(true);
              } else if (this.viewRegistrarPracticante.isVisible()) {
                this.viewRegistrarPracticante.setVisible(false);
                this.viewEncargado.setVisible(true);
              }
              break;
        }
    }
    
    /**
     * Envia un PDF con las minutas de un practicante al profesor que las solicita.
     * 
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws Exception 
     */
    private void enviarMinutas() throws FileNotFoundException, DocumentException, Exception{
        
        String emailDestino = this.viewConsultaMinuta.jTextFieldEmail.getText();
        
        //Enviar minutasTmp por correo
        Document document = new Document();
        String nombre = "Minutas.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(nombre));
        document.open();
        for(int i = 0; i != minutasTmp.size();i++){
            document.add(new Paragraph("Titulo " + minutasTmp.get(i).getTitulo() + "\n"
            + "Fecha Inicio " + minutasTmp.get(i).getFechaInicio().getDia() + "/" + minutasTmp.get(i).getFechaInicio().getMes() + "/" + minutasTmp.get(i).getFechaInicio().getAgno() + "    Hora: " + minutasTmp.get(i).getFechaInicio().getHora() + "/" + minutasTmp.get(i).getFechaInicio().getMinutos() + "/" + minutasTmp.get(i).getFechaInicio().getSegundos() + "\n" 
            + "Fecha Finalizacion " + minutasTmp.get(i).getFechaInicio().getDia() + "/" + minutasTmp.get(i).getFechaInicio().getMes() + "/" + minutasTmp.get(i).getFechaInicio().getAgno() + "    Hora: " + minutasTmp.get(i).getFechaFinal().getHora() + "/" + minutasTmp.get(i).getFechaFinal().getMinutos() + "/" + minutasTmp.get(i).getFechaFinal().getSegundos() + "\n"
            + "Lugar " + minutasTmp.get(i).getLugar() + "\n"));
            String partici = "Participantes:\n";
            for(int j = 0; j != minutasTmp.get(i).getListaParticipantes().size(); j++){
                partici = partici + minutasTmp.get(i).getListaParticipantes().get(j) + "\n";
            }
            document.add(new Paragraph(partici));
            String temas = "Temas:\n";
            for(int j = 0; j != minutasTmp.get(i).getTemas().size(); j++){
                temas = temas + minutasTmp.get(i).getTemas().get(j) + "\n";
            }
            document.add(new Paragraph(temas));
            Image image = Image.getInstance(minutasTmp.get(i).getArchivo());
            document.add(image);
        }
        document.close();
        server.EmailSend.enviarArchivo(emailDestino, "Minutas del Practicante", "Minutas.pdf", "Minutas.pdf");
        
    }
    
    /** Agrega una minuta seleccionada a la lista de minutas a enviar. */
    private void agregarMinutaEnviar(){
        
        Practicante p = this.model.buscarPracticante(this.viewConsultaMinuta.jComboBoxPracticante.getSelectedItem().toString().substring(0,10));
        
        for(int i = 0; i < p.getReuniones().size(); i++){
            for(int e = 0; e < p.getReuniones().get(i).getMinutas().size();e++){
                
                if(p.getReuniones().get(i).getMinutas().get(e).getTitulo().equals(this.viewConsultaMinuta.jComboBoxMinutas.getSelectedItem().toString())){
                    minutasTmp.add(p.getReuniones().get(i).getMinutas().get(e));
                    this.viewConsultaMinuta.jTextArea.append(p.getReuniones().get(i).getMinutas().get(e).getTitulo() + "\n");
                    this.viewConsultaMinuta.jComboBoxMinutas.removeItemAt(this.viewConsultaMinuta.jComboBoxMinutas.getSelectedIndex());
                    JOptionPane.showMessageDialog(null, "Se ha agregado la minuta correctamente");
                }
            }
        }
    }
    
    /** Actualiza el JComboBox correspondiente con las minutas disponibles. */
    private void actualizarComboBoxMinutas(){
        
        this.viewConsultaMinuta.jComboBoxMinutas.removeAllItems();
        
        Practicante p = this.model.buscarPracticante(this.viewConsultaMinuta.jComboBoxPracticante.getSelectedItem().toString().substring(0,10));
        
        for(int i = 0; i < p.getReuniones().size(); i++){
            for(int e = 0; e < p.getReuniones().get(i).getMinutas().size();e++){
                this.viewConsultaMinuta.jComboBoxMinutas.addItem(p.getReuniones().get(i).getMinutas().get(e).getTitulo());
            }
        }
    }
    
    /** Actualiza el JComboBox correspondiente con los practicantes disponibles. */
    private void actualizarComboBoxPracticantesMinutas(){
        
        this.viewConsultaMinuta.jComboBoxPracticante.removeAllItems();
        
        for(int i = 0; i < this.model.getPracticantes().size();i++){
            if(this.model.getPracticantes().get(i).getProfesorAsesor().equals(usuarioProfesor.getId())){
                this.viewConsultaMinuta.jComboBoxPracticante.addItem(this.model.getPracticantes().get(i).getCarnet() + " " + this.model.getPracticantes().get(i).getNombre());
            }   
        }
    }
    
    /** Abre un archivo entregable para ser visto por el profesor. */
    private void abrirEntregable(){
        
        Practicante p = this.model.buscarPracticante(this.viewConsultarEntregable.jComboBoxPracticante.getSelectedItem().toString().substring(0,10));
        
        for(int i = 0; i < p.getCalendario().getEvaluaciones().size(); i++){
            
            if(p.getCalendario().getEvaluaciones().get(i).getDescripcion().equals(this.viewConsultarEntregable.jComboBoxEntregable.getSelectedItem().toString())){
                
               archivoTemporal = p.getCalendario().getEvaluaciones().get(i).getArchivo(); 
            }
            
        }
        
        try {

            File objetofile = new File (archivoTemporal);
            Desktop.getDesktop().open(objetofile);

        }catch (IOException ex) {

               System.out.println(ex);

        }
        
    }
    
    /** Actualiza el JComboBox correspondiente con los entregables disponibles. */
    private void actualizarComboBoxEntregables(){
        
        this.viewConsultarEntregable.jComboBoxEntregable.removeAllItems();
        
        Practicante p = this.model.buscarPracticante(this.viewConsultarEntregable.jComboBoxPracticante.getSelectedItem().toString().substring(0,10));

        for(int i = 0; i < p.getCalendario().getEvaluaciones().size(); i++){
            if(p.getCalendario().getEvaluaciones().get(i).isEntregado()){
                this.viewConsultarEntregable.jComboBoxEntregable.addItem(p.getCalendario().getEvaluaciones().get(i).getDescripcion());
            }
        }
        
    }
    
    /** Actualiza el JComboBox correspondiente con los practicantes disponibles. */
    private void actualizarComboBoxPracticanteEntregable(){
        
        this.viewConsultarEntregable.jComboBoxPracticante.removeAllItems();
        
        int semestre = Integer.valueOf(this.viewConsultarEntregable.jComboBoxPeriodo.getSelectedItem().toString().substring(0,1));
        int agno = Integer.valueOf(this.viewConsultarEntregable.jComboBoxPeriodo.getSelectedItem().toString().substring(2,6));
        
        for(int i = 0; i < this.model.getPracticantes().size();i++){
            if(this.model.getPracticantes().get(i).getPeriodo().getAgno() == agno && this.model.getPracticantes().get(i).getPeriodo().getNumeroSemestre() == semestre){
                if(this.model.getPracticantes().get(i).getProfesorAsesor().equals(usuarioProfesor.getId())){
                    this.viewConsultarEntregable.jComboBoxPracticante.addItem(this.model.getPracticantes().get(i).getCarnet() + " " + this.model.getPracticantes().get(i).getNombre());
                }
            }
        }
    }
    
    /** Actualiza el JComboBox correspondiente con los periodos disponibles. */
    private void actualizarComboBoxPeriodoEntregable(){
        
        this.viewConsultarEntregable.jComboBoxPeriodo.removeAllItems();
        
        for(int i = 0; i < this.model.getPeriodos().size();i++){
           this.viewConsultarEntregable.jComboBoxPeriodo.addItem(this.model.getPeriodos().get(i).getNumeroSemestre() + " " + this.model.getPeriodos().get(i).getAgno());
        }
        
    }
    
    /** Asigna nota a una evaluación. */
    private void asignarNota(){
        
        boolean finRubros = false;
        
        this.viewAsignarNota.jComboBoxRubros.addItem("a");
        
        if(this.viewAsignarNota.jComboBoxRubros.getItemAt(0).equals("a")){
            finRubros = true;
            this.viewAsignarNota.jComboBoxRubros.removeItemAt(0);
        }
        
        if(finRubros){
            
            Practicante p = this.model.buscarPracticante(idTemporalPracticante);
            
            for(int i = 0; i < p.getCalendario().getEvaluaciones().size(); i++){
                if(p.getCalendario().getEvaluaciones().get(i).getDescripcion().equals(evaluacionTmp)){
                    p.getCalendario().getEvaluaciones().get(i).setNota(Double.valueOf(this.viewAsignarNota.jTextFieldNotaProfesor.getText()) + Double.valueOf(this.viewAsignarNota.jTextFieldNotaEncargado.getText()));
                    p.getCalendario().getEvaluaciones().get(i).setComentarios(this.viewAsignarNota.jTextAreaComentarios.getText());
                    this.model.asignarNota(p);
                    //this.daoPracticante.guardar(this.model.getPracticantes());
                    JOptionPane.showMessageDialog(null, "Se ha registrado correctamente la nota");
                    
                    this.viewAsignarNota.setVisible(false);
                    this.viewProfesor.setVisible(true);
                    
                    this.viewAsignarNota = new VentanaAsignarNota();
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Aun no ha calificado todos los rubros");
        }
    }
    
    /** Califica un rubro de una evaluacion. */
    private void calificarRubro(){
        
        if(this.viewAsignarNota.jComboBoxRubros.getSelectedItem().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun rubro");
        }else{
            
            if(!this.viewAsignarNota.jTextFieldNota.getText().isEmpty()){
                
                Practicante p = this.model.buscarPracticante(idTemporalPracticante);
            
                String s = "";

                int contador = 0;
                while(this.viewAsignarNota.jComboBoxRubros.getSelectedItem().toString().charAt(contador) != '|'){
                    s += this.viewAsignarNota.jComboBoxRubros.getSelectedItem().toString().charAt(contador);
                    contador++;
                }

                int nota = Integer.valueOf(this.viewAsignarNota.jTextFieldNota.getText());

                for(int i = 0; i < p.getCalendario().getEvaluaciones().size(); i++){

                    if(p.getCalendario().getEvaluaciones().get(i).getDescripcion().equals(evaluacionTmp)){

                        for(int e = 0; e < p.getCalendario().getEvaluaciones().get(i).getRubros().size(); e++){

                            if(p.getCalendario().getEvaluaciones().get(i).getRubros().get(e).getDetalle().equals(s)){
                                
                                if(p.getCalendario().getEvaluaciones().get(i).getRubros().get(e).getPorcentaje() < nota){
                                    JOptionPane.showMessageDialog(null, "El valor que ingreso es mayor al valor del rubro.");
                                }else{
                                    p.getCalendario().getEvaluaciones().get(i).getRubros().get(e).setNota(nota);
                                    this.viewAsignarNota.jComboBoxRubros.removeItemAt(this.viewAsignarNota.jComboBoxRubros.getSelectedIndex());
                                    JOptionPane.showMessageDialog(null, "Ha calificado correctamente el rubro");
                                }
                            }
                        }
                    }
                }
                
                this.model.guardarEvaluacionesPracticante(p);
                
            }else{
                JOptionPane.showMessageDialog(null, "No ha ingresado una nota");
            }
        }
    }
    
    /** Actualiza el JComboBox correspondiente con los rubros de la evaluación disponibles. */
    private void actualizarComboBoxRubrosAsignarNotas(){
        
        Practicante p = this.model.buscarPracticante(idTemporalPracticante);
        this.viewAsignarNota.jComboBoxRubros.removeAllItems();
        
        String s = "";
        int contador = 0;
        while(this.viewAsignarNota.jComboBoxEvaluaciones.getSelectedItem().toString().charAt(contador) != '|'){
            s += this.viewAsignarNota.jComboBoxEvaluaciones.getSelectedItem().toString().charAt(contador);
            contador++;
        }
        
        for(int i = 0; i < p.getCalendario().getEvaluaciones().size(); i++){
            if(p.getCalendario().getEvaluaciones().get(i).getDescripcion().equals(s)){
                evaluacionTmp = p.getCalendario().getEvaluaciones().get(i).getDescripcion();
                for(int e = 0; e < p.getCalendario().getEvaluaciones().get(i).getRubros().size(); e++){
                    this.viewAsignarNota.jComboBoxRubros.addItem(p.getCalendario().getEvaluaciones().get(i).getRubros().get(e).getDetalle() + "| " + p.getCalendario().getEvaluaciones().get(i).getRubros().get(e).getPorcentaje() + "%");
                }
            }
        }
    }
    
    /** Actualiza el JComboBox correspondiente con las evaluaciónes disponibles. */
    private void actualizarComboBoxEvaluacionesAsignarNotas(){
        
        this.viewAsignarNota.jComboBoxEvaluaciones.removeAllItems();
        
        Practicante p = this.model.buscarPracticante(this.viewAsignarNota.jComboBoxPracticante.getSelectedItem().toString().substring(0,10));
        idTemporalPracticante = p.getCarnet();
        for(int i = 0; i < p.getCalendario().getEvaluaciones().size(); i++){
            this.viewAsignarNota.jComboBoxEvaluaciones.addItem(p.getCalendario().getEvaluaciones().get(i).getDescripcion() + "| Fecha entrega: " + p.getCalendario().getEvaluaciones().get(i).getFechaEntrega().toString());
        }
        
    }
    
    /** Actualiza el JComboBox correspondiente con los practicantes disponibles. */
    private void actualizarComboBoxPracticantesAsignarNotas(){
        this.viewAsignarNota.jComboBoxPracticante.removeAllItems();
        for(int i = 0; i < this.model.getPracticantes().size();i++){
            if(this.model.getPracticantes().get(i).getProfesorAsesor().equals(usuarioProfesor.getId())){
                this.viewAsignarNota.jComboBoxPracticante.addItem(this.model.getPracticantes().get(i).getCarnet() + " " + this.model.getPracticantes().get(i).getNombre());
            }
        }
    }
    
    /** Actualiza el JComboBox correspondiente con las reuniones del practicante. */
    private void actualizarComboBoxMinuta(){
        
        this.viewCrearMinuta.jComboBoxReunion.removeAllItems();
        
        for(int i = 0; i < usuarioPracticante.getReuniones().size(); i++){
            this.viewCrearMinuta.jComboBoxReunion.addItem(usuarioPracticante.getReuniones().get(i).getTema());
        }
        
    }
    
    /** Crea una nueva reunión. */
    private void solicitarReunion(){
        
        int dia = Integer.valueOf(this.viewReunion.jTextFieldDia.getText());
        int mes = Integer.valueOf(this.viewReunion.jTextFieldMes.getText());
        int agno = Integer.valueOf(this.viewReunion.jTextFieldAgno.getText());
        int hora = Integer.valueOf(this.viewReunion.jTextFieldHora.getText());
        int minutos = Integer.valueOf(this.viewReunion.jTextFieldMinuto.getText());
        int segundos = Integer.valueOf(this.viewReunion.jTextFieldSegundo.getText());
        
        Fecha f = new Fecha(dia,mes,agno,hora,minutos,segundos);
        
        String tema = this.viewReunion.jTextFieldTema.getText();
        String lugar = this.viewReunion.jTextFieldLugar.getText();
        String agenda = this.viewReunion.jTextAreaAgenda.getText();
        
        PeriodoPractica pTmp = this.model.buscarPeriodo(Integer.valueOf(this.viewReunion.jComboBoxPeriodo.getSelectedItem().toString().substring(0,1)), Integer.valueOf(this.viewReunion.jComboBoxPeriodo.getSelectedItem().toString().substring(2,6)));
        Reunion r = new Reunion(f, tema, agenda, lugar);
        
        this.model.solicitarReunionPracticante(this.viewReunion.jComboBoxPracticante.getSelectedItem().toString().substring(0,10), r);
        //this.model.leerPracticantes();
        //this.daoPracticante.guardar(this.model.getPracticantes());
        JOptionPane.showMessageDialog(null, "Se ha registrado la reunion satisfactoriamente");
        this.viewReunion.setVisible(false);
        this.viewReunion = new SolicitarReunion();
        this.viewProfesor.setVisible(true);
        
    }
    
    /** Actualiza el JComboBox correspondiente con los periodos de práctica disponibles. */
    private void actualizarComboBoxReunion(){
        
        this.viewReunion.jComboBoxPeriodo.removeAllItems();
        
        
        for(int i = 0; i < this.model.getPeriodos().size(); i++){
            this.viewReunion.jComboBoxPeriodo.addItem(this.model.getPeriodos().get(i).getNumeroSemestre() + " " + this.model.getPeriodos().get(i).getAgno());
        }
        
        
    }    
    
    /** Actualiza el JComboBox correspondiente con los practicantes disponibles. */
    private void actualizarComboBoxReunionPracticantes(){
        
        this.viewReunion.jComboBoxPracticante.removeAllItems();
        
        for(int i = 0; i < this.model.getPracticantes().size(); i++){
            if(this.model.getPracticantes().get(i).getProfesorAsesor().equals(usuarioProfesor.getId())){
                if(this.model.getPracticantes().get(i).getPeriodo().getNumeroSemestre() == Integer.valueOf(this.viewReunion.jComboBoxPeriodo.getSelectedItem().toString().substring(0,1)) && this.model.getPracticantes().get(i).getPeriodo().getAgno() == Integer.valueOf(this.viewReunion.jComboBoxPeriodo.getSelectedItem().toString().substring(2,6))){
                    
                    this.viewReunion.jComboBoxPracticante.addItem(this.model.getPracticantes().get(i).getCarnet() + " " + this.model.getPracticantes().get(i).getNombre());
                    
                }
                
                
            }
        }
        
    }
    
    /** Crea una nueva minuta. */
    private void crearMinuta(){
        
        String titulo = this.viewCrearMinuta.jTextFieldTitulo.getText();
        String lugar = this.viewCrearMinuta.jTextFieldLugar.getText();
        
        int dia = Integer.valueOf(this.viewCrearMinuta.jTextFieldDia.getText());
        int mes = Integer.valueOf(this.viewCrearMinuta.jTextFieldMes.getText());
        int agno = Integer.valueOf(this.viewCrearMinuta.jTextFieldAgno.getText());
        
        int horaInicio = Integer.valueOf(this.viewCrearMinuta.jTextFieldHoraInicio.getText());
        int minutosInicio = Integer.valueOf(this.viewCrearMinuta.jTextFieldMinutoInicio.getText());
        int segundosInicio = Integer.valueOf(this.viewCrearMinuta.jTextFieldSegundoInicio.getText());
        
        Fecha f1 = new Fecha(dia,mes,agno,horaInicio, minutosInicio, segundosInicio);
        
        int horaFinal = Integer.valueOf(this.viewCrearMinuta.jTextFieldHoraFinal.getText());
        int minutosFinal = Integer.valueOf(this.viewCrearMinuta.jTextFieldMinutoFinal.getText());
        int segundosFinal = Integer.valueOf(this.viewCrearMinuta.jTextFieldSegundoFinal.getText());
        
        Fecha f2 = new Fecha(dia,mes,agno,horaFinal, minutosFinal, segundosFinal);
        
        Minuta m = new Minuta(titulo, participantesMinutaTmp, f1, f2, temasMinutaTmp, lugar, this.viewCrearMinuta.jTextFieldFoto.getText());
        
        if(this.model.crearMinuta(usuarioPracticante.getCarnet(), m, this.viewCrearMinuta.jComboBoxReunion.getSelectedItem().toString())){
            //this.daoPracticante.guardar(this.model.getPracticantes());
            JOptionPane.showMessageDialog(null, "Se ha agregado la minuta correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        }
        this.viewCrearMinuta.setVisible(false);
        this.viewPracticante.setVisible(true);
        this.viewCrearMinuta = new CrearMinuta();
    }
    
    /** Agrega temas a una minuta. */
    private void agregarTemas(){
        
        if(!this.viewCrearMinuta.jTextFieldTema.getText().isEmpty()){
            temasMinutaTmp.add(this.viewCrearMinuta.jTextFieldTema.getText());
            this.viewCrearMinuta.jTextFieldTema.setText("");
            JOptionPane.showMessageDialog(null, "Se ha agregado correcamente el tema");
        }else{
            JOptionPane.showMessageDialog(null, "El campo de temas se encuentra vacio.");
        }
        
    }
    
    /** Agrega un participante a una minuta. */
    private void agregarParticipante(){
        if(!this.viewCrearMinuta.jTextFieldParticipante.getText().isEmpty()){
            participantesMinutaTmp.add(this.viewCrearMinuta.jTextFieldParticipante.getText());
            this.viewCrearMinuta.jTextFieldParticipante.setText("");
            JOptionPane.showMessageDialog(null, "Se ha agregado correcamente el participante");
        }else{
            JOptionPane.showMessageDialog(null, "El campo de participantes se encuentra vacio.");
        }
    }

    /** Carga un entregable para una evaluación del practicante. */
    private void registrarEntregable(){
        
        if(archivoTemporal.isEmpty()){
            JOptionPane.showMessageDialog(null, "No ha cargado el archivo.");
        }else{
            
            String s = "";
            
            int contador = 0;
            
            while(this.viewRegistrarEntregable.jComboBoxEvaluaciones.getSelectedItem().toString().charAt(contador) != '|'){
                
                s += this.viewRegistrarEntregable.jComboBoxEvaluaciones.getSelectedItem().toString().charAt(contador);
                contador++;
                
            }
            
            this.model.registrarEntregable(usuarioPracticante.getCarnet(), s, archivoTemporal);
            JOptionPane.showMessageDialog(null, "Se ha registrado el entregable");
            usuarioPracticante = this.model.buscarPracticante(usuarioPracticante.getCarnet());
            //this.daoPracticante.guardar(this.model.getPracticantes());
            this.viewRegistrarEntregable.setVisible(false);
            this.viewPracticante.setVisible(true);
            
            this.viewRegistrarEntregable = new RegistrarEntregable();
        }
        
    }
    
    /** Selecciona un archivo. */
    private void seleccionarArchivo(){
        
        try{
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            int returnValue = jfc.showOpenDialog(null);
            // int returnValue = jfc.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    archivoTemporal = selectedFile.getAbsolutePath();
                    
            }
        }catch(Exception e){
            
        }
    }

    /** Actualiza el JComboBox correspondiente con las evaluaciones disponibles. */
    private void mostrarEvaluaciones() {
        
        String s;
        
        this.viewRegistrarEntregable.jComboBoxEvaluaciones.removeAllItems();
        
        if(!usuarioPracticante.getCalendario().getEvaluaciones().isEmpty()) {
            for (int i = 0; i < usuarioPracticante.getCalendario().getEvaluaciones().size(); i++) {
                
                s = usuarioPracticante.getCalendario().getEvaluaciones().get(i).getDescripcion() + "| " + usuarioPracticante.getCalendario().getEvaluaciones().get(i).getValorPorcentual() + "%" + "  Entregado:" + usuarioPracticante.getCalendario().getEvaluaciones().get(i).isEntregado() + " Fecha de entrega limite: " +usuarioPracticante.getCalendario().getEvaluaciones().get(i).getFechaEntrega().toString() + "\n\n";
                this.viewRegistrarEntregable.jComboBoxEvaluaciones.addItem(s);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El periodo del practicante aun no tiene un calendario de evaluacion");
            this.viewRegistrarEntregable.setVisible(false);
            this.viewPracticante.setVisible(true);
        }

    }

    /** Actualiza el calendario de evaluacion para el periodo seleccionado. */
    private void actualizarCalendariosEstudiantes(PeriodoPractica pPeriodo) {
        this.model.actualizarCalendarios(pPeriodo);
    }

    /** Agrega un calendario de evaluación. */
    private void agregarCalendario() {

        int semestre = Integer.valueOf(this.viewCrearCalendario.jComboBoxPeriodo.getSelectedItem().toString().substring(0, 1));
        int agno = Integer.valueOf(this.viewCrearCalendario.jComboBoxPeriodo.getSelectedItem().toString().substring(2, 6));

        PeriodoPractica tmp = this.model.buscarPeriodo(semestre, agno);

        this.model.crearCalendarioEvaluacion(tmp, new CalendarioEvaluacion(evaluacionesTmp));

        actualizarCalendariosEstudiantes(tmp);

        //this.daoPeriodo.guardar(this.model.getPeriodos());

        JOptionPane.showMessageDialog(null, "Se ha creado el calendario satisfactoriamente");
        this.viewCrearCalendario.setVisible(false);
        this.viewCrearCalendario = new CrearCalendario();
        this.viewEncargado.setVisible(true);

    }

    /** Agrega una evaluación al calendario. */
    private void agregarEvaluacion() {

        if (totalEvaluaciones == 100) {
            JOptionPane.showMessageDialog(null, "El calendario ya alcanzo el 100% del valor y esta completo.");
            this.viewCrearCalendario.setVisible(false);
            this.viewEncargado.setVisible(true);
        } else {

            if (totalRubros < 100) {
                JOptionPane.showMessageDialog(null, "Los rubros aun no alcanzan el 100% del valor, por favor agregar lo faltante");
            } else {

                String descripcion = this.viewCrearCalendario.jTextFieldDescripcionEvaluacion.getText();

                int dia = Integer.valueOf(this.viewCrearCalendario.jTextFieldDia.getText());
                int mes = Integer.valueOf(this.viewCrearCalendario.jTextFieldMes.getText());
                int agno = Integer.valueOf(this.viewCrearCalendario.jTextFieldAgno.getText());
                int hora = Integer.valueOf(this.viewCrearCalendario.jTextFieldHora.getText());
                int minutos = Integer.valueOf(this.viewCrearCalendario.jTextFieldMinutos.getText());
                int segundos = Integer.valueOf(this.viewCrearCalendario.jTextFieldSegundos.getText());

                double valorPorcentual = Double.valueOf(this.viewCrearCalendario.jTextFieldValorPorcentual.getText());
                String profesor = this.viewCrearCalendario.jComboBoxProfesores.getSelectedItem().toString();

                Fecha fechaEntrega = new Fecha(dia, mes, agno, hora, minutos, segundos);
                evaluacionesTmp.add(new Evaluacion(descripcion, fechaEntrega, valorPorcentual, profesor, rubrosTmp));
                totalEvaluaciones += valorPorcentual;

                this.viewCrearCalendario.jLabelTotalEvaluaciones.setText(String.valueOf(evaluacionesTmp.size()));
                this.viewCrearCalendario.jLabelTotalPorcentual.setText(String.valueOf(totalEvaluaciones));

                if (totalEvaluaciones == 100) {
                    JOptionPane.showMessageDialog(null, "El total del porcentaje de todas las evalaciones sumados ya alcanzaron el 100% ya se puede guardar el calendario");
                }

                rubrosTmp = new ArrayList<Rubro>();
                totalRubros = 0;
                
                this.viewCrearCalendario.jTextArea.setText("");
                this.viewCrearCalendario.jTextFieldDescripcionEvaluacion.setText("");
                this.viewCrearCalendario.jTextFieldDescripcionRubro.setText("");
                this.viewCrearCalendario.jTextFieldValorRubro.setText("");
                this.viewCrearCalendario.jTextFieldValorPorcentual.setText("");
                this.viewCrearCalendario.jTextFieldDia.setText("");
                this.viewCrearCalendario.jTextFieldMes.setText("");
                this.viewCrearCalendario.jTextFieldAgno.setText("");
                this.viewCrearCalendario.jTextFieldHora.setText("");
                this.viewCrearCalendario.jTextFieldMinutos.setText("");
                this.viewCrearCalendario.jTextFieldSegundos.setText("");
            }
        }
    }

    /** Agrega un rubro a una evaluación. */
    private void agregarRubro() {

        if (totalRubros == 100) {
            JOptionPane.showMessageDialog(null, "Ya alcanzo el 100% del porcentaje, no puede agregar mas rubros.");
        } else {
            String descripcion = this.viewCrearCalendario.jTextFieldDescripcionRubro.getText();
            double valorPorcentual = Double.valueOf(this.viewCrearCalendario.jTextFieldValorRubro.getText());

            if ((totalRubros + valorPorcentual) > 100) {
                JOptionPane.showMessageDialog(null, "El valor del rubro hace que el valor de la evaluacion sobrepase el 100% por favor bajar el valor del rubro.");
            } else {
                totalRubros += valorPorcentual;

                if (totalRubros == 100) {
                    JOptionPane.showMessageDialog(null, "El valor de los rubros ha alcanzado el 100%, proceda a agregar la evaluacion.");
                }

                this.viewCrearCalendario.jLabelTotalRubros.setText(String.valueOf(totalRubros));

                this.viewCrearCalendario.jTextArea.append(descripcion + " " + valorPorcentual + "%\n");
                rubrosTmp.add(new Rubro(descripcion, valorPorcentual));
            }
        }
    }

    /** Actualiza el JComboBox correspondiente con los periodos disponibles. */
    private void actualizarPeriodos() {
        this.viewCrearCalendario.jComboBoxPeriodo.removeAllItems();
        for (int i = 0; i < this.model.getPeriodos().size(); i++) {
            this.viewCrearCalendario.jComboBoxPeriodo.addItem(this.model.getPeriodos().get(i).getNumeroSemestre() + " " + this.model.getPeriodos().get(i).getAgno());
        }
    }
    
    /** Selecciona el periodo al cual agregar el calendario de evaluación. */
    private void seleccionarPeriodo() {

        int semestre = Integer.valueOf(this.viewCrearCalendario.jComboBoxPeriodo.getSelectedItem().toString().substring(0, 1));
        int agno = Integer.valueOf(this.viewCrearCalendario.jComboBoxPeriodo.getSelectedItem().toString().substring(2, 6));

        PeriodoPractica tmp = this.model.buscarPeriodo(semestre, agno);

        if (!tmp.getCalendario().getEvaluaciones().isEmpty()) {

            JOptionPane.showMessageDialog(null, "El calendario de este periodo ya está completo");

        } else {
            JOptionPane.showMessageDialog(null, "El periodo aun no tiene calendario de evaluacion, puede comenzar a agregar evaluaciones.");
            this.viewCrearCalendario.jLabelTotalEvaluaciones.setText("0");
            this.viewCrearCalendario.jLabelTotalPorcentual.setText("0");
        }
    }

    /** Agrega un periodo de práctica. */
    private void registrarPeriodo() {

        int numeroSemestre = Integer.valueOf(this.viewRegistrarPeriodo.jComboBoxSemestre.getSelectedItem().toString());
        int agno = Integer.valueOf(this.viewRegistrarPeriodo.jTextFieldAgno.getText());

        if (this.model.buscarPeriodo(numeroSemestre, agno) != null) {

            JOptionPane.showMessageDialog(null, "El periodo ya existe.");
        } else {
            this.model.registrarPeriodoPractica(numeroSemestre, agno);
            //this.daoPeriodo.guardar(this.model.getPeriodos());
            this.viewRegistrarPeriodo.setVisible(false);
            this.viewEncargado.setVisible(true);
            JOptionPane.showMessageDialog(null, "El periodo se ha registrado correctamente");

            this.viewRegistrarPeriodo.jTextFieldAgno.setText("");
        }
    }

    /** Actualiza el JComboBox correspondiente con las empresas disponibles. */
    private void actualizarComboBoxRegisEmpresa() {

        this.viewRegistrarEmpresa.jComboBoxSupervisor.removeAllItems();

        for (int i = 0; i < this.model.getEncargados().size(); i++) {
            this.viewRegistrarEmpresa.jComboBoxSupervisor.addItem(this.model.getEncargados().get(i).getId() + " " + this.model.getEncargados().get(i).getNombre());
        }

    }

    /** Registra una nueva empresa. */
    private void registrarEmpresa() {

        String razonSocial = this.viewRegistrarEmpresa.jTextFieldRazonSocial.getText();
        String cedulaJuridica = this.viewRegistrarEmpresa.jTextFieldCedulaJuridica.getText();
        String telefono = this.viewRegistrarEmpresa.jTextFieldTelefono.getText();
        String direccion = this.viewRegistrarEmpresa.jTextFieldDireccion.getText();
        String supervisor = this.viewRegistrarEmpresa.jComboBoxSupervisor.getSelectedItem().toString().substring(0, 5);

        if (this.model.buscarEmpresa(cedulaJuridica) != null) {
            JOptionPane.showMessageDialog(null, "La empresa ya existe.");
        } else {
            this.model.registrarEmpresa(razonSocial, cedulaJuridica, direccion, supervisor, telefono);
            //this.daoEmpresa.guardar(this.model.getEmpresas());
            this.viewRegistrarEmpresa.setVisible(false);
            this.viewEncargado.setVisible(true);
            JOptionPane.showMessageDialog(null, "La empresa se ha registrado correctamente");
            
            this.viewRegistrarEmpresa = new RegisEmpresa();
        }
    }

    /** Registra un nuevo profesor. */
    private void registrarProfesor() {

        String nombre = this.viewRegistrarProfesor.jTextFieldNombre.getText();
        String telefono = this.viewRegistrarProfesor.jTextFieldTelefono.getText();
        String email = this.viewRegistrarProfesor.jTextFieldEmail.getText();
        String password = this.viewRegistrarProfesor.jTextFieldPassword.getText();
        String id = this.viewRegistrarProfesor.jTextFieldId.getText();

        if (this.model.buscarProfesor(id) != null) {
            JOptionPane.showMessageDialog(null, "El profesor ya existe.");
        } else {
            this.model.registrarProfesor(nombre, telefono, email, password, id);
            //this.daoProfesor.guardar(this.model.getProfesores());
            this.viewRegistrarProfesor.setVisible(false);
            this.viewEncargado.setVisible(true);
            JOptionPane.showMessageDialog(null, "El profesor se ha registrado correctamente");
            
            this.viewRegistrarProfesor = new RegisProf();
        }

    }

    /** 
     * Actualiza los JComboBox correspondientes con los datos disponibles para el ingreso de un
     * nuevo practicante.
     */
    private void actualizarComboBoxRegisPract() {

        this.viewRegistrarPracticante.jComboBoxProfesorAsesor.removeAllItems();
        this.viewRegistrarPracticante.jComboBoxProfesorCurso.removeAllItems();
        this.viewRegistrarPracticante.jComboBoxEmpresas.removeAllItems();
        this.viewRegistrarPracticante.jComboBoxSemestre.removeAllItems();
        for (int i = 0; i < this.model.getProfesores().size(); i++) {
            this.viewRegistrarPracticante.jComboBoxProfesorAsesor.addItem(this.model.getProfesores().get(i).getId() + " " + this.model.getProfesores().get(i).getNombre());
            this.viewRegistrarPracticante.jComboBoxProfesorCurso.addItem(this.model.getProfesores().get(i).getId() + " " + this.model.getProfesores().get(i).getNombre());
        }
        for (int i = 0; i < this.model.getEmpresas().size(); i++) {
            this.viewRegistrarPracticante.jComboBoxEmpresas.addItem(this.model.getEmpresas().get(i).getCedulaJuridica() + " " + this.model.getEmpresas().get(i).getRazonSocial());
        }
        for (int i = 0; i < this.model.getPeriodos().size(); i++) {
            this.viewRegistrarPracticante.jComboBoxSemestre.addItem(this.model.getPeriodos().get(i).getNumeroSemestre() + " " + this.model.getPeriodos().get(i).getAgno());
        }
    }

    /** Registra un nuevo practicante. */
    public void registrarPracticante() {

        String nombre = this.viewRegistrarPracticante.jTextFieldNombre.getText();
        String carnet = this.viewRegistrarPracticante.jTextFieldCarnet.getText();
        String cedula = this.viewRegistrarPracticante.jTextFieldCedula.getText();
        String telefono = this.viewRegistrarPracticante.jTextFieldTelefono.getText();
        String nacimiento = this.viewRegistrarPracticante.jTextFieldNacimiento.getText();
        String direccion = this.viewRegistrarPracticante.jTextFieldDireccion.getText();
        String email = this.viewRegistrarPracticante.jTextFieldEmail.getText();
        String password = this.viewRegistrarPracticante.jTextFieldContrasena.getText();

        PeriodoPractica periodo = this.model.buscarPeriodo(Integer.valueOf(this.viewRegistrarPracticante.jComboBoxSemestre.getSelectedItem().toString().substring(0, 1)), Integer.valueOf(this.viewRegistrarPracticante.jComboBoxSemestre.getSelectedItem().toString().substring(2, 6)));
        
        String profesorAsesor = this.viewRegistrarPracticante.jComboBoxProfesorAsesor.getSelectedItem().toString().substring(0, 3);
        String profesorCurso = this.viewRegistrarPracticante.jComboBoxProfesorCurso.getSelectedItem().toString().substring(0, 3);
        String empresa = this.viewRegistrarPracticante.jComboBoxEmpresas.getSelectedItem().toString().substring(0, 5);

        if (this.model.buscarPracticante(carnet) != null) {
            JOptionPane.showMessageDialog(null, "El practicante ya existe.");
        } else {

            this.model.registrarPracticante(nombre, carnet, cedula, telefono, nacimiento, direccion, email, password, periodo, profesorAsesor, profesorCurso, empresa);
            //this.daoPracticante.guardar(this.model.getPracticantes());

            if (periodo.getCalendario() != null) {

                this.model.buscarPracticante(carnet).setCalendario((CalendarioEvaluacion) periodo.getCalendario().clone());
                //HACER COPIA DE CALENDARIO
                //this.daoPracticante.guardar(this.model.getPracticantes());
                this.model.daoPracticante.guardar(this.model.getPracticantes());
            }

            this.viewRegistrarPracticante.setVisible(false);
            this.viewEncargado.setVisible(true);
            JOptionPane.showMessageDialog(null, "El practicante se ha registrado correctamente");

            
            this.viewRegistrarPracticante = new RegisPracticante();
        }
    }

    /** Inicia sesion en el sistema según el tipo de usuario seleccionado. */
    public void iniciarSesion() {

        String rol = viewLogIn.jComboBoxRol.getSelectedItem().toString();
        String usuario = viewLogIn.jTextFieldUser.getText();
        String password = String.valueOf(viewLogIn.jPasswordField.getPassword());
        boolean encontrado = false;

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Algun campo se encuentra vacio");
        }

        if (rol.equals("Encargado")) {

            ArrayList<Encargado> encargados = this.model.getEncargados();

            for (int i = 0; i < encargados.size(); i++) {
                if (encargados.get(i).getEmail().equals(usuario) && encargados.get(i).getPassword().equals(password)) {
                    encontrado = true;
                    JOptionPane.showMessageDialog(null, "Sesion iniciada correctamente");
                    this.viewLogIn.setVisible(false);
                    this.viewEncargado.setVisible(true);
                    this.viewEncargado.jLabelNombre.setText(encargados.get(i).getNombre());
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Los datos ingresados son incorrectos");
            }
        }
        if (rol.equals("Practicante")) {

            ArrayList<Practicante> practicantes = this.model.getPracticantes();

            for (int i = 0; i < practicantes.size(); i++) {
                if (practicantes.get(i).getEmail().equals(usuario) && practicantes.get(i).getPassword().equals(password)) {
                    encontrado = true;
                    JOptionPane.showMessageDialog(null, "Sesion iniciada correctamente");
                    usuarioPracticante = practicantes.get(i);
                    this.viewLogIn.setVisible(false);
                    this.viewPracticante.setVisible(true);
                    this.viewPracticante.jLabelNombre.setText(practicantes.get(i).getNombre());
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Los datos ingresados son incorrectos");
            }
        }
        
        if (rol.equals("Profesor")) {

            ArrayList<Profesor> profesores = this.model.getProfesores();

            for (int i = 0; i < profesores.size(); i++) {
                if (profesores.get(i).getEmail().equals(usuario) && profesores.get(i).getPassword().equals(password)) {
                    encontrado = true;
                    JOptionPane.showMessageDialog(null, "Sesion iniciada correctamente");
                    usuarioProfesor = profesores.get(i);
                    this.viewLogIn.setVisible(false);
                    this.viewProfesor.setVisible(true);
                    this.viewProfesor.jLabelNombre.setText(profesores.get(i).getNombre());
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Los datos ingresados son incorrectos");
            }
        }
    }
/*
    private void guardarProfesores() {
        this.model.setProfesores(daoProfesor.obtenerTodos());
    }

    private void guardarEmpresas() {
        this.model.setEmpresas(daoEmpresa.obtenerTodos());
    }

    private void guardarPracticantes() {
        this.model.setPracticantes(daoPracticante.obtenerTodos());
    }

    private void guardarEncargados() {
        this.model.setEncargados(daoEncargado.obtenerTodos());
    }

    private void guardarPeriodos() {
        this.model.setPeriodos(daoPeriodo.obtenerTodos());
    }

    private void cargarElServidor() {
        guardarProfesores();
        guardarEmpresas();
        guardarPracticantes();
        guardarEncargados();
        guardarPeriodos();
    }*/
    
    private void registrarEncargadoRapido(){
        this.model.registrarEncargado("Adrian", "Gerente", "8839315", "adrian", "123123", "araya");
        
    }
    
    private void registroRapido(){
        
        this.model.registrarPeriodoPractica(1, 2018);
        ///this.daoPeriodo.guardar(this.model.getPeriodos());
        
        this.model.registrarPeriodoPractica(2, 2018);
        //this.daoPeriodo.guardar(this.model.getPeriodos());
        
        this.model.registrarPeriodoPractica(1, 2019);
        //this.daoPeriodo.guardar(this.model.getPeriodos());
        
        this.model.registrarEmpresa("Intel", "123456", "San Jose", "123123", "27733931");
        //this.daoEmpresa.guardar(this.model.getEmpresas());
        
        this.model.registrarEmpresa("Mac", "234567", "Cartago", "123123", "22114411");
        //this.daoEmpresa.guardar(this.model.getEmpresas());
        
        this.model.registrarProfesor("Luis", "88776655", "adrian.araya.420@gmail.com", "luis", "1111");
        //this.daoProfesor.guardar(this.model.getProfesores());
        
        this.model.registrarProfesor("Carlos", "88112233", "carlos@gmail.com", "carlos", "2222");
        //this.daoProfesor.guardar(this.model.getProfesores());
        
        this.model.registrarProfesor("Diego", "88111111", "diego@gmail.com", "diego", "3333");
        //this.daoProfesor.guardar(this.model.getProfesores());
        
        this.model.registrarProfesor("Masis", "88000000", "Masis@gmail.com", "masis", "4444");
        //this.daoProfesor.guardar(this.model.getProfesores());
        
        this.model.registrarPracticante("adrian", "2018319701", "604530340", "88393511", "21/06/1999", "San Vito", "clettersb@gmail.com", "helado", this.model.buscarPeriodo(1, 2019), "1111", "2222", "123456");
        //this.daoPracticante.guardar(this.model.getPracticantes());
        
        this.model.registrarPracticante("dylan", "2018123123", "644411111", "88122112", "05/06/1999", "Cartago", "dylan@gmail.com", "dylan", this.model.buscarPeriodo(1, 2018), "2222", "1111", "234567");
        //this.daoPracticante.guardar(this.model.getPracticantes());
        
        this.model.registrarPracticante("roberto", "2018456456", "612341234", "88334411", "09/06/1999", "San Jose", "roberto@gmail.com", "roberto", this.model.buscarPeriodo(2, 2018), "3333", "2222", "123456");
        //this.daoPracticante.guardar(this.model.getPracticantes());
    }

    /** Muestra la informacion de todas las evaluaciones del practicante. */
    public void consultarNotas() {
        usuarioPracticante = this.model.buscarPracticante(usuarioPracticante.getCarnet());
      if (usuarioPracticante.getCalendario().getEvaluaciones() == null) {
        JOptionPane.showMessageDialog(null, "No hay calendario asignado");
        this.viewNotas.setVisible(false);
        this.viewPracticante.setVisible(true);
      } else {
        String notas = "Descripción\tFecha de Entrega\tValor\tResponsable\t\tEstado\tNota\n";
        for (Evaluacion evaluacion:usuarioPracticante.getCalendario().getEvaluaciones()) {
          notas += evaluacion.getDescripcion() + "\t";
          notas += evaluacion.getFechaEntrega().toString() + "\t";
          notas += "" + evaluacion.getValorPorcentual() + "\t";
          notas += evaluacion.getIdResponsable() + "\t";
          if (evaluacion.isEntregado()) {
            notas += "entregado\t";
          } else {
            notas += "no entregado\t";
          }
          notas += "" + evaluacion.getNota() + "\n";
          notas += "Rubros:\nDetalle\tPorcentaje\tNota\n";
          for (Rubro rubro:evaluacion.getRubros()) {
            notas += rubro.getDetalle() + "\t";
            notas += "" + rubro.getPorcentaje() + "\t";
            notas += "" + rubro.getNota() + "\n\n";
          }
        }
        this.viewNotas.jTextAreaNotas.append(notas);
      }
    }
 
    /** Muestra la informacion del calendario de evaluación del periodo seleccionado. */
    public void consultarCalendario() {
      String agno = viewCalendario.jTextFieldAnio.getText();
      String periodo = viewCalendario.jTextFieldPeriodo.getText();
      if (periodo.isEmpty() || agno.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Indique un periodo y aÃ±o para consultar");
      } else {
        Integer agnoInt = Integer.parseInt(agno);
        Integer periodoInt = Integer.parseInt(periodo);
        if ((periodoInt < 1 || periodoInt > 2) || (agnoInt <= 0)) {
          JOptionPane.showMessageDialog(null, "Periodo o aÃ±o invÃ¡lido");
        } else {
          PeriodoPractica periodoAConsultar = this.model.buscarPeriodo(periodoInt, agnoInt);
          if (periodoAConsultar == null) {
            JOptionPane.showMessageDialog(null, "No hay informacion de ese periodo");
          } else {
            CalendarioEvaluacion calendarioACOnsultar = periodoAConsultar.getCalendario();
            if (calendarioACOnsultar == null) {
              JOptionPane.showMessageDialog(null, "No hay calendarios para ese periodo");
            } else {
              String calendario = "";
              for (Evaluacion evaluacion:calendarioACOnsultar.getEvaluaciones()) {
                calendario += "DescripciÃ³n\tFecha de Entrega\tValor\tResponsable\t\tEstado\tNota\n";
                calendario += evaluacion.getDescripcion() + "\t";
                calendario += evaluacion.getFechaEntrega().toString() + "\t";
                calendario += "" + evaluacion.getValorPorcentual() + "\t";
                calendario += evaluacion.getIdResponsable() + "\t";
                if (evaluacion.isEntregado()) {
                  calendario += "entregado\t";
                } else {
                  calendario += "no entregado\t";
                }
                calendario += "" + evaluacion.getNota() + "\n";
                calendario += "Rubros:\nDetalle\tPorcentaje\tNota\n";
                for (Rubro rubro:evaluacion.getRubros()) {
                  calendario += rubro.getDetalle() + "\t";
                  calendario += "" + rubro.getPorcentaje() + "\t";
                  calendario += "" + rubro.getNota() + "\n\n";
                }
              }
              this.viewCalendario.jTextAreaCalendario.append(calendario);
            }
          }
        }
      }
    }
    
    /** Actualiza el JComboBox correspondiente con los periodos de práctica disponibles. */
    public void actualizarReporteFinalNotas(){
        for (int i = 0; i < this.model.getPeriodos().size(); i++) {
            this.viewConsultarReporte.jComboBoxPeriodo.addItem(this.model.getPeriodos().get(i).getNumeroSemestre() + " " + this.model.getPeriodos().get(i).getAgno());
        }
    }
    
    /** Muestra las notas finales de un practicante. */
    public void mostrarNotasFinales() {
      String periodo = this.viewConsultarReporte.jComboBoxPeriodo.getSelectedItem().toString();
      String[] periodoElementos = periodo.split(" ");
      ArrayList<Practicante> listaPracticantes = this.model.getPracticantes();
      if (listaPracticantes == null) {
        JOptionPane.showMessageDialog(null, "No hay estudiantes en ese periodo");
      } else {
        String reporte = "Nombre\t\tNota Final\n";
        for (Practicante practicante:listaPracticantes) {
          if (practicante.getPeriodo().getNumeroSemestre() == Integer.parseInt(periodoElementos[0])
              && practicante.getPeriodo().getAgno() == Integer.parseInt(periodoElementos[1])) {
            reporte += practicante.getNombre() + "\t";
            reporte += practicante.getNotaFinal() + "\n\n";
          }
        }
        this.viewConsultarReporte.jTextAreaNotasFinales.append(reporte);
      }
    }
    
    /** Actualiza el JComboBox correspondiente con los periodos de práctica disponibles. */
    public void actualizarDatosPracticante(){
        for (int i = 0; i < this.model.getPeriodos().size(); i++) {
            this.viewConsultarDatos.jComboBoxPeriodo.addItem(this.model.getPeriodos().get(i).getNumeroSemestre() + " " + this.model.getPeriodos().get(i).getAgno());
        }
    }
    
    /** Muestra la informacion del practicante, profesor asesor y empresa. */
    public void consultarDatosEstudiante() {
      String periodo = this.viewConsultarDatos.jComboBoxPeriodo.getSelectedItem().toString();
      String[] periodoElementos = periodo.split(" ");
      String identificacion = this.viewConsultarDatos.jTextFieldIdentificacion.getText();
      Practicante practicante = this.model.buscarPracticante(identificacion);
      if (practicante == null) {
        JOptionPane.showMessageDialog(null, "Carnet no valido");
      } else {
        if (practicante.getPeriodo().getNumeroSemestre() != Integer.parseInt(periodoElementos[0]) || practicante.getPeriodo().getAgno() != Integer.parseInt(periodoElementos[1])) {
          JOptionPane.showMessageDialog(null, "El estudiante no esta en ese periodo");
        } else {
          String datos = "Periodo: " + periodoElementos[0] + " " + periodoElementos[1] + "\n";
          datos += "Nombre: " + practicante.getNombre() + "\n";
          datos += "Identificacion: " + practicante.getCedula() + "\n";
          datos += "Carnet: " + practicante.getCarnet() + "\n";
          datos += "Telefono: " + practicante.getTelefono() + "\n";
          datos += "Fecha de nacimiento: " + practicante.getNacimiento() + "\n";
          datos += "Direccion: " + practicante.getDireccion() + "\n";
          datos += "E-mail: " + practicante.getEmail() + "\n";
          datos += "Nota Final: " + practicante.getNotaFinal() + "\n\n";
          Profesor profesor = this.model.buscarProfesor(practicante.getProfesorAsesor());
          datos += "Profesor asesor: " + profesor.getNombre() + "\n";
          datos += "\tTelefono: " + profesor.getTelefono() + "\n";
          datos += "\tE-mail: " + profesor.getEmail() + "\nEmpresa\n";
          Empresa empresa = this.model.buscarEmpresa(practicante.getEmpresa());
          datos += "Razon social: " + empresa.getRazonSocial() + "\n";
          datos += "Cedula juridica: " + empresa.getCedulaJuridica() + "\n";
          datos += "Telefono: " + empresa.getTelefono() + "\n";
          datos += "Direccion: " + empresa.getDireccion() + "\n";
          System.out.println(empresa.getEncargado());
          Encargado encargado = this.model.buscarEncargado(empresa.getEncargado());
          datos += "Encargado: " + encargado.getNombre() + "\n";
          datos += "Puesto:" + encargado.getPuesto() + "\n";
          datos += "Telefono: " + encargado.getTelefono() + "\n";
          datos += "E-mail: " + encargado.getEmail() + "\n";
          this.viewConsultarDatos.jTextAreaConsulta.append(datos);
        }
      }
    }
}
