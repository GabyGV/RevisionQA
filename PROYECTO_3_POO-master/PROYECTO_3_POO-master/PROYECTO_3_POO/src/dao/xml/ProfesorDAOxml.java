/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.xml;

import dao.ProfesorDAO;
import java.io.Serializable;
import java.util.ArrayList;
import model.usuarios.Profesor;
import persistencia.xml.ControlProfesorXML;

/**
 * Implemetación de la interfaz ProfesorDAO, en XML
 * 
 * @author Adrian, Dylan y Roberto
 */
public class ProfesorDAOxml implements ProfesorDAO, Serializable{

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean insertar(Profesor t) {
        
        ArrayList<Profesor> profesores = obtenerTodos();
            
        profesores.add(t);
        
        return guardar(profesores);
 
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public Profesor obtener(String id) {
        ArrayList<Profesor> profesores = obtenerTodos();
        
        for(int i = 0; i < profesores.size(); i++){
            if(profesores.get(i).getId().equals(id)){   
                return profesores.get(i);   
            }
        }
        return null;
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean modificar(Profesor t) {

        eliminar(t.getId());
        return insertar(t);
        
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean eliminar(String id) {
        
        ArrayList<Profesor> profesores = obtenerTodos();
        
        for(int i = 0; i < profesores.size(); i++){
            if(profesores.get(i).getId().equals(id)){   
                profesores.remove(profesores.get(i));
                return guardar(profesores);
            }
        }
        return false;
        
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public ArrayList<Profesor> obtenerTodos() {
        
        return ControlProfesorXML.leerProfesoresXML();
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean guardar(ArrayList<Profesor> t) {

        try{
            ControlProfesorXML.crearXML(t);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
}
