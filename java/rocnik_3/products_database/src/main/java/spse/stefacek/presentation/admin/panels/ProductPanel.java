package spse.stefacek.presentation.admin.panels;

import java.awt.Window;

import spse.stefacek.data.dao.impl.ProductDaoJDBC;
import spse.stefacek.data.model.Product;
import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.presentation.admin.dialogs.form.BaseFormDialog;
import spse.stefacek.presentation.admin.dialogs.form.ProductForm;
import spse.stefacek.presentation.admin.handlers.ExceptionHandler;
import spse.stefacek.presentation.admin.models.tables.ProductTableModel;
import spse.stefacek.service.impl.ProductService;

public class ProductPanel extends BasePanel<Product> {
  private final ProductDaoJDBC productDao;

  public ProductPanel() {
    super(new ProductService());
    this.productDao = new ProductDaoJDBC();
    tableModel = new ProductTableModel();
    init();
  }

  private void init() {
    table.setModel(tableModel);
    refreshData();
  }

  @Override
  protected void refreshData() {
    try {
      ((ProductTableModel) tableModel).setProducts(this.productDao.getAll());
      tableModel.fireTableDataChanged();
    } catch (DatabaseException e) {
      ExceptionHandler.handle(this, e);
    }

  }

  @Override
  protected Product getItemAt(int row) {
    return ((ProductTableModel) tableModel).getItemAt(row);
  }

  @Override
  protected String getItemName(Product item) {
    return item.getName();
  }

  @Override
  protected BaseFormDialog<Product> createDialog(Product item) {
    if (item == null) {
      item = new Product();
    }

    return new ProductForm((Window) this.getTopLevelAncestor(), (ProductService) service, item);
  }
}
