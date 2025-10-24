package client;

import client.service.generic.ErrorHandler;
import client.service.rmi.ChatClientRMIImpl;
import client.service.sockets.ChatClientSocket;
import shared.Config;

import java.io.IOException;

public class ClientApp {
  public static void main(String[] args) {
    if (Config.STARTUP_MODE == 0) {
      ChatClientSocket client = new ChatClientSocket();
      try {
        client.startClient();
      } catch (IOException e) {
        ErrorHandler.handle(null, e);
      }
    } else if (Config.STARTUP_MODE == 1) {
      try {
        ChatClientRMIImpl client = new ChatClientRMIImpl();
        client.startClient();
      } catch (IOException e) {
        ErrorHandler.handle(null, e);
      }
    } else {
      System.out.println("Invalid STARTUP_MODE in Config.java");
    }

  }
}
