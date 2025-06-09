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

import spse.stefacek.data.dao.interfaces.CategoryDao;
import spse.stefacek.data.model.Category;
import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.exceptions.templates.ErrorSeverity;
import spse.stefacek.util.DBConnection;

public class CategoryDaoJDBC implements CategoryDao {
  public CategoryDaoJDBC() {

  }

  @Override
  public Category save(Category entity) throws DatabaseException {
    Category category = null;
    int entityId = entity.getId();

    if (entityId == 0) {
      category = insert(entity);
    } else if (entityId > 0) {
      category = update(entity);
    }

    return category;
  }

  private Category insert(Category category) throws DatabaseException {
    String insertQuery = "INSERT INTO categories (name, description) VALUES (?, ?)";
    
    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, category.getName());
        stmt.setString(2, category.getDescription());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0)
          return null;

        try (ResultSet keys = stmt.getGeneratedKeys()) {
          if (keys.next()) {
            category.setId(keys.getInt(1));
            category.setCreatedAt(LocalDateTime.now());
          } else {
            throw new DatabaseException("Nebyly vygenerovány žádné klíče.", new Throwable(), ErrorSeverity.ERROR);
          }
        }
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při vkládání.", e, ErrorSeverity.ERROR);
    }

    return category;
  }

  private Category update(Category category) throws DatabaseException {
    String updateQuery = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";
    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
        stmt.setString(1, category.getName());
        stmt.setString(2, category.getDescription());
        stmt.setInt(3, category.getId());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0)
          category = null;
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při úpravě.", e, ErrorSeverity.ERROR);
    }
    return category;
  }

  @Override
  public Optional<Category> getById(int id) throws DatabaseException {
    String query = "SELECT * FROM categories WHERE category_id = ?";

    Category category = null;

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setLong(1, id);
        try (ResultSet results = stmt.executeQuery()) {
          if (results.next()) {
            category = new Category();
            category.setId(results.getInt("category_id"));
            category.setName(results.getString("name"));
            category.setDescription(results.getString("description"));
            category.setCreatedAt(results.getTimestamp("created_at").toLocalDateTime());
          }
        }
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při získávání.", e, ErrorSeverity.ERROR);
    }

    return Optional.ofNullable(category);
  }

  @Override
  public List<Category> getAll() throws DatabaseException {
    String query = "SELECT * FROM categories";

    List<Category> categories = new ArrayList<>();

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (Statement stmt = conn.createStatement()) {
        try (ResultSet results = stmt.executeQuery(query)) {
          while (results.next()) {
            Category category = new Category();
            category.setId(results.getInt("category_id"));
            category.setName(results.getString("name"));
            category.setDescription(results.getString("description"));
            category.setCreatedAt(results.getTimestamp("created_at").toLocalDateTime());
            categories.add(category);
          }
        }
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při získávání.", e, ErrorSeverity.ERROR);
    }

    return categories;
  }

  @Override
  public int delete(int id) throws DatabaseException {
    String query = "DELETE FROM categories WHERE category_id = ?";
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
