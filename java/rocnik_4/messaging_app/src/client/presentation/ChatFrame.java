package client.presentation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import client.service.generic.ErrorHandler;
import client.service.rmi.ChatClientRMI;
import client.service.sockets.ChatClient;
import shared.enums.ActionPacketType;
import shared.enums.MessagePacketType;
import shared.packets.ActionPacket;
import shared.packets.MessagePacket;

public class ChatFrame extends JFrame {
  ChatClient client;
  ChatClientRMI clientRMI;
  JTextArea messageInput;
  JPanel chatWrapper;
  JScrollPane chatScroll;
  JPanel userList;
  JScrollPane userScroll;

  public ChatFrame(ChatClient client) {
    this.client = client;

    initSettings();
    initComponents();
  }

  public ChatFrame(ChatClientRMI clientRMI) {
    this.clientRMI = clientRMI;

    initSettings();
    initComponents();
  }

  private void initSettings() {
    setTitle("Chatovací aplikace");
    setSize(800, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void initComponents() {
    /*
     * Header with nickname label
     */
    JLabel usernameLabel;
    try {
      usernameLabel = new JLabel((client != null) ? client.getNickname() : clientRMI.getNickname());
    } catch (RemoteException e) {
      usernameLabel = new JLabel("UNKNOWN");
      ErrorHandler.handle(null, e);
    }
    JPanel header = new JPanel();
    header.add(usernameLabel);

    /*
     * Center panel
     */
    JSplitPane centerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    centerPanel.setDividerLocation(600);

    chatWrapper = new JPanel();
    chatScroll = new JScrollPane(chatWrapper);

    userList = new JPanel();
    userScroll = new JScrollPane(userList);

    centerPanel.setLeftComponent(chatScroll);
    centerPanel.setRightComponent(userScroll);

    /*
     * Action bar
     */
    messageInput = new JTextArea(1, 50);
    JButton sendMessageBtn = new JButton("Poslat");
    sendMessageBtn.addActionListener(_ -> {
      if (client != null)
        client
            .sendPacket(
                new MessagePacket(client.getNickname(), MessagePacketType.CHAT_MESSAGE, messageInput.getText()));
      else if (clientRMI != null)
        try {
          clientRMI.sendPacket(new MessagePacket(clientRMI.getNickname(), MessagePacketType.CHAT_MESSAGE,
              messageInput.getText()));
        } catch (RemoteException e) {
          ErrorHandler.handle(this, e);
        }
      messageInput.setText("");
    });
    JPanel sendMessages = new JPanel();
    sendMessages.add(messageInput);
    sendMessages.add(sendMessageBtn);

    /*
     * General panel
     */
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.add(header, BorderLayout.NORTH);
    mainPanel.add(centerPanel, BorderLayout.CENTER);
    mainPanel.add(sendMessages, BorderLayout.SOUTH);

    add(mainPanel);

    /* Leave on close */
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        if (client != null)
          client.sendPacket(new ActionPacket(ActionPacketType.LEAVE_CHATROOM, client.getNickname(), null));
        else if (clientRMI != null)
          try {
            clientRMI.sendPacket(new ActionPacket(ActionPacketType.LEAVE_CHATROOM, clientRMI.getNickname(), null));
          } catch (RemoteException ex) {
            ErrorHandler.handle(null, ex);
          }

        dispose();
      }
    });
  }

  public void refreshChatMessages(List<MessagePacket> messages) {
    JPanel chatContents = new JPanel();
    chatContents.setLayout(new BoxLayout(chatContents, BoxLayout.Y_AXIS));

    for (MessagePacket msg : messages) {
      JPanel messageRow = new JPanel();

      String sender = (msg.getSender().matches("SERVER")) ? "" : String.format("[%s]:", msg.getSender());
      JLabel senderLabel = new JLabel(sender);
      JLabel messageContent = new JLabel(msg.getMessageContent());

      messageRow.add(senderLabel);
      messageRow.add(messageContent);

      chatContents.add(messageRow);
    }

    /*
     * Refresh view
     */

    Component[] wrapperComponents = chatWrapper.getComponents();
    for (Component comp : wrapperComponents) {
      chatWrapper.remove(comp);
    }
    chatWrapper.add(chatContents);
    chatScroll.setViewportView(chatWrapper);
    repaint();
  }

  public void refreshActiveUsers(List<String> users) {
    JPanel userContents = new JPanel();
    userContents.setLayout(new BoxLayout(userContents, BoxLayout.Y_AXIS));

    for (String user : users) {
      JPanel userRow = new JPanel();

      JLabel userLabel = new JLabel(user);

      userRow.add(userLabel);

      userContents.add(userRow);
    }

    /*
     * Refresh view
     */

    Component[] wrapperComponents = userList.getComponents();
    for (Component comp : wrapperComponents) {
      userList.remove(comp);
    }
    userList.add(userContents);
    userScroll.setViewportView(userList);
    repaint();
  }
}
