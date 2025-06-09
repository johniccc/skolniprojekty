package spse.stefacek.presentation.admin.models.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import spse.stefacek.data.model.Enhancement;

public class EnhancementTableModel extends AbstractTableModel {
  private List<Enhancement> enhancements;
  private final String[] columnNames = { "ID", "Název", "Popis", "Cena", "Datum vytvoření" };

  public EnhancementTableModel() {
  }

  public EnhancementTableModel(List<Enhancement> enhancements) {
    this.enhancements = enhancements;
  }

  @Override
  public int getRowCount() {
    return enhancements.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public String getColumnName(int column) {
    return columnNames[column];
  }

  public Enhancement getItemAt(int rowIndex) {
    return enhancements.get(rowIndex);
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Enhancement enhancement = enhancements.get(rowIndex);

    return switch (columnIndex) {
      case 0 -> enhancement.getId();
      case 1 -> enhancement.getName();
      case 2 -> enhancement.getDescription();
      case 3 -> enhancement.getPrice();
      case 4 -> enhancement.getCreatedAt();
      default -> null;
    };
  }

  public void setEnhancements(List<Enhancement> enhancements) {
    this.enhancements = enhancements;
  }
}
