package controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.Instant;
import javax.swing.JOptionPane;

public class ErrorHandler {
  public void handleError(Exception exception) {
    JOptionPane.showMessageDialog(null, exception.getMessage(), "Chyba", JOptionPane.ERROR_MESSAGE);
    logError(exception);
  }

  private void logError(Exception exception) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.dat", true))) {
      writer.write("Date: " + Instant.now() + ": ");
      writer.write(exception.getMessage());
      writer.newLine();
      writer.flush();
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Při zápisu do logu došlo k chybě.", "Chyba", JOptionPane.ERROR_MESSAGE);
    }
  }
}
