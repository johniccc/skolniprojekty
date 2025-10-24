package client.presentation;

import client.service.generic.ErrorHandler;
import client.service.rmi.ChatClientRMI;
import client.service.sockets.ChatClient;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import shared.enums.MessagePacketType;
import shared.helpers.TextCleaner;
import shared.packets.MessagePacket;

public class LoginFrame extends JFrame {
  JTextField nicknameInput;
  ChatClient client;
  ChatClientRMI clientRMI;

  public LoginFrame(ChatClient client) {
    this.client = client;

    initSettings();
    initComponents();
  }

  public LoginFrame(ChatClientRMI clientRMI) {
    this.clientRMI = clientRMI;

    initSettings();
    initComponents();
  }

  private void initSettings() {
    setTitle("Přihlášení");
    setSize(500, 300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void initComponents() {
    JLabel nicknameLabel = new JLabel("Uživatelské jméno");
    nicknameLabel.setFont(new Font(null, Font.BOLD, 14));
    nicknameInput = new JTextField(15);

    JPanel nicknameWrapper = new JPanel();
    nicknameWrapper.setLayout(new GridLayout(2, 1, 0, 5));
    nicknameWrapper.add(nicknameLabel);
    nicknameWrapper.add(nicknameInput);

    JPanel inputContainer = new JPanel();
    inputContainer.add(nicknameWrapper);

    JPanel inputPanel = new JPanel(new GridBagLayout());
    inputPanel.add(inputContainer);

    JButton joinButton = new JButton("Připojit se");
    joinButton.addActionListener((_) -> {
      String userInput = TextCleaner.cleanUserInput(nicknameInput.getText());
      if (!userInput.equals("")) {
        if (client != null) {
          client.sendPacket(new MessagePacket("SERVER", MessagePacketType.INPUT_DETAILS, userInput));
        } else if (clientRMI != null) {
          try {
            clientRMI.sendPacket(new MessagePacket("SERVER", MessagePacketType.INPUT_DETAILS, userInput));
          } catch (RemoteException e) {
            ErrorHandler.handle(this, e);
          }
        }
      } else {
        ErrorHandler.handle(this, new Exception("Uživatelské jméno nemůže být prázdné."));
      }

    });

    JPanel actionButtonWrapper = new JPanel();
    actionButtonWrapper.add(joinButton);

    JPanel actionButtonContainer = new JPanel();
    actionButtonContainer.add(actionButtonWrapper);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.add(inputPanel, BorderLayout.CENTER);
    mainPanel.add(actionButtonContainer, BorderLayout.SOUTH);

    add(mainPanel);
  }
}
