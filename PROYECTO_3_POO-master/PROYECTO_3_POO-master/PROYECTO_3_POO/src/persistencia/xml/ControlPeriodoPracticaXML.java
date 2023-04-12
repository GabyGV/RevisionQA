/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.CalendarioEvaluacion;
import model.Evaluacion;
import model.Fecha;
import model.PeriodoPractica;
import model.Rubro;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Persistencia de datos por medio de XML para PeriodoPractica.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class ControlPeriodoPracticaXML {
    
    /** Crea el archivo con los datos de los periodos suministrados. */
    public static void crearXML(ArrayList<PeriodoPractica> listaPeriodo) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
    String nomArchivo = "Periodos";
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation implementation = builder.getDOMImplementation();
      Document document = implementation.createDocument(null, nomArchivo, null);
      document.setXmlVersion("1.0");

      // NODO RAIZ
      Element raiz = document.getDocumentElement();
      for (int i = 0; i < listaPeriodo.size(); i++) {  
          
          
        Element itemNode = document.createElement("PERIODO");
                
        //ATRIBUTOS 

        Element periodoNumeroSemestreNode = document.createElement("SEMESTRE");
        Text nodeNumeroSemestreValue =document.createTextNode("" + listaPeriodo.get(i).getNumeroSemestre());
        periodoNumeroSemestreNode.appendChild(nodeNumeroSemestreValue);
        itemNode.appendChild(periodoNumeroSemestreNode);

        Element periodoAgnoNode = document.createElement("AGNO");
        Text nodePeriodoAgnoValue =document.createTextNode("" + listaPeriodo.get(i).getAgno());
        periodoAgnoNode.appendChild(nodePeriodoAgnoValue);
        itemNode.appendChild(periodoAgnoNode);
        
        Element calendarioNode = document.createElement("CALENDARIO_EVALUACION");
                
                //Lista de evaluaciones
                Element listaEvaluacionesNode = document.createElement("EVALUACIONES");

                if(!listaPeriodo.get(i).getCalendario().getEvaluaciones().isEmpty()){

                    for(Evaluacion indiceLista : listaPeriodo.get(i).getCalendario().getEvaluaciones()) {

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
                    System.out.println("6");
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
                    System.out.println("7");

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
          
       
        
        raiz.appendChild(itemNode);
      }
      // GENERA XML
      Source source = new DOMSource(document);

      // DONDE SE GUARDARA
      Result result = new StreamResult(new java.io.File(nomArchivo + ".xml"));
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.transform(source, result);
    } catch (ParserConfigurationException e) {
      System.out.println("excepcion de configuracion de parser");
    } catch (TransformerException ex) {
      Logger.getLogger(ControlProfesorXML.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  /** Retorna una lista con los periodos dentro del archivo. */
  public static ArrayList<PeriodoPractica> leerPeriodoXML(){
      ArrayList<PeriodoPractica> listaFinal = new ArrayList<PeriodoPractica>();
        try{
            File archivo = new File("Periodos.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);

            document.getDocumentElement().normalize();

           
            
            NodeList listaEncargados = document.getElementsByTagName("PERIODO");
            
            for(int i = 0 ; i < listaEncargados.getLength() ; i++) {
                Node nodo = listaEncargados.item(i);
                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    int semestre = Integer.valueOf(element.getElementsByTagName("SEMESTRE").item(0).getTextContent());
                    int agno = Integer.valueOf(element.getElementsByTagName("AGNO").item(0).getTextContent());
                    
                    
                    
                    
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
                    PeriodoPractica pTmp = new PeriodoPractica(semestre,agno);
                    pTmp.setCalendario(calendario);
                    listaFinal.add(pTmp);
                }
            }

        }catch(Exception e) {
            return listaFinal;
        }
        return listaFinal;
    }
    
    
}
