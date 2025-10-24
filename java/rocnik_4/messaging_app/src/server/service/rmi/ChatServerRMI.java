package server.service.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

import client.service.rmi.ChatClientRMI;

public interface ChatServerRMI extends Remote {
    /*
     * Builds upon ChatClient and ClientHandler interface
     */

    void authenticateClient(ChatClientRMI client) throws RemoteException;

    void start() throws RemoteException;

    void addClient(ChatClientRMI client) throws RemoteException;

    void removeClient(ChatClientRMI client) throws RemoteException;

    Map<String, ChatClientRMI> getClients() throws RemoteException;

    void sendBroadcastMessage(String sender, String message) throws RemoteException;

    void sendPrivateMessage(String sender, String recepient, String message) throws RemoteException;
}
