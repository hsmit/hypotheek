package nl.hannessmit.hypotheek.service;

import nl.hannessmit.hypotheek.domain.EigenWoningForfaitSchijf;
import nl.hannessmit.hypotheek.domain.EigenWoningForfaitTabel;

/**
 * http://www.belastingdienst.nl/wps/wcm/connect/bldcontentnl/belastingdienst/prive/woning/eigenwoningforfait/eigenwoningforfait_berekenen/eigenwoningforfait_berekenen
 */
public class EigenWoningForfaitCalculator {

    private EigenWoningForfaitTabel ewf2016;
    private EigenWoningForfaitTabel ewf2015;
    private EigenWoningForfaitTabel ewf2014;

    public EigenWoningForfaitCalculator(){
        ewf2016 = new EigenWoningForfaitTabel();
        ewf2016.getSchijven().add(new EigenWoningForfaitSchijf(0, 12500, a -> 0.0 * a));
        ewf2016.getSchijven().add(new EigenWoningForfaitSchijf(12500, 25000, a -> 0.003 * a));
        ewf2016.getSchijven().add(new EigenWoningForfaitSchijf(25000, 50000, a -> 0.0045  * a));
        ewf2016.getSchijven().add(new EigenWoningForfaitSchijf(50000, 75000, a -> 0.006  * a));
        ewf2016.getSchijven().add(new EigenWoningForfaitSchijf(75000, 1050000, a -> 0.0075 * a));
        ewf2016.getSchijven().add(new EigenWoningForfaitSchijf(1050000, Double.MAX_VALUE, a -> (7875 + (0.0235 * (a - 1050000)))));

        ewf2015 = new EigenWoningForfaitTabel();
        ewf2015.getSchijven().add(new EigenWoningForfaitSchijf(0, 12500, a -> 0.0 * a));
        ewf2015.getSchijven().add(new EigenWoningForfaitSchijf(12500, 25000, a -> 0.003 * a));
        ewf2015.getSchijven().add(new EigenWoningForfaitSchijf(25000, 50000, a -> 0.0045 * a));
        ewf2015.getSchijven().add(new EigenWoningForfaitSchijf(50000, 75000, a -> 0.006  * a));
        ewf2015.getSchijven().add(new EigenWoningForfaitSchijf(75000, 1050000, a -> 0.0075 * a));
        ewf2015.getSchijven().add(new EigenWoningForfaitSchijf(1050000, Double.MAX_VALUE, a -> (7875 + (0.0205 * (a - 1050000)))));

        ewf2014 = new EigenWoningForfaitTabel();
        ewf2014.getSchijven().add(new EigenWoningForfaitSchijf(0, 12500, a -> 0.0 * a));
        ewf2014.getSchijven().add(new EigenWoningForfaitSchijf(12500, 25000, a -> 0.0025 * a));
        ewf2014.getSchijven().add(new EigenWoningForfaitSchijf(25000, 50000, a -> 0.0040 * a));
        ewf2014.getSchijven().add(new EigenWoningForfaitSchijf(50000, 75000, a -> 0.0055  * a));
        ewf2014.getSchijven().add(new EigenWoningForfaitSchijf(75000, 1040000, a -> 0.0070 * a));
        ewf2014.getSchijven().add(new EigenWoningForfaitSchijf(1040000, Double.MAX_VALUE, a -> (7350 + (0.018 * (a - 1050000)))));

    }

    public double calculate(int year, double amount){
        if (year == 2014) {
            return ewf2014.calculate(amount);
        } else if (year == 2015){
            return ewf2015.calculate(amount);
        } else if (year == 2016) {
            return ewf2016.calculate(amount);
        }
        throw new RuntimeException("invalid year " + year);
    }
}
