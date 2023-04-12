package persistencia.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.CalendarioEvaluacion;
import model.Evaluacion;
import model.Fecha;
import model.Minuta;
import model.usuarios.Practicante;
import model.PeriodoPractica;
import model.Reunion;
import model.Rubro;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Persistencia de datos por medio de XML para Practicante.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class ControlPracticanteXML {
    
    /** Retorna una lista con los practicantes dentro del archivo. */
    public static ArrayList<Practicante> leerPracticantesXML() {
        
        ArrayList<Practicante> listaFinal = new ArrayList<Practicante>();
        
        try {
            File archivo = new File("Practicantes.xml");
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            
            document.getDocumentElement().normalize();
            
            System.out.println("Elemento raiz: " + document.getDocumentElement().getNodeName());
            
            NodeList listaPracticantes = document.getElementsByTagName("PRACTICANTE");
            

            
            for(int i = 0 ; i < listaPracticantes.getLength() ; i++) {
                Node nodo = listaPracticantes.item(i);
                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    String nombre = element.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    String carnet = element.getElementsByTagName("CARNET").item(0).getTextContent();
                    String cedula = element.getElementsByTagName("CEDULA").item(0).getTextContent();
                    String telefono = element.getElementsByTagName("TELEFONO").item(0).getTextContent();
                    String nacimiento = element.getElementsByTagName("NACIMIENTO").item(0).getTextContent();
                    String direccion = element.getElementsByTagName("DIRECCION").item(0).getTextContent();
                    String email = element.getElementsByTagName("EMAIL").item(0).getTextContent();
                    String password = element.getElementsByTagName("PASSWORD").item(0).getTextContent();
                    String notaFinal = element.getElementsByTagName("NOTA_FINAL").item(0).getTextContent();
                    String profesorAsesor = element.getElementsByTagName("PROFESOR_ASESOR").item(0).getTextContent();
                    String profesorCurso = element.getElementsByTagName("PROFESOR_CURSO").item(0).getTextContent();
                    String empresa = element.getElementsByTagName("EMPRESA").item(0).getTextContent();
                    

                    
                    PeriodoPractica periodo = new PeriodoPractica(Integer.valueOf(element.getElementsByTagName("PERIODO").item(0).getChildNodes().item(0).getTextContent()) , Integer.valueOf(element.getElementsByTagName("PERIODO").item(0).getChildNodes().item(1).getTextContent()));
                    
                    ArrayList<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();
                    
                    for(int a = 0; a < element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().getLength(); a++){
                        
                        String descripcion = element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(0).getTextContent();
                        String descripcionEntregable = element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(1).getTextContent();
                        String archivoP = element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(2).getTextContent();
                        String comentarios = element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(3).getTextContent();
                        String valorPorcentual = element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(4).getTextContent();
                        String responsable = element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(5).getTextContent();
                        String entregado = element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(6).getTextContent();
                        String nota = element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(7).getTextContent();
                        
                        int fechaEntregaDia = Integer.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(8).getChildNodes().item(0).getTextContent());
                        int fechaEntregaMes = Integer.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(8).getChildNodes().item(1).getTextContent());
                        int fechaEntregaAgno = Integer.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(8).getChildNodes().item(2).getTextContent());
                        int fechaEntregaHora = Integer.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(8).getChildNodes().item(3).getTextContent());
                        int fechaEntregaMin = Integer.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(8).getChildNodes().item(4).getTextContent());
                        int fechaEntregaSec = Integer.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(8).getChildNodes().item(5).getTextContent());
                        
                        Fecha fechaEntrega = new Fecha(fechaEntregaDia,fechaEntregaMes,fechaEntregaAgno,fechaEntregaHora,fechaEntregaMin,fechaEntregaSec);
                        
                        int fechaEntregableDia = Integer.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(9).getChildNodes().item(0).getTextContent());
                        int fechaEntregableMes = Integer.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(9).getChildNodes().item(1).getTextContent());
                        int fechaEntregableAgno = Integer.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(9).getChildNodes().item(2).getTextContent());
                        int fechaEntregableHora = Integer.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(9).getChildNodes().item(3).getTextContent());
                        int fechaEntregableMin = Integer.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(9).getChildNodes().item(4).getTextContent());
                        int fechaEntregableSec = Integer.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(9).getChildNodes().item(5).getTextContent());
      
                        Fecha fechaEntregable = new Fecha(fechaEntregableDia,fechaEntregableMes,fechaEntregableAgno,fechaEntregableHora,fechaEntregableMin,fechaEntregableSec);
                        
                        ArrayList<Rubro> rubrosTmp = new ArrayList<Rubro>();
                        
                        for(int y = 0; y < element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(10).getChildNodes().getLength(); y++){
                            
                            Rubro r = new Rubro(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(10).getChildNodes().item(y).getChildNodes().item(0).getTextContent(), Double.valueOf(element.getElementsByTagName("CALENDARIO_EVALUACION").item(0).getChildNodes().item(0).getChildNodes().item(a).getChildNodes().item(10).getChildNodes().item(y).getChildNodes().item(1).getTextContent()));
                            rubrosTmp.add(r);
                            
                        }
                        
                        Evaluacion v = new Evaluacion(descripcion, fechaEntrega, Double.valueOf(valorPorcentual), responsable, rubrosTmp);
                        
                        v.setDescripcionDelEntregable(descripcionEntregable);
                        v.setArchivo(archivoP);
                        v.setComentarios(comentarios);
                        v.setEntregado(Boolean.valueOf(entregado));
                        v.setNota(Double.valueOf(nota));
                        v.setFechaEntregaEntregable(fechaEntregable);
                        
                        evaluaciones.add(v);
                        
                    }
                    
                    CalendarioEvaluacion calendario = new CalendarioEvaluacion(evaluaciones);
                    
                    //reuniones
                    
                    Practicante practicante = new Practicante(nombre, carnet, cedula, telefono, nacimiento, direccion, email, password, periodo, profesorAsesor, profesorCurso, empresa);
                    practicante.setCalendario(calendario);
                    practicante.setNotaFinal(Double.valueOf(notaFinal));
                    
                    ArrayList<Reunion> reunionesTmp = new ArrayList<Reunion>();
                    
                    for(int q = 0; q < element.getElementsByTagName("REUNIONES").item(0).getChildNodes().getLength(); q++){
                        
                        int dia = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(0).getChildNodes().item(0).getTextContent());
                        int mes = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(0).getChildNodes().item(1).getTextContent());
                        int agno = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(0).getChildNodes().item(2).getTextContent());
                        int hora = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(0).getChildNodes().item(3).getTextContent());
                        int min = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(0).getChildNodes().item(4).getTextContent());
                        int seg = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(0).getChildNodes().item(5).getTextContent());
                        
                        Fecha fechaReunion = new Fecha(dia,mes,agno,hora,min,seg);
                        
                        String tema = element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(1).getTextContent();
                        String lugar = element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(2).getTextContent();
                        String agenda = element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(3).getTextContent();
                        //MINUTAS
                        
                        ArrayList<Minuta> minutasTmp = new ArrayList<Minuta>();
                        
                        for(int g = 0; g < element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().getLength(); g++){
 
                            String tituloMinuta = element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(0).getTextContent();
                            ArrayList<String> participantesTmp = new ArrayList<String>();
                            
                            for(int u = 0; u < element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(1).getChildNodes().getLength(); u++){
                                
                                participantesTmp.add(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(1).getChildNodes().item(u).getTextContent());   
                            }
                            
                            int diaInicio = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(2).getChildNodes().item(0).getTextContent());
                            int mesInicio = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(2).getChildNodes().item(1).getTextContent());
                            int agnoInicio = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(2).getChildNodes().item(2).getTextContent());
                            int horaInicio = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(2).getChildNodes().item(3).getTextContent());
                            int minutoInicio = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(2).getChildNodes().item(4).getTextContent());
                            int segundoInicio = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(2).getChildNodes().item(5).getTextContent());

                            Fecha fechaInicio = new Fecha(diaInicio,mesInicio,agnoInicio,horaInicio,minutoInicio,segundoInicio);

                            int diaFinal = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(3).getChildNodes().item(0).getTextContent());
                            int mesFinal = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(3).getChildNodes().item(1).getTextContent());
                            int agnoFinal = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(3).getChildNodes().item(2).getTextContent());
                            int horaFinal = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(3).getChildNodes().item(3).getTextContent());
                            int minutoFinal = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(3).getChildNodes().item(4).getTextContent());
                            int segundoFinal = Integer.valueOf(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(3).getChildNodes().item(5).getTextContent());

                            Fecha fechaFinal = new Fecha(diaFinal,mesFinal,agnoFinal,horaFinal,minutoFinal,segundoFinal);

                            ArrayList<String> temasTmp = new ArrayList<String>();
                            
                            for(int u = 0; u < element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(4).getChildNodes().getLength(); u++){
                                
                                temasTmp.add(element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(4).getChildNodes().item(u).getTextContent());   
                            }

                            String lugarMinuta = element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(5).getTextContent();
                            String ArchivoMinuta = element.getElementsByTagName("REUNIONES").item(0).getChildNodes().item(q).getChildNodes().item(4).getChildNodes().item(g).getChildNodes().item(6).getTextContent();

                            Minuta minutaTmp = new Minuta(tituloMinuta, participantesTmp, fechaInicio, fechaFinal, temasTmp, lugarMinuta, ArchivoMinuta);
                            
                            minutasTmp.add(minutaTmp);
                            
                            
                        }
                        
                        Reunion r = new Reunion(fechaReunion,tema,lugar,agenda);
                        r.setMinutas(minutasTmp);
                        
                        reunionesTmp.add(r);
                    }
                    practicante.setReuniones(reunionesTmp);
                    listaFinal.add(practicante);
                }
            }
            
        } catch(Exception e) {
            return listaFinal;
        }
        return listaFinal;
    }
    
    /** Crea el archivo con los datos de los practicantes suministrados. */
    public static boolean crearXML(List<Practicante> listaPracticantes) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        String nomArchivo = "Practicantes";
        
        
        
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, nomArchivo, null);
            document.setXmlVersion("1.0");
            
            // NODO RAIZ
            Element raiz = document.getDocumentElement();
            
            for(int i = 0 ; i <listaPracticantes.size() ; i++) {
                
                Element itemNode = document.createElement("PRACTICANTE");
                
                //ATRIBUTOS NORMALES
                
                Element nombreNode = document.createElement("NOMBRE");
                Text nodeIdValue =document.createTextNode(listaPracticantes.get(i).getNombre());
                nombreNode.appendChild(nodeIdValue);
                itemNode.appendChild(nombreNode);
                
                Element carnetNode = document.createElement("CARNET");
                Text nodeCarnetValue =document.createTextNode(listaPracticantes.get(i).getCarnet());
                carnetNode.appendChild(nodeCarnetValue);
                itemNode.appendChild(carnetNode);
                
                Element cedulaNode = document.createElement("CEDULA");
                Text nodeCedulaValue =document.createTextNode(listaPracticantes.get(i).getCedula());
                cedulaNode.appendChild(nodeCedulaValue);
                itemNode.appendChild(cedulaNode);
                
                Element telefonoNode = document.createElement("TELEFONO");
                Text nodeTelefonoValue =document.createTextNode(listaPracticantes.get(i).getTelefono());
                telefonoNode.appendChild(nodeTelefonoValue);
                itemNode.appendChild(telefonoNode);

                Element nacimientoNode = document.createElement("NACIMIENTO");
                Text nodeNacimientoValue =document.createTextNode(listaPracticantes.get(i).getNacimiento());
                nacimientoNode.appendChild(nodeNacimientoValue);
                itemNode.appendChild(nacimientoNode);
                
                Element direccionNode = document.createElement("DIRECCION");
                Text nodeDireccionValue =document.createTextNode(listaPracticantes.get(i).getDireccion());
                direccionNode.appendChild(nodeDireccionValue);
                itemNode.appendChild(direccionNode);
                
                Element emailNode = document.createElement("EMAIL");
                Text nodeEmailValue =document.createTextNode(listaPracticantes.get(i).getEmail());
                emailNode.appendChild(nodeEmailValue);
                itemNode.appendChild(emailNode);
                
                Element passwordNode = document.createElement("PASSWORD");
                Text nodePasswordoValue =document.createTextNode(listaPracticantes.get(i).getPassword());
                passwordNode.appendChild(nodePasswordoValue);
                itemNode.appendChild(passwordNode);
                
                Element periodoNode = document.createElement("PERIODO");
                
                //ATRIBUTOS 
                
                Element periodoNumeroSemestreNode = document.createElement("SEMESTRE");
                Text nodeNumeroSemestreValue =document.createTextNode("" + listaPracticantes.get(i).getPeriodo().getNumeroSemestre());
                periodoNumeroSemestreNode.appendChild(nodeNumeroSemestreValue);
                periodoNode.appendChild(periodoNumeroSemestreNode);
                
                Element periodoAgnoNode = document.createElement("AGNO");
                Text nodePeriodoAgnoValue =document.createTextNode("" + listaPracticantes.get(i).getPeriodo().getAgno());
                periodoAgnoNode.appendChild(nodePeriodoAgnoValue);
                periodoNode.appendChild(periodoAgnoNode);
                
                
                
                itemNode.appendChild(periodoNode);
                
                //PROFESOR ASESOR
                
                Element profesorAsesorNode = document.createElement("PROFESOR_ASESOR");
                Text nodeProfesorAsesorIdValue = document.createTextNode(listaPracticantes.get(i).getProfesorAsesor());
                profesorAsesorNode.appendChild(nodeProfesorAsesorIdValue);
               
                
                itemNode.appendChild(profesorAsesorNode);
                
                //PROFESOR DEL CURSO
                Element profesorCursoNode = document.createElement("PROFESOR_CURSO");
                Text nodeProfesorCursorValue = document.createTextNode(listaPracticantes.get(i).getProfesorCurso());
                profesorCursoNode.appendChild(nodeProfesorCursorValue);
               
                
                itemNode.appendChild(profesorCursoNode);
                        
                
                Element notaFinalNode = document.createElement("NOTA_FINAL");
                Text nodenotaFinalValue =document.createTextNode("" + listaPracticantes.get(i).getNotaFinal());
                notaFinalNode.appendChild(nodenotaFinalValue);
                itemNode.appendChild(notaFinalNode);
                
                //EMPRESA
                Element empresaNode = document.createElement("EMPRESA");
                Text nodeCedulaJuridicaValue =document.createTextNode(listaPracticantes.get(i).getEmpresa());
                empresaNode.appendChild(nodeCedulaJuridicaValue);
                
                itemNode.appendChild(empresaNode);
                
                //Calendario
                Element calendarioNode = document.createElement("CALENDARIO_EVALUACION");
                
                //Lista de evaluaciones
                Element listaEvaluacionesNode = document.createElement("EVALUACIONES");
                
                if(!listaPracticantes.get(i).getCalendario().getEvaluaciones().isEmpty()){
                    
                    for(Evaluacion indiceLista : listaPracticantes.get(i).getCalendario().getEvaluaciones()) {
                    
                    //Elementos de la lista
                    Element elementoListaNode = document.createElement("EVALUACION");
                    
                    Element elementoListaDescripcionNode = document.createElement("DESCRIPCION");
                    Text nodeElementoListaDescripcionValue =document.createTextNode(indiceLista.getDescripcion());
                    elementoListaDescripcionNode.appendChild(nodeElementoListaDescripcionValue);
                    elementoListaNode.appendChild(elementoListaDescripcionNode);
                    
                    Element elementoListaDescripcionEntregableNode = document.createElement("DESCRIPCION_ENTREGABLE");
                    Text nodeElementoListaDescripcionEntregableValue =document.createTextNode(indiceLista.getDescripcionDelEntregable());
                    elementoListaDescripcionEntregableNode.appendChild(nodeElementoListaDescripcionEntregableValue);
                    elementoListaNode.appendChild(elementoListaDescripcionEntregableNode);
                    
                    Element elementoListaArchivoNode = document.createElement("ARCHIVO");
                    Text nodeElementoListaArchivoValue =document.createTextNode(indiceLista.getArchivo());
                    elementoListaArchivoNode.appendChild(nodeElementoListaArchivoValue);
                    elementoListaNode.appendChild(elementoListaArchivoNode);
                    
                    Element elementoListaComentariosNode = document.createElement("COMENTARIOS");
                    Text nodeElementoListaComentariosValue =document.createTextNode(indiceLista.getComentarios());
                    elementoListaComentariosNode.appendChild(nodeElementoListaComentariosValue);
                    elementoListaNode.appendChild(elementoListaComentariosNode);
                    
                    //VALOR PORCENTUAL
                    Element elementoListaValorPorcentualNode = document.createElement("VALOR_PORCENTUAL");
                    Text nodeElementoListaValorPorcentualValue =document.createTextNode(""+indiceLista.getValorPorcentual());
                    elementoListaValorPorcentualNode.appendChild(nodeElementoListaValorPorcentualValue);
                    elementoListaNode.appendChild(elementoListaValorPorcentualNode);
                    
                    Element elementoListaResponsableNode = document.createElement("RESPONSABLE");
                    Text nodeElementoListaResponsableValue =document.createTextNode(indiceLista.getResponsable());
                    elementoListaResponsableNode.appendChild(nodeElementoListaResponsableValue);
                    elementoListaNode.appendChild(elementoListaResponsableNode);
                    
                    Element elementoListaEntregadoNode = document.createElement("ENTREGADO");
                    Text nodeElementoListaEntregadoValue =document.createTextNode(Boolean.toString(indiceLista.isEntregado()));
                    elementoListaEntregadoNode.appendChild(nodeElementoListaEntregadoValue);
                    elementoListaNode.appendChild(elementoListaEntregadoNode);
                    
                    Element elementoListaNotaNode = document.createElement("NOTA");
                    Text nodeElementoListaNotaValue =document.createTextNode(""+indiceLista.getNota());
                    elementoListaNotaNode.appendChild(nodeElementoListaNotaValue);
                    elementoListaNode.appendChild(elementoListaNotaNode);
                    
                    
                    Element elementoListaFechaEntregaNode = document.createElement("FECHA_ENTREGA");
                    //dia
                    Element elementoListaFechaEntregaDiaNode = document.createElement("DIA");
                    Text nodeElementoListaFechaEntregaDiaValue =document.createTextNode(""+indiceLista.getFechaEntrega().getDia());
                    elementoListaFechaEntregaDiaNode.appendChild(nodeElementoListaFechaEntregaDiaValue);
                    elementoListaFechaEntregaNode.appendChild(elementoListaFechaEntregaDiaNode);
                    
                    //mes
                    Element elementoListaFechaEntregaMesNode = document.createElement("MES");
                    Text nodeElementoListaFechaEntregaMesValue =document.createTextNode(""+indiceLista.getFechaEntrega().getMes());
                    elementoListaFechaEntregaMesNode.appendChild(nodeElementoListaFechaEntregaMesValue);
                    elementoListaFechaEntregaNode.appendChild(elementoListaFechaEntregaMesNode);
                    
                    //agno
                    Element elementoListaFechaEntregaAgnoNode = document.createElement("AGNO");
                    Text nodeElementoListaFechaEntregaAgnoValue =document.createTextNode(""+indiceLista.getFechaEntrega().getAgno());
                    elementoListaFechaEntregaAgnoNode.appendChild(nodeElementoListaFechaEntregaAgnoValue);
                    elementoListaFechaEntregaNode.appendChild(elementoListaFechaEntregaAgnoNode);
                    
                    //hora
                    Element elementoListaFechaEntregaHoraNode = document.createElement("HORA");
                    Text nodeElementoListaFechaEntregaHoraValue =document.createTextNode(""+indiceLista.getFechaEntrega().getHora());
                    elementoListaFechaEntregaHoraNode.appendChild(nodeElementoListaFechaEntregaHoraValue);
                    elementoListaFechaEntregaNode.appendChild(elementoListaFechaEntregaHoraNode);
                    
                    //minuto
                    Element elementoListaFechaEntregaMinutoNode = document.createElement("MINUTO");
                    Text nodeElementoListaFechaEntregaMinutoValue =document.createTextNode(""+indiceLista.getFechaEntrega().getMinutos());
                    elementoListaFechaEntregaMinutoNode.appendChild(nodeElementoListaFechaEntregaMinutoValue);
                    elementoListaFechaEntregaNode.appendChild(elementoListaFechaEntregaMinutoNode);
                    
                    //segundo
                    Element elementoListaFechaEntregaSegundoNode = document.createElement("SEGUNDO");
                    Text nodeElementoListaFechaEntregaSegundoValue =document.createTextNode(""+indiceLista.getFechaEntrega().getSegundos());
                    elementoListaFechaEntregaSegundoNode.appendChild(nodeElementoListaFechaEntregaSegundoValue);
                    elementoListaFechaEntregaNode.appendChild(elementoListaFechaEntregaSegundoNode);
                    
                    elementoListaNode.appendChild(elementoListaFechaEntregaNode);
                    
                    //Fecha entragable

                    Element elementoListaFechaEntregableNode = document.createElement("FECHA_ENTREGABLE");
                    //dia
                    Element elementoListaFechaEntregableDiaNode = document.createElement("DIA");
                    Text nodeElementoListaFechaEntregableDiaValue =document.createTextNode(""+indiceLista.getFechaEntregaEntregable().getDia());
                    elementoListaFechaEntregableDiaNode.appendChild(nodeElementoListaFechaEntregableDiaValue);
                    elementoListaFechaEntregableNode.appendChild(elementoListaFechaEntregableDiaNode);
                    
                    //mes
                    Element elementoListaFechaEntregableMesNode = document.createElement("MES");
                    Text nodeElementoListaFechaEntregableMesValue =document.createTextNode(""+indiceLista.getFechaEntregaEntregable().getMes());
                    elementoListaFechaEntregableMesNode.appendChild(nodeElementoListaFechaEntregableMesValue);
                    elementoListaFechaEntregableNode.appendChild(elementoListaFechaEntregableMesNode);
                    
                    //agno
                    Element elementoListaFechaEntregableAgnoNode = document.createElement("AGNO");
                    Text nodeElementoListaFechaEntregableAgnoValue =document.createTextNode(""+indiceLista.getFechaEntregaEntregable().getAgno());
                    elementoListaFechaEntregableAgnoNode.appendChild(nodeElementoListaFechaEntregableAgnoValue);
                    elementoListaFechaEntregableNode.appendChild(elementoListaFechaEntregableAgnoNode);
                    
                    //hora
                    Element elementoListaFechaEntregableHoraNode = document.createElement("HORA");
                    Text nodeElementoListaFechaEntregableHoraValue =document.createTextNode(""+indiceLista.getFechaEntregaEntregable().getHora());
                    elementoListaFechaEntregableHoraNode.appendChild(nodeElementoListaFechaEntregableHoraValue);
                    elementoListaFechaEntregableNode.appendChild(elementoListaFechaEntregableHoraNode);
                    
                    //minuto
                    Element elementoListaFechaEntregableMinutoNode = document.createElement("MINUTO");
                    Text nodeElementoListaFechaEntregableMinutoValue =document.createTextNode(""+indiceLista.getFechaEntregaEntregable().getMinutos());
                    elementoListaFechaEntregableMinutoNode.appendChild(nodeElementoListaFechaEntregableMinutoValue);
                    elementoListaFechaEntregableNode.appendChild(elementoListaFechaEntregableMinutoNode);
                    
                    //segundo
                    Element elementoListaFechaEntregableSegundoNode = document.createElement("SEGUNDO");
                    Text nodeElementoListaFechaEntregableSegundoValue =document.createTextNode(""+indiceLista.getFechaEntregaEntregable().getSegundos());
                    elementoListaFechaEntregableSegundoNode.appendChild(nodeElementoListaFechaEntregableSegundoValue);
                    elementoListaFechaEntregableNode.appendChild(elementoListaFechaEntregableSegundoNode);
                    
                    elementoListaNode.appendChild(elementoListaFechaEntregableNode);
                   
                    
                    Element listaEvaluacionesRubrosNode = document.createElement("RUBROS");
                    
                    if(!indiceLista.getRubros().isEmpty()){
                        
                        for (Rubro indiceListaRubro : indiceLista.getRubros()) {

                        //Elementos de la lista
                        Element elementoEvaluacionesRubrosNode = document.createElement("RUBRO");

                        Element elementoEvaluacionesRubrosDetalleNode = document.createElement("DETALLE");
                        Text nodeElementoEvaluacionesRubrosDetalleValue =document.createTextNode(indiceListaRubro.getDetalle());
                        elementoEvaluacionesRubrosDetalleNode.appendChild(nodeElementoEvaluacionesRubrosDetalleValue);
                        elementoEvaluacionesRubrosNode.appendChild(elementoEvaluacionesRubrosDetalleNode);
                        
                        Element elementoEvaluacionesRubrosPorcentajeNode = document.createElement("PORCENTAJE");
                        Text nodeElementoEvaluacionesRubrosPorcentajeValue =document.createTextNode(""+indiceListaRubro.getPorcentaje());
                        elementoEvaluacionesRubrosPorcentajeNode.appendChild(nodeElementoEvaluacionesRubrosPorcentajeValue);
                        elementoEvaluacionesRubrosNode.appendChild(elementoEvaluacionesRubrosPorcentajeNode);
                        
                        Element elementoEvaluacionesRubrosNotaNode = document.createElement("NOTA");
                        Text nodeElementoEvaluacionesRubrosNotaValue =document.createTextNode(""+indiceListaRubro.getNota());
                        elementoEvaluacionesRubrosNotaNode.appendChild(nodeElementoEvaluacionesRubrosNotaValue);
                        elementoEvaluacionesRubrosNode.appendChild(elementoEvaluacionesRubrosNotaNode);
                        
                        listaEvaluacionesRubrosNode.appendChild(elementoEvaluacionesRubrosNode);
                        
                        }
                            
                        elementoListaNode.appendChild(listaEvaluacionesRubrosNode);
                    }
                    
                    listaEvaluacionesNode.appendChild(elementoListaNode);
                    }
                }
                
                calendarioNode.appendChild(listaEvaluacionesNode);
                        
                itemNode.appendChild(calendarioNode);
                
                Element listaReunionesNode = document.createElement("REUNIONES");
                
                for (Reunion indiceListaReunion : listaPracticantes.get(i).getReuniones()) {

                    //Elementos de la lista
                    Element elementoListaReunionNode = document.createElement("REUNION");

                    Element elementoListaReunionFechaNode = document.createElement("FECHA");
                    
                    //dia
                    Element elementoListaReunionFechaDiaNode = document.createElement("DIA");
                    Text nodeelementoListaReunionFechaDiaValue =document.createTextNode(""+indiceListaReunion.getFecha().getDia());
                    elementoListaReunionFechaDiaNode.appendChild(nodeelementoListaReunionFechaDiaValue);
                    elementoListaReunionFechaNode.appendChild(elementoListaReunionFechaDiaNode);
                    
                    //mes
                    Element elementoListaReunionFechaMesNode = document.createElement("MES");
                    Text nodeelementoListaReunionFechaMesValue =document.createTextNode(""+indiceListaReunion.getFecha().getMes());
                    elementoListaReunionFechaMesNode.appendChild(nodeelementoListaReunionFechaMesValue);
                    elementoListaReunionFechaNode.appendChild(elementoListaReunionFechaMesNode);
                    
                    //agno
                    Element elementoListaReunionFechaAgnoNode = document.createElement("AGNO");
                    Text nodeelementoListaReunionFechaAgnoValue =document.createTextNode(""+indiceListaReunion.getFecha().getAgno());
                    elementoListaReunionFechaAgnoNode.appendChild(nodeelementoListaReunionFechaAgnoValue);
                    elementoListaReunionFechaNode.appendChild(elementoListaReunionFechaAgnoNode);
                    
                    //hora
                    Element elementoListaReunionFechaHoraNode = document.createElement("HORA");
                    Text nodeelementoListaReunionFechaHoraValue =document.createTextNode(""+indiceListaReunion.getFecha().getHora());
                    elementoListaReunionFechaHoraNode.appendChild(nodeelementoListaReunionFechaHoraValue);
                    elementoListaReunionFechaNode.appendChild(elementoListaReunionFechaHoraNode);
                    
                    //minuto
                    Element elementoListaReunionFechaMinutoNode = document.createElement("MINUTO");
                    Text nodeelementoListaReunionFechaMinutoValue =document.createTextNode(""+indiceListaReunion.getFecha().getMinutos());
                    elementoListaReunionFechaMinutoNode.appendChild(nodeelementoListaReunionFechaMinutoValue);
                    elementoListaReunionFechaNode.appendChild(elementoListaReunionFechaMinutoNode);
                    
                    //segundo
                    Element elementoListaReunionFechaSegundoNode = document.createElement("SEGUNDO");
                    Text nodeelementoListaReunionFechaSegundoValue =document.createTextNode(""+indiceListaReunion.getFecha().getSegundos());
                    elementoListaReunionFechaSegundoNode.appendChild(nodeelementoListaReunionFechaSegundoValue);
                    elementoListaReunionFechaNode.appendChild(elementoListaReunionFechaSegundoNode);
                   
                    elementoListaReunionNode.appendChild(elementoListaReunionFechaNode);
                    
                    Element elementoListaReunionTemaNode = document.createElement("TEMA");
                    Text nodeElementoListaReunionTemaValue =document.createTextNode(indiceListaReunion.getTema());
                    elementoListaReunionTemaNode.appendChild(nodeElementoListaReunionTemaValue);
                    elementoListaReunionNode.appendChild(elementoListaReunionTemaNode);
                    
                    Element elementoListaReunionLugarNode = document.createElement("LUGAR");
                    Text nodeElementoListaReunionLugarValue =document.createTextNode(indiceListaReunion.getLugar());
                    elementoListaReunionLugarNode.appendChild(nodeElementoListaReunionLugarValue);
                    elementoListaReunionNode.appendChild(elementoListaReunionLugarNode);
                    
                    Element elementoListaReunionAgendaNode = document.createElement("AGENDA");
                    Text nodeElementoListaReunionAgendaValue =document.createTextNode(indiceListaReunion.getAgenda());
                    elementoListaReunionAgendaNode.appendChild(nodeElementoListaReunionAgendaValue);
                    elementoListaReunionNode.appendChild(elementoListaReunionAgendaNode);
                   

                    //Lista de minutas
                    
                    Element listaReunionListaMinutasNode = document.createElement("MINUTAS");
                    //elementoListaReunionNode.appendChild(listaReunionListaMinutasNode);
                
                    for (Minuta indiceListaMinutas : indiceListaReunion.getMinutas()) {

                        Element elementoListaReunionListaMinutasNode = document.createElement("MINUTA");
                        
                        //Elementos de la lista
                        Element elementoMinutasTituloNode = document.createElement("TITULO");
                        Text nodeElementoMinutasTituloTituloValue =document.createTextNode(indiceListaMinutas.getTitulo());
                        elementoMinutasTituloNode.appendChild(nodeElementoMinutasTituloTituloValue);
                        elementoListaReunionListaMinutasNode.appendChild(elementoMinutasTituloNode);
                        
                        Element elementoListaReunionListaMinutasListaParticipantesNode = document.createElement("PARTICIPANTES");
                
                        for (String indiceParticipantes : indiceListaMinutas.getListaParticipantes()) {
                            
                            Element elementoListaReunionListaMinutasElementoListaParticipantesNode = document.createElement("PARTICIPANTE");
                            Text nodeElementoListaReunionListaMinutasElementoListaParticipantesNodeValue =document.createTextNode(indiceParticipantes);
                            elementoListaReunionListaMinutasElementoListaParticipantesNode.appendChild(nodeElementoListaReunionListaMinutasElementoListaParticipantesNodeValue);
                            elementoListaReunionListaMinutasListaParticipantesNode.appendChild(elementoListaReunionListaMinutasElementoListaParticipantesNode);
                            
                        }
                        
                        elementoListaReunionListaMinutasNode.appendChild(elementoListaReunionListaMinutasListaParticipantesNode);
                        
                        //Fecha inicio
                        
                        Element elementoListaMinutasFechaInicio = document.createElement("FECHA_INICIO");
                        //dia
                        Element elementoListaMinutasFechaInicioDiaNode = document.createElement("DIA");
                        Text nodeElementoListaMinutasFechaInicioDiaValue =document.createTextNode(""+indiceListaMinutas.getFechaInicio().getDia());
                        elementoListaMinutasFechaInicioDiaNode.appendChild(nodeElementoListaMinutasFechaInicioDiaValue);
                        elementoListaMinutasFechaInicio.appendChild(elementoListaMinutasFechaInicioDiaNode);

                        //mes
                        Element elementoListaMinutasFechaInicioMesNode = document.createElement("MES");
                        Text nodeElementoListaMinutasFechaInicioMesValue =document.createTextNode(""+indiceListaMinutas.getFechaInicio().getMes());
                        elementoListaMinutasFechaInicioMesNode.appendChild(nodeElementoListaMinutasFechaInicioMesValue);
                        elementoListaMinutasFechaInicio.appendChild(elementoListaMinutasFechaInicioMesNode);

                        //agno
                        Element elementoListaMinutasFechaInicioAgnoNode = document.createElement("AGNO");
                        Text nodeElementoListaMinutasFechaInicioAgnoValue =document.createTextNode(""+indiceListaMinutas.getFechaInicio().getAgno());
                        elementoListaMinutasFechaInicioAgnoNode.appendChild(nodeElementoListaMinutasFechaInicioAgnoValue);
                        elementoListaMinutasFechaInicio.appendChild(elementoListaMinutasFechaInicioAgnoNode);

                        //hora
                        Element elementoListaMinutasFechaInicioHoraNode = document.createElement("HORA");
                        Text nodeElementoListaMinutasFechaInicioHoraValue =document.createTextNode(""+indiceListaMinutas.getFechaInicio().getHora());
                        elementoListaMinutasFechaInicioHoraNode.appendChild(nodeElementoListaMinutasFechaInicioHoraValue);
                        elementoListaMinutasFechaInicio.appendChild(elementoListaMinutasFechaInicioHoraNode);

                        //minuto
                        Element elementoListaMinutasFechaInicioMinutoNode = document.createElement("MINUTO");
                        Text nodeElementoListaMinutasFechaInicioMinutoValue =document.createTextNode(""+indiceListaMinutas.getFechaInicio().getMinutos());
                        elementoListaMinutasFechaInicioMinutoNode.appendChild(nodeElementoListaMinutasFechaInicioMinutoValue);
                        elementoListaMinutasFechaInicio.appendChild(elementoListaMinutasFechaInicioMinutoNode);

                        //segundo
                        Element elementoListaMinutasFechaInicioSegundoNode = document.createElement("SEGUNDO");
                        Text nodeElementoListaMinutasFechaInicioSegundoValue =document.createTextNode(""+indiceListaMinutas.getFechaInicio().getSegundos());
                        elementoListaMinutasFechaInicioSegundoNode.appendChild(nodeElementoListaMinutasFechaInicioSegundoValue);
                        elementoListaMinutasFechaInicio.appendChild(elementoListaMinutasFechaInicioSegundoNode);

                        elementoListaReunionListaMinutasNode.appendChild(elementoListaMinutasFechaInicio);
                        
                        //Fecha final
                        
                        Element elementoListaMinutasFechaFinal = document.createElement("FECHA_FINAL");
                        //dia
                        Element elementoListaMinutasFechaFinalDiaNode = document.createElement("DIA");
                        Text nodeElementoListaMinutasFechaFinalDiaValue =document.createTextNode(""+indiceListaMinutas.getFechaFinal().getDia());
                        elementoListaMinutasFechaFinalDiaNode.appendChild(nodeElementoListaMinutasFechaFinalDiaValue);
                        elementoListaMinutasFechaFinal.appendChild(elementoListaMinutasFechaFinalDiaNode);

                        //mes
                        Element elementoListaMinutasFechaFinalMesNode = document.createElement("MES");
                        Text nodeElementoListaMinutasFechaFinalMesValue =document.createTextNode(""+indiceListaMinutas.getFechaFinal().getMes());
                        elementoListaMinutasFechaFinalMesNode.appendChild(nodeElementoListaMinutasFechaFinalMesValue);
                        elementoListaMinutasFechaFinal.appendChild(elementoListaMinutasFechaFinalMesNode);

                        //agno
                        Element elementoListaMinutasFechaFinalAgnoNode = document.createElement("AGNO");
                        Text nodeElementoListaMinutasFechaFinalAgnoValue =document.createTextNode(""+indiceListaMinutas.getFechaFinal().getAgno());
                        elementoListaMinutasFechaFinalAgnoNode.appendChild(nodeElementoListaMinutasFechaFinalAgnoValue);
                        elementoListaMinutasFechaFinal.appendChild(elementoListaMinutasFechaFinalAgnoNode);

                        //hora
                        Element elementoListaMinutasFechaFinalHoraNode = document.createElement("HORA");
                        Text nodeElementoListaMinutasFechaFinalHoraValue =document.createTextNode(""+indiceListaMinutas.getFechaFinal().getHora());
                        elementoListaMinutasFechaFinalHoraNode.appendChild(nodeElementoListaMinutasFechaFinalHoraValue);
                        elementoListaMinutasFechaFinal.appendChild(elementoListaMinutasFechaFinalHoraNode);

                        //minuto
                        Element elementoListaMinutasFechaFinalMinutoNode = document.createElement("MINUTO");
                        Text nodeElementoListaMinutasFechaFinalMinutoValue =document.createTextNode(""+indiceListaMinutas.getFechaFinal().getMinutos());
                        elementoListaMinutasFechaFinalMinutoNode.appendChild(nodeElementoListaMinutasFechaFinalMinutoValue);
                        elementoListaMinutasFechaFinal.appendChild(elementoListaMinutasFechaFinalMinutoNode);

                        //segundo
                        Element elementoListaMinutasFechaFinalSegundoNode = document.createElement("SEGUNDO");
                        Text nodeElementoListaMinutasFechaFinalSegundoValue =document.createTextNode(""+indiceListaMinutas.getFechaFinal().getSegundos());
                        elementoListaMinutasFechaFinalSegundoNode.appendChild(nodeElementoListaMinutasFechaFinalSegundoValue);
                        elementoListaMinutasFechaFinal.appendChild(elementoListaMinutasFechaFinalSegundoNode);

                        elementoListaReunionListaMinutasNode.appendChild(elementoListaMinutasFechaFinal);
                        
                        Element elementoListaReunionListaMinutasListaTemasNode = document.createElement("TEMAS");
                
                        for (String indiceTemas : indiceListaMinutas.getTemas()) {
                            
                            Element elementoListaReunionListaMinutasElementoListaTemasNode = document.createElement("TEMA");
                            Text nodeElementoListaReunionListaMinutasElementoListaTemasNodeValue =document.createTextNode(indiceTemas);
                            elementoListaReunionListaMinutasElementoListaTemasNode.appendChild(nodeElementoListaReunionListaMinutasElementoListaTemasNodeValue);
                            elementoListaReunionListaMinutasListaTemasNode.appendChild(elementoListaReunionListaMinutasElementoListaTemasNode);
                            
                        }
                        elementoListaReunionListaMinutasNode.appendChild(elementoListaReunionListaMinutasListaTemasNode);
                        
                        Element elementoListaReunionListaMinutasLugarNode = document.createElement("LUGAR");
                        Text nodeElementoListaReunionListaMinutasLugarValue =document.createTextNode(indiceListaMinutas.getLugar());
                        elementoListaReunionListaMinutasLugarNode.appendChild(nodeElementoListaReunionListaMinutasLugarValue);
                        elementoListaReunionListaMinutasNode.appendChild(elementoListaReunionListaMinutasLugarNode);
                        
                        Element elementoListaReunionListaMinutasArchivoNode = document.createElement("ARCHIVO");
                        Text nodeElementoListaReunionListaMinutasArchivoValue =document.createTextNode(indiceListaMinutas.getArchivo());
                        elementoListaReunionListaMinutasArchivoNode.appendChild(nodeElementoListaReunionListaMinutasArchivoValue);
                        elementoListaReunionListaMinutasNode.appendChild(elementoListaReunionListaMinutasArchivoNode);
                        
                        listaReunionListaMinutasNode.appendChild(elementoListaReunionListaMinutasNode);
                        
                    }
                    
                    elementoListaReunionNode.appendChild(listaReunionListaMinutasNode);
                    
                    listaReunionesNode.appendChild(elementoListaReunionNode);
                }
                
                
                
                
                itemNode.appendChild(listaReunionesNode);
                
                raiz.appendChild(itemNode);
               
            }
            
            // GENERA XML
            Source source = new DOMSource(document);
            
            // DONDE SE GUARDARA
            
            Result result = new StreamResult(new java.io.File(nomArchivo + ".xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        
        } catch(ParserConfigurationException e) {
            return false;
        }
        return true;
    }
    
    
}
