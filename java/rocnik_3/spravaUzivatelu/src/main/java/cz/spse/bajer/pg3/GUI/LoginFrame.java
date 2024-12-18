package cz.spse.bajer.pg3.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import cz.spse.bajer.pg3.User;
import cz.spse.bajer.pg3.UserManager;

public class LoginFrame extends JFrame {
  final private UserManager userManagerInstance;
  private JTextField inputUsername;
  private JTextField inputPassword;

  public LoginFrame() {
    this.userManagerInstance = UserManager.getInstance();
    setTitle("Přihlásit se");
    setSize(400, 300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    buildComponents();
  }

  final public void buildComponents() {
    int inputFieldWidth = 15;

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());

    JPanel loginInputPanel = new JPanel();
    loginInputPanel.setLayout(new GridBagLayout());

    JPanel inputFieldContainer = new JPanel();
    inputFieldContainer.setLayout(new GridLayout(2, 1, 0, 10));

    JPanel inputFieldUsername = new JPanel();
    inputFieldUsername.setLayout(new FlowLayout(FlowLayout.RIGHT));

    JLabel labelUsername = new JLabel("Uživatelské jméno");
    inputUsername = new JTextField(inputFieldWidth);
    inputUsername.addActionListener(e -> {
      this.continueAction();
    });
    inputFieldUsername.add(labelUsername);
    inputFieldUsername.add(inputUsername);

    inputFieldContainer.add(inputFieldUsername);

    JPanel inputFieldPassword = new JPanel();
    inputFieldPassword.setLayout(new FlowLayout(FlowLayout.RIGHT));

    JLabel labelPassword = new JLabel("Heslo");
    inputPassword = new JTextField(inputFieldWidth);
    inputPassword.addActionListener(e -> {
      this.continueAction();
    });
    inputFieldPassword.add(labelPassword);
    inputFieldPassword.add(inputPassword);

    inputFieldContainer.add(inputFieldPassword);

    loginInputPanel.add(inputFieldContainer);

    JPanel loginActionPanel = new JPanel();

    JPanel actionButtonContainer = new JPanel();

    JButton buttonLogin = new JButton("Přihlásit se");
    buttonLogin.addActionListener(e -> {
      this.continueAction();
    });
    actionButtonContainer.add(buttonLogin);

    loginActionPanel.add(actionButtonContainer);

    mainPanel.add(loginInputPanel, BorderLayout.CENTER);
    mainPanel.add(loginActionPanel, BorderLayout.SOUTH);

    add(mainPanel);
  }

  public void continueAction() {
    String username = this.inputUsername.getText().strip();
    String password = this.inputPassword.getText().strip();

    User loggedInUser = this.userManagerInstance.authenticateLogin(username, password);

    if (loggedInUser != null) {
      LoginFrame.this.dispose();
      if (loggedInUser.getIsAdmin()) {
        SwingUtilities.invokeLater(() -> new AdminFrame(username, loggedInUser).setVisible(true));
      } else {
        SwingUtilities.invokeLater(() -> new UserFrame(username, loggedInUser).setVisible(true));
      }
    } else {
      JOptionPane.showMessageDialog(LoginFrame.this, "Nesprávné údaje. Zkuste to znovu.", "Chyba",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
