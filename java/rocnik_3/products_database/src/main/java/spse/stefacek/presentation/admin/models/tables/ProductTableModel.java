package spse.stefacek.presentation.admin.models.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import spse.stefacek.data.model.Product;

public class ProductTableModel extends AbstractTableModel {
  private List<Product> products;
  private final String[] columnNames = { "ID", "Název", "Popis", "Cena", "Datum vytvoření" };

  public ProductTableModel() {
  }

  public ProductTableModel(List<Product> products) {
    this.products = products;
  }

  @Override
  public int getRowCount() {
    return products.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public String getColumnName(int column) {
    return columnNames[column];
  }

  public Product getItemAt(int rowIndex) {
    return products.get(rowIndex);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Product product = products.get(rowIndex);

    return switch (columnIndex) {
      case 0 -> product.getId();
      case 1 -> product.getName();
      case 2 -> product.getDescription();
      case 3 -> product.getPrice();
      case 4 -> product.getCreatedAt();
      default -> null;
    };
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

}
