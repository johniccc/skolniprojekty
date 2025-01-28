package controllers;

import java.awt.Dialog;
import java.io.File;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import models.OrderInternalDataSource;
import models.ReceiptsGenerator;
import models.variables.Order;
import models.variables.Product;
import views.MainFrame;
import views.OrderHistory;
import views.OrderOverview;

public class ReceiptsController {
  private final OrderInternalDataSource orderModel;
  private final ReceiptsGenerator receiptsGenerator;
  private final ErrorHandler errorHandler;

  public ReceiptsController() {
    this.orderModel = OrderInternalDataSource.getInstance();
    this.receiptsGenerator = new ReceiptsGenerator();
    this.errorHandler = new ErrorHandler();
  }

  public void showOrderHistory(MainFrame parent) {
    Object[] orderColumnNames = { "ID účtenky", "Čas", "Počet položek", "Celková cena" };
    List<Order> orders = orderModel.getAll();

    Object[][] orderData = new Object[orders.size()][orderColumnNames.length];
    for (int i = 0; i < orders.size(); i++) {
      Order order = orders.get(i);
      orderData[i][0] = i + 1;
      orderData[i][1] = order.getTimestamp().toString();
      orderData[i][2] = order.getProductsCount();
      orderData[i][3] = order.getTotalPrice();
    }

    JDialog orderHistoryDialog = new OrderHistory(parent, Dialog.ModalityType.APPLICATION_MODAL, orderData,
        orderColumnNames);
    orderHistoryDialog.setVisible(true);
  }

  public void printReceipt(int[] selectedRows) {
    switch (selectedRows.length) {
      case 0 -> errorHandler.handleError(new Exception("Pro vytisknutí je potřeba vybrat alespoň jednu účtenku!"));
      case 1 -> {
        File receiptFile = receiptsGenerator.generateReceipt(orderModel.getById(selectedRows[0]));
        JOptionPane.showMessageDialog(null, "Účtenka byla vytisknuta do souboru "
            + receiptFile.getPath().replace("receipts\\", "") + ".", "Potvrzení", JOptionPane.PLAIN_MESSAGE);
      }
      default -> {
        String[] receiptFileNames = new String[selectedRows.length];
        for (int i : selectedRows) {
          File receiptFile = receiptsGenerator.generateReceipt(orderModel.getById(i));
          receiptFileNames[i] = receiptFile.getPath().replace("receipts\\", "");
        }
        JOptionPane.showMessageDialog(null, "Účtenky byly vytisknuty do souborů "
            + String.join(", ", receiptFileNames) + ".", "Potvrzení", JOptionPane.PLAIN_MESSAGE);
      }
    }
  }

  public void showReceipt(int[] selectedRows) {
    if (selectedRows.length > 1) {
      errorHandler.handleError(new Exception("Lze zobrazit pouze jednu účtenku!"));
    } else if (selectedRows.length == 0) {
      errorHandler.handleError(new Exception("Vyber jednu účtenku!"));
    } else {
      Order order = orderModel.getById(selectedRows[0]);

      List<Product> orderData = order.getProducts();
      String[] orderColumnNames = { "Název", "Cena" };
      double totalPrice = order.getTotalPrice();

      SwingUtilities.invokeLater(() -> {
        JDialog orderOverview = new OrderOverview(MainFrame.getInstance(), Dialog.ModalityType.APPLICATION_MODAL,
            orderData,
            orderColumnNames, totalPrice);
        orderOverview.setVisible(true);
      });
    }
  }
}
