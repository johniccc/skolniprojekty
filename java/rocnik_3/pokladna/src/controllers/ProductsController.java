package controllers;

import java.awt.Dialog;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import models.CartInternalDataSource;
import models.OrderInternalDataSource;
import models.ReceiptsGenerator;
import models.variables.Order;
import models.variables.Product;
import views.MainFrame;
import views.OrderConfirm;

public class ProductsController {
  private final CartInternalDataSource cartModel;
  private final OrderInternalDataSource orderModel;
  private MainFrame mainFrame;
  private ErrorHandler errorHandler;
  private final ReceiptsGenerator receiptsGenerator;

  public ProductsController() {
    this.cartModel = CartInternalDataSource.getInstance();
    this.orderModel = OrderInternalDataSource.getInstance();
    this.errorHandler = new ErrorHandler();
    this.receiptsGenerator = new ReceiptsGenerator();
  }

  public void initMainFrame() {
    SwingUtilities.invokeLater(() -> {
      mainFrame = MainFrame.getInstance();
      mainFrame.setVisible(true);
    });
  }

  public void addToCart(Product product) {
    cartModel.add(product);
    MainFrame.getInstance().updateCart();
  }

  public void removeFromCart(int id) {
    cartModel.remove(id);
    MainFrame.getInstance().updateCart();
  }

  public void clearCart() {
    cartModel.removeAll();
    MainFrame.getInstance().updateCart();
  }

  public void continueOrder() {
    if (cartModel.getAll().isEmpty()) {
      Exception ex = new IllegalStateException("Košík je prázdný!");
      errorHandler = new ErrorHandler();
      errorHandler.handleError(ex);
      return;
    }

    List<Product> orderData = cartModel.getAll();
    String[] orderColumnNames = { "Název", "Cena" };
    double totalPrice = cartModel.getTotalPrice();

    JDialog confirmOrderDialog = new OrderConfirm(MainFrame.getInstance(), Dialog.ModalityType.APPLICATION_MODAL, orderData, orderColumnNames, totalPrice);
    confirmOrderDialog.setVisible(true);
  }

  public void discardOrder(Dialog parent, MainFrame owner) {
    cartModel.removeAll();
    parent.dispose();
    owner.updateCart();
  }

  public void confirmOrder(Dialog parent, MainFrame owner) {
    List<Product> products = cartModel.getAll();

    // ? Create copies of products, because of object reference collisions
    List<Product> productsCopy = new ArrayList<>();
    for (Product product : products) {
      productsCopy.add(new Product(product));
    }

    Order order = new Order(productsCopy);
    orderModel.add(order);
    cartModel.removeAll();

    File receiptFile = receiptsGenerator.generateReceipt(order);
    JOptionPane.showMessageDialog(parent, "Objednávka byla úspěšně provedena!\nÚčtenka byla vytisknuta do souboru "
        + receiptFile.getPath().replace("receipts\\", "") + ".", "Potvrzení", JOptionPane.PLAIN_MESSAGE);
    parent.dispose();
    owner.updateCart();
  }
}
