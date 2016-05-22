package nl.hannessmit.hypotheek.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Hannes on 21-5-2016.
 */
public class EigenWoningForfaitCalculatorTest {
    EigenWoningForfaitCalculator ewfc;

    @Before
    public void setup(){
        ewfc = new EigenWoningForfaitCalculator();
    }

    @Test
    public void testCalculate(){
        Assert.assertEquals(1953.0, ewfc.calculate(2014, 279000), 0.01);
        Assert.assertEquals(1575.0, ewfc.calculate(2016, 210000), 0.01);
        Assert.assertEquals(1500.0, ewfc.calculate(2015, 200000), 0.01);
    }
}
