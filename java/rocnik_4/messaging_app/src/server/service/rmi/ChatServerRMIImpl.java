package server.service.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import client.service.rmi.ChatClientRMI;
import shared.Config;
import shared.enums.ActionPacketType;
import shared.enums.MessagePacketType;
import shared.packets.ActionPacket;
import shared.packets.ErrorPacket;
import shared.packets.MessagePacket;

public class ChatServerRMIImpl extends UnicastRemoteObject implements ChatServerRMI {
	private final Map<String, ChatClientRMI> clients = new ConcurrentHashMap<>();

	public ChatServerRMIImpl() throws RemoteException {
		super();
	}

	@Override
	public void start() throws RemoteException {
		Registry registry = LocateRegistry.createRegistry(Config.PORT_NUMBER);
		registry.rebind("ChatServer", this);
		System.out.println("RMI server started on port " + Config.PORT_NUMBER);
	}

	@Override
	public void authenticateClient(ChatClientRMI client) throws RemoteException {
		if (client.getNickname() == null || client.getNickname().isEmpty()) {
			client.receivePacket(new ActionPacket(ActionPacketType.INVOKE_SIGN_IN, "", null));
		} else {
			if (getClients().containsKey(client.getNickname())) {
				client.receivePacket(new ErrorPacket("Username already taken."));
				return;
			}
			addClient(client);
			client.receivePacket(new ActionPacket(ActionPacketType.CHATROOM_ACCESS_GRANTED, client.getNickname(), null));
		}
	}

	@Override
	public void addClient(ChatClientRMI client) throws RemoteException {
		clients.put(client.getNickname(), client);
		sendBroadcastMessage("SERVER", client.getNickname() + " joined the channel.");
	}

	@Override
	public void removeClient(ChatClientRMI client) throws RemoteException {
		clients.remove(client.getNickname());
		sendBroadcastMessage("SERVER", client.getNickname() + " left the channel.");
	}

	@Override
	public Map<String, ChatClientRMI> getClients() throws RemoteException {
		return clients;
	}

	@Override
	public void sendBroadcastMessage(String sender, String message) throws RemoteException {
		for (Map.Entry<String, ChatClientRMI> recipient : clients.entrySet()) {
			ChatClientRMI chatClient = recipient.getValue();
			try {
				chatClient.receivePacket(new MessagePacket(sender, MessagePacketType.CHAT_MESSAGE, message));
			} catch (Exception e) {
				System.out.println("Error sending broadcast message to " + recipient.getKey() + ": " + e.getMessage());
			}
		}
	}

	@Override
	public void sendPrivateMessage(String sender, String recipient, String message) throws RemoteException {
		// TODO Implement private messaging
	}

}
