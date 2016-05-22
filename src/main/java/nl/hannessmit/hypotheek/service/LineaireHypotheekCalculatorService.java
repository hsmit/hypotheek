package nl.hannessmit.hypotheek.service;

import nl.hannessmit.hypotheek.domain.Hypotheek;
import nl.hannessmit.hypotheek.domain.HypotheekMonth;
import nl.hannessmit.hypotheek.domain.HypotheekTable;

import java.util.List;

/**
 * Created by Hannes on 18-5-2016.
 */
public class LineaireHypotheekCalculatorService implements HypotheekCalculatorService{

    private InkomstenBelastingCalculatorService inkomstenBelastingCalculatorService = new InkomstenBelastingCalculatorService();

    /**
     * Returns the value that has to be payed on jaarRente in the i'th month
     *
     * @param hypotheek the object that contains the hypotheek parameters
     * @param month (always starting at month 1)
     * @return amount to be payed on jaarRente for a specific month
     */
    public double calculateRenteForMonth(Hypotheek hypotheek, int month) {
        // check that month is within hypotheek looptijd
        if (month > (12 * hypotheek.getDurationYears()) || month < 1){
            return 0.0;
        }
        double maandAflossing = calculateAflossingForMonth(hypotheek, month); // willekeurige maand
        double afgelost = (month-1) * maandAflossing;
        double outcome = hypotheek.getJaarRente() * (hypotheek.getLeenbedrag() - afgelost) / 12;
        System.out.println("calc for month " + month + " => " + outcome);
        return outcome;
    }

    /**
     * Returns the value that has to be payed on aflossing in the i'th month
     *
     * @param hypotheek the object that contains the hypotheek parameters
     * @param month (always starting at month 1)
     * @return amount to be payed on aflossing in specific month
     */
    public double calculateAflossingForMonth(Hypotheek hypotheek, int month) {
        if (month > (12 * hypotheek.getDurationYears()) || month < 1) {
            return 0.0;
        }
        // parameter i is not relevant here
        double lb = hypotheek.getLeenbedrag();
        int dy = hypotheek.getDurationYears();
        int m = 12; // months
        return lb / (dy * m);
    }

    /**
     * Returns the value that has to be payed combined in the i'th mont
     * @param hypotheek the object that contains the hypotheek parameters
     * @param month (always starting at month 1)
     * @return
     */
    public double calculateBrutoMaandlast(Hypotheek hypotheek, int month){
        return calculateAflossingForMonth(hypotheek, month) + calculateRenteForMonth(hypotheek, month);
    }

    public double calculateHypotheekRenteJaar(Hypotheek hypotheek, int year){
        // starts with year 1
        int startMonth = 12 * (year - 1) + 1;
        int endMonth = 12 * year;
        double yearRente = 0.0;
        for (int i = startMonth; i<=endMonth; i++){

            yearRente += calculateRenteForMonth(hypotheek, i);
        }
        return yearRente;
    }

    public HypotheekMonth calculateLineaireHypotheekMaand(Hypotheek hypotheek1, int month) {
        HypotheekMonth hypotheekMonth = new HypotheekMonth();
        hypotheekMonth.setMonth(month);
        hypotheekMonth.setAflossing(calculateAflossingForMonth(hypotheek1, month));
        hypotheekMonth.setRente(calculateRenteForMonth(hypotheek1, month));
        hypotheekMonth.setBruto(calculateBrutoMaandlast(hypotheek1, month));
        return hypotheekMonth;
    }

    public HypotheekTable createLineaireHypotheekTabel(Hypotheek hypotheek) {
        int months = hypotheek.getDurationYears() * 12;
        HypotheekTable hypotheekTable = new HypotheekTable();
        List<HypotheekMonth> tabel = hypotheekTable.getLijst();
        for (int m = 1; m <= months; m++){
            HypotheekMonth hypotheekMonth = calculateLineaireHypotheekMaand(hypotheek, m);
            tabel.add(hypotheekMonth);
        }
        return hypotheekTable;
    }
}
