package spse.stefacek.presentation.admin.frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import spse.stefacek.presentation.admin.panels.CategoryPanel;
import spse.stefacek.presentation.admin.panels.EnhancementPanel;
import spse.stefacek.presentation.admin.panels.ProductPanel;

public class AdminFrame extends JFrame {

  public AdminFrame() {

    initComponents();
    setTitle("Správa produktů");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setLocationRelativeTo(null);
  }

  private void initComponents() {
    JTabbedPane tabbedPane = new JTabbedPane();

    tabbedPane.addTab("Produkty", new ProductPanel());
    tabbedPane.addTab("Vylepšení", new EnhancementPanel());
    tabbedPane.addTab("Kategorie", new CategoryPanel());

    this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
  }
}
