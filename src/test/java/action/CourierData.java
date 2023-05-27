package action;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@Data
@NoArgsConstructor
public class CourierData {
    private String courierLogin;
    private String courierPassword;
    private String courierFirstName;

    private String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public void generateLoginPassName() {
        courierLogin = generateRandomString();
        courierPassword = generateRandomString();
        courierFirstName = generateRandomString();
    }
}