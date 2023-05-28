package action;

import constants.OrderListParams;

import java.util.Random;

public class OrderValue {
    private static final String EMPTY_PARAM = "empty_param";
    Random random = new Random();

    public String paramValue(String value) {
        switch (value) {
            case OrderListParams.NEAREST_STATION:
                return closeToStation();
            case OrderListParams.PAGE:
                return Integer.toString(random.nextInt(10));
            case OrderListParams.LIMIT:
                return Integer.toString(random.nextInt(30));
            default:
                return EMPTY_PARAM;
        }
    }

    private String closeToStation() {
        return "[\"" + random.nextInt(120) + "\", \"" + random.nextInt(120) + "\"]";
    }
}