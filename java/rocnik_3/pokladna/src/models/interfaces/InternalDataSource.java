package models.interfaces;

import java.util.List;

public interface InternalDataSource<T> {
  List<T> getAll();

  T getById(int id);

  boolean add(T t);

  T remove(int id);

  void removeAll();

  double getTotalPrice();
}
