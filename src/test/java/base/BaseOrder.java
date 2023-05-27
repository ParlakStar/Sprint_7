package base;

import action.OrderCardData;
import action.OrderValue;
import action.OrderAction;
import io.qameta.allure.Step;
import resources.OrderCard;
import resources.TrackCard;

public class BaseOrder {
    public OrderAction orderAction = new OrderAction();
    public OrderCardData orderCardData = new OrderCardData();
    public OrderValue orderValue = new OrderValue();
    public OrderCard orderCard;

    @Step("Заполнение карточки заказа случайными данными")
    public void generateOrderData() {
        orderCardData.generateRandomDataField();
        orderCard = new OrderCard(
                orderCardData.getFirstName(), orderCardData.getLastName(),
                orderCardData.getAddress(), orderCardData.getMetroStation(),
                orderCardData.getPhone(), orderCardData.getRentTime(),
                orderCardData.getDeliveryDate(), orderCardData.getComment());
    }

    @Step("Заполнение карточки заказа случайными данными + цвет")
    public void generateOrderData(String[] color) {
        orderCardData.generateRandomDataField();
        orderCard = new OrderCard(
                orderCardData.getFirstName(), orderCardData.getLastName(),
                orderCardData.getAddress(), orderCardData.getMetroStation(),
                orderCardData.getPhone(), orderCardData.getRentTime(),
                orderCardData.getDeliveryDate(), orderCardData.getComment(), color);
    }


    @Step("Отмена тестового заказа")
    public void cancelTestOrder(TrackCard trackCard) {
        orderAction.putRequestCancelOrderByTrack(trackCard.getTrack());
    }

}
