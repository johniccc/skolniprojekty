package client.service.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import client.presentation.ChatFrame;
import client.presentation.LoginFrame;
import client.service.generic.ErrorHandler;
import server.service.rmi.ChatServerRMI;
import shared.Config;
import shared.enums.ActionPacketType;
import shared.enums.MessagePacketType;
import shared.packets.ActionPacket;
import shared.packets.ErrorPacket;
import shared.packets.MessagePacket;
import shared.packets.Packet;

public class ChatClientRMIImpl extends UnicastRemoteObject implements ChatClientRMI {
    ChatServerRMI handler;
    String nickname;

    LoginFrame login;
    ChatFrame chatWindow;

    List<MessagePacket> allMessages = new ArrayList<>();

    public ChatClientRMIImpl() throws RemoteException {
        super();
    }

    public void startClient() {
        try {
            connectToServer("localhost", Config.PORT_NUMBER);
            handler.authenticateClient(this);
        } catch (RemoteException e) {
            ErrorHandler.handle(null, e);
        }

    }

    @Override
    public void connectToServer(String hostname, int port) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(hostname, port);
            this.handler = (ChatServerRMI) registry.lookup(Config.RMI_ID);
        } catch (Exception e) {
            ErrorHandler.handle(null, e);
        }
    }

    @Override
    public void receivePacket(Packet packet) throws RemoteException {
        new Thread(() -> {
            switch (packet) {
                case MessagePacket msg -> {
                    switch (msg.getMessageType()) {
                        case MessagePacketType.CHAT_MESSAGE:
                            allMessages.add(msg);
                            if (chatWindow != null) {
                                chatWindow.refreshChatMessages(allMessages);
                                try {
                                    chatWindow.refreshActiveUsers(new ArrayList<String>(handler.getClients().keySet()));
                                } catch (Exception e) {
                                    ErrorHandler.handle(null, e);
                                }
                            }
                        default:
                            break;
                    }
                }
                case ActionPacket action -> {
                    switch (action.getActionType()) {
                        case ActionPacketType.INVOKE_SIGN_IN:
                            SwingUtilities.invokeLater(() -> {
                                login = new LoginFrame(this);
                                login.setVisible(true);
                            });
                            break;
                        case ActionPacketType.CHATROOM_ACCESS_GRANTED:
                            nickname = action.getMessage();
                            login.dispose();
                            login = null;
                            chatWindow = new ChatFrame(this);
                            chatWindow.setVisible(true);
                            chatWindow.refreshChatMessages(allMessages);
                            try {
                                chatWindow.refreshActiveUsers(new ArrayList<String>(handler.getClients().keySet()));
                            } catch (RemoteException e) {
                                ErrorHandler.handle(null, e);
                            }

                            break;
                        default:
                            break;
                    }
                }
                case ErrorPacket error -> {
                    ErrorHandler.handle(null, error);
                }
                case null -> {
                    break;
                }
                default -> {
                    break;
                }
            }
        }).start();

    }

    public void sendPacket(Packet packet) throws RemoteException {
        // Implement sending private messages
        new Thread(() -> {
            try {
                switch (packet) {
                    case MessagePacket msg -> {
                        switch (msg.getMessageType()) {
                            case MessagePacketType.INPUT_DETAILS:
                                nickname = msg.getMessageContent();
                                handler.authenticateClient(this);
                                break;
                            case MessagePacketType.CHAT_MESSAGE:
                                handler.sendBroadcastMessage(this.getNickname(), msg.getMessageContent());
                                break;
                            default:
                                break;
                        }
                    }
                    case ActionPacket action -> {
                        switch (action.getActionType()) {
                            case ActionPacketType.LEAVE_CHATROOM:
                                handler.removeClient(this);
                                break;
                            default:
                                break;
                        }
                    }
                    default -> {
                        break;
                    }
                }
            } catch (RemoteException e) {
                ErrorHandler.handle(null, e);
            }
        }).start();

    }

    @Override
    public String getNickname() throws RemoteException {
        return this.nickname;
    }
}
