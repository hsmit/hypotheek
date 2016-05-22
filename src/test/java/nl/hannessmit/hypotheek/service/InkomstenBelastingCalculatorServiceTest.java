package nl.hannessmit.hypotheek.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InkomstenBelastingCalculatorServiceTest {

    private InkomstenBelastingCalculatorService calculator;

    @Before
    public void setup(){
        calculator = new InkomstenBelastingCalculatorService();
    }

    @Test
    public void calcTax() throws Exception {
        assertEquals(15669.570, calculator.calcTax(2014, 40000), 0.01);
        assertEquals(15718.861, calculator.calcTax(2015, 40000), 0.01);
        assertEquals( 7312.599, calculator.calcTax(2016, 20000), 0.01);
    }

}