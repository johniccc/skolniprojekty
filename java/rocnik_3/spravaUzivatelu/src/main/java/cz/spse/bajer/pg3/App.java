// ! NOT THREAD SAFE

package cz.spse.bajer.pg3;

import javax.swing.SwingUtilities;

import cz.spse.bajer.pg3.GUI.LoadingFrame;
import cz.spse.bajer.pg3.GUI.LoginFrame;

public class App {
    public static void main(String[] args) {
        LoadingFrame loadingFrame = new LoadingFrame();
        loadingFrame.setVisible(true);

        UserManager.getInstance();
        loadingFrame.dispose();

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
