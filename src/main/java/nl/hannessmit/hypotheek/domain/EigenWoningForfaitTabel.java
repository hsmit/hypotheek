package nl.hannessmit.hypotheek.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hannes on 21-5-2016.
 */
public class EigenWoningForfaitTabel {
    public List<EigenWoningForfaitSchijf> getSchijven() {
        return schijven;
    }

    public void setSchijven(List<EigenWoningForfaitSchijf> schijven) {
        this.schijven = schijven;
    }

    private List<EigenWoningForfaitSchijf> schijven = new ArrayList<EigenWoningForfaitSchijf>();

    public double calculate(double wozAmount){
        for (EigenWoningForfaitSchijf ewfs : schijven){
            if (ewfs.applies(wozAmount)){
                return ewfs.calc(wozAmount);
            }
        }
        throw new RuntimeException("invalid amount");
    }
}
