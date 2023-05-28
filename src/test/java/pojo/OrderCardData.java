package pojo;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import support.Support;

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
        phone = Support.phone();
        rentTime = random.nextInt(10);
        deliveryDate = Support.data();
        comment = randomString();
    }

    private String randomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }
}
