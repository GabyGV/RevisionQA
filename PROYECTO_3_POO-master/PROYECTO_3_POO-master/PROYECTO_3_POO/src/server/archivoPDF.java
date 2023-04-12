/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import java.io.*;

/**
 * Crea un archivo PDF.
 * 
 * @author Adrian, Dylan y Roberto
 */
public class archivoPDF {
    
    /**
     * Crea un archivo PDF con una imagen.
     * 
     * @param fileName nombre del archivo
     * @param texto cuerpo del archivo
     * @param imagenFile imagen para agregar al archivo
     * @throws FileNotFoundException
     * @throws DocumentException
     * @throws Exception 
     */
    public static void pdfCreateImage(String fileName, String texto, String imagenFile) throws FileNotFoundException, DocumentException, Exception{
        Document document = new Document();
        String nombre = fileName+".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(nombre));
        document.open();
        document.add(new Paragraph(texto));
        Image image = Image.getInstance(imagenFile);
        document.add(image);
        document.close();
    }
}
