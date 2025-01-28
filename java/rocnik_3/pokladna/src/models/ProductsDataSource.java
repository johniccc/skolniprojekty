package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.interfaces.DataSource;
import models.variables.Product;

public class ProductsDataSource implements DataSource {

  @Override
  public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("products.csv"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] values = line.split(",");
        Product product = new Product(values[0], Double.parseDouble(values[1]));
        products.add(product);
      }
    } catch (IOException e) {
      return null;
    }
    return products;
  }
}
