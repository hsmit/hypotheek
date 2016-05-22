package nl.hannessmit.hypotheek.domain;

import java.util.function.Function;

/**
 * Created by Hannes on 22-5-2016.
 */
public class InkomstenBelastingSchijf {
    private double min;
    private double max;
    private Function<Double, Double> f;

    public InkomstenBelastingSchijf(double min, double max, Function<Double, Double> f) {
        this.min = min;
        this.max = max;
        this.f = f;
    }

    public boolean applies(double amount) {
        if (amount < min) {
            return false;
        }
        return true;
    }

    public double calc(double amount) {
        double calcAmount = Math.min(amount - min, max - min);
        Double outcome = f.apply(calcAmount);
        String msg = String.format("amount %11.2f has %11.2f between %11.2f and %11.2f => %11.2f", amount, calcAmount, min, max, outcome);
        System.out.println(msg);
        return outcome;
    }
}
