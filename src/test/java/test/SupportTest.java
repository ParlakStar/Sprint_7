package test;

import io.qameta.allure.junit4.DisplayName;
import junit.framework.TestCase;
import org.hamcrest.MatcherAssert;
import support.Support;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;

public class SupportTest extends TestCase {

    @DisplayName("Генерирование двухзначного числа для телефона")
    public void testTwoQuantityRandom() {
        int twoQuantityRandom = Support.twoQuantityRandom();

        assertEquals(2, Integer.toString(twoQuantityRandom).length());
    }

    @DisplayName("Генерирование трехзначного числа для телефона")
    public void testThreeQuantityRandom() {
        int threeQuantityRandom = Support.threeQuantityRandom();

        assertEquals(3, Integer.toString(threeQuantityRandom).length());
    }

    @DisplayName("Генерирование двухзначного числа для даты (день)")
    public void testRandomDay() {
        int randomDay = Support.randomDay();

        MatcherAssert.assertThat(Integer.toString(randomDay).length(), either(is(1)).or(is(2)));
    }

    @DisplayName("Генерирование двухзначного числа для даты (месяц)")
    public void testRandomMonth() {
        int randomMonth = Support.randomMonth();

        MatcherAssert.assertThat(Integer.toString(randomMonth).length(), either(is(1)).or(is(2)));
    }

    @DisplayName("Генерирование четырехзначного числа для даты (год)")
    public void testRandomYear() {
        int randomYear = Support.randomYear();

        assertEquals(4, Integer.toString(randomYear).length());
    }
}