package server.service.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import shared.enums.ActionPacketType;
import shared.packets.ActionPacket;
import shared.packets.ErrorPacket;
import shared.packets.MessagePacket;
import shared.packets.Packet;

public class ClientHandlerSocket implements ClientHandler {
  private Socket socket;
  private final ChatServerSocket server;
  private ObjectInputStream input;
  private ObjectOutputStream output;

  private String nickname;

  public ClientHandlerSocket(Socket clientSocket, ChatServerSocket server) {
    this.socket = clientSocket;
    this.server = server;

    try {
      this.output = new ObjectOutputStream(clientSocket.getOutputStream());
      this.input = new ObjectInputStream(clientSocket.getInputStream());
    } catch (IOException e) {
      System.out.println("Error connecting to client...");
    }

  }

  @Override
  public void run() {
    try {
      if (!handleAuthentication()) {
        return;
      }

      handleChatMessages();

    } catch (IOException | ClassNotFoundException e) {
      sendPacket(new ErrorPacket("Error: Couldn't read recieved packet"));
    } finally {
      cleanup();
    }

  }

  private boolean handleAuthentication() {
    try {
      sendPacket(new ActionPacket(ActionPacketType.INVOKE_SIGN_IN, "", null));

      while (true) { // Keep trying until success or disconnect
        Object receivedObject = input.readObject();

        if (receivedObject instanceof MessagePacket msgPacket) {
          String tempNickname = msgPacket.getMessageContent();

          if (server.getClients().containsKey(tempNickname)) {
            sendPacket(new ErrorPacket("Username already taken."));
          } else {
            this.nickname = tempNickname;
            sendPacket(new ActionPacket(ActionPacketType.CHATROOM_ACCESS_GRANTED, this.nickname, null));
            server.addClient(this.nickname, this);
            server.sendBroadcastMessage("SERVER", nickname + " joined the channel.");
            server.sendBroadcastAction(ActionPacketType.REFRESH_USERS_LIST, "",
                server.getClients().keySet().stream().toList());
            return true; // Authentication successful
          }
        } else {
          sendPacket(new ErrorPacket("Error: Wrong packet type"));
        }
      }
    } catch (IOException | ClassNotFoundException e) {
      sendPacket(new ErrorPacket("Error: Couldn't read received packet"));
      return false;
    }
  }

  private void handleChatMessages() throws IOException, ClassNotFoundException {
    Packet packet;
    while ((packet = (Packet) input.readObject()) != null) {
      processRecievedMessage(packet);
    }
  }

  @Override
  public void sendPacket(Packet packet) {
    try {
      output.writeObject(packet);
    } catch (IOException e) {
      System.out.println("Error: Couldn't send packet to client");
    }
  }

  public void processRecievedMessage(Packet packet) {
    switch (packet) {
      case MessagePacket msg -> {
        server.sendBroadcastMessage(msg.getSender(), msg.getMessageContent());
        break;
      }
      case ActionPacket action -> {
        switch (action.getActionType()) {
          case ActionPacketType.LEAVE_CHATROOM:
            server.removeClient(this.nickname);
            server.sendBroadcastMessage("SERVER", nickname + " left the channel.");
            server.sendBroadcastAction(ActionPacketType.REFRESH_USERS_LIST, "",
                server.getClients().keySet().stream().toList());
            try {
              socket.close();
            } catch (IOException e) {
              System.err.println("Error closing socket: " + e.getMessage());
            }
            break;
          default:
            break;
        }
      }
      case null -> {
        break;
      }
      default -> {
        break;
      }
    }
  }

  private void cleanup() {
    try {
      if (nickname != null) {
        server.removeClient(nickname);
      }
      if (socket != null) {
        socket.close();
      }
    } catch (IOException e) {
      System.err.println("Error during cleanup: " + e.getMessage());
    }
  }

}
