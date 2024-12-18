package cz.spse.bajer.pg3.GUI;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import cz.spse.bajer.pg3.InputFormatCheck;
import cz.spse.bajer.pg3.UserManager;

public class AddUserDialog extends UserInputTemplate {
  public AddUserDialog(AdminFrame adminFrame) {
    super(adminFrame);
    setTitle("Přidat uživatele");
    submitButton.setText("Potvrdit");
  }

  @Override
  protected void onSubmit() {
    String username = usernameField.getText().strip();
    String password = passwordField.getText().strip();
    String title = titleField.getText().strip();
    String gender = genderField.getSelectedItem().toString();
    String name = nameField.getText().strip();
    String surname = surnameField.getText().strip();
    String birthDate = birthDateField.getText().strip();
    String email = emailField.getText().strip();
    String phone = phoneField.getText().strip();
    String birthCode = birthCodeField.getText().strip();
    boolean IsAdmin = adminCheckBox.isSelected();

    try {
      InputFormatCheck.checkAll(title, gender, name, surname, birthDate, birthCode, phone, email, password);
      LocalDate birthDateParsed = LocalDate.parse(birthDate, UserManager.dateFormat);
      String addSuccess = userManagerInstance.addUser(username, title, gender, name, surname, birthDateParsed,
          birthCode,
          phone,
          email,
          password, IsAdmin);
      if (addSuccess != null) {
        JOptionPane.showMessageDialog(this, addSuccess, "Chyba",
            JOptionPane.ERROR_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(this, "Uživatel byl úspešně přidán.", "Úspěch",
            JOptionPane.INFORMATION_MESSAGE);
        dispose();
        this.adminFrame.refreshTableData();
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
          "Chyba", JOptionPane.ERROR_MESSAGE);
    }
  }
}
