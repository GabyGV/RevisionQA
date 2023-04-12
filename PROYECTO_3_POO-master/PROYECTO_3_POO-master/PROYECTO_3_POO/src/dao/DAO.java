/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

/**
 * Interfaz con métodos para el acceso de datos desde la base de datos.
 * 
 * @author Adrian, Dylan y Roberto
 */
public interface DAO <T> {
    
    /**
     * Inserta a la base de datos.
     * 
     * @param t parametro a insertar
     * @return si fue insertado
     */
    boolean insertar(T t);
    
    /**
     * Obtiene el objeto con el id proporcionado, de la base ed datos.
     * 
     * @param id del objeto solicitado
     * @return el objeto solicitado o null si no existe
     */
    T obtener(String id);
    
    /**
     * Modifica el objeto en la base de datos.
     * 
     * @param t objeto a modificar
     * @return si fue modificado
     */
    boolean modificar(T t);
    
    /**
     * Elimina el objeto de la base de datos.
     * 
     * @param id del objeto a eliminar.
     * @return si fue eliminado
     */
    boolean eliminar(String id);
    
    /**
     * Obtiene todos los objetos de un tipo.
     * 
     * @return una arreglo de todos los objetos de un tipo.
     */
    ArrayList<T> obtenerTodos();
    
    /**
     * Guarda el objeto en la base de datos.
     * 
     * @param t objeto a guardar
     * @return si se guardo
     */
    boolean guardar(ArrayList<T> t);
    
}
