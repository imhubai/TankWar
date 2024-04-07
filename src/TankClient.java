import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TankClient extends JFrame {
    int x = 50, y = 50;
    int Window_width=800,Window_height=600;
    Image offScreenImage = null;

    public static void main(String[] args) {
        TankClient tankClient = new TankClient();
        tankClient.lunchFrame();
    }

    public void update(Graphics g){
        if(offScreenImage==null){
            offScreenImage = this.createImage(Window_width,Window_height);
        }
        Graphics getOffScreen = offScreenImage.getGraphics();
        Color color=getOffScreen.getColor();
        getOffScreen.setColor(Color.BLACK);
        getOffScreen.fillRect(0,0,Window_width,Window_height);
        getOffScreen.setColor(color);
        print(getOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(new Color(255, 100, 50, 255));
        g.fillRect(x, y, 30, 30);
        g.setColor(new Color(10, 100, 50, 255));
        g.fillRect(x, y + 5, 5, 10);
        g.fillRect(x + 25, y + 5, 5, 10);
        g.setColor(Color.BLACK);
        g.fillRect(x + 12, y - 2, 5, 20);
        g.setColor(color);
        x += 5;
        y += 5;
    }

    public void lunchFrame() {
        this.setLocation(0, 100);
        this.setSize(Window_width, Window_height);
        setTitle("TankWar");
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
                setTitle("TankWar - Pause");
            }
        });
        setResizable(false);
        setVisible(true);

        new Thread(new PaintThread()).start();
    }

    private class PaintThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
