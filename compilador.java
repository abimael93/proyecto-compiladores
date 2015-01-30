import java.util.*;

public class compilador {
        
  Scanner Leer=new Scanner(System.in);
  
    public compilador() {
    }
    
    
    public static void main(String[] args) {
    	 int cont =10;
        lexico lex= new lexico();
       	do{
        lex.recibeSimbolo();
       	}while(cont<100);
    }
}
