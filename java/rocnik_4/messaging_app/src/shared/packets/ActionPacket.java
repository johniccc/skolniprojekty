package shared.packets;

import java.util.List;

import shared.enums.ActionPacketType;

public class ActionPacket extends Packet {
  private ActionPacketType actionType;
  private String message;
  private List<String> userList;

  public ActionPacket(ActionPacketType actionType, String message, List<String> userList) {
    this.actionType = actionType;
    this.message = message;
    this.userList = userList;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ActionPacketType getActionType() {
    return this.actionType;
  }

  public void setActionType(ActionPacketType actionType) {
    this.actionType = actionType;
  }

  public List<String> getUserList() {
    return this.userList;
  }

  public void setUserList(List<String> userList) {
    this.userList = userList;
  }

}
