package support;


import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class Support {
    private static final String CODE_COUNTRY = "+7";
    private final Random random = new Random();

    public String data() {
        return randomYear() + "-" + randomMonth() + "-" + randomDay();
    }

    public String phone() {
        return CODE_COUNTRY + " " + threeQuantityRandom() + " " + threeQuantityRandom() + " " + twoQuantityRandom() + " " + twoQuantityRandom();
    }

    public int twoQuantityRandom() {
        return random.nextInt((99 - 10) + 1) + 10;
    }

    public int threeQuantityRandom() {
        return random.nextInt((999 - 100) + 1) + 100;
    }

    public int randomDay() {
        return random.nextInt(28);
    }

    public int randomMonth() {
        return random.nextInt(13);
    }

    public int randomYear() {
        return random.nextInt((2023 - 2020) + 1) + 2020;
    }
}