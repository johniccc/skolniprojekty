package models.interfaces;

import java.util.List;
import models.variables.Product;

public interface DataSource {
  List<Product> getAllProducts();
}
