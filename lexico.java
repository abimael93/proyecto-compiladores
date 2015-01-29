
public class lexico extends compilador {

    public lexico() {
    	
    }
    
  		String Cadena="";
    	int E=100;
    	int[][] tabla=
    		{
    			{E,1,2,3,5,5,E,7,8,E},
    			{E,E,E,E,E,E,E,E,E,E},
    			{E,E,E,E,E,E,E,E,E,E},
    			{E,E,E,E,4,E,E,E,E,E},
    			{E,E,E,E,E,E,E,E,E,E},
    			{E,E,E,E,6,6,E,E,E,E},
    			{E,E,E,E,E,E,E,E,E,E},
    			{E,E,E,E,E,E,E,7,7,E},
    			{E,E,E,E,E,E,E,E,8,9},
    			{E,E,E,E,E,E,E,E,10,E},
    			{E,E,E,E,E,E,E,E,10,E},
    		};
    		
    public void recibeSimbolo()
    {
    	int i,estado=0,valor=0;
    	char c;
    	System.out.println("Ingresa una cadena\n");
    	Cadena=Leer.nextLine();
    	for(i=0;i<Cadena.length();i++)
    	{
    		c=Cadena.charAt(i);
    		valor=analizaCaracter(c);
    		estado=tabla[estado][valor];
    	}
    	
    }
    
    public int analizaCaracter(char caracter)
    {
    	int valor=-1;
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
    		case '<':case '>':case '!':
    			valor=5;
    			break;
    		case '_':
    			valor=7;
    			break;
    		case '.':
    			valor=9;
    			break;
    		
    	}
    	return valor;
    }

}