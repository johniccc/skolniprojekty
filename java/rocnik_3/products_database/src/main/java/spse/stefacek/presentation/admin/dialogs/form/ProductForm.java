package spse.stefacek.presentation.admin.dialogs.form;

import java.awt.GridLayout;
import java.awt.Window;
import java.math.BigDecimal;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import spse.stefacek.data.model.Product;
import spse.stefacek.service.impl.ProductService;

public class ProductForm extends BaseFormDialog<Product> {
  private JLabel nameLabel, descriptionLabel, priceLabel;
  private JTextField nameField, descriptionField, priceField;

  public ProductForm(Window owner, ProductService service, Product item) {
    super(owner, service, item);
  }

  @Override
  protected int getItemId(Product item) {
    return item.getId();
  }

  @Override
  protected void createForm() {
    formPanel = new JPanel();
    formPanel.setLayout(new GridLayout(0, 2, 5, 5));

    nameLabel = new JLabel("Název");
    nameField = new JTextField(20);

    descriptionLabel = new JLabel("Popis produktu");
    descriptionField = new JTextField(20);

    priceLabel = new JLabel("Cena");
    priceField = new JTextField(20);

    formPanel.add(nameLabel);
    formPanel.add(nameField);

    formPanel.add(descriptionLabel);
    formPanel.add(descriptionField);

    formPanel.add(priceLabel);
    formPanel.add(priceField);
  }

  @Override
  protected void fillFormFields(Product item) {
    nameField.setText(item.getName());
    descriptionField.setText(item.getDescription());
    priceField.setText(item.getPrice().toString());
  }

  @Override
  protected Product getItemFromFormFields() {
    Product product = isNewItem ? new Product() : item;
    product.setName(nameField.getText());
    product.setDescription(descriptionField.getText());
    product.setPrice(new BigDecimal(priceField.getText().replaceAll("\\s+", "")));

    return product;
  }

}
