/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Connection.CuentaBanco;
import Connection.SaldoCliente;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase dedicada a realizar todas las operaciones sobre las bases de datos. 
 */
public class OperacionesBD {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //
    static final String DB_URL = "jdbc:mysql://remotemysql.com:3306/1u4u1bXpuT";
    static final String USER = "1u4u1bXpuT";
    static final String PASS = "UrL8CdwyUi";
    static Connection conn = null;
    static Statement stmt = null;
    static String SQL = "";

    //Método de conexión a la base de datos
    public static boolean conexion() {

        boolean conexion = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            conexion = true;
        } catch (Exception e) {
            System.out.println(e);
            conexion = false;
        }
        return conexion;
    }



    //Método para crear en la base de datos la cuenta y relacionarla con un cliente
    public static void InsertarCuenta(CuentaBanco n, ObjectOutputStream s) throws IOException {

        if (!conexion()) {
            System.out.println("Error al conectar a la base de datos");
        } else {
            try {
                conn.setAutoCommit(false);
                System.out.println("Iniciando insercion de datos");
                SQL = "insert into movimientos (cuenta, saldo) values ("
                        + n.getNum()
                        +","
                        + n.getSaldo()
                        + ");";
                stmt.executeUpdate(SQL);
                conn.commit();
                System.out.println("CUENTA CREADA CON EXITO");
                s.writeObject("msn desde el servidor: Cuenta creada con exito");

            } catch (SQLException ex) {
                try {
                    System.out.println(ex.getMessage());
                    System.out.println("ERROR LOS DATOS NO SE GUARDARON");
                    s.writeObject("msn desde el servidor: Error, la cuenta no se creo");
                    conn.rollback();

                } catch (SQLException ex1) {
                    Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex1);
                }

            } finally {
                System.out.println("Transaccion terminada");
            }
        }

    }

    
        public static void saldo(SaldoCliente l, ObjectOutputStream s) throws IOException {

        if (!conexion()) {
            System.out.println("Error al conectar a la base de datos");
        } else {
            
            
            try {
                conn.setAutoCommit(false);
                System.out.println("Iniciando consulta de saldo");
                SQL = "SELECT movimientos.saldo FROM movimientos WHERE movimientos.cuenta =" + l.getNumeroCuenta() + ";"
                        ;
                ResultSet rs = stmt.executeQuery(SQL);
                conn.commit();
               
                if (rs.next()) {
                     int x = rs.getInt(1);
                     System.out.println("CONSULTA EXITOSA. saldo:" + x);
                     s.writeObject("msn desde el servidor: el saldo es: " + x );
                    
                }
               

            } catch (SQLException ex) {
                try {
                    System.out.println(ex.getMessage());
                    System.out.println("ERROR la consulta no se hizo");
                    s.writeObject("msn desde el servidor: Error, la consulta no se hizo");
                    conn.rollback();

                } catch (SQLException ex1) {
                    Logger.getLogger(OperacionesBD.class.getName()).log(Level.SEVERE, null, ex1);
                }

            } finally {
                System.out.println("Transaccion terminada");
            }
        }

    }
        
}    
        


