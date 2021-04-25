package mainClass;

import javax.swing.JFrame;
import javax.swing.UIManager;

import interfata.DatabaseViewer;

public class MainClass {
	
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        DatabaseViewer ClientViewer = new DatabaseViewer(); 
        ClientViewer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ClientViewer.pack();
        ClientViewer.setVisible(true);
        ClientViewer.setResizable(false);

    }

}
