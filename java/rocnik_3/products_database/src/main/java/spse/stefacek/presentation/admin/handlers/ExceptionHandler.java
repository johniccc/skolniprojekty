package spse.stefacek.presentation.admin.handlers;

import java.awt.Component;

import javax.swing.JOptionPane;

import spse.stefacek.exceptions.templates.AbstractException;
import spse.stefacek.exceptions.templates.ErrorSeverity;

public class ExceptionHandler {
  public static void handle(Component parentComponent, AbstractException e) {
    // TODO Logging logic
    handleException(parentComponent, e);
  }

  private static void handleException(Component parentComponent, AbstractException e) {
    ErrorSeverity es = e.getSeverity();
    String title;
    int messageType;

    switch (es) {
      case INFO -> {
        title = "Info";
        messageType = JOptionPane.INFORMATION_MESSAGE;
      }
      case WARNING -> {
        title = "Varování";
        messageType = JOptionPane.WARNING_MESSAGE;
      }
      case ERROR -> {
        title = "Chyba";
        messageType = JOptionPane.ERROR_MESSAGE;
      }
      case FATAL -> {
        title = "Kritická chyba";
        messageType = JOptionPane.ERROR_MESSAGE;
      }
      default -> {
        title = "Chyba";
        messageType = JOptionPane.ERROR_MESSAGE;
      }
    }

    JOptionPane.showMessageDialog(parentComponent, e.getMessage(), title, messageType);
  }
}
