/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.xml;

import dao.EncargadoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import model.usuarios.Encargado;
import persistencia.xml.ControlEncargadoXML;

/**
 * Implemetación de la interfaz EncargadoDAO, en XML
 * 
 * @author Adrian, Dylan y Roberto
 */
public class EncargadoDAOxml implements EncargadoDAO, Serializable {

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean insertar(Encargado t) {
        
        ArrayList<Encargado> encargados = obtenerTodos();
            
        encargados.add(t);
        
        return guardar(encargados);
 
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public Encargado obtener(String id) {
        ArrayList<Encargado> encargados = obtenerTodos();
        
        for(int i = 0; i < encargados.size(); i++){
            if(encargados.get(i).getId().equals(id)){   
                return encargados.get(i);   
            }
        }
        return null;
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean modificar(Encargado t) {

        eliminar(t.getId());
        return insertar(t);
        
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean eliminar(String id) {
        
        ArrayList<Encargado> practicantes = obtenerTodos();
        
        for(int i = 0; i < practicantes.size(); i++){
            if(practicantes.get(i).getId().equals(id)){   
                practicantes.remove(practicantes.get(i));
                return guardar(practicantes);
            }
        }
        return false;
        
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public ArrayList<Encargado> obtenerTodos() {
        return ControlEncargadoXML.leerEncargadosXML();
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean guardar(ArrayList<Encargado> t) {
        
        try{
            ControlEncargadoXML.crearXML(t);
        }catch(Exception e){
            return true;
        }
        return true;
    }
    
}
