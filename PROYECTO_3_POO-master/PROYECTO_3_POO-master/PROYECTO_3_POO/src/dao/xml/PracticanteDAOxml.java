/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.xml;

import dao.PracticanteDAO;
import java.io.Serializable;
import java.util.ArrayList;
import model.usuarios.Practicante;
import persistencia.xml.ControlPracticanteXML;

/**
 * Implemetación de la interfaz PracticanteDAO, en XML
 * 
 * @author Adrian, Dylan y Roberto
 */
public class PracticanteDAOxml implements PracticanteDAO, Serializable {

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean insertar(Practicante t) {
        
        ArrayList<Practicante> practicantes = obtenerTodos();
            
        practicantes.add(t);
        
        return guardar(practicantes);
 
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public Practicante obtener(String id) {
        ArrayList<Practicante> practicantes = obtenerTodos();
        
        for(int i = 0; i < practicantes.size(); i++){
            if(practicantes.get(i).getCarnet().equals(id)){   
                return practicantes.get(i);   
            }
        }
        return null;
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean modificar(Practicante t) {

        eliminar(t.getCarnet());
        return insertar(t);
        
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean eliminar(String id) {
        
        ArrayList<Practicante> practicantes = obtenerTodos();
        
        for(int i = 0; i < practicantes.size(); i++){
            if(practicantes.get(i).getCarnet().equals(id)){   
                practicantes.remove(practicantes.get(i));
                return guardar(practicantes);
            }
        }
        return false;
        
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public ArrayList<Practicante> obtenerTodos() {
        
        return ControlPracticanteXML.leerPracticantesXML();
        
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean guardar(ArrayList<Practicante> t) {
        
        
        try{
            return ControlPracticanteXML.crearXML(t);
        }catch(Exception e){
            return false;
        }
        
        
    }
    
}
