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
import model.usuarios.Profesor;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Persistencia de datos por medio de XML para Profesor.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class ControlProfesorXML {
  
  /** Crea el archivo con los datos de los profesores suministrados. */
  public static void crearXML(ArrayList<Profesor> listaProfesores) {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
    String nomArchivo = "Profesores";
    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation implementation = builder.getDOMImplementation();
      Document document = implementation.createDocument(null, nomArchivo, null);
      document.setXmlVersion("1.0");

      // NODO RAIZ
      Element raiz = document.getDocumentElement();
      for (int i = 0; i < listaProfesores.size(); i++) {  
        //PROFESOR ASESOR
        Element profesorNode = document.createElement("PROFESOR");

        //Atributos del objeto secundario
        Element profesorNombreNode = document.createElement("NOMBRE");
        Text nodeProfesorNombreValue =document.createTextNode(listaProfesores.get(i).getNombre());
        profesorNombreNode.appendChild(nodeProfesorNombreValue);
        profesorNode.appendChild(profesorNombreNode);

        Element profesorTelefonoNode = document.createElement("TELEFONO");
        Text nodeProfesorTelefonoValue =document.createTextNode(listaProfesores.get(i).getTelefono());
        profesorTelefonoNode.appendChild(nodeProfesorTelefonoValue);
        profesorNode.appendChild(profesorTelefonoNode);

        Element profesorEmailNode = document.createElement("EMAIL");
        Text nodeProfesorEmailValue = document.createTextNode(listaProfesores.get(i).getEmail());
        profesorEmailNode.appendChild(nodeProfesorEmailValue);
        profesorNode.appendChild(profesorEmailNode);

        Element profesorPasswordNode = document.createElement("PASSWORD");
        Text nodeProfesorPasswordValue = document.createTextNode(listaProfesores.get(i).getPassword());
        profesorPasswordNode.appendChild(nodeProfesorPasswordValue);
        profesorNode.appendChild(profesorPasswordNode);

        Element profesorIdNode = document.createElement("ID");
        Text nodeProfesorIdValue = document.createTextNode(listaProfesores.get(i).getId());
        profesorIdNode.appendChild(nodeProfesorIdValue);
        profesorNode.appendChild(profesorIdNode);
        
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
  public static ArrayList<Profesor> leerProfesoresXML(){
      ArrayList<Profesor> listaFinal = new ArrayList<Profesor>();
        try{
            File archivo = new File("Profesores.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);

            document.getDocumentElement().normalize();

            
            
            NodeList listaEncargados = document.getElementsByTagName("PROFESOR");
            
            for(int i = 0 ; i < listaEncargados.getLength() ; i++) {
                Node nodo = listaEncargados.item(i);
                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    String nombre = element.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    String telefono = element.getElementsByTagName("TELEFONO").item(0).getTextContent();
                    String email = element.getElementsByTagName("EMAIL").item(0).getTextContent();
                    String password = element.getElementsByTagName("PASSWORD").item(0).getTextContent();
                    String id = element.getElementsByTagName("ID").item(0).getTextContent();
                    
                    Profesor pTmp = new Profesor(nombre,telefono,email,password,id);
                    
                    listaFinal.add(pTmp);
                }
            }

        }catch(Exception e) {
            return listaFinal;
        }
        return listaFinal;
    }
}
