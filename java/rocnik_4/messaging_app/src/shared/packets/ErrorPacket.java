package shared.packets;

public class ErrorPacket extends Packet {
  String errorMessage;

  public ErrorPacket(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

}
