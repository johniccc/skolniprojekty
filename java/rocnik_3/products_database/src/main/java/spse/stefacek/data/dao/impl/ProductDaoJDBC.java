package spse.stefacek.data.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import spse.stefacek.data.dao.interfaces.ProductDao;
import spse.stefacek.data.model.Category;
import spse.stefacek.data.model.Enhancement;
import spse.stefacek.data.model.Product;
import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.exceptions.templates.ErrorSeverity;
import spse.stefacek.util.DBConnection;

public class ProductDaoJDBC implements ProductDao {

  public ProductDaoJDBC() {
  }

  @Override
  public int addEnhancement(int productId, int enhancementId) throws DatabaseException {
    String query = "INSERT INTO product_enhancements VALUES (?, ?)";
    int affectedRows = 0;

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, productId);
        stmt.setInt(2, enhancementId);

        affectedRows = stmt.executeUpdate();
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při vkládání.", e, ErrorSeverity.ERROR);
    }

    return affectedRows;
  }

  @Override
  public int addCategory(int productId, int categoryId) throws DatabaseException {
    String query = "INSERT INTO product_categories VALUES (?, ?)";
    int affectedRows = 0;

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, productId);
        stmt.setInt(2, categoryId);

        affectedRows = stmt.executeUpdate();
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při vkládání.", e, ErrorSeverity.ERROR);
    }

    return affectedRows;
  }

  @Override
  public List<Enhancement> getEnhancements(int productId) throws DatabaseException {
    String query = "SELECT e.enhancement_id, e.name, e.description, e.price, e.created_at " +
        "FROM product_enhancements pe " +
        "JOIN enhancements e ON pe.enhancement_id = e.enhancement_id " +
        "WHERE pe.product_id = ?";

    List<Enhancement> enhancements = new ArrayList<>();

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, productId);
        try (ResultSet results = stmt.executeQuery()) {
          while (results.next()) {
            Enhancement e = new Enhancement();
            e.setId(results.getInt("enhancement_id"));
            e.setName(results.getString("name"));
            e.setDescription(results.getString("description"));
            e.setPrice(results.getBigDecimal("price"));
            e.setCreatedAt(results.getTimestamp("created_at").toLocalDateTime());
            enhancements.add(e);
          }
        }
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při získávání.", e, ErrorSeverity.ERROR);
    }

    return enhancements;
  }

  @Override
  public List<Category> getCategories(int productId) throws DatabaseException {
    String query = "SELECT c.category_id, c.name, c.description, c.created_at " +
        "FROM product_categories pc " +
        "JOIN categories c ON pc.category_id = c.category_id " +
        "WHERE pc.product_id = ?";

    List<Category> categories = new ArrayList<>();

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, productId);
        try (ResultSet results = stmt.executeQuery()) {
          while (results.next()) {
            Category c = new Category();
            c.setId(results.getInt("category_id"));
            c.setName(results.getString("name"));
            c.setDescription(results.getString("description"));
            c.setCreatedAt(results.getTimestamp("created_at").toLocalDateTime());
            categories.add(c);
          }
        }
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při žískávání.", e, ErrorSeverity.ERROR);
    }

    return categories;
  }

  @Override
  public int removeEnhancement(int productId, int enhancementId) throws DatabaseException {
    String query = "DELETE FROM product_enhancements WHERE product_id = ? AND enhancement_id = ?";
    int affectedRows = 0;

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, productId);
        stmt.setInt(2, enhancementId);

        affectedRows = stmt.executeUpdate();
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při mazání.", e, ErrorSeverity.ERROR);
    }

    return affectedRows;
  }

  @Override
  public int removeCategory(int productId, int categoryId) throws DatabaseException {
    String query = "DELETE FROM product_categories WHERE product_id = ? AND category_id = ?";
    int affectedRows = 0;

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, productId);
        stmt.setInt(2, categoryId);

        affectedRows = stmt.executeUpdate();
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při mazání.", e, ErrorSeverity.ERROR);
    }

    return affectedRows;
  }

  @Override
  public Product save(Product entity) throws DatabaseException {
    Product product = null;
    int entityId = entity.getId();

    if (entityId == 0) {
      product = insert(entity);
    } else if (entityId > 0) {
      product = update(entity);
    }

    return product;
  }

  private Product insert(Product product) throws DatabaseException {
    String insertQuery = "INSERT INTO products (name, description, price) VALUES (?, ?, ?)";

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getDescription());
        stmt.setBigDecimal(3, product.getPrice());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0)
          return null;

        try (ResultSet keys = stmt.getGeneratedKeys()) {
          if (keys.next()) {
            product.setId(keys.getInt(1));
            product.setCreatedAt(LocalDateTime.now());
          } else {
            throw new DatabaseException("Nebyly vygenerovány žádné klíče.", new Throwable(), ErrorSeverity.ERROR);
          }
        }
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při vkládání.", e, ErrorSeverity.ERROR);
    }

    return product;
  }

  private Product update(Product product) throws DatabaseException {
    String updateQuery = "UPDATE products SET name = ?, description = ?, price = ? WHERE product_id = ?";
    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getDescription());
        stmt.setBigDecimal(3, product.getPrice());
        stmt.setInt(4, product.getId());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0)
          product = null;
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při úpravě.", e, ErrorSeverity.ERROR);
    }
    return product;
  }

  @Override
  public Optional<Product> getById(int id) throws DatabaseException {
    String query = "SELECT * FROM products WHERE product_id = ?";

    Product product = null;

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setLong(1, id);
        try (ResultSet results = stmt.executeQuery()) {
          if (results.next()) {
            product = new Product();
            product.setId(results.getInt("product_id"));
            product.setName(results.getString("name"));
            product.setDescription(results.getString("description"));
            product.setPrice(results.getBigDecimal("price"));
            product.setCreatedAt(results.getTimestamp("created_at").toLocalDateTime());
          }
        }
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při získávání.", e, ErrorSeverity.ERROR);
    }

    return Optional.ofNullable(product);
  }

  @Override
  public List<Product> getAll() throws DatabaseException {
    String query = "SELECT * FROM products";

    List<Product> products = new ArrayList<>();

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (Statement stmt = conn.createStatement()) {
        try (ResultSet results = stmt.executeQuery(query)) {
          while (results.next()) {
            Product product = new Product();
            product.setId(results.getInt("product_id"));
            product.setName(results.getString("name"));
            product.setDescription(results.getString("description"));
            product.setPrice(results.getBigDecimal("price"));
            product.setCreatedAt(results.getTimestamp("created_at").toLocalDateTime());
            products.add(product);
          }
        }
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při získávání.", e, ErrorSeverity.ERROR);
    }

    return products;
  }

  @Override
  public int delete(int id) throws DatabaseException {
    String query = "DELETE FROM products WHERE product_id = ?";
    int affectedRows = 0;

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, id);
        affectedRows = stmt.executeUpdate();
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při mazání.", e, ErrorSeverity.ERROR);
    }

    return affectedRows;
  }

}
