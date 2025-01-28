package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import models.variables.Product;

public class OrderOverview extends JDialog {
  public JPanel frameWrapper;
  private JPanel headerPanel;
  private JScrollPane orderItemsWrapper;
  private JPanel orderItems;
  private final double totalPrice;
  private final List<Product> orderData;
  private final String[] orderColumnNames;
  private JLabel title;
  private JPanel columnNames;
  private JPanel orderWrapper;
  private JPanel pricePanel;
  private JLabel totalPriceLabel;

  public OrderOverview(MainFrame owner, ModalityType modalityType, List<Product> orderData, String[] orderColumnNames,
      double totalPrice) {
    super(owner, modalityType);
    this.orderData = orderData;
    this.orderColumnNames = orderColumnNames;
    this.totalPrice = totalPrice;

    setTitle("Výpis objednávky");
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    initComponents();
    pack();
    setLocationRelativeTo(null);
  }

  private void initComponents() {
    headerPanel = new JPanel();
    headerPanel.setLayout(new FlowLayout());

    title = new JLabel("Výpis objednávky");
    title.setHorizontalAlignment(JLabel.CENTER);
    title.setFont(new Font("Dialog", Font.BOLD, 24));
    headerPanel.add(title);

    orderWrapper = new JPanel();
    orderWrapper.setLayout(new BoxLayout(orderWrapper, BoxLayout.Y_AXIS));

    columnNames = new JPanel();
    columnNames.setLayout(new BoxLayout(columnNames, BoxLayout.X_AXIS));
    for (int i = 0; i < orderColumnNames.length; i++) {
      JLabel label = new JLabel(orderColumnNames[i]);
      label.setFont(new Font("Dialog", Font.BOLD, 18));
      columnNames.add(label);
      if ((i + 1) != orderColumnNames.length)
        columnNames.add(Box.createHorizontalGlue());
    }

    orderItemsWrapper = new JScrollPane();
    orderItems = new JPanel();
    orderItems.setLayout(new BoxLayout(orderItems, BoxLayout.Y_AXIS));

    for (Product product : orderData) {
      int detailsFontSize = 16;

      JPanel itemPanel = new JPanel();
      itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));

      JLabel productName = new JLabel(product.getName());
      productName.setFont(new Font("Dialog", Font.PLAIN, detailsFontSize));
      JLabel price = new JLabel(String.valueOf(product.getPrice()) + " Kč");
      price.setFont(new Font("Dialog", Font.PLAIN, detailsFontSize));

      itemPanel.add(productName);
      itemPanel.add(Box.createHorizontalStrut(250));
      itemPanel.add(Box.createHorizontalGlue());
      itemPanel.add(price);

      orderItems.add(itemPanel);
    }
    orderItemsWrapper.setViewportView(orderItems);

    orderWrapper.add(columnNames);
    orderWrapper.add(Box.createVerticalStrut(10));
    orderWrapper.add(orderItems);

    pricePanel = new JPanel();

    totalPriceLabel = new JLabel("Celková cena: " + String.valueOf(totalPrice) + " Kč");
    totalPriceLabel.setHorizontalAlignment(JLabel.CENTER);
    totalPriceLabel.setFont(new Font("Dialog", Font.BOLD, 18));
    pricePanel.add(totalPriceLabel);

    setLayout(new BorderLayout());
    frameWrapper = new JPanel();
    frameWrapper.setLayout(new BoxLayout(frameWrapper, BoxLayout.Y_AXIS));
    int topBotPadding = 20;
    int sidePadding = 20;
    frameWrapper.setBorder(BorderFactory.createEmptyBorder(topBotPadding, sidePadding, topBotPadding, sidePadding));

    frameWrapper.add(headerPanel);
    frameWrapper.add(Box.createVerticalStrut(20));
    frameWrapper.add(orderWrapper);
    frameWrapper.add(Box.createVerticalStrut(35));
    frameWrapper.add(pricePanel);

    add(frameWrapper, BorderLayout.NORTH);
  }
}
