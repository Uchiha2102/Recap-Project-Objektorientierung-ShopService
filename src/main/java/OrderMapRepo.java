import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepo{
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Order getOrderById(String id) {
        return orders.get(id);
    }

    @Override
    public Order addOrder(Order newOrder) {
        orders.put(newOrder.id(), newOrder);
        return newOrder;
    }

    @Override
    public void removeOrder(String id) {
        orders.remove(id);
    }

    @Override
    public void updateOrderStatus(String orderId, OrderStatus newStatus) {
        if (orders.containsKey(orderId)) {
            Order existingOrder = orders.get(orderId);
            orders.put(orderId, new Order(existingOrder.id(), existingOrder.products(), newStatus));
        }
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        List<Order> filteredOrders = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.orderStatus() == status) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }
    }

