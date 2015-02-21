import java.io.*;

public class lexico extends compilador {

    public lexico() {
    	
    }
    
	String linea	= "";
	int E			= 100;
	int[][] tabla 	=
		{
	/*Estados*/
	 			{E,1,2,3,5,5,E,7,8,E,9,11,12},//0
				{E,E,E,E,E,E,E,E,E,E,E,E,E},//1
				{E,E,E,E,E,E,E,E,E,E,E,E,E},//2
				{E,E,E,E,4,E,E,E,E,E,E,E,E},//3
				{E,E,E,E,E,E,E,E,E,E,E,E,E},//4
				{E,E,E,E,6,E,E,E,E,E,E,E,E},//5
				{E,E,E,E,E,E,E,E,E,E,E,E,E},//6
				{E,E,E,E,E,E,E,7,7,E,E,E,E},//7
				{E,E,E,E,E,E,E,E,8,9,E,E,E},//8
				{E,E,E,E,6,E,E,E,10,E,E,E,E},//9
				{E,E,E,E,E,E,E,E,10,E,E,E,E},//10
				{E,E,E,E,E,E,E,E,E,E,E,E,E},//11
				{E,E,E,E,E,E,E,E,E,E,E,E,E},//12
		};
	int punteroLinea = -1, tam;
	File archivo;
	FileReader fr;
	BufferedReader br;
	
	public void abrir_archivo( String nombre_archivo ) {
		File archivo = new File(nombre_archivo);
		try {
			fr = new FileReader (archivo);
			br = new BufferedReader (fr);
		} catch (FileNotFoundException fnfe) {
			System.out.println("Error, no se encontró el archivo especificado!");
		}
	}
	
	public void cerrar_archivo( ) {
		try {
            if( null != fr ) {
               fr.close();    
            }
    	} catch (Exception e) {
        	e.printStackTrace();
     	}
	}
    		
    public int analizaSimbolo( String cadena ) {
    	int i, estado = 0, valor = 0;
    	char c;
    
    	estado = analizaReservada( cadena );
    	
    	if( estado < 1 ) {
	    	for( i = 0 ; i < cadena.length() && estado != E; i++ ) {
	    		c		= cadena.charAt( i );
	    		valor 	= analizaCaracter( c );
	    		estado	= tabla[estado][valor];
	    	}
    	}
    	
    	return estado;
   
    }
    
    public int siguienteSimbolo() {
    	String cadena = "";
    	char c;
    	
    	if( punteroLinea == -1 ) {
    		if( (linea = leerLinea()) == null ) {
    			return 0;
    		}
    		
    		while( linea.trim().length() <= 0 ) {
    			if( (linea = leerLinea()) == null ) {
	    			return 0;
	    		}
    		}
    		
    		punteroLinea 	= 0;
    		linea			= linea.trim();
    		tam 			= linea.length();
    	}
    	
    
    	if( linea.charAt( punteroLinea ) == ' ' ) {
    		linea = linea.substring( punteroLinea, tam ).trim();
    		punteroLinea 	= 0;
    		linea 			= linea.trim();
    		tam				= linea.length();
    	}
    	
    	if( esSeparador( linea.charAt( punteroLinea ) ) ) {
    		switch( linea.charAt( punteroLinea ) ) {
    			case '>': case '<': case '!': case '=':
    				cadena += linea.charAt( punteroLinea++ );
    				if(  punteroLinea < tam && linea.charAt( punteroLinea ) == '=' ) {
    					cadena += linea.charAt( punteroLinea );
    					punteroLinea++;
    				}
    				break;
    			case ' ':
    				while( linea.trim().length() <= 0 ) {
    					if( (linea = leerLinea()) == null ) {
			    			return 0;
			    		}
    				}
    				break;
	    		case '+': case '-': case '*': case '/': case '(': case ')':
	    			cadena += linea.charAt( punteroLinea++ );
	    		
	    			break;
    		}
    	} else {
    		do {
    			cadena += linea.charAt( punteroLinea++ );
    		}while( punteroLinea < tam && !esSeparador( linea.charAt( punteroLinea ) ) );
    	}
    	
    	if( punteroLinea == tam ) {
    
    		punteroLinea = -1;
    	}
    	
    
    	
    	//Regresamos el substring
    	return analizaSimbolo( cadena );
    }
    
    public String leerLinea( ) {
    	
    	try {
    	return br.readLine();
    	} catch (IOException ioe) {
    		ioe.printStackTrace();
    	}
    	return null;
    }
    
    public boolean esSeparador( char c ) {
    	switch( c ) {
    		case '>': case '<': case '!': case '=': case ' ':
    		case '+': case '-': case '*': case '/': case '(':
    		case ')': case '?': case '¿': case '{': case '}':
    			return true;
    	}
    	return false;
    }
    
    public int analizaReservada(String palabra) {
    	int valor=0;
    	switch(palabra)
    	{
    		case "and":case "or":case "not":
    			valor=20;
    			break;
    		case "if": case "then":case "while":case "else":case "do":case "for":case "begin":case "end":
    		case "writeln":case "readln":case "var":case "program":case "char":case "integer":case "byte":
    		case "Real":case "Boolean":case "String": case "word":case "longint":case "shortint":
    			valor=21;
    			break;
    		case ".":
    			valor=22;
    			break;
    			
    	}
    	return valor;
    	
    }
    
    public void imprimeEstado( int estado ) {
	    switch ( estado ) {
	    	case 0://Fin de Archivo
	    		break;
	        case 1:
	            System.out.println("Delimitador");
	            break;
	        case 2:
	            System.out.println("Operador Aritmético");
	            break;
	        case 3:
	            System.out.println("Declaracion");
	            break;
	        case 4:
	            System.out.println("Operador Asignación");
	            break;
	        case 5: case 6:
	            System.out.println("Operador Relacional");
	            break;
	        case 7:
	            System.out.println("Identificador");
	            break;
	        case 8:
	            System.out.println("Entero");
	            break;
	        case 10:
	            System.out.println("Real");
	            break;
	        case 11:
	            System.out.println("Parentesis-Izq");
	            break;
	        case 12:
	            System.out.println("Parentesis-Der");
	            break;
	        case 20:
	            System.out.println("Operador Lógico");
	            break;
	        case 21:
	            System.out.println("Palabra Reservada");
	            break;
	        case 22:
	            System.out.println("Punto");//Final del Programa
	            break;
	        default:
	            System.out.println("Error");
	            break;
    	}
	}
    public int analizaCaracter(char caracter)
    {
    	int valor=0;
    	if(Character.isLetter(caracter))
    		return valor=7;
    	else if(Character.isDigit(caracter))
    		return valor=8;
    
    	else switch(caracter)
    	{
    		case ',': case';':
    			valor=1;
    			break;
    		case ':':
    			valor=3;
    			break;
    		case '=':
    			valor=4;
    			break;
    		case '+':case '-':case '*':case '/':
    			valor=2;
    			break;
    		case '<':case '>':
    			valor=5;
    			break;
    		case '_':
    			valor=7;
    			break;
    		case '.':
    			valor=9;
    			break;
    		case '!':
    			valor=10;
    			break;
    		case '(':
    			valor=11;
    			break;
    		case ')':
    			valor=12;
    			break;
    	}
    	return valor;
    }

}