import java.util.*;
import java.io.*;

public class compilador {
        
  Scanner Leer=new Scanner(System.in);
  
    public compilador() {
    }
    
    
    public static void main(String[] args) {
		int tamanioLinea, i, sigSimbolo = -1 ;
		FileReader fr;
		BufferedReader br;
		String linea = "";
        
        lexico lex= new lexico();
        
		File archivo = new File("entrada.txt");
		try{
			//C�digo
			fr = new FileReader (archivo);
			br = new BufferedReader (fr);
			while( (linea = br.readLine() ) != null && sigSimbolo != lex.E ) {
				//System.out.println("Entr�!");
				tamanioLinea = linea.length();
				System.out.println("Tama�o de la l�nea: " + tamanioLinea );
				sigSimbolo = -1;
				for( i = 0, lex.inicioSim = lex.finSim = 0 ; lex.finSim < tamanioLinea && sigSimbolo != lex.E && i < 2; lex.inicioSim = lex.finSim , i++) {
					//System.out.println("Entr�1!");
					sigSimbolo = lex.recibeSimbolo( lex.siguienteSimbolo( linea , tamanioLinea ) );
					System.out.println("Puntero: " + lex.finSim );
					//System.out.println("Entr�2!");
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
			System.out.println("Error, no se encontr� el archivo especificado!");
		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		
		//System.out.println("hola!");
    }
}
