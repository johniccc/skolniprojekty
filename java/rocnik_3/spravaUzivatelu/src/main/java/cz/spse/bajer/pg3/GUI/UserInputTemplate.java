package cz.spse.bajer.pg3.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cz.spse.bajer.pg3.UserManager;

public abstract class UserInputTemplate extends JFrame {
  protected UserManager userManagerInstance;
  protected JPanel mainPanel;
  protected JPanel textFieldContainer;
  protected JPanel usernamePanel, adminPanel;
  protected JTextField usernameField, titleField, nameField, surnameField, birthDateField, emailField,
      phoneField,
      birthCodeField;
  protected JComboBox<String> genderField;
  protected JCheckBox adminCheckBox;
  protected JTextField passwordField;
  protected JButton submitButton;
  protected AdminFrame adminFrame;

  public UserInputTemplate() {
    this.userManagerInstance = UserManager.getInstance();
    setSize(700, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    buildComponents();
  }

  public UserInputTemplate(AdminFrame adminFrame) {
    this.userManagerInstance = UserManager.getInstance();
    this.adminFrame = adminFrame;
    setSize(700, 450);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    adminFrame.setEnabled(false);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent windowEvent) {
        adminFrame.setEnabled(true);
        adminFrame.toFront();
        adminFrame.requestFocus();
      }

      @Override
      public void windowClosed(WindowEvent windowEvent) {
        adminFrame.setEnabled(true);
        adminFrame.toFront();
        adminFrame.requestFocus();
      }
    });

    buildComponents();
  }

  private void buildComponents() {
    int inputFieldWidth = 15;

    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    FlowLayout labelFieldLayout = new FlowLayout(FlowLayout.RIGHT, 10, 0);

    JLabel usernameLabel = new JLabel("Uživatelské jméno");
    usernameField = new JTextField(inputFieldWidth);
    usernamePanel = new JPanel();
    usernamePanel.setLayout(labelFieldLayout);
    usernamePanel.add(usernameLabel);
    usernamePanel.add(usernameField);

    JLabel passwordLabel = new JLabel("Heslo");
    passwordField = new JTextField(inputFieldWidth);
    JPanel passwordPanel = new JPanel();
    passwordPanel.setLayout(labelFieldLayout);
    passwordPanel.add(passwordLabel);
    passwordPanel.add(passwordField);

    JLabel titleLabel = new JLabel("Titul");
    titleField = new JTextField(inputFieldWidth);
    JPanel titlePanel = new JPanel();
    titlePanel.setLayout(labelFieldLayout);
    titlePanel.add(titleLabel);
    titlePanel.add(titleField);

    JLabel genderLabel = new JLabel("Pohlaví");
    genderField = new JComboBox<>(
        new String[] { "Muž", "Žena" });
    genderField.setPreferredSize(titleField.getPreferredSize());
    JPanel genderPanel = new JPanel();
    genderPanel.setLayout(labelFieldLayout);
    genderPanel.add(genderLabel);
    genderPanel.add(genderField);

    JLabel nameLabel = new JLabel("Jméno");
    nameField = new JTextField(inputFieldWidth);
    JPanel namePanel = new JPanel();
    namePanel.setLayout(labelFieldLayout);
    namePanel.add(nameLabel);
    namePanel.add(nameField);

    JLabel surnameLabel = new JLabel("Příjmení");
    surnameField = new JTextField(inputFieldWidth);
    JPanel surnamePanel = new JPanel();
    surnamePanel.setLayout(labelFieldLayout);
    surnamePanel.add(surnameLabel);
    surnamePanel.add(surnameField);

    JLabel birthDateLabel = new JLabel("Datum narození");
    birthDateField = new JTextField(inputFieldWidth);
    JPanel birthDatePanel = new JPanel();
    birthDatePanel.setLayout(labelFieldLayout);
    birthDatePanel.add(birthDateLabel);
    birthDatePanel.add(birthDateField);

    JLabel emailLabel = new JLabel("Email");
    emailField = new JTextField(inputFieldWidth);
    JPanel emailPanel = new JPanel();
    emailPanel.setLayout(labelFieldLayout);
    emailPanel.add(emailLabel);
    emailPanel.add(emailField);

    JLabel phoneLabel = new JLabel("Telefon");
    phoneField = new JTextField(inputFieldWidth);
    JPanel phonePanel = new JPanel();
    phonePanel.setLayout(labelFieldLayout);
    phonePanel.add(phoneLabel);
    phonePanel.add(phoneField);

    JLabel birthCodeLabel = new JLabel("Rodné číslo");
    birthCodeField = new JTextField(inputFieldWidth);
    JPanel birthCodePanel = new JPanel();
    birthCodePanel.setLayout(labelFieldLayout);
    birthCodePanel.add(birthCodeLabel);
    birthCodePanel.add(birthCodeField);

    JLabel adminLabel = new JLabel("Admin");
    adminCheckBox = new JCheckBox();
    adminPanel = new JPanel();
    adminPanel.setLayout(labelFieldLayout);
    adminPanel.add(adminLabel);
    adminPanel.add(adminCheckBox);

    textFieldContainer = new JPanel();
    textFieldContainer.setLayout(new GridLayout(0, 2, 50, 10));
    textFieldContainer.add(usernamePanel);
    textFieldContainer.add(passwordPanel);
    textFieldContainer.add(titlePanel);
    textFieldContainer.add(genderPanel);
    textFieldContainer.add(namePanel);
    textFieldContainer.add(surnamePanel);
    textFieldContainer.add(birthDatePanel);
    textFieldContainer.add(emailPanel);
    textFieldContainer.add(phonePanel);
    textFieldContainer.add(birthCodePanel);
    textFieldContainer.add(adminPanel);

    JPanel top = new JPanel();
    top.setLayout(new GridBagLayout());

    top.add(textFieldContainer);

    submitButton = new JButton("TENTO TEXT PŘEPIŠ");
    submitButton.addActionListener(e -> {
      onSubmit();
    });

    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
    buttons.add(submitButton);

    mainPanel.add(top, BorderLayout.CENTER);
    mainPanel.add(buttons, BorderLayout.SOUTH);

    add(mainPanel);
  }

  protected abstract void onSubmit();
}
