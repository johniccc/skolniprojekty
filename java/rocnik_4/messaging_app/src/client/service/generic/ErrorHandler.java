package client.service.generic;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import shared.packets.ErrorPacket;

public class ErrorHandler {
  public static void handle(Component parent, ErrorPacket packet) {
    handleServerError(parent, packet);
  }

  public static void handle(Component parent, Exception e) {
    handleClientError(parent, e);
  }

  private static void handleServerError(Component parent, ErrorPacket packet) {
    SwingUtilities.invokeLater(
        () -> JOptionPane.showMessageDialog(parent, packet.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE));
  }

  private static void handleClientError(Component parent, Exception e) {
    SwingUtilities.invokeLater(
        () -> JOptionPane.showMessageDialog(parent, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE));
  }
}
