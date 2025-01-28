package views;

import controllers.ReceiptsController;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrderHistory extends JDialog {
  private JTable table;
  private DefaultTableModel tableModel;
  private JScrollPane receipts;
  private JPanel buttons;
  private JButton showBtn;
  private JButton printBtn;
  private final Object[][] tableData;
  private final Object[] tableColumns;
  private final ReceiptsController receiptsController;

  public OrderHistory(Frame owner, ModalityType modalityType, Object[][] tableData, Object[] tableColumns) {
    super(owner, modalityType);
    this.tableData = tableData;
    this.tableColumns = tableColumns;
    this.receiptsController = new ReceiptsController();

    setTitle("Výpis objednávky");
    setSize(1000, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    initComponents();
  }

  private void initComponents() {
    receipts = new JScrollPane();

    tableModel = new DefaultTableModel(tableData, tableColumns) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    table = new JTable(tableModel);

    receipts.setViewportView(table);

    buttons = new JPanel();

    showBtn = new JButton("Zobrazit objednávku");
    showBtn.addActionListener(e -> {
      receiptsController.showReceipt(table.getSelectedRows());
    });

    printBtn = new JButton("Vytisknout účtenku");
    printBtn.addActionListener(e -> {
      receiptsController.printReceipt(table.getSelectedRows());
    });

    int topBotPadding = 15;
    int sidePadding = 25;

    showBtn.setMargin(new Insets(topBotPadding, sidePadding, topBotPadding, sidePadding));
    printBtn.setMargin(new Insets(topBotPadding, sidePadding, topBotPadding, sidePadding));

    buttons.add(showBtn);
    buttons.add(printBtn);

    add(receipts, BorderLayout.CENTER);
    add(buttons, BorderLayout.SOUTH);
  }
}
