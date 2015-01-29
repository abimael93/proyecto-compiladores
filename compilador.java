import java.util.*;

public class compilador {
        
  Scanner Leer=new Scanner(System.in);
  
    public compilador() {
    }
    
    
    public static void main(String[] args) {
    
        lexico lex= new lexico();
       
        lex.recibeSimbolo();
      
    }
}
