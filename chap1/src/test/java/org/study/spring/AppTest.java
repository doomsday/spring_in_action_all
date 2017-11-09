package org.study.spring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.springinaction.configuration.AppConfig;
import com.springinaction.spelsystem.DataCollector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles("dev")
public class AppTest {

    @Autowired
    private DataCollector dataCollector;

    @Test
    public void dataCollectorShallHasTime() {
        assertNotNull(dataCollector.getTime());
    }

    @Test
    public void dataCollectorShallHasArtist() {
        assertTrue(dataCollector.getArtist().equals("The Beatles"));
    }

    @Test
    public void vendorShallBeOracle() {
        assertTrue(dataCollector.getVendor().equals("Oracle Corporation"));
    }

    @Test
    public void spElShallAutowireBeanToProperty() {
        assertNotNull(dataCollector.getSgtPeppers());
    }

    @Test
    public void spElMethodCallShallReturnAnalCunt() {
        assertTrue(dataCollector.getSelected().toUpperCase().equals("ANAL CUNT"));
    }

    @Test
    public void spElShallProtectFromNullPointerException() {
        assertNull(dataCollector.getNullArtist());
    }

    @Test
    public void piShallBePi() {
        assertTrue(dataCollector.getPI().equals(3.14159265358979323846));
    }

    @Test
    public void circumferenceValueShallBePredefined() {
        assertTrue(dataCollector.getCircumference().equals(787.2831189896021));
    }

    @Test
    public void emailShallBeValid() {
        assertTrue(dataCollector.getEmailValid());
    }

    @Test
    public void shallBeThreeCannibalCorpseSongs() {
        assertTrue(dataCollector.getCannibalCorpseSongs().size() == 3);
    }

    @Test
    public void shallBeTwoCanvasSolarisSongsProjected() {
        assertTrue(dataCollector.getCanvasSolarisSongsStrings().size() == 2);
    }
}
