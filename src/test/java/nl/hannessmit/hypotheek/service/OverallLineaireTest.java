package nl.hannessmit.hypotheek.service;

import nl.hannessmit.hypotheek.domain.Hypotheek;
import nl.hannessmit.hypotheek.domain.LineaireHypotheek;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OverallLineaireTest {
    private HypotheekCalculatorService          hypotheekCalculator;
    private InkomstenBelastingCalculatorService ibCalculator;
    private EigenWoningForfaitCalculator        ewfCalculator;


    @Before
    public void setup(){
        hypotheekCalculator = new LineaireHypotheekCalculatorService();
        ibCalculator = new InkomstenBelastingCalculatorService();
        ewfCalculator = new EigenWoningForfaitCalculator();
    }

    /**
     * http://www.homefinance.nl/hypotheek/informatie/hypotheekrenteaftrek/hypotheekrenteaftrek-berekenen.asp
     */
    @Test
    public void test1(){
        int year = 2015;
        int startHypotheekJaar = 2015;
        int hypotheekWaarde = 200000;
        int wozWaarde = 200000;
        double jaarInkomen = 40000;
        int looptijd = 30;
        double rente = 5.0775757575 / 100.0;
        // hack:
        rente = 4/100.0; year = 2014; startHypotheekJaar = 2014;

        double inkomstenbelasting = ibCalculator.calcTax(year, jaarInkomen);
        System.out.println("IB: " + inkomstenbelasting);
        double ewf = ewfCalculator.calculate(year, wozWaarde);
        System.out.println("EWF: " + ewf);
        Hypotheek hypotheek = new LineaireHypotheek(looptijd, hypotheekWaarde, rente);

        double eersteMaandLast = hypotheekCalculator.calculateBrutoMaandlast(hypotheek, 1);
        System.out.println("bruto 1e maand: " + eersteMaandLast);

        double jaarRente = hypotheekCalculator.calculateHypotheekRenteJaar(hypotheek, (year-startHypotheekJaar+1));
        System.out.println("jaar rente: " + jaarRente);

        double jaarInkomenMetWoning = jaarInkomen - jaarRente + ewf;
        System.out.println("jaar inkomen met woning " + jaarInkomenMetWoning);

        double inkomstenbelastingMetHuis = ibCalculator.calcTax(year, jaarInkomenMetWoning);
        System.out.println("IB (+woning): " + inkomstenbelastingMetHuis);

        double fiscaalVoordeel = inkomstenbelasting - inkomstenbelastingMetHuis;
        System.out.println("FISCAAL JAARVOORDEEL " + fiscaalVoordeel);

        double fiscaalMaandVoordeel = fiscaalVoordeel / 12.0;
        System.out.println("FISCAAL MAANDVOORDEEL " + fiscaalMaandVoordeel);

        double nettoMaandlast = eersteMaandLast - fiscaalMaandVoordeel;
        System.out.println("Eerst netto maandlast " + nettoMaandlast);
        assertEquals(1003, nettoMaandlast, 0.5);


    }
}
