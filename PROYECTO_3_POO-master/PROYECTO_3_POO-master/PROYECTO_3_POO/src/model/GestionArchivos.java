/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Gestión de archivos dentro del sistema operativo.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class GestionArchivos {
    
    FileInputStream entrada;
    FileOutputStream salida;
    File Archivo;
    
    /** Construye los objetos de la clase. */
    public GestionArchivos(){
        
    }
    
    /** Retorna una cadena con el contenido del archivo solicitado. */
    public String abrirArchivoTexto(File archivo){
        
        String contenido = "";
        
        try{
            entrada = new FileInputStream(archivo);
            int ascci;
            
            while((ascci = entrada.read()) != -1){
                char caracter = (char)ascci;
                contenido += caracter;    
            }
        }catch(Exception e){
            
        }
        return contenido;
    }
    
}
