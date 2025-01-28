package models;

import controllers.ErrorHandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import models.variables.Order;
import models.variables.Product;

public class ReceiptsGenerator {
  private final ErrorHandler errorHandler;
  private File indexFile;
  private final String folderPath = "receipts";
  private final String indexFilePath = "receipt_index.dat";

  public ReceiptsGenerator() {
    this.errorHandler = new ErrorHandler();
    createIndex();
  }

  public final void createIndex() {
    try {
      File folder = new File(folderPath);

      if (!folder.exists()) {
        folder.mkdir();
      }

      this.indexFile = new File(folder, indexFilePath);
      this.indexFile.createNewFile();
    } catch (IOException e) {
      errorHandler.handleError(new Exception("Chyba při vytváření souboru index: " + e.getMessage()));
    }
  }

  public void clearIndex() {
    try (FileWriter writer = new FileWriter(indexFile, false)) {
      writer.flush();
    } catch (IOException e) {
      errorHandler.handleError(new Exception("Chyba při zápisu do indexu: " + e.getMessage()));
    }
  }

  public File generateReceipt(Order order) {
    int receiptCount = getCount();
    if (receiptCount >= 10) {
      receiptCount = 0;
      clearIndex();
    }

    int index = receiptCount + 1;
    String filePath = "receipt" + index + ".txt";

    File folder = new File(folderPath);
    if (!folder.exists()) {
      folder.mkdir();
    }
    if (indexFile == null || !indexFile.exists()) {
      createIndex();
    }

    File receipt = new File(folder, filePath);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(receipt))) {
      writer.write(order.getTimestamp().toString() + "\n");
      writer.newLine();

      writer.write(String.format("%-30s %10s%n", "Produkt", "Cena"));
      writer.write("=========================================\n");

      for (Product product : order.getProducts()) {
        writer.write(String.format("%-30s %10.2f%n", product.getName(), product.getPrice()));
      }

      writer.write("=========================================\n");
      writer.write(String.format("%-30s %10.2f%n", "Celková cena", order.getTotalPrice()));
    } catch (IOException e) {
      errorHandler.handleError(new Exception("Chyba při zápisu do souboru: " + e.getMessage()));
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(indexFile, true))) {
      writer.write(index);
      writer.newLine();
    } catch (IOException e) {
      errorHandler.handleError(new Exception("Chyba při zápisu do indexu: " + e.getMessage()));
    }

    return receipt;
  }

  public int getCount() {
    int count = 0;

    File folder = new File(folderPath);
    if (!folder.exists() || !indexFile.exists()) {
      return 0;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(indexFile))) {
      while (reader.readLine() != null) {
        count++;
      }
    } catch (IOException e) {
      errorHandler.handleError(new Exception("Chyba při čtení z indexu: " + e.getMessage()));
    }
    return count;
  }
}
