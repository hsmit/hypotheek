package nl.hannessmit.hypotheek.service;

import nl.hannessmit.hypotheek.domain.Hypotheek;
import nl.hannessmit.hypotheek.domain.HypotheekMonth;
import nl.hannessmit.hypotheek.domain.HypotheekTable;

/**
 * Created by Hannes on 18-5-2016.
 */
public interface HypotheekCalculatorService {

    double calculateRenteForMonth(Hypotheek hypotheek, int month);

    double calculateAflossingForMonth(Hypotheek hypotheek, int month);

    double calculateBrutoMaandlast(Hypotheek hypotheek, int month);

    double calculateHypotheekRenteJaar(Hypotheek hypotheek, int jaar);

    HypotheekMonth calculateLineaireHypotheekMaand(Hypotheek hypotheek1, int month);

    HypotheekTable createLineaireHypotheekTabel(Hypotheek hypotheek1);
}
