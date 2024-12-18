package cz.spse.bajer.pg3.GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadingFrame extends JFrame {

  public LoadingFrame() {
    setTitle("Načítání");
    setSize(400, 300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    buildComponents();
  }

  final public void buildComponents() {
    JLabel loadingText = new JLabel("Načítání aplikace...");
    loadingText.setFont(new Font("SansSerif", Font.BOLD, 20));

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new GridBagLayout());

    mainPanel.add(loadingText);

    setLayout(new BorderLayout());
    add(mainPanel, BorderLayout.CENTER);
  }
}
