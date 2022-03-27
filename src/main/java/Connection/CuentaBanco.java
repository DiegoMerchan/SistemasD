package Connection;

import java.io.Serializable;

public class CuentaBanco implements Serializable {

    private int num;
    private int saldo;

    public CuentaBanco(int num, int saldo) {
        this.num = num;
        this.saldo = saldo;

    }

    public CuentaBanco() {
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the saldo
     */
    public int getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

  
}
