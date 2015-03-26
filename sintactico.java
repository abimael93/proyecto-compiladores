import java.io.*;

public class sintactico {

lexico lex = new lexico();
    public sintactico() {

    }

    public void call_lexico(){
    	
        lex.abrir_archivo("entrada.txt");
        program();
        System.out.println("All works -> syntax OK!");
		lex.cerrar_archivo();
	}

	public void program(){
		//Inicio Declaracion de nombre del Programa(Obligatoria)
		lex.sigSimbolo();
		comprueba("program");
		if(lex.valorSimbolo==7)
		{
			lex.sigSimbolo();
		}
		else
			Error();
		comprueba(";");//Fin Declaracion de nombre del Programa(Obligatoria)

		Declaracion();
		//fin Deckaracion de variables (Opcional)

		comprueba("begin");
		if(!lex.simbolo.equals("end."))
			{
					Cuerpo();
			}

		comprueba("end.");
		
	}

	public void Declaracion(){
		//Inicio declaracion variables
		if(lex.simbolo.equals("var")){
			lex.sigSimbolo();
			//Variables Inicio
			while(!lex.simbolo.equals("begin")){
				// inicio comprueba identificadores
				do{
					Identificador();
					if(lex.simbolo.equals(",")){
						lex.sigSimbolo();
					}
				}while(!lex.simbolo.equals(":"));
				//fin comprueba identificadores
				comprueba(":");
				//inicio comprueba tipo de dato
				Tipo_Dato();
				//fin comprueba tipo de dato
				comprueba(";");
			}//fin variables
		}//fin declaracion variables
	}


	public void Cuerpo(){

		Sentencia();
		if(lex.valorSimbolo==1)
		{
			lex.sigSimbolo();
			Cuerpo();
		}
	
	}
	

	public void Sentencia()
	{
		
		switch(lex.valorSimbolo)
		{
			case 7:
			Sentencia_Asignacion();
			break;

			case 100:
			Sentencia_if();
			break;

			case 108:
			Sentencia_writeln();
			break;

			case 109:
			Sentencia_readln();
			break;

			default:
			break;
		}

	}

	public void Sentencia_Asignacion(){
		if(lex.valorSimbolo==7)
		{
			lex.sigSimbolo();
			comprueba(":=");
			Expresion();
		}
	}
	
	public void Sentencia_readln(){
		if(lex.valorSimbolo==109)
		{
			lex.sigSimbolo();
			if(lex.valorSimbolo==11)
			{
				lex.sigSimbolo();
				Variables();
				comprueba(")");

			}
		}
	}

	public void Variables(){
		if(lex.valorSimbolo==7)
			{
				lex.sigSimbolo();
				if(lex.simbolo.equals(",")){
						lex.sigSimbolo();
						Variables();
					}
				
			}
	}
	public void Sentencia_writeln(){
		if(lex.valorSimbolo==108)
		{
			lex.sigSimbolo();
			if(lex.valorSimbolo==11)
			{
				lex.sigSimbolo();
				Expresion();
				comprueba(")");

			}
		}

	}

	
	public void Sentencia_if()
	{
		if(lex.valorSimbolo==100)
		{
			lex.sigSimbolo();
			Expresion();
			comprueba("then");
			Sentencia();
			Else();
		}
	}

	
	public void Else() 
	{	
		if(lex.valorSimbolo==103){
			System.out.println("Else");
			lex.sigSimbolo();
			Sentencia();
		}

	}

	//Funcion para los tipos de expresiones falta evaluar los operadores de asignacion
	public void Expresion(){

		if(lex.valorSimbolo==7 ||lex.valorSimbolo==8||lex.valorSimbolo==10)//compromar un identificador, o un numero(entero,real)
			{
				lex.sigSimbolo();
				Exp_Aritmetica();
			}

			
	}

	public void Exp_Aritmetica(){
		if(lex.valorSimbolo==2)
		{	
			lex.sigSimbolo();
			if(lex.valorSimbolo==7 ||lex.valorSimbolo==8||lex.valorSimbolo==10)//compromar un identificador, o un numero(entero,real)
			{
				lex.sigSimbolo();
				
			}
			Exp_Aritmetica();
		}
		
	}


	public void Identificador(){
		if(lex.valorSimbolo==7)
			{
				lex.sigSimbolo();				
			}
		else
			Error();
	}

	public void Tipo_Dato(){
		if(lex.valorSimbolo==113||lex.valorSimbolo==115)
			{
				lex.sigSimbolo();
			}
		else
			Error();
	}

	public void comprueba(String simbolo){
		if(simbolo.equals(lex.simbolo))
		{
			lex.sigSimbolo();
		}
		else{
			Error();
		}
		
	}
	public void Error()
	{
		System.out.println("Error:"+lex.simbolo);
		lex.sigSimbolo();
		System.out.println("Siguiente:"+lex.simbolo);
		System.exit(0); 
	}


}