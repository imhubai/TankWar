import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TankClient extends JFrame {
    public void paint(Graphics g){
        Color color = g.getColor();
        g.setColor(new Color(255,100,50,255));
        g.fillRect(50,50,30,30);
        g.setColor(new Color(10,100,50,255));
        g.fillRect(50,55,5,10);
        g.fillRect(75,55,5,10);
        g.setColor(Color.BLACK);
        g.fillRect(62,48,5,20);
        g.setColor(color);
    }
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
