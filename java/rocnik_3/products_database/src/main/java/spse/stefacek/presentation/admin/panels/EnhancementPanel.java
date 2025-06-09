package spse.stefacek.presentation.admin.panels;

import java.awt.Window;

import spse.stefacek.data.dao.impl.EnhancementDaoJDBC;
import spse.stefacek.data.model.Enhancement;
import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.presentation.admin.dialogs.form.BaseFormDialog;
import spse.stefacek.presentation.admin.dialogs.form.EnhancementForm;
import spse.stefacek.presentation.admin.handlers.ExceptionHandler;
import spse.stefacek.presentation.admin.models.tables.EnhancementTableModel;
import spse.stefacek.service.impl.EnhancementService;

public class EnhancementPanel extends BasePanel<Enhancement> {
  private final EnhancementDaoJDBC enhancementDao;

  public EnhancementPanel() {
    super(new EnhancementService());
    this.enhancementDao = new EnhancementDaoJDBC();
    tableModel = new EnhancementTableModel();
    init();
  }

  private void init() {
    table.setModel(tableModel);
    refreshData();
  }

  @Override
  protected void refreshData() {
    try {
      ((EnhancementTableModel) tableModel).setEnhancements(enhancementDao.getAll());
      tableModel.fireTableDataChanged();
    } catch (DatabaseException e) {
      ExceptionHandler.handle(this, e);
    }

  }

  @Override
  protected Enhancement getItemAt(int row) {
    return ((EnhancementTableModel) tableModel).getItemAt(row);
  }

  @Override
  protected String getItemName(Enhancement item) {
    return item.getName();
  }

  @Override
  protected BaseFormDialog<Enhancement> createDialog(Enhancement item) {
    if (item == null) {
      item = new Enhancement();
    }

    return new EnhancementForm((Window) this.getTopLevelAncestor(), (EnhancementService) service, item);
  }
}
