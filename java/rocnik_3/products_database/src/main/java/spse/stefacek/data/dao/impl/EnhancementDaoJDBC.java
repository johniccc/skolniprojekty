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

import spse.stefacek.data.dao.interfaces.EnhancementDao;
import spse.stefacek.data.model.Enhancement;
import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.exceptions.templates.ErrorSeverity;
import spse.stefacek.util.DBConnection;

public class EnhancementDaoJDBC implements EnhancementDao {
  public EnhancementDaoJDBC() {

  }

  @Override
  public Enhancement save(Enhancement entity) throws DatabaseException {
    Enhancement enhancement = null;
    int entityId = entity.getId();

    if (entityId == 0) {
      enhancement = insert(entity);
    } else if (entityId > 0) {
      enhancement = update(entity);
    }

    return enhancement;
  }

  private Enhancement insert(Enhancement enhancement) throws DatabaseException {
    String insertQuery = "INSERT INTO enhancements (name, description, price) VALUES (?, ?, ?)";

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, enhancement.getName());
        stmt.setString(2, enhancement.getDescription());
        stmt.setBigDecimal(3, enhancement.getPrice());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0)
          return null;

        try (ResultSet keys = stmt.getGeneratedKeys()) {
          if (keys.next()) {
            enhancement.setId(keys.getInt(1));
            enhancement.setCreatedAt(LocalDateTime.now());
          } else {
            throw new DatabaseException("Nebyly vygenerovány žádné klíče.", new Throwable(), ErrorSeverity.ERROR);
          }
        }
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při vkládání.", e, ErrorSeverity.ERROR);
    }

    return enhancement;
  }

  private Enhancement update(Enhancement enhancement) throws DatabaseException {
    String updateQuery = "UPDATE enhancements SET name = ?, description = ?, price = ? WHERE enhancement_id = ?";
    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
        stmt.setString(1, enhancement.getName());
        stmt.setString(2, enhancement.getDescription());
        stmt.setBigDecimal(3, enhancement.getPrice());
        stmt.setInt(4, enhancement.getId());

        int affectedRows = stmt.executeUpdate();
        if (affectedRows == 0)
          enhancement = null;
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při úpravě.", e, ErrorSeverity.ERROR);
    }
    return enhancement;
  }

  @Override
  public Optional<Enhancement> getById(int id) throws DatabaseException {
    String query = "SELECT * FROM enhancements WHERE enhancement_id = ?";

    Enhancement enhancement = null;

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setLong(1, id);
        try (ResultSet results = stmt.executeQuery()) {
          if (results.next()) {
            enhancement = new Enhancement();
            enhancement.setId(results.getInt("enhancement_id"));
            enhancement.setName(results.getString("name"));
            enhancement.setDescription(results.getString("description"));
            enhancement.setPrice(results.getBigDecimal("price"));
            enhancement.setCreatedAt(results.getTimestamp("created_at").toLocalDateTime());
          }
        }
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při získávání.", e, ErrorSeverity.ERROR);
    }

    return Optional.ofNullable(enhancement);
  }

  @Override
  public List<Enhancement> getAll() throws DatabaseException {
    String query = "SELECT * FROM enhancements";

    List<Enhancement> enhancements = new ArrayList<>();

    try (Connection conn = DBConnection.getInstance().getConnection()) {
      try (Statement stmt = conn.createStatement()) {
        try (ResultSet results = stmt.executeQuery(query)) {
          while (results.next()) {
            Enhancement enhancement = new Enhancement();
            enhancement.setId(results.getInt("enhancement_id"));
            enhancement.setName(results.getString("name"));
            enhancement.setDescription(results.getString("description"));
            enhancement.setPrice(results.getBigDecimal("price"));
            enhancement.setCreatedAt(results.getTimestamp("created_at").toLocalDateTime());
            enhancements.add(enhancement);
          }
        }
      }
    } catch (SQLException e) {
      throw new DatabaseException("Nastala chyba databáze při získávání.", e, ErrorSeverity.ERROR);
    }

    return enhancements;
  }

  @Override
  public int delete(int id) throws DatabaseException {
    String query = "DELETE FROM enhancements WHERE enhancement_id = ?";
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
