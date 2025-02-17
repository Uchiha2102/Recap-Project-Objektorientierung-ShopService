import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {

    @Test
    void getOrders() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product),OrderStatus.PROCESSING
        );
        repo.addOrder(newOrder);

        //WHEN
        List<Order> actual = repo.getOrders();

        //THEN
        List<Order> expected = new ArrayList<>();
        Product product1 = new Product("1", "Apfel");
        expected.add(new Order("1", List.of(product1),OrderStatus.PROCESSING));

        assertEquals(actual, expected);
    }

    @Test
    void getOrderById() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product),OrderStatus.PROCESSING);
        repo.addOrder(newOrder);

        //WHEN
        Order actual = repo.getOrderById("1");

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1),OrderStatus.PROCESSING);

        assertEquals(actual, expected);
    }

    @Test
    void addOrder() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();
        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product),OrderStatus.PROCESSING);

        //WHEN
        Order actual = repo.addOrder(newOrder);

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1),OrderStatus.PROCESSING);
        assertEquals(actual, expected);
        assertEquals(repo.getOrderById("1"), expected);
    }

    @Test
    void removeOrder() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        //WHEN
        repo.removeOrder("1");

        //THEN
        assertNull(repo.getOrderById("1"));
    }

    @Test
    void updateOrderStatus_existingOrder() {
        // GIVEN
        OrderListRepo repo = new OrderListRepo();
        Product product = new Product("1", "Apfel");
        Order order = new Order("1", List.of(product), OrderStatus.PROCESSING);
        repo.addOrder(order);

        // WHEN
        repo.updateOrderStatus("1", OrderStatus.IN_DELIVERY);

        // THEN
        Order updatedOrder = repo.getOrderById("1");
        assertNotNull(updatedOrder);
        assertEquals(OrderStatus.IN_DELIVERY, updatedOrder.orderStatus());
    }

    @Test
    void getOrdersByStatus() {
        // GIVEN
        OrderMapRepo repo = new OrderMapRepo();

        Product product1 = new Product("1", "Apfel");
        Product product2 = new Product("2", "Banane");

        Order order1 = new Order("1", List.of(product1), OrderStatus.PROCESSING);
        Order order2 = new Order("2", List.of(product2), OrderStatus.IN_DELIVERY);
        Order order3 = new Order("3", List.of(product1, product2), OrderStatus.PROCESSING);

        repo.addOrder(order1);
        repo.addOrder(order2);
        repo.addOrder(order3);

        // WHEN
        List<Order> processingOrders = repo.getOrdersByStatus(OrderStatus.PROCESSING);
        List<Order> inDeliveryOrders = repo.getOrdersByStatus(OrderStatus.IN_DELIVERY);
        List<Order> completedOrders = repo.getOrdersByStatus(OrderStatus.COMPLETED);

        // THEN
        assertEquals(2, processingOrders.size());
        assertTrue(processingOrders.contains(order1));
        assertTrue(processingOrders.contains(order3));

        assertEquals(1, inDeliveryOrders.size());
        assertTrue(inDeliveryOrders.contains(order2));

        assertEquals(0, completedOrders.size());
    }
}
