package models.variables;

import java.time.Instant;
import java.util.List;

public class Order {
  private final List<Product> products;
  private final Instant timestamp;

  public Order (List<Product> products) {
    this.products = products;
    this.timestamp = Instant.now();
  }

  public List<Product> getProducts() {
    return products;
  }
  
  public Instant getTimestamp() {
    return timestamp;
  }

  public int getProductsCount() {
    return products.size();
  }

  public double getTotalPrice() {
    double totalPrice = 0;
    for (Product product : products) {
      totalPrice += product.getPrice();
    }
    return totalPrice;
  }
}
