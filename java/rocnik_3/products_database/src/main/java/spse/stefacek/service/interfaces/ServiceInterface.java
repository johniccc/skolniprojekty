package spse.stefacek.service.interfaces;

import java.util.List;

import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.exceptions.ServiceException;

public interface ServiceInterface<T> {
  List<T> getAll() throws DatabaseException;

  void deleteItem(T item) throws DatabaseException, ServiceException;

  T save(T item) throws DatabaseException, ServiceException;
}
