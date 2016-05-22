package nl.hannessmit.hypotheek.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hannes on 18-5-2016.
 */
public class HypotheekTable {
    private List<HypotheekMonth> lijst = new ArrayList<HypotheekMonth>();

    public List<HypotheekMonth> getLijst() {
        return lijst;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (HypotheekMonth lhm : lijst) {
            stringBuilder.append(lhm.toString() + "\n");
        }
        return stringBuilder.toString();
    }
}
