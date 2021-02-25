package ch.timonhueppi.m335.bmicalc;

import org.junit.Test;

import ch.timonhueppi.m335.bmicalc.service.BMIService;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void bmiTest() {
        BMIService bmiService = new BMIService();

        bmiService.setWeight(75f);
        bmiService.setHeight(1.8f);

        assertEquals(23, bmiService.calcBMI(), 1);
    }
}