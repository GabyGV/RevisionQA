/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.xml;

import dao.EmpresaDAO;
import java.io.Serializable;
import java.util.ArrayList;
import model.Empresa;
import persistencia.xml.ControlEmpresaXML;

/**
 * Implemetación de la interfaz EmpresaDAO, en XML
 * 
 * @author Adrian, Dylan y Roberto
 */
public class EmpresaDAOxml implements EmpresaDAO, Serializable{

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean insertar(Empresa t) {
        
        ArrayList<Empresa> empresas = obtenerTodos();
            
        empresas.add(t);
        
        return guardar(empresas);
 
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public Empresa obtener(String id) {
        ArrayList<Empresa> empresas = obtenerTodos();
        
        for(int i = 0; i < empresas.size(); i++){
            if(empresas.get(i).getCedulaJuridica().equals(id)){   
                return empresas.get(i);   
            }
        }
        return null;
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean modificar(Empresa t) {

        eliminar(t.getCedulaJuridica());
        return insertar(t);
        
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean eliminar(String id) {
        
        ArrayList<Empresa> empresas = obtenerTodos();
        
        for(int i = 0; i < empresas.size(); i++){
            if(empresas.get(i).getCedulaJuridica().equals(id)){   
                empresas.remove(empresas.get(i));
                return guardar(empresas);
            }
        }
        return false;
        
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public ArrayList<Empresa> obtenerTodos() {
        
        return ControlEmpresaXML.leerProfesoresXML();
    }

    /** Comportamiento descrito en la interfaz DAO. */
    @Override
    public boolean guardar(ArrayList<Empresa> t) {

        try{
            ControlEmpresaXML.crearXML(t);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
}
