package client.service.sockets;

import client.presentation.ChatFrame;
import client.presentation.LoginFrame;
import client.service.generic.ErrorHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import shared.Config;
import shared.enums.ActionPacketType;
import shared.enums.MessagePacketType;
import shared.packets.ActionPacket;
import shared.packets.ErrorPacket;
import shared.packets.MessagePacket;
import shared.packets.Packet;

public class ChatClientSocket implements ChatClient {
  Socket clientSocket;
  ObjectOutputStream output;
  ObjectInputStream input;
  String nickname;

  LoginFrame login;
  ChatFrame chatWindow;

  List<MessagePacket> allMessages = new ArrayList<>();

  public void startClient() throws ConnectException {
    connectToServer("localhost", Config.PORT_NUMBER);

    new Thread(() -> { // IncomingPacketsManager thread
      try {
        recieveIncomingPackets();
      } catch (ConnectException e) {
        ErrorHandler.handle(null, e);
      }
    }).start();
  }

  @Override
  public void connectToServer(String hostname, int port) throws ConnectException {
    try {
      clientSocket = new Socket(hostname, port);
      output = new ObjectOutputStream(clientSocket.getOutputStream());
      input = new ObjectInputStream(clientSocket.getInputStream());
    } catch (IOException e) {
      throw new ConnectException("Couldn't connect to server: " + e.getMessage());
    }
  }

  @Override
  public synchronized void sendPacket(Packet packet) {
    try {
      output.writeObject(packet);
    } catch (IOException e) {
      ErrorHandler.handle(null, e);
    }
  }

  @Override
  public void recieveIncomingPackets() throws ConnectException {
    try {
      Packet packet;
      while ((packet = (Packet) input.readObject()) != null) {
        processIncomingPacket(packet);
      }
    } catch (IOException | ClassNotFoundException e) {
      throw new ConnectException("Packet Recieving Error: " + e.getMessage());
    }

  }

  @Override
  public void processIncomingPacket(Packet packet) {
    switch (packet) {
      case MessagePacket msg -> {
        switch (msg.getMessageType()) {
          case MessagePacketType.CHAT_MESSAGE:
            allMessages.add(msg);
            if (chatWindow != null) {
              chatWindow.refreshChatMessages(allMessages);
            }
          default:
            break;
        }
      }
      case ActionPacket action -> {
        switch (action.getActionType()) {
          case ActionPacketType.INVOKE_SIGN_IN:
            SwingUtilities.invokeLater(() -> {
              login = new LoginFrame(this);
              login.setVisible(true);
            });
            break;
          case ActionPacketType.CHATROOM_ACCESS_GRANTED:
            nickname = action.getMessage();
            login.dispose();
            login = null;
            chatWindow = new ChatFrame(this);
            chatWindow.setVisible(true);
            chatWindow.refreshChatMessages(allMessages);
            break;
          case ActionPacketType.REFRESH_USERS_LIST:
            if (chatWindow != null) {
              chatWindow.refreshActiveUsers(action.getUserList());
            }
            break;
          default:
            break;
        }
      }
      case ErrorPacket error -> {
        ErrorHandler.handle(null, error);
      }
      case null -> {
        break;
      }
      default -> {
        break;
      }
    }
  }

  @Override
  public String getNickname() {
    return nickname;
  }
}
