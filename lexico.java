import java.io.*;

public class lexico extends compilador {

    public lexico() {
    	
    }
    
	String linea	= "";
	int E			= 100;
	int[][] tabla 	=
		{
			{E,1,2,3,5,5,E,7,8,E,9},
			{E,E,E,E,E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E,E,E,E,E},
			{E,E,E,E,4,E,E,E,E,E,E},
			{E,E,E,E,E,E,E,E,E,E,E},
			{E,E,E,E,6,E,E,E,E,E,E},
			{E,E,E,E,E,E,E,E,E,E,E},
			{E,E,E,E,E,E,E,7,7,E,E},
			{E,E,E,E,E,E,E,E,8,9,E},
			{E,E,E,E,6,E,E,E,10,E,E},
			{E,E,E,E,E,E,E,E,10,E,E},
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
			//fnfe.printStackTrace();
			System.out.println("Error, no se encontr� el archivo especificado!");
		}
	}
	
	public void cerrar_archivo( ) {
		try {
            if( null != fr ) {
               fr.close();    
            }
    	} catch (Exception e2) {
        	e2.printStackTrace();
     	}
	}
    		
    public int analizaSimbolo( String cadena ) {
    	int i, estado = 0, valor = 0;
    	char c;
    	//System.out.println("Ingresa una cadena\n");
    	//Cadena=Leer.nextLine();
    	estado = analizaReservada( cadena );
    	
    	if( estado < 1 ) {
	    	for( i = 0 ; i < cadena.length() && estado != E; i++ ) {
	    		c		= cadena.charAt( i );
	    		valor 	= analizaCaracter( c );
	    		estado	= tabla[estado][valor];
	    	}
    	}
    	
    	return estado;
    	//imprimeEstado( estado );
    }
    
    public int siguienteSimbolo() {
    	String cadena = "";
    	char c;
    	
    	if( punteroLinea == -1 ) {
    		if( (linea = br.readLine()) == null ) {
    			return 0;
    		}
    		
    		while( linea.trim().length() <= 0 ) {
    			if( (linea = br.readLine()) == null ) {
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
    				if( linea.charAt( punteroLinea + 1 ) == '=' ) {
    					cadena += linea.charAt( punteroLinea );
    				}
    				break;
    			case ' ':
    				while( linea.trim().length() <= 0 ) {
    					if( (linea = br.readLine()) == null ) {
			    			return 0;
			    		}
    				}
    				break;
	    		case '+': case '-': case '*': case '/': case '(': case ')':
	    			cadena += linea.charAt( punteroLinea++ );
	    			break;
    		}
    		punteroLinea++;
    	} else {
    		do {
    			cadena += linea.charAt( punteroLinea++ );
    		}while( punteroLinea < tam && !esSeparador( linea.charAt( punteroLinea ) ) )
    	}
    	
    	if( punteroLinea == tam ) {
    		punteroLinea = -1;
    	}
    	
    	/*
    	while( ( linea.charAt(  ) == ' ' ) && finSim < tam ) {
    		finSim++;
    		inicioSim++;
    	}
    	
    	//System.out.println( "Entr�3!" );
    	if( finSim < tam ) {
    		//if()
    		while( ( finSim < tam && !esSeparador( linea.charAt( finSim ) ) )  ) {
	    		c 		= linea.charAt( finSim );
	    		cadena += c;
	    		finSim++;
	    	}
    	}*/
    	
    	//System.out.println("Entr�!4: " + cadena );
    	
    	//Regresamos el substring
    	return analizaSimbolo( cadena );
    }
    
    public boolean esSeparador( char c ) {
    	switch( c ) {
    		case '>': case '<': case '!': case '=': case ' ':
    		case '+': case '-': case '*': case '/': case '(':
    		case ')':
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
	        case 1:
	            System.out.println("Delimitador");
	            break;
	        case 2:
	            System.out.println("Operador Aritm�tico");
	            break;
	        case 3:
	            System.out.println("Declaracion");
	            break;
	        case 4:
	            System.out.println("Operador Asignaci�n");
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
	        case 20:
	            System.out.println("Operador L�gico");
	            break;
	        case 21:
	            System.out.println("Palabra Reservada");
	            break;
	        case 22:
	            System.out.println("Punto");//Final del Programa
	            break;
	        case 100:
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
    	}
    	return valor;
    }

}