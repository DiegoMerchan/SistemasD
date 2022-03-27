package Connection;

import java.io.Serializable;


public class SaldoCliente implements Serializable{
    
    private int numeroCuenta;
 

    public SaldoCliente(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        
    }

    public SaldoCliente() {
    }

    /**
     * @return the numeroCuenta
     */
    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
   
    
}
