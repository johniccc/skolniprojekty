package spse.stefacek.presentation.admin.dialogs.form;

import java.awt.GridLayout;
import java.awt.Window;
import java.math.BigDecimal;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import spse.stefacek.data.model.Enhancement;
import spse.stefacek.service.impl.EnhancementService;

public class EnhancementForm extends BaseFormDialog<Enhancement> {
  private JLabel nameLabel, descriptionLabel, priceLabel;
  private JTextField nameField, descriptionField, priceField;

  public EnhancementForm(Window owner, EnhancementService service, Enhancement item) {
    super(owner, service, item);
  }

  @Override
  protected int getItemId(Enhancement item) {
    return item.getId();
  }

  @Override
  protected void createForm() {
    formPanel = new JPanel();
    formPanel.setLayout(new GridLayout(0, 2, 5, 5));

    nameLabel = new JLabel("Název");
    nameField = new JTextField(20);

    descriptionLabel = new JLabel("Popis vylepšení");
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
  protected void fillFormFields(Enhancement item) {
    nameField.setText(item.getName());
    descriptionField.setText(item.getDescription());
    priceField.setText(item.getPrice().toString());
  }

  @Override
  protected Enhancement getItemFromFormFields() {
    Enhancement enhancement = isNewItem ? new Enhancement() : item;
    enhancement.setName(nameField.getText());
    enhancement.setDescription(descriptionField.getText());
    enhancement.setPrice(new BigDecimal(priceField.getText().replaceAll("\\s+", "")));

    return enhancement;
  }
}
