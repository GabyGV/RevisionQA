/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.xml;

import dao.PeriodoPracticaDAO;
import java.io.Serializable;
import java.util.ArrayList;
import model.PeriodoPractica;
import persistencia.xml.ControlPeriodoPracticaXML;

/**
 * Implemetación de la interfaz PeriodoPracticaDAO, en XML
 * 
 * @author Adrian, Dylan y Roberto
 */
public class PeriodoPracticaDAOxml implements PeriodoPracticaDAO, Serializable{

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean insertar(PeriodoPractica t) {
        
        ArrayList<PeriodoPractica> periodos = obtenerTodos();
            
        periodos.add(t);
        
        return guardar(periodos);
 
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public PeriodoPractica obtener(String id) {
        return null;
    }
    
    /** Comportamiento descrito en la interfaz DAO. */
    public PeriodoPractica obtener(int semestre, int agno){
        
        ArrayList<PeriodoPractica> periodos = obtenerTodos();
        
        for(int i = 0; i < periodos.size(); i++){

            
            if(periodos.get(i).getAgno() == agno && periodos.get(i).getNumeroSemestre() == semestre){   
               
                return periodos.get(i);   
            }
        }
        return null;
        
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean modificar(PeriodoPractica t) {

        eliminar(t.getAgno(), t.getNumeroSemestre());
        return insertar(t);
        
    }
    

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean eliminar(String id) {
        return true;
    }
    
    /** Comportamiento descrito en la interfaz DAO. */
    public boolean eliminar(int agno, int semestre){
        ArrayList<PeriodoPractica> periodos = obtenerTodos();
        
        for(int i = 0; i < periodos.size(); i++){
            if(periodos.get(i).getAgno() == agno && periodos.get(i).getNumeroSemestre() == semestre){   
                periodos.remove(periodos.get(i));
                return guardar(periodos);
            }
        }
        return false;
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public ArrayList<PeriodoPractica> obtenerTodos() {
        
        return ControlPeriodoPracticaXML.leerPeriodoXML();

    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean guardar(ArrayList<PeriodoPractica> t) {

        try{
            ControlPeriodoPracticaXML.crearXML(t);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
