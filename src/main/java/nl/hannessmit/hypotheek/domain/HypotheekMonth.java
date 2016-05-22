package nl.hannessmit.hypotheek.domain;

/**
 * Created by Hannes on 18-5-2016.
 */
public class HypotheekMonth {
    private int month;
    private double aflossing;
    private double rente;
    private double bruto;

    public String toString(){
        return String.format("%s  %.2f  %.2f  %.2f", month, aflossing, rente, bruto);
    }

    public void setAflossing(double aflossing) {
        this.aflossing = aflossing;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public double getAflossing() {
        return aflossing;
    }

    public double getRente() {
        return rente;
    }

    public void setRente(double rente) {
        this.rente = rente;
    }

    public double getBruto() {
        return bruto;
    }

    public void setBruto(double bruto) {
        this.bruto = bruto;
    }
}
