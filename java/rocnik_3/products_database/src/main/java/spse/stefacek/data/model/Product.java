package spse.stefacek.data.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
  private int product_id;
  private String name;
  private String description;
  private BigDecimal price;
  private LocalDateTime created_at;

  // DAO
  public Product() {
  }

  // Service
  public Product(String name, BigDecimal price) {
    this.name = name;
    this.price = price;
  }

  public int getId() {
    return this.product_id;
  }

  public void setId(int product_id) {
    this.product_id = product_id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public LocalDateTime getCreatedAt() {
    return this.created_at;
  }

  public void setCreatedAt(LocalDateTime created_at) {
    this.created_at = created_at;
  }
}
