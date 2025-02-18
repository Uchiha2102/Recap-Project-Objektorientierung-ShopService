import java.util.List;

public interface OrderRepo {

    List<Order> getOrders();

    Order getOrderById(String id);

    Order addOrder(Order newOrder);

    void removeOrder(String id);

    void updateOrderStatus(String orderId, OrderStatus newStatus);

    List<Order> getOrdersByStatus(OrderStatus status);

}
