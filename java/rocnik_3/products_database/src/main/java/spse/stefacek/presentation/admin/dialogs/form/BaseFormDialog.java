package spse.stefacek.presentation.admin.dialogs.form;

import java.awt.BorderLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import spse.stefacek.exceptions.DatabaseException;
import spse.stefacek.exceptions.PresentationException;
import spse.stefacek.exceptions.ServiceException;
import spse.stefacek.exceptions.templates.ErrorSeverity;
import spse.stefacek.presentation.admin.handlers.ExceptionHandler;
import spse.stefacek.service.interfaces.ServiceInterface;

public abstract class BaseFormDialog<T> extends JDialog {
  protected ServiceInterface<T> service;
  protected T item;
  protected boolean isNewItem;

  protected JPanel formPanel;
  protected JPanel btnPanel;
  protected JButton saveBtn, cancelBtn;

  public BaseFormDialog(Window owner, ServiceInterface<T> service, T item) {
    super(owner);
    this.service = service;
    this.item = item;

    init();
  }

  private void init() {
    this.isNewItem = getItemId(item) == 0;

    setTitle(this.isNewItem ? "Přidat" : "Upravit");
    setModal(true);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);
    setSize(400, 300);

    setLayout(new BorderLayout());
    initComponents();

    setLocationRelativeTo(getOwner());
    setVisible(true);
  }

  @SuppressWarnings("unused")
  protected void initComponents() {
    btnPanel = new JPanel();

    saveBtn = new JButton("Uložit");
    saveBtn.addActionListener(e -> {
      saveItem();
    });

    cancelBtn = new JButton("Zrušit");
    cancelBtn.addActionListener(e -> {
      dispose();
    });

    btnPanel.add(saveBtn);
    btnPanel.add(cancelBtn);

    createForm();

    add(formPanel, BorderLayout.CENTER);
    add(btnPanel, BorderLayout.SOUTH);

    if (!isNewItem)
      fillFormFields(item);
  }

  protected boolean showConfirm() {
    return JOptionPane.showConfirmDialog(this,
        "Opravdu chcete provést změny?",
        "Potvrzení", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
  }

  protected void showSuccess(String message) {
    JOptionPane.showMessageDialog(this, message, "Úspěch", JOptionPane.INFORMATION_MESSAGE);
  }

  protected void saveItem() {
    T newItem = getItemFromFormFields();

    if (newItem == null)
      ExceptionHandler.handle(this,
          new PresentationException("Extrahování dat z formuláře se nezdařilo.", new Throwable(), ErrorSeverity.ERROR));

    if (showConfirm()) {
      try {
        service.save(newItem);
        showSuccess("Položka byla úspěšně uložena.");
        dispose();
      } catch (DatabaseException | ServiceException e) {
        ExceptionHandler.handle(this, e);
      }
    }
  }

  protected abstract int getItemId(T item);

  protected abstract void createForm();

  protected abstract void fillFormFields(T item);

  protected abstract T getItemFromFormFields();
}
