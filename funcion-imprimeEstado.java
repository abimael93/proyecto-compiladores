public void imprimeEstado( int estado ) {
    switch ( estado ) {
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