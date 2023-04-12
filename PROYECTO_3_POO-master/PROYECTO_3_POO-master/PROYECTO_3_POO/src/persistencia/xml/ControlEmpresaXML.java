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
import model.Empresa;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Persistencia de datos por medio de XML para Empresa.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class ControlEmpresaXML {
    
    /** Crea el archivo con los datos de las empresas suministrados. */
    public static void crearXML(ArrayList<Empresa> listaEmpresa) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
    String nomArchivo = "Empresas";
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation implementation = builder.getDOMImplementation();
      Document document = implementation.createDocument(null, nomArchivo, null);
      document.setXmlVersion("1.0");

      // NODO RAIZ
      Element raiz = document.getDocumentElement();
      for (int i = 0; i < listaEmpresa.size(); i++) {  
        //PROFESOR ASESOR
        Element profesorNode = document.createElement("EMPRESA");

        //Atributos del objeto secundario
        Element profesorNombreNode = document.createElement("RAZON_SOCIAL");
        Text nodeProfesorNombreValue =document.createTextNode(listaEmpresa.get(i).getRazonSocial());
        profesorNombreNode.appendChild(nodeProfesorNombreValue);
        profesorNode.appendChild(profesorNombreNode);

        Element profesorCedulaNode = document.createElement("CEDULA_JURIDICA");
        Text nodeProfesorCedulaValue =document.createTextNode(listaEmpresa.get(i).getCedulaJuridica());
        profesorCedulaNode.appendChild(nodeProfesorCedulaValue);
        profesorNode.appendChild(profesorCedulaNode);
        
        Element profesorTelefonoNode = document.createElement("TELEFONO");
        Text nodeProfesorTelefonoValue =document.createTextNode(listaEmpresa.get(i).getTelefono());
        profesorTelefonoNode.appendChild(nodeProfesorTelefonoValue);
        profesorNode.appendChild(profesorTelefonoNode);
        

        Element profesorEmailNode = document.createElement("DIRECCION");
        Text nodeProfesorEmailValue = document.createTextNode(listaEmpresa.get(i).getDireccion());
        profesorEmailNode.appendChild(nodeProfesorEmailValue);
        profesorNode.appendChild(profesorEmailNode);

        Element profesorPasswordNode = document.createElement("ENCARGADO");
        Text nodeProfesorPasswordValue = document.createTextNode(listaEmpresa.get(i).getEncargado());
        profesorPasswordNode.appendChild(nodeProfesorPasswordValue);
        profesorNode.appendChild(profesorPasswordNode);
        
        raiz.appendChild(profesorNode);
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
  
  /** Retorna una lista con los profesores dentro del archivo. */
  public static ArrayList<Empresa> leerProfesoresXML(){
      ArrayList<Empresa> listaFinal = new ArrayList<Empresa>();
        try{
            File archivo = new File("Empresas.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);

            document.getDocumentElement().normalize();

            
            
            NodeList listaEncargados = document.getElementsByTagName("EMPRESA");
            
            for(int i = 0 ; i < listaEncargados.getLength() ; i++) {
                Node nodo = listaEncargados.item(i);
                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    String razonSocial = element.getElementsByTagName("RAZON_SOCIAL").item(0).getTextContent();
                    String cedula = element.getElementsByTagName("CEDULA_JURIDICA").item(0).getTextContent();
                    String telefono = element.getElementsByTagName("TELEFONO").item(0).getTextContent();
                    String direccion = element.getElementsByTagName("DIRECCION").item(0).getTextContent();
                    String encargado = element.getElementsByTagName("ENCARGADO").item(0).getTextContent();
                    
                    Empresa pTmp = new Empresa(razonSocial,cedula,direccion ,encargado,telefono );
                    
                    listaFinal.add(pTmp);
                    
                   
                    
                }
            }

        }catch(Exception e) {
            return listaFinal;
        }
        return listaFinal;
    }
    
}
