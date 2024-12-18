package cz.spse.bajer.pg3.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cz.spse.bajer.pg3.User;
import cz.spse.bajer.pg3.UserManager;

public class AdminFrame extends JFrame {
  final private UserManager userManagerInstance;
  final private String[] columnNames;
  private DefaultTableModel tableModel;
  private JComboBox<String> chooseSort, chooseFilter;
  private JTable usersTable;
  final private String currentUsername;
  //final private User currentUser;

  public AdminFrame(String username, User uzivatel) {
    this.userManagerInstance = UserManager.getInstance();
    this.columnNames = new String[] { "Uživatelské jméno", "Heslo", "Titul", "Pohlaví", "Jméno", "Příjmení",
        "Datum narození",
        "Email",
        "Telefon", "Rodné číslo", "Admin" };
    this.currentUsername = username;
    //this.currentUser = uzivatel;
    setTitle("Přehled studentů");

    setSize(1000, 400);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    vytvorKomponenty();
  }

  final public void vytvorKomponenty() {
    int tablePadding = 20;
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBorder(new EmptyBorder(tablePadding, tablePadding, tablePadding, tablePadding));

    JLabel sortLabel = new JLabel("Seřadit dle:");
    chooseSort = new JComboBox<>(
        new String[] { "Výchozí", "Dle křestního jména", "Dle příjmení", "Dle data narození" });

    JPanel sortPanel = new JPanel();
    sortPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
    sortPanel.add(sortLabel);
    sortPanel.add(chooseSort);

    JLabel filterLabel = new JLabel("Filtrovat:");
    chooseFilter = new JComboBox<>(
        new String[] { "Všichni", "Administrátoři", "Lidé nad 18 let", "Muži", "Ženy" });

    JPanel filterPanel = new JPanel();
    filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
    filterPanel.add(filterLabel);
    filterPanel.add(chooseFilter);

    chooseSort.addActionListener(e -> {
      AdminFrame.this.manipulateTableData(chooseSort.getSelectedItem().toString(),
          chooseFilter.getSelectedItem().toString());
    });
    chooseFilter.addActionListener(e -> {
      AdminFrame.this.manipulateTableData(chooseSort.getSelectedItem().toString(),
          chooseFilter.getSelectedItem().toString());
    });

    JPanel sortAndFilter = new JPanel();
    sortAndFilter.setLayout(new GridLayout(1, 2, 10, 0));
    sortAndFilter.add(sortPanel);
    sortAndFilter.add(filterPanel);

    JPanel dataManipulation = new JPanel();
    dataManipulation.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    dataManipulation.setBorder(new EmptyBorder(0, 0, 30, 0));
    dataManipulation.add(sortAndFilter);

    this.tableModel = new DefaultTableModel(this.getData(), this.columnNames);
    usersTable = new JTable(this.tableModel);

    JScrollPane tableContainer = new JScrollPane(usersTable);

    JPanel buttons = new JPanel();
    buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));

    JButton addUser = new JButton("Přidat");
    addUser.addActionListener(e -> {
      AdminFrame.this.addUser();
    });

    JButton removeUser = new JButton("Odebrat");
    removeUser.addActionListener(e -> {
      AdminFrame.this.removeUser();
    });

    JButton editUser = new JButton("Upravit/Změnit heslo");
    editUser.addActionListener(e -> {
      AdminFrame.this.editUser();
    });

    JButton refreshUsers = new JButton("Obnovit seznam");
    refreshUsers.addActionListener(e -> {
      AdminFrame.this.refreshUsers();
    });

    buttons.add(addUser);
    buttons.add(removeUser);
    buttons.add(editUser);
    buttons.add(refreshUsers);

    mainPanel.add(dataManipulation, BorderLayout.NORTH);
    mainPanel.add(tableContainer, BorderLayout.CENTER);
    mainPanel.add(buttons, BorderLayout.SOUTH);

    add(mainPanel);
  }

  private void addUser() {
    SwingUtilities.invokeLater(() -> {
      new AddUserDialog(this).setVisible(true);
    });
  }

  private void removeUser() {
    int selectedRow = usersTable.getSelectedRow();
    if (selectedRow != -1) {
      String username = (String) tableModel.getValueAt(selectedRow, 0);
      if (!username.equals(AdminFrame.this.currentUsername)) {
        User uzivatelRemove = this.userManagerInstance.getUserList().get(username);
        int confirm = JOptionPane.showConfirmDialog(AdminFrame.this,
            String.format("Chcete smazat uživatele %s %s?", uzivatelRemove.getFirstName(),
                uzivatelRemove.getSurname()),
            "Pozor",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.OK_OPTION) {
          boolean removeResult = this.userManagerInstance.removeUser(username);
          if (removeResult) {
            JOptionPane.showMessageDialog(AdminFrame.this, "Odebrání proběhlo úspěšně.", "Potvrzení",
                JOptionPane.INFORMATION_MESSAGE);
            refreshTableData();
          } else {
            JOptionPane.showMessageDialog(AdminFrame.this, "Uživatel nebyl nalezen.", "Chyba",
                JOptionPane.ERROR_MESSAGE);
          }
        }
      } else {
        JOptionPane.showMessageDialog(AdminFrame.this,
            "Nemůžete odebrat vlastního uživatele.", "Chyba", JOptionPane.ERROR_MESSAGE);
      }
    } else {
      JOptionPane.showMessageDialog(AdminFrame.this, "Vyberte uživatele k odebrání.", "Chyba",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void editUser() {
    int selectedRow = usersTable.getSelectedRow();
    if (selectedRow != -1) {
      String username = (String) tableModel.getValueAt(selectedRow, 0);
      User user = this.userManagerInstance.getUserList().get(username);
      SwingUtilities.invokeLater(() -> {
        new EditUserDialog(this, username, user).setVisible(true);
      });
    } else {
      JOptionPane.showMessageDialog(AdminFrame.this, "Vyberte uživatele k úpravě.", "Chyba",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  private void refreshUsers() {
    refreshTableData();
    JOptionPane.showMessageDialog(AdminFrame.this, "Data v tabulce byla aktualizována.", "Potvrzení",
        JOptionPane.INFORMATION_MESSAGE);
  }

  private Object[][] getData() {
    Map<String, User> userList = this.userManagerInstance.getUserList();
    Object[][] data = new Object[userList.size()][11];
    int row = 0;

    for (Entry<String, User> user : userList.entrySet()) {
      User userObject = user.getValue();

      data[row][0] = user.getKey();
      data[row][1] = userObject.getHashedPassword();
      data[row][2] = userObject.getTitle();
      data[row][3] = userObject.getGender();
      data[row][4] = userObject.getFirstName();
      data[row][5] = userObject.getSurname();
      data[row][6] = userObject.getBirthDate();
      data[row][7] = userObject.getEmail();
      data[row][8] = userObject.getPhone();
      data[row][9] = userObject.getBirthNumber();
      data[row][10] = (userObject.getIsAdmin()) ? "Ano" : "Ne";
      row++;
    }
    return data;
  }

  private void manipulateTableData(String sortInput, String filterInput) {
    Map<String, User> userList = this.userManagerInstance.getUserList();

    Stream<Entry<String, User>> userStream = userList.entrySet().stream();

    userStream = switch (sortInput) {
      case "Dle křestního jména" ->
        userStream
            .sorted(Comparator.comparing(entry -> entry.getValue().getFirstName(), String.CASE_INSENSITIVE_ORDER));
      case "Dle příjmení" ->
        userStream.sorted(Comparator.comparing(entry -> entry.getValue().getSurname(), String.CASE_INSENSITIVE_ORDER));
      case "Dle data narození" -> userStream.sorted(Comparator.comparing(entry -> entry.getValue().getBirthDate()));
      default -> userStream;
    };

    userStream = switch (filterInput) {
      case "Administrátoři" -> userStream.filter(entry -> entry.getValue().getIsAdmin());
      case "Lidé nad 18 let" ->
        userStream.filter(entry -> entry.getValue().getBirthDate().isBefore(LocalDate.now().minusYears(18)));
      case "Muži" -> userStream.filter(entry -> entry.getValue().getGender().equals("Muž"));
      case "Ženy" -> userStream.filter(entry -> entry.getValue().getGender().equals("Žena"));
      default -> userStream;
    };

    Object[][] updatedData = convertToDataArray(userStream);
    this.tableModel.setDataVector(updatedData, this.columnNames);
  }

  private Object[][] convertToDataArray(Stream<Entry<String, User>> stream) {
    return stream.map(entry -> {
      User user = entry.getValue();
      return new Object[] {
          entry.getKey(),
          user.getHashedPassword(),
          user.getTitle(),
          user.getGender(),
          user.getFirstName(),
          user.getSurname(),
          user.getBirthDate(),
          user.getEmail(),
          user.getPhone(),
          user.getBirthNumber(),
          user.getIsAdmin() ? "Ano" : "Ne"
      };
    }).toArray(Object[][]::new);
  }

  public void refreshTableData() {
    manipulateTableData(chooseSort.getSelectedItem().toString(), chooseFilter.getSelectedItem().toString());
  }
}
