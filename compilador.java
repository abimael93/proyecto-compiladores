import java.util.*;
import java.io.*;

public class compilador {
        
  Scanner Leer=new Scanner(System.in);
  
    public compilador() {
    }
    
    
    public static void main(String[] args) {
		int sigSimbolo;
        lexico lex = new lexico();
        
        sigSimbolo = -1;
        lex.abrir_archivo("entrada.txt");
        
        //El cero indica el fin de archivo y la E el error
        while( sigSimbolo != lex.E && sigSimbolo != 0 ) {
        	sigSimbolo = lex.siguienteSimbolo();
        	lex.imprimeEstado( sigSimbolo );
        }
        
        //Escritura de la salida
        FileWriter fichero = null;
        PrintWriter pw = null;
     	try {
         	fichero = new FileWriter("salida.txt",true);
         	pw = new PrintWriter(fichero);
 			if( sigSimbolo == 0 ) 
 				pw.println("1");
	         else
	        	pw.println("0");
 
        	} catch (Exception e) {
           		 e.printStackTrace();
        		}
         try{
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        
		lex.cerrar_archivo();
    }
}
