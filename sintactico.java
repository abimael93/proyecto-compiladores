import java.io.*;

public class sintactico {

lexico lex = new lexico();
    public sintactico() {

    }

    public void call_lexico(){
    	
        lex.abrir_archivo("entrada.txt");
        program();
        System.out.println("GG IZI");
		lex.cerrar_archivo();
	}

	public void program(){

		lex.sigSimbolo();
		comprueba("program");
		if(lex.valorSimbolo==7)
		{
			lex.sigSimbolo();
		}
		else
			Error();
		comprueba(";");
		Declaracion();
		comprueba("begin");
		//Cuerpo();
		if(lex.valorSimbolo==22){}
		else
			Error();
	}

	public void Declaracion(){
		//Inicio declaracion variables
		if(lex.simbolo.equals("var")){
			lex.sigSimbolo();
			//Variables Inicio
			while(!lex.simbolo.equals("begin")){
				// inicio comprueba identificadores
				do{
					if(lex.valorSimbolo==7)
					{
						lex.sigSimbolo();				
					}
					else
					Error();
					if(lex.simbolo.equals(",")){
						lex.sigSimbolo();
					}
				}while(!lex.simbolo.equals(":"));
				//fin comprueba identificadores
				comprueba(":");
				//inicio comprueba tipo de dato
				if(lex.valorSimbolo==113||lex.valorSimbolo==115)
				{
					lex.sigSimbolo();
				}
				else
					Error();
				//fin comprueba tipo de dato
				comprueba(";");
			}//fin variables
		}//fin declaracion variables
	}

	public void Cuerpo(){
		while(!lex.simbolo.equals("end")){

		}
		lex.sigSimbolo();
	}

	public void comprueba(String simbolo){

		if(simbolo.equals(lex.simbolo)){
			lex.sigSimbolo();
		}
		else{
			Error();
		}
		
	}
	public void Error()
	{
		System.out.println("Error:"+lex.simbolo);
		System.exit(0); 
	}


}