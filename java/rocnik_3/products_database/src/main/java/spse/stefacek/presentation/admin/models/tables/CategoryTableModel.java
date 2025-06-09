package spse.stefacek.presentation.admin.models.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import spse.stefacek.data.model.Category;

public class CategoryTableModel extends AbstractTableModel {
  private List<Category> categories;
  private final String[] columnNames = { "ID", "Název", "Popis", "Datum vytvoření" };

  public CategoryTableModel() {
  }

  public CategoryTableModel(List<Category> products) {
    this.categories = products;
  }

  @Override
  public int getRowCount() {
    return categories.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public String getColumnName(int column) {
    return columnNames[column];
  }

  public Category getItemAt(int rowIndex) {
    return categories.get(rowIndex);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Category category = getItemAt(rowIndex);

    return switch (columnIndex) {
      case 0 -> category.getId();
      case 1 -> category.getName();
      case 2 -> category.getDescription();
      case 3 -> category.getCreatedAt();
      default -> null;
    };
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
