package server;

import server.service.rmi.ChatServerRMI;
import server.service.rmi.ChatServerRMIImpl;
import server.service.sockets.ChatServer;
import server.service.sockets.ChatServerSocket;
import server.service.sockets.ClientHandlerSocket;
import shared.Config;

public class ServerApp {
  public static void main(String[] args) {
    if (Config.STARTUP_MODE == 0) {
      ChatServer<ClientHandlerSocket> server = new ChatServerSocket();
      try {
        server.start();
      } catch (Exception e) {
        System.out.println("Socket server failed to start: " + e.getMessage());
      }
    } else if (Config.STARTUP_MODE == 1) {
      try {
        ChatServerRMI server = new ChatServerRMIImpl();
        server.start();
      } catch (Exception e) {
        System.out.println("RMI server failed to start: " + e.getMessage());
      }
    } else {
      System.out.println("Invalid STARTUP_MODE in Config.java");
    }

  }

}
