package spse.stefacek.app;

import javax.swing.SwingUtilities;

import spse.stefacek.presentation.admin.frames.AdminFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminFrame adminFrame = new AdminFrame();
            adminFrame.setVisible(true);
        });
    }
}
