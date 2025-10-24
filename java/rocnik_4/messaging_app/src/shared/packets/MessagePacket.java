package shared.packets;

import shared.enums.MessagePacketType;

public class MessagePacket extends Packet {
  String sender;
  MessagePacketType messageType;
  String messageContent;

  public MessagePacket(String sender, MessagePacketType messageType, String messageContent) {
    this.sender = sender;
    this.messageType = messageType;
    this.messageContent = messageContent;
  }

  public String getSender() {
    return this.sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public MessagePacketType getMessageType() {
    return this.messageType;
  }

  public void setMessageType(MessagePacketType messageType) {
    this.messageType = messageType;
  }

  public String getMessageContent() {
    return this.messageContent;
  }

  public void setMessageContent(String messageContent) {
    this.messageContent = messageContent;
  }

}
