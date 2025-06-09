package spse.stefacek.data.dao.interfaces;

import java.util.List;
import java.util.Optional;

import spse.stefacek.exceptions.DatabaseException;

public interface BaseDao<T> {
  T save(T entity) throws DatabaseException;

  Optional<T> getById(int id) throws DatabaseException;

  List<T> getAll() throws DatabaseException;

  int delete(int id) throws DatabaseException;
}
