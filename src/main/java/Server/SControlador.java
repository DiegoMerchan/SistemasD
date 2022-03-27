package Server;

import java.io.IOException;
import java.sql.SQLException;

public class SControlador {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

           
       while (true){        
        Servidor serv = new Servidor(); // se crea el servidor
        System.out.println("Iniciando servidor...\n ");
        serv.startServer();
       }

    }
}
