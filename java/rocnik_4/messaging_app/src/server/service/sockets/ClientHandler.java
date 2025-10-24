package server.service.sockets;

import shared.packets.Packet;

public interface ClientHandler extends Runnable {
  void sendPacket(Packet packet);

  void processRecievedMessage(Packet packet);
}
