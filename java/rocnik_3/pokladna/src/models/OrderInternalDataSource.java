package models;

import java.util.ArrayList;
import java.util.List;
import models.interfaces.InternalDataSource;
import models.variables.Order;

public class OrderInternalDataSource implements InternalDataSource<Order> {
    private final List<Order> orders;
    private static OrderInternalDataSource instance;

    private OrderInternalDataSource() {
        this.orders = new ArrayList<>();
    }

    public static OrderInternalDataSource getInstance() {
        if (instance == null) {
            instance = new OrderInternalDataSource();
        }
        return instance;
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public Order getById(int id) {
        return orders.get(id);
    }

    @Override
    public boolean add(Order order) {       
        return orders.add(order);
    }

    @Override
    public Order remove(int id) {
        return orders.remove(id);
    }

    @Override
    public void removeAll() {
        orders.clear();
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = 0;
        for (Order order : orders) {
            totalPrice += order.getTotalPrice();
        }
        return totalPrice;
    }

    public double getTotalPriceById(int id) {
        double totalPrice = -1;
        Order order = orders.get(id);
        if (order != null) {
            totalPrice = order.getTotalPrice();
        }
        return totalPrice;
    }
}
