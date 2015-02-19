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
        }
        /*
		File archivo = new File("entrada.txt");
		try{
			//Código
			fr = new FileReader (archivo);
			br = new BufferedReader (fr);
			while( (linea = br.readLine() ) != null && sigSimbolo != lex.E ) {
				//System.out.println("Entró!");
				tamanioLinea = linea.length();
				System.out.println("Tamaño de la línea: " + tamanioLinea );
				sigSimbolo = -1;
				for( i = 0, lex.inicioSim = lex.finSim = 0 ; lex.finSim < tamanioLinea && sigSimbolo != lex.E && i < 2; lex.inicioSim = lex.finSim , i++) {
					//System.out.println("Entró1!");
					sigSimbolo = lex.recibeSimbolo( lex.siguienteSimbolo( linea , tamanioLinea ) );
					System.out.println("Puntero: " + lex.finSim );
					//System.out.println("Entró2!");
					System.out.print( sigSimbolo + ": " );
					lex.imprimeEstado( sigSimbolo );
					//break;
				}
				break;
			}
			if(sigSimbolo != lex.E ) {
				System.out.println("1");
			} else {
				System.out.println("0");
			}
			
		} catch (FileNotFoundException fnfe){
			//fnfe.printStackTrace();
			System.out.println("Error, no se encontró el archivo especificado!");
		} catch (IOException ioe){
			ioe.printStackTrace();
		}*/
		
		//System.out.println("hola!");
		lex.cerrar_archivo();
    }
}
