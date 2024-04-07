import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TankClient extends JFrame {
    public void lunchFrame(){
        this.setLocation(0,100);
        this.setSize(800,600);
        setTitle("TankWar 0210");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowActivated(WindowEvent e) {
                super.windowActivated(e);
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                super.windowDeactivated(e);
            }
        });
        setResizable(false);
        setVisible(true);
    }
    public static void main(String[] args) {
        TankClient tankClient = new TankClient();
        tankClient.lunchFrame();
    }
}
