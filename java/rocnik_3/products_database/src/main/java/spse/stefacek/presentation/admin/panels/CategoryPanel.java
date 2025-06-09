package spse.stefacek.presentation.admin.panels;

import java.awt.Window;

import spse.stefacek.data.dao.impl.CategoryDaoJDBC;
import spse.stefacek.data.model.Category;
import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.presentation.admin.dialogs.form.BaseFormDialog;
import spse.stefacek.presentation.admin.dialogs.form.CategoryForm;
import spse.stefacek.presentation.admin.handlers.ExceptionHandler;
import spse.stefacek.presentation.admin.models.tables.CategoryTableModel;
import spse.stefacek.service.impl.CategoryService;

public class CategoryPanel extends BasePanel<Category> {
  private final CategoryDaoJDBC categoryDao;

  public CategoryPanel() {
    super(new CategoryService());
    this.categoryDao = new CategoryDaoJDBC();
    tableModel = new CategoryTableModel();
    init();
  }

  private void init() {
    table.setModel(tableModel);
    refreshData();
  }

  @Override
  protected void refreshData() {
    try {
      ((CategoryTableModel) tableModel).setCategories(categoryDao.getAll());
      tableModel.fireTableDataChanged();
    } catch (DatabaseException e) {
      ExceptionHandler.handle(this, e);
    }
  }

  @Override
  protected Category getItemAt(int row) {
    return ((CategoryTableModel) tableModel).getItemAt(row);
  }

  @Override
  protected String getItemName(Category item) {
    return item.getName();
  }

  @Override
  protected BaseFormDialog<Category> createDialog(Category item) {
    if (item == null) {
      item = new Category();
    }

    return new CategoryForm((Window) this.getTopLevelAncestor(), (CategoryService) service, item);
  }
}
