import java.util.*;
import java.io.*;

public class compilador {
        
  Scanner Leer=new Scanner(System.in);
  
    public compilador() {
    }
    
    
    public static void main(String[] args) {
		sintactico sin = new sintactico();
    	sin.call_lexico();
    }
}
