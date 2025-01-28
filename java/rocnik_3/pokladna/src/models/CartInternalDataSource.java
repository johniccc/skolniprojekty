package models;

import java.util.ArrayList;
import java.util.List;
import models.interfaces.InternalDataSource;
import models.variables.Product;

public class CartInternalDataSource implements InternalDataSource<Product> {

  private final List<Product> items;
  private static CartInternalDataSource instance;

  private CartInternalDataSource() {
    this.items = new ArrayList<>();
  }

  public static CartInternalDataSource getInstance() {
    if (instance == null) {
      instance = new CartInternalDataSource();
    }
    return instance;
  }

  @Override
  public List<Product> getAll() {
    return items;
  }

  @Override
  public Product getById(int id) {
    return items.get(id);
  }

  @Override
  public boolean add(Product product) {
    return items.add(product);
  }

  @Override
  public Product remove(int id) {
    return items.remove(id);
  }

  @Override
  public void removeAll() {
    items.clear();
  }

  @Override
  public double getTotalPrice() {
    double totalPrice = 0;
    for (Product product : items) {
      totalPrice += product.getPrice();
    }
    return totalPrice;
  }
}
