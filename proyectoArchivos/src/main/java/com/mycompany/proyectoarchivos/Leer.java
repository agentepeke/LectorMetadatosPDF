/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoarchivos;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peke
 */
public class Leer {
    visualizador ruta;
    
    
    
  /*  static void leer()throws IOException{
    RandomAccessFile archivo = new RandomAccessFile("Lectorpdfd.bin", "rw");
        archivo.seek(archivo.length());
    }*/
        
        // Conseguimos la ubicacion de la tabla ref
    public String tablaReferencia(String ruta, long retroceder) {
        String tabla = "";
        try {
            RandomAccessFile archivoPdf = new RandomAccessFile(ruta, "rw");
            if (retroceder == -1) {
                retroceder = archivoPdf.length() - 188;//ubicar la posicion de la xref util
                archivoPdf.seek(retroceder);
                boolean OA = false;
                boolean OD = false;


                while (!OA) {
                    byte B[] = new byte[1];
                    archivoPdf.read(B);
                    if ("a".equals(Integer.toHexString(B[0]))) {
                        OA = true;
                    } else {
                        retroceder -= 1;
                    }

                    archivoPdf.seek(retroceder);
                }

                OA = false;
                archivoPdf.seek(retroceder + 1);

                while (!OD) {
                    byte B[] = new byte[1];
                    archivoPdf.read(B);
                    if ("d".equals(Integer.toHexString(B[0]))) {
                        OD = true;
                    } else {
                        tabla += new String(B);

                    }
                }
                System.out.println(tabla + "---- XREF----");

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tabla;
        
    }
    // tenemos que usar la ubicacionde la Xref
    public String Info(String ruta, long retroceder) {
        
        String tablaInfo = "";
        try {
            RandomAccessFile archivoPdf = new RandomAccessFile(ruta, "rw");
            if (retroceder == -1) {
                retroceder = archivoPdf.length() - 282;
                archivoPdf.seek(retroceder);
                boolean aux1 = false;
                boolean aux2 = false;

                while (!aux1) {
                    byte B[] = new byte[1];
                    archivoPdf.read(B);
                    
                    if ("/".equals(Integer.toHexString(B[0]))) {
                        aux1 = true;
                    } else {
                        retroceder -= 1;
                    }

                    archivoPdf.seek(retroceder);
                }

                aux1 = false;
                archivoPdf.seek(retroceder + 1);

                while (!aux2) {
                    byte B[] = new byte[1];
                    archivoPdf.read(B);
                    if ("/".equals(Integer.toHexString(B[0]))) {
                        aux2 = true;
                    } else {
                        tablaInfo += new String(B);

                    }
                }
                System.out.println(tablaInfo + "---- INFO----");

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Leer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tablaInfo;
        
    }
    
    
/*public void BuscarTabla(String ruta) throws IOException{

        RandomAccessFile archivoPdf = new RandomAccessFile( ruta , "rw");
        String trailer = "trailer";
        
        archivoPdf.seek(0);
        while(archivoPdf.getFilePointer() < archivoPdf.length()){
            if (archivoPdf.readUTF().equals( "trailer")){
                archivoPdf.skipBytes(5);
                archivoPdf.readInt();
                
                

            }
            System.out.println(archivoPdf.readInt());
        }
         
    }
*/
    public boolean Existe() {
        File archivo = new File("Lectorpdfd.bin");
        return archivo.exists();
    }
    
       
}
