package nl.hannessmit.hypotheek.domain;

/**
 * Created by Hannes on 18-5-2016.
 */
public class LineaireHypotheek implements Hypotheek {

    private int durationYears;

    private double leenbedrag;

    private double jaarRente;  //TODO: er is blijkbaar onderscheid tussen nominale en effectieve jaarRente. Ik weet nog niet wat het verschil is

    public LineaireHypotheek(){}
    public LineaireHypotheek(int duration, double leenbedrag, double jaarRente) {
        this.durationYears = duration;
        this.leenbedrag = leenbedrag;
        this.jaarRente = jaarRente;
    }

    public void setDurationYears(int durationYears) {
        this.durationYears = durationYears;
    }

    public int getDurationYears() {
        return durationYears;
    }

    public void setLeenbedrag(double leenbedrag) {
        this.leenbedrag = leenbedrag;
    }

    public double getLeenbedrag() {
        return leenbedrag;
    }

    public void setJaarRente(double jaarRente) {
        this.jaarRente = jaarRente;
    }

    public double getJaarRente() {
        return jaarRente;
    }
}
