package nl.hannessmit.hypotheek.service;

import nl.hannessmit.hypotheek.domain.InkomstenBelastingSchijf;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.belastingdienst.nl/wps/wcm/connect/bldcontentnl/belastingdienst/prive/inkomstenbelasting/heffingskortingen_boxen_tarieven/boxen_en_tarieven/overzicht_tarieven_en_schijven/
 */
public class InkomstenBelastingCalculatorService {

    public List<InkomstenBelastingSchijf> schijven2016voorAOW;
    public List<InkomstenBelastingSchijf> schijven2015voorAOW;
    public List<InkomstenBelastingSchijf> schijven2014voorAOW;

    public InkomstenBelastingCalculatorService() {

        schijven2016voorAOW = new ArrayList<>();
        schijven2016voorAOW.add(new InkomstenBelastingSchijf(0, 19922, a -> a * 0.3655));
        schijven2016voorAOW.add(new InkomstenBelastingSchijf(19923, 33715, a -> a * 0.404));
        schijven2016voorAOW.add(new InkomstenBelastingSchijf(33716, 66421, a -> a * 0.404));
        schijven2016voorAOW.add(new InkomstenBelastingSchijf(66422, Double.MAX_VALUE, a -> a * 0.52));

        schijven2015voorAOW = new ArrayList<>();
        schijven2015voorAOW.add(new InkomstenBelastingSchijf(0, 19822, a -> a * 0.3655));
        schijven2015voorAOW.add(new InkomstenBelastingSchijf(19823, 33589, a -> a * 0.42));
        schijven2015voorAOW.add(new InkomstenBelastingSchijf(33590, 57585, a -> a * 0.42));
        schijven2015voorAOW.add(new InkomstenBelastingSchijf(57586, Double.MAX_VALUE, a -> a * 0.52));

        schijven2014voorAOW = new ArrayList<>();
        schijven2014voorAOW.add(new InkomstenBelastingSchijf(0, 19645, a -> a * 0.3625));
        schijven2014voorAOW.add(new InkomstenBelastingSchijf(19646, 33363, a -> a * 0.42));
        schijven2014voorAOW.add(new InkomstenBelastingSchijf(33364, 56531, a -> a * 0.42));
        schijven2014voorAOW.add(new InkomstenBelastingSchijf(56532, Double.MAX_VALUE, a -> a * 0.52));

    }

    public double calcTax(int year, double inkomsten){
        System.out.println("calculate IB for " + year);
        if (year == 2016){
            return calcForSchijven(inkomsten, schijven2016voorAOW);
        } else if (year == 2015){
            return calcForSchijven(inkomsten, schijven2015voorAOW);
        } else if (year == 2014){
            return calcForSchijven(inkomsten, schijven2014voorAOW);
        } else {
            throw new RuntimeException("invalid year");
        }
    }

    private double calcForSchijven(double inkomsten, List<InkomstenBelastingSchijf> schijven) {
        double totalTaxAmount = 0.0;
        for (InkomstenBelastingSchijf schijf : schijven){
            if (schijf.applies(inkomsten)){
                totalTaxAmount += schijf.calc(inkomsten);
            }
        }
        System.out.println(String.format("total: %.2f", totalTaxAmount));
        return totalTaxAmount;
    }
}
