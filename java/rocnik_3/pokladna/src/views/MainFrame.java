package views;

import controllers.ProductsController;
import controllers.ReceiptsController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import models.CartInternalDataSource;
import models.ProductsDataSource;
import models.variables.Product;

public class MainFrame extends JFrame {
  private JPanel toolbar;
  private JButton orderHistoryBtn;
  private JScrollPane cart;
  private JSplitPane splitPane;
  private JScrollPane products;
  private JPanel cartContainer;
  private JPanel productsPanel;
  private JPanel cartButtons;
  private JButton continueBtn;
  private JButton clearBtn;
  private final ProductsController productsController;
  private final ReceiptsController receiptsController;
  private final CartInternalDataSource cartModel;
  private final int topBotBtnPadding = 15;
  private final int sideBtnPadding = 25;

  private static MainFrame instance;

  private MainFrame(List<Product> products) {
    setTitle("Pokladna");
    setSize(1400, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.productsController = new ProductsController();
    this.receiptsController = new ReceiptsController();
    this.cartModel = CartInternalDataSource.getInstance();

    initComponents(products);

    updateCart();
  }

  public static MainFrame getInstance() {
    if (instance == null) {
      ProductsDataSource products = new ProductsDataSource();
      instance = new MainFrame(products.getAllProducts());
    }
    return instance;
  }

  private void initComponents(List<Product> productsList) {
    setLayout(new BorderLayout());

    products = new JScrollPane();
    productsPanel = new JPanel();
    productsPanel.setLayout(new GridLayout(0, 4));

    for (Product product : productsList) {
      JButton productBtn = new JButton();
      productBtn.setMargin(new Insets(40, 10, 40, 10));
      productBtn.setLayout(new GridBagLayout());
      productBtn.setSize(50, 50);
      productBtn.addActionListener(e -> {
        productsController.addToCart(product);
      });

      JLabel productNameLabel = new JLabel(product.getName());
      productNameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
      JLabel productPriceLabel = new JLabel(String.valueOf(product.getPrice()) + " Kč");
      productPriceLabel.setFont(new Font("Dialog", Font.PLAIN, 14));

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      gbc.insets = new Insets(0, 0, 5, 0);
      gbc.weightx = 0;
      gbc.weighty = 0;
      gbc.fill = GridBagConstraints.NONE;

      productBtn.add(productNameLabel, gbc);

      gbc.gridy = 1;

      productBtn.add(productPriceLabel, gbc);

      productsPanel.add(productBtn);
    }

    products.setViewportView(productsPanel);

    cartContainer = new JPanel();
    cartContainer.setLayout(new BoxLayout(cartContainer, BoxLayout.Y_AXIS));

    cart = new JScrollPane();
    cart.setPreferredSize(new Dimension(250, getHeight()));
    cart.setBorder(new TitledBorder(null, "Košík"));

    cartButtons = new JPanel();
    cartButtons.setLayout(new GridLayout(0, 2));

    clearBtn = new JButton("Odebrat vše");
    clearBtn.addActionListener(e -> {
      productsController.clearCart();
    });

    continueBtn = new JButton("Pokračovat");
    continueBtn.addActionListener(e -> {
      productsController.continueOrder();
    });

    clearBtn.setMargin(new Insets(topBotBtnPadding, sideBtnPadding, topBotBtnPadding, sideBtnPadding));
    continueBtn.setMargin(new Insets(topBotBtnPadding, sideBtnPadding, topBotBtnPadding, sideBtnPadding));

    cartButtons.add(clearBtn);
    cartButtons.add(continueBtn);

    cartContainer.add(cart);
    cartContainer.add(cartButtons);

    toolbar = new JPanel();
    toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));

    orderHistoryBtn = new JButton("Historie objednávek");
    orderHistoryBtn.addActionListener(e -> {
      receiptsController.showOrderHistory(this);
    });

    orderHistoryBtn.setMargin(new Insets(topBotBtnPadding, sideBtnPadding, topBotBtnPadding, sideBtnPadding));

    toolbar.add(orderHistoryBtn);

    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, products, cartContainer);
    splitPane.setDividerLocation(1000);
    splitPane.setResizeWeight(1);

    add(splitPane, BorderLayout.CENTER);
    add(toolbar, BorderLayout.SOUTH);
  }

  public final void updateCart() {
    List<Product> cartItems = cartModel.getAll();

    JPanel wrapper = new JPanel();
    wrapper.setLayout(new BorderLayout());

    JPanel cartPanel = new JPanel();
    cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
    cartPanel.setAlignmentY(Component.TOP_ALIGNMENT);

    if (cartItems.isEmpty()) {
      cartPanel.setLayout(new GridBagLayout());

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = GridBagConstraints.CENTER;
      gbc.insets = new Insets(0, 0, 5, 0);
      gbc.weightx = 0;
      gbc.weighty = 0;
      gbc.fill = GridBagConstraints.NONE;

      JLabel cartEmptyLabel = new JLabel("Košík je prázdný");

      cartPanel.add(cartEmptyLabel, gbc);
      cart.setViewportView(cartPanel);
      return;
    }

    for (int i = 0; i < cartItems.size(); i++) {
      final int id = i;

      JPanel item = new JPanel();
      item.setLayout(new GridBagLayout());

      JLabel itemName = new JLabel(cartItems.get(id).getName());
      itemName.setFont(new Font("Dialog", Font.BOLD, 16));
      JLabel itemPrice = new JLabel(String.valueOf(cartItems.get(id).getPrice()) + " Kč");
      itemPrice.setFont(new Font("Dialog", Font.ITALIC, 14));

      ImageIcon imageIcon = new ImageIcon("x.png");
      Image image = imageIcon.getImage();
      int originalWidth = image.getWidth(null);
      int originalHeight = image.getHeight(null);
      int newHeight = 30;
      int newWidth = (int) ((newHeight / (double) originalHeight) * originalWidth);
      image = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
      imageIcon = new ImageIcon(image);

      int btnPadding = 10;
      int btnSize = newHeight + btnPadding;
      JButton removeBtn = new JButton();
      removeBtn.setPreferredSize(new Dimension(btnSize, btnSize));
      removeBtn.setIcon(imageIcon);

      removeBtn.addActionListener(e -> {
        productsController.removeFromCart(id);
      });

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weightx = 0;
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.NONE;
      item.add(itemName, gbc);

      gbc.gridx = 1;
      gbc.weightx = 1;
      gbc.anchor = GridBagConstraints.WEST;
      item.add(itemPrice, gbc);

      gbc.gridx = 2;
      gbc.weightx = 0;
      gbc.anchor = GridBagConstraints.EAST;
      gbc.fill = GridBagConstraints.NONE;
      item.add(removeBtn, gbc);

      item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
      item.setPreferredSize(new Dimension(item.getPreferredSize().width, 50));
      item.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

      cartPanel.add(item);
      cartPanel.add(Box.createVerticalStrut(10));
    }

    wrapper.add(cartPanel, BorderLayout.NORTH);

    cart.setViewportView(wrapper);
    cart.revalidate();
    cart.repaint();
  }
}
