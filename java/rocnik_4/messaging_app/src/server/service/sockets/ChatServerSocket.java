package server.service.sockets;

import java.util.List;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import shared.Config;
import shared.enums.ActionPacketType;
import shared.enums.MessagePacketType;
import shared.packets.ActionPacket;
import shared.packets.MessagePacket;

public class ChatServerSocket implements ChatServer<ClientHandlerSocket> {
  private final Map<String, ClientHandlerSocket> clients = new ConcurrentHashMap<>();
  private ServerSocket serverSocket;

  @Override
  public void start() {
    try {

      this.serverSocket = new ServerSocket(Config.PORT_NUMBER);
      System.out.println("Socket server started on port " + Config.PORT_NUMBER);

      while (!serverSocket.isClosed()) {
        Socket clientSocket = serverSocket.accept();
        ClientHandler client = new ClientHandlerSocket(clientSocket, this);
        new Thread(client).start();
      }

    } catch (IOException e) {
      System.out.println("Error during server initialization: " + e.getMessage());
    }
  }

  @Override
  public synchronized void addClient(String nickname, ClientHandlerSocket clientHandler) {
    clients.put(nickname, clientHandler);
  }

  @Override
  public synchronized void removeClient(String nickname) {
    clients.remove(nickname);
  }

  @Override
  public synchronized Map<String, ClientHandlerSocket> getClients() {
    return clients;
  }

  @Override
  public synchronized void sendBroadcastMessage(String sender, String message) {
    for (Map.Entry<String, ClientHandlerSocket> client : clients.entrySet()) {
      ClientHandlerSocket clientHandler = client.getValue();
      clientHandler
          .sendPacket(new MessagePacket(sender, MessagePacketType.CHAT_MESSAGE, message));
    }
  }

  @Override
  public synchronized void sendBroadcastAction(ActionPacketType actionType, String message, List<String> data) {
    for (Map.Entry<String, ClientHandlerSocket> client : clients.entrySet()) {
      ClientHandlerSocket clientHandler = client.getValue();
      clientHandler.sendPacket(new ActionPacket(actionType, message, data));
    }
  }

  @Override
  public synchronized void sendPrivateMessage(String sender, String recepient, String message) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
