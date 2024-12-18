package cz.spse.bajer.pg3.GUI;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import cz.spse.bajer.pg3.InputFormatCheck;
import cz.spse.bajer.pg3.User;
import cz.spse.bajer.pg3.UserManager;

public class EditUserDialog extends UserInputTemplate {
  final private User currentUser;
  //final private String currentUsername;

  public EditUserDialog(AdminFrame adminFrame, String username, User user) {
    super(adminFrame);
    //this.currentUsername = username;
    this.currentUser = user;
    setTitle("Upravit uživatele");

    super.textFieldContainer.remove(super.usernamePanel);
    submitButton.setText("Potvrdit");

    fillInFields();
  }

  private void fillInFields() {
    passwordField.setText(this.currentUser.getHashedPassword());
    titleField.setText(this.currentUser.getTitle());
    genderField.setSelectedItem(this.currentUser.getGender());
    nameField.setText(this.currentUser.getFirstName());
    surnameField.setText(this.currentUser.getSurname());
    birthDateField.setText(this.currentUser.getBirthDate().format(UserManager.dateFormat));
    emailField.setText(this.currentUser.getEmail());
    phoneField.setText(this.currentUser.getPhone());
    birthCodeField.setText(this.currentUser.getBirthNumber());
    adminCheckBox.setSelected(this.currentUser.getIsAdmin());
  }

  @Override
  protected void onSubmit() {
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
      currentUser.setPassword(password);
      currentUser.setTitle(title);
      currentUser.setGender(gender);
      currentUser.setFirstName(name);
      currentUser.setSurname(surname);
      currentUser.setBirthDate(birthDateParsed);
      currentUser.setEmail(email);
      currentUser.setPhone(phone);
      currentUser.setBirthNumber(birthCode);
      currentUser.setIsAdmin(IsAdmin);
      JOptionPane.showMessageDialog(this, "Uživatel byl úspešně upraven.", "Úspěch",
          JOptionPane.INFORMATION_MESSAGE);
      dispose();
      this.adminFrame.refreshTableData();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
          "Chyba", JOptionPane.ERROR_MESSAGE);
    }
  }
}
