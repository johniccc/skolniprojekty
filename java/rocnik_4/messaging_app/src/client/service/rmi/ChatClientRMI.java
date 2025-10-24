package client.service.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import shared.packets.Packet;

public interface ChatClientRMI extends Remote {
    /*
     * Builds upon ChatClient interface
     */

    void connectToServer(String hostname, int port) throws RemoteException;

    void receivePacket(Packet packet) throws RemoteException;

    void sendPacket(Packet packet) throws RemoteException;

    String getNickname() throws RemoteException;
}
