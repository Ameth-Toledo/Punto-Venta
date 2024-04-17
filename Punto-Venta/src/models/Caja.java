package models;

public class Caja {

    private double saldoTotal;
    private double apertura;
    private double arqueo;


    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public Caja() {
    }
    public double getApertura() {
        return apertura;
    }

    public void setApertura(double apertura) {
        this.apertura = apertura;
    }

    public void sumarSaldo(double monto){
        saldoTotal += monto;
    }


}