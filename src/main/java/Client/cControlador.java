package Client;


import Connection.CuentaBanco;
import Connection.SaldoCliente;
import java.io.IOException;
import java.util.Scanner;

public class cControlador {

    static Scanner entrada = new Scanner(System.in);
    private static int n;

    public static void main(String[] args) throws IOException {

        // Desplegar menu para el usuario
        do {

            cControlador.menu();
            System.out.println("");
            n = cControlador.entrada.nextInt();
            entrada.nextLine();

            switch (n) {
                case 1:
                    cControlador.crearCuenta();
                    break;
                case 2:
                    cControlador.consultarSaldo();
                    break;
               
            }

        } while (n < 3);

    }

    // metodo para desplegar menu
    public static void menu() {
        System.out.println("BIENVENIDO BANCO XYZ\n");
        System.out.println("1. Crear cuenta.");
        System.out.println("2. Consultar Cuenta.");
        System.out.println("3. Salir \n");

    }



    
    //Método para crear una nueva cuenta
    public static void crearCuenta() throws IOException {

        Cliente cli = new Cliente(); //Se crea el Socket cliente

        // pedimos por consola los datos de la nueva cuenta
        System.out.println("Digite el numero de cuenta");
        int num = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Por favor digite el saldo inicial:\n");
        int saldo = entrada.nextInt();
        entrada.nextLine();
         CuentaBanco c = new CuentaBanco(num, saldo);

        System.out.println("Creando cuenta..\n");
        cli.NuevaCuenta(c);//Se inicia el cliente
    }

      
    
       //Método para consultar una nueva cuenta
    public static void consultarSaldo() throws IOException {

        Cliente cli = new Cliente(); //Se crea el Socket cliente

        // pedimos por consola los datos 
        System.out.println("Digite el numero de cuenta");
        int num = entrada.nextInt();
        entrada.nextLine();
       

        SaldoCliente s = new SaldoCliente(num);

        System.out.println("Consultando Saldo...\n");
        cli.Saldo(s);//Se inicia el cliente
    }

}
