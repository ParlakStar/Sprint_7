package action;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

@Data
public class OrderCardData {
    private final Random random = new Random();
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;

    public void generateRandomDataField() {
        firstName = randomString();
        lastName = randomString();
        address = randomString();
        metroStation = random.nextInt(50);
        phone = phone();
        rentTime = random.nextInt(10);
        deliveryDate = data();
        comment = randomString();
    }

    private String randomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }


    //todo: change data
    private String data() {
        String day = String.valueOf(random.nextInt(28));
        String month = String.valueOf(random.nextInt(13));
        String year = String.valueOf(random.nextInt((2023 - 2020) + 1) + 2020);

        return year + "-" + month + "-" + day;
    }

    //todo: change phone
    private String phone() {
        String phoneFormat3NumberCod = String.valueOf(random.nextInt((999 - 100) + 1) + 100);
        String phoneFormat3Number = String.valueOf(random.nextInt((999 - 100) + 1) + 100);
        String phoneFormat2Number = String.valueOf(random.nextInt((99 - 10) + 1) + 10);
        String phoneFormat2NumberLast = String.valueOf(random.nextInt((99 - 10) + 1) + 10);

        return "+7" + " " + phoneFormat3NumberCod + " " + phoneFormat3Number + " " + phoneFormat2Number + " " + phoneFormat2NumberLast;
    }
}
