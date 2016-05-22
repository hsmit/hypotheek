package nl.hannessmit.hypotheek;

import nl.hannessmit.hypotheek.service.InkomstenBelastingCalculatorService;

/**
 * Created by Hannes on 22-5-2016.
 */
public class CalcIBTax {
    public static void main(String[] args) {
        System.out.println(args.length);
        int year = Integer.valueOf(args[0]);
        double income = Double.valueOf(args[1]);
        InkomstenBelastingCalculatorService service = new InkomstenBelastingCalculatorService();
        System.out.println(String.format("%.2f", service.calcTax(year, income)));
    }
}
