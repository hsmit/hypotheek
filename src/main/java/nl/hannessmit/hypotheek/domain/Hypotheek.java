package nl.hannessmit.hypotheek.domain;

/**
 * Created by Hannes on 18-5-2016.
 */
public interface Hypotheek {

    void setDurationYears(int durationYears);

    int getDurationYears();

    void setLeenbedrag(double leenbedrag);

    double getLeenbedrag();

    void setJaarRente(double jaarRente);

    double getJaarRente();
}
