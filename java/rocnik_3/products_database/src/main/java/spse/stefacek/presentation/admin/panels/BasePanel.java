package spse.stefacek.presentation.admin.panels;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.exceptions.ServiceException;
import spse.stefacek.exceptions.templates.ErrorSeverity;
import spse.stefacek.presentation.admin.dialogs.form.BaseFormDialog;
import spse.stefacek.presentation.admin.handlers.ExceptionHandler;
import spse.stefacek.service.interfaces.ServiceInterface;

public abstract class BasePanel<T> extends JPanel {
  protected JTable table;
  protected AbstractTableModel tableModel;
  protected JButton addBtn, editBtn, deleteBtn, refreshBtn;
  protected ServiceInterface<T> service;

  public BasePanel(ServiceInterface<T> service) {
    this.service = service;
    setLayout(new BorderLayout());
    initComponents();
  }

  private void initComponents() {
    table = new JTable();
    table.getTableHeader().setReorderingAllowed(false);
    table.getTableHeader().setResizingAllowed(false);

    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);

    JPanel btnPanel = new JPanel();
    addBtn = new JButton("Přidat");
    editBtn = new JButton("Upravit");
    deleteBtn = new JButton("Smazat");
    refreshBtn = new JButton("Aktualizovat data");

    btnPanel.add(addBtn);
    btnPanel.add(editBtn);
    btnPanel.add(deleteBtn);
    btnPanel.add(refreshBtn);

    add(btnPanel, BorderLayout.SOUTH);

    setupListeners();
  }

  protected boolean confirmDelete(String itemName) {
    return JOptionPane.showConfirmDialog(this,
        "Opravdu chcete smazat: " + itemName + "?",
        "Potvrzení", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
  }

  protected void showSuccess(String message) {
    JOptionPane.showMessageDialog(this, message, "Úspěch", JOptionPane.INFORMATION_MESSAGE);
  }

  @SuppressWarnings("unused")
  private void setupListeners() {
    addBtn.addActionListener(e -> {
      handleAdd();
    });
    editBtn.addActionListener(e -> {
      handleEdit();
    });
    deleteBtn.addActionListener(e -> {
      handleDelete();
    });
    refreshBtn.addActionListener(e -> {
      refreshData();
      showSuccess("Seznam byl úspěšně akutalizován.");
    });
  }

  protected void handleAdd() {
    createDialog(null);
    refreshData();
  };

  protected void handleEdit() {
    int selectedRow = table.getSelectedRow();

    if (selectedRow == -1) {
      ExceptionHandler.handle(this,
          new ServiceException("Nebyl vybrán žádný záznam.", new Throwable(), ErrorSeverity.ERROR));
      return;
    }

    T selectedItem = getItemAt(selectedRow);
    createDialog(selectedItem);
    refreshData();
  };

  protected void handleDelete() {
    int selectedRow = table.getSelectedRow();

    if (selectedRow == -1) {
      ExceptionHandler.handle(this,
          new ServiceException("Nebyl vybrán žádný záznam.", new Throwable(), ErrorSeverity.ERROR));
      return;
    }

    T selectedItem = getItemAt(selectedRow);
    String itemName = getItemName(selectedItem);

    if (confirmDelete(itemName)) {
      try {
        service.deleteItem(selectedItem);
        showSuccess("Mazání proběhlo úspěšně.");
        refreshData();
      } catch (DatabaseException | ServiceException e) {
        ExceptionHandler.handle(this, e);
      }
    }
  };

  protected abstract void refreshData();

  protected abstract T getItemAt(int row);

  protected abstract String getItemName(T item);

  protected abstract BaseFormDialog<T> createDialog(T item);
}
