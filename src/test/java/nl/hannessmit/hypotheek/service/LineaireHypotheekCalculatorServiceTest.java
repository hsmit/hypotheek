package nl.hannessmit.hypotheek.service;

import static org.junit.Assert.*;

import nl.hannessmit.hypotheek.domain.Hypotheek;
import nl.hannessmit.hypotheek.domain.LineaireHypotheek;
import nl.hannessmit.hypotheek.domain.HypotheekTable;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Hannes on 18-5-2016.
 */
public class LineaireHypotheekCalculatorServiceTest {

    private Hypotheek hypotheek;
    private HypotheekCalculatorService hypotheekCalculatorService;

    @Before
    public void setUp(){
        hypotheekCalculatorService = new LineaireHypotheekCalculatorService();

        hypotheek = new LineaireHypotheek();
        hypotheek.setDurationYears(30);
        hypotheek.setLeenbedrag(150000);
        hypotheek.setJaarRente(0.06);
        //hypotheek = new LineaireHypotheek(30, 150000, 0.06);
    }

    @Test
    public void getAflossing() throws Exception {
        assertEquals(416.67, hypotheekCalculatorService.calculateAflossingForMonth(hypotheek, 1), 0.01);
        assertEquals(416.67, hypotheekCalculatorService.calculateAflossingForMonth(hypotheek, 2), 0.01);
        assertEquals(416.67, hypotheekCalculatorService.calculateAflossingForMonth(hypotheek, 360), 0.01);
        assertEquals(0, hypotheekCalculatorService.calculateAflossingForMonth(hypotheek, 0), 0.01);
        assertEquals(0, hypotheekCalculatorService.calculateAflossingForMonth(hypotheek, 361), 0.01);
    }

    @Test
    public void calculateRenteForMonth() throws Exception {
        assertEquals(750.0, hypotheekCalculatorService.calculateRenteForMonth(hypotheek, 1), 0.01);
        assertEquals(747.92, hypotheekCalculatorService.calculateRenteForMonth(hypotheek, 2), 0.01);
        assertEquals(414.58, hypotheekCalculatorService.calculateRenteForMonth(hypotheek, 162), 0.01);
        assertEquals(2.08, hypotheekCalculatorService.calculateRenteForMonth(hypotheek, 360), 0.01);
        assertEquals(0, hypotheekCalculatorService.calculateRenteForMonth(hypotheek, 0), 0.01);
        assertEquals(0, hypotheekCalculatorService.calculateRenteForMonth(hypotheek, 361), 0.01);
    }

    @Test
    public void calculateRenteForYear() throws Exception {
        double totalRente = 0;
        for (int i = 1; i<=12; i++){
            double monthRente = hypotheekCalculatorService.calculateRenteForMonth(hypotheek, i);
            totalRente += monthRente;
        }
        assertEquals(8862.50, totalRente, 0.01);
    }

    @Test
    public void calculateBrutoMaandLast() throws Exception {
        assertEquals(1166.67, hypotheekCalculatorService.calculateBrutoMaandlast(hypotheek, 1), 0.01);
        assertEquals(0, hypotheekCalculatorService.calculateBrutoMaandlast(hypotheek, 0), 0.01);
        assertEquals(0, hypotheekCalculatorService.calculateBrutoMaandlast(hypotheek, 361), 0.01);
    }

    @Test
    public void showTable() throws Exception {
        Hypotheek hypotheek1 = new LineaireHypotheek(30, 210000, 0.019);

        HypotheekTable hypotheekTable = hypotheekCalculatorService.createLineaireHypotheekTabel(hypotheek1);
        System.out.println("jr, aflos, rente, bruto");
        System.out.println(hypotheekTable.toString());
    }

    @Test
    public void berekenHypotheekRenteAftrekVoorEenJaar() throws Exception {
        Hypotheek hypotheek = new LineaireHypotheek(30, 200000, 0.04);

        //http://blog.eyeopen.nl/hypotheekrenteaftrek-berekenen
        //assertEquals(8000, hypotheekCalculatorService.calculateHypotheekRenteJaar(hypotheek, 1), 0.01);

        // bij https://www.berekenhet.nl/hypotheek-en-wonen/hypotheekrenteaftrek.html
        // en http://www.homefinance.nl/hypotheek/berekenen/uitgebreide-maandlasten-berekening.asp#bm1
        assertEquals(7878, hypotheekCalculatorService.calculateHypotheekRenteJaar(hypotheek, 1), 0.5);
        assertEquals(7611, hypotheekCalculatorService.calculateHypotheekRenteJaar(hypotheek, 2), 0.5);
        assertEquals(7344, hypotheekCalculatorService.calculateHypotheekRenteJaar(hypotheek, 3), 0.5);
        assertEquals(144, hypotheekCalculatorService.calculateHypotheekRenteJaar(hypotheek, 30), 0.5);
    }

}