package client.service.sockets;

import java.net.ConnectException;
import shared.packets.Packet;

public interface ChatClient {

  void connectToServer(String hostname, int port) throws ConnectException;

  void sendPacket(Packet packet);

  void recieveIncomingPackets() throws ConnectException;

  void processIncomingPacket(Packet packet);

  String getNickname();
}
