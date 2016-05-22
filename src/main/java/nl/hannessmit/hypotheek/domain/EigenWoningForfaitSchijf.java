package nl.hannessmit.hypotheek.domain;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Hannes on 21-5-2016.
 */
public class EigenWoningForfaitSchijf {
    double min;
    double max;
    Function<Double, Double> f;

    public EigenWoningForfaitSchijf(double min, double max, Function<Double, Double> f) {
        this.min = min;
        this.max = max;
        this.f = f;
    }

    public boolean applies(double amount){
        if (amount <= min){
            return false;
        }
        if (amount > max) {
            return false;
        }
        return true;
    }

    public double calc(double amount){
        return f.apply(amount);
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }
}
