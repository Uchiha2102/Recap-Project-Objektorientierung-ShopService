import java.util.ArrayList;
import java.util.List;

public class OrderListRepo implements OrderRepo{
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrderById(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public Order addOrder(Order newOrder) {
        orders.add(newOrder);
        return newOrder;
    }

    public void removeOrder(String id) {
        for (Order order : orders) {
            if (order.id().equals(id)) {
                orders.remove(order);
                return;
            }
        }
    }

    @Override
    public void updateOrderStatus(String orderId, OrderStatus newStatus) {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (order.id().equals(orderId)) {
                orders.set(i, new Order(order.id(), order.products(), newStatus));
                return;
            }
        }
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        List<Order> filteredOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.orderStatus() == status) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }

}
