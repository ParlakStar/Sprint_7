package support;

import io.qameta.allure.junit4.DisplayName;
import junit.framework.TestCase;

public class SupportTest extends TestCase {

    @DisplayName("Генерирование двухчисловой последовательности для телефона")
    public void testTwoQuantityRandom() {
        int twoQuantityRandom = Support.twoQuantityRandom();

        assertEquals(2, Integer.toString(twoQuantityRandom).length());
    }

    @DisplayName("")
    public void testThreeQuantityRandom() {
        int threeQuantityRandom = Support.threeQuantityRandom();

        assertEquals(3, Integer.toString(threeQuantityRandom).length());
    }

    @DisplayName("")
    public void testRandomDay() {
        int randomDay = Support.randomDay();

        assertEquals(2, Integer.toString(randomDay).length());
    }

    @DisplayName("")
    public void testRandomMonth() {
        int randomMonth = Support.randomMonth();

        assertEquals(2, Integer.toString(randomMonth).length());
    }

    @DisplayName("")
    public void testRandomYear() {
        int randomYear = Support.randomYear();

        assertEquals(4, Integer.toString(randomYear).length());
    }
}