package nl.hannessmit.hypotheek.domain;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Hannes on 21-5-2016.
 */
public class EigenWoningForfaitSchijfTest {
    @Test
    public void applies() throws Exception {
        EigenWoningForfaitSchijf schijf = new EigenWoningForfaitSchijf(0, 10, null);
        Assert.assertTrue(schijf.applies(1));
        Assert.assertTrue(schijf.applies(10));
        Assert.assertFalse(schijf.applies(11));
        Assert.assertFalse(schijf.applies(0));
    }

}