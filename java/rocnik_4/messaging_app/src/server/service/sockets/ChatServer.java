package server.service.sockets;

import java.util.List;
import java.util.Map;

import shared.enums.ActionPacketType;

public interface ChatServer<T> {
  void start();

  void addClient(String nickname, T clientHandler);

  void removeClient(String nickname);

  Map<String, T> getClients();

  void sendBroadcastMessage(String sender, String message);

  void sendBroadcastAction(ActionPacketType actionType, String message, List<String> data);

  void sendPrivateMessage(String sender, String recepient, String message);
}
