package Server;


import Connection.CuentaBanco;
import Connection.SaldoCliente;
import Connection.Sconnector;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor extends Sconnector {

 
    CuentaBanco nuevaCuenta;
    String tipo;
    SaldoCliente saldo;

    public Servidor() throws IOException {
        super("servidor"); // iniciamos una instancia de Socket tipo servidor
    }

    public void startServer() throws ClassNotFoundException { // Con este metodo iniciamos el server

        
                try {
                    System.out.println("Esperando conexión en puerto " + ss.getLocalPort()); //Esperando conexión
                    cs = ss.accept(); //Accept comienza el socket y espera una conexión desde un cliente
                    entradaCliente = new ObjectInputStream(cs.getInputStream()); // inicializamos socket
                    salidaCliente = new ObjectOutputStream(cs.getOutputStream());
                    System.out.println("Cliente en línea");
                    tipo = (String) entradaCliente.readObject();
                    if ("crearCuenta".equals(tipo)) {
                        System.out.println("Ejecutando crear nueva cuenta...\n");
                        crearCuenta();
                    }
                    if ("saldo".equals(tipo)) {
                        System.out.println("Ejecutando consulta de saldo...\n");
                        saldo();
                    }

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
        

    }


    //Método para crear cuenta 
    private void crearCuenta() throws ClassNotFoundException {

        try {

            nuevaCuenta = (CuentaBanco) entradaCliente.readObject();
            System.out.println("Recibido en el servidor una nueva cuenta: " + nuevaCuenta.getNum());
            OperacionesBD.InsertarCuenta(nuevaCuenta, salidaCliente);
            close(); // Cerramos conexion 
            System.out.println("Fin de la conexión");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

     
       
    //Metodo para consultar el saldo
    private void saldo() throws ClassNotFoundException {

        try {

            saldo = (SaldoCliente) entradaCliente.readObject();
            System.out.println("Recibido en el servidor una consulta de saldo: " + saldo.getNumeroCuenta());
            OperacionesBD.saldo(saldo, salidaCliente);
            close(); // Cerramos conexion 
            System.out.println("Fin de la conexión");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void close() {

        try {
            ss.close();//Se finaliza la conexión con el cliente
            entradaCliente.close();
            cs.close();
            salidaCliente.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
