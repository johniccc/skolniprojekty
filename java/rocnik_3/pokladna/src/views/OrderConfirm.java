package views;

import controllers.ProductsController;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import models.variables.Product;

public class OrderConfirm extends OrderOverview {
  private final ProductsController productsController;

  private final MainFrame owner;

  private JPanel buttons;
  private JButton discardBtn;
  private JButton confirmBtn;

  public OrderConfirm(MainFrame owner, ModalityType modalityType, List<Product> orderData, String[] orderColumnNames,
      double totalPrice) {
    super(owner, modalityType, orderData, orderColumnNames, totalPrice);
    this.productsController = new ProductsController();
    this.owner = owner;

    initComponents();
    pack();
    setLocationRelativeTo(null);
  }

  private void initComponents() {
    buttons = new JPanel();
    buttons.setLayout(new GridLayout(0, 2));

    discardBtn = new JButton("Zahodit");
    discardBtn.addActionListener(e -> {
      productsController.discardOrder(this, owner);
    });

    confirmBtn = new JButton("Potvrdit");
    confirmBtn.addActionListener(e -> {
      productsController.confirmOrder(this, owner);
    });

    int topBotPadding = 15;
    int sidePadding = 25;

    discardBtn.setMargin(new Insets(topBotPadding, sidePadding, topBotPadding, sidePadding));
    confirmBtn.setMargin(new Insets(topBotPadding, sidePadding, topBotPadding, sidePadding));
    buttons.add(discardBtn);
    buttons.add(confirmBtn);

    frameWrapper.add(Box.createVerticalStrut(10));
    frameWrapper.add(buttons);
  }
}
