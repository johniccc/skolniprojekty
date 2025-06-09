package spse.stefacek.presentation.admin.dialogs.form;

import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import spse.stefacek.data.model.Category;
import spse.stefacek.service.impl.CategoryService;

public class CategoryForm extends BaseFormDialog<Category> {
  private JLabel nameLabel, descriptionLabel;
  private JTextField nameField, descriptionField;

  public CategoryForm(Window owner, CategoryService service, Category item) {
    super(owner, service, item);
  }

  @Override
  protected int getItemId(Category item) {
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

    formPanel.add(nameLabel);
    formPanel.add(nameField);

    formPanel.add(descriptionLabel);
    formPanel.add(descriptionField);
  }

  @Override
  protected void fillFormFields(Category item) {
    nameField.setText(item.getName());
    descriptionField.setText(item.getDescription());
  }

  @Override
  protected Category getItemFromFormFields() {
    Category category = isNewItem ? new Category() : item;
    category.setName(nameField.getText());
    category.setDescription(descriptionField.getText());

    return category;
  }

}
