/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.usuarios.Encargado;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Persistencia de datos por medio de XML para Encargado.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class ControlEncargadoXML {
    
    /** Retorna una lista con los encargados dentro del archivo. */
    public static ArrayList<Encargado> leerEncargadosXML(){
        ArrayList<Encargado> listaFinal = new ArrayList<Encargado>();
        try{
            File archivo = new File("Encargados.xml");
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);

            document.getDocumentElement().normalize();

            
            
            NodeList listaEncargados = document.getElementsByTagName("ENCARGADO");
            
            for(int i = 0 ; i < listaEncargados.getLength() ; i++) {
                Node nodo = listaEncargados.item(i);
                
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    
                    
                    listaFinal.add(new Encargado(element.getElementsByTagName("NOMBRE").item(0).getTextContent(), element.getElementsByTagName("PUESTO").item(0).getTextContent(), element.getElementsByTagName("TELEFONO").item(0).getTextContent(), element.getElementsByTagName("EMAIL").item(0).getTextContent(), element.getElementsByTagName("ID").item(0).getTextContent(),element.getElementsByTagName("PASSWORD").item(0).getTextContent()));
                    
                    
                }
            }

        }catch(Exception e) {
            return listaFinal;
        }
        return listaFinal;
    }
    
    /** Crea el archivo con los datos de los encargados suministrados. */
    public static void crearXML(List<Encargado> listaEncargados) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String nomArchivo = "Encargados";
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, nomArchivo, null);
            document.setXmlVersion("1.0");
            
            // NODO RAIZ
            Element raiz = document.getDocumentElement();
            
            for(int i = 0 ; i <listaEncargados.size() ; i++) {
                Element itemNode = document.createElement("ENCARGADO");
                
                Element nombreNode = document.createElement("NOMBRE");
                Text nombreNodeValue =document.createTextNode(listaEncargados.get(i).getNombre());
                nombreNode.appendChild(nombreNodeValue);
                itemNode.appendChild(nombreNode);
                
                Element puestoNode = document.createElement("PUESTO");
                Text puestoNodeValue =document.createTextNode(listaEncargados.get(i).getPuesto());
                puestoNode.appendChild(puestoNodeValue);
                itemNode.appendChild(puestoNode);
                
                Element telefonoNode = document.createElement("TELEFONO");
                Text telefonoNodeValue =document.createTextNode(listaEncargados.get(i).getTelefono());
                telefonoNode.appendChild(telefonoNodeValue);
                itemNode.appendChild(telefonoNode);
                
                Element emailNode = document.createElement("EMAIL");
                Text emailNodeValue =document.createTextNode(listaEncargados.get(i).getEmail());
                emailNode.appendChild(emailNodeValue);
                itemNode.appendChild(emailNode);
                
                Element idNode = document.createElement("ID");
                Text idNodeValue =document.createTextNode(listaEncargados.get(i).getId());
                idNode.appendChild(idNodeValue);
                itemNode.appendChild(idNode);

                Element passwordNode = document.createElement("PASSWORD");
                Text passwordNodeValue =document.createTextNode(listaEncargados.get(i).getPassword());
                passwordNode.appendChild(passwordNodeValue);
                itemNode.appendChild(passwordNode);
                
                raiz.appendChild(itemNode);
            }
            
            // GENERA XML
            Source source = new DOMSource(document);
            
            // DONDE SE GUARDARA
            Result result = new StreamResult(new java.io.File(nomArchivo + ".xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        
        } catch(ParserConfigurationException e) {
            
        }
    }
}

