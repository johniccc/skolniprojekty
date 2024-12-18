package cz.spse.bajer.pg3.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cz.spse.bajer.pg3.InputFormatCheck;
import cz.spse.bajer.pg3.UserManager;
import cz.spse.bajer.pg3.User;

public class UserFrame extends UserInputTemplate {
  final private User currentUser;
  final private String currentUsername;

  public UserFrame(String username, User user) {
    super();
    this.currentUsername = username;
    this.currentUser = user;
    setTitle("Upravit uživatele");

    submitButton.setText("Potvrdit");

    textFieldContainer.remove(usernamePanel);
    textFieldContainer.remove(adminPanel);

    addHeading();
    fillInFields();
  }

  private void addHeading() {
    JLabel heading = new JLabel(
        String.format("%s", this.currentUsername));

    JPanel headingPanel = new JPanel();
    headingPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
    headingPanel.add(heading);

    mainPanel.add(headingPanel, BorderLayout.NORTH);
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
      JOptionPane.showMessageDialog(this, "Uživatel byl úspešně upraven.", "Úspěch",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, e.getMessage(),
          "Chyba", JOptionPane.ERROR_MESSAGE);
    }
  }
}
