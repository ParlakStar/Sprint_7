package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourierCard {
    private String login;
    private String password;
    private String firstName;

    public CourierCard(String login, String password) {
        this.login = login;
        this.password = password;
    }
}