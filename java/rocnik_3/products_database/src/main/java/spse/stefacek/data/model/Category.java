package spse.stefacek.data.model;

import java.time.LocalDateTime;

public class Category {
  private int category_id;
  private String name;
  private String description;
  private LocalDateTime created_at;

  // DAO
  public Category() {
  }

  // Service
  public Category(String name) {
    this.name = name;
  }

  public int getId() {
    return this.category_id;
  }

  public void setId(int category_id) {
    this.category_id = category_id;
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

  public LocalDateTime getCreatedAt() {
    return this.created_at;
  }

  public void setCreatedAt(LocalDateTime created_at) {
    this.created_at = created_at;
  }
}
