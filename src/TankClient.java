import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TankClient extends Frame {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    int x = 50, y = 50;
    Tank myTank = new Tank(50, 50, this);
    Tank enemyTank = new Tank(100, 100, false, this);
    List<Missile> missileList = new ArrayList<>();
    Missile m = null;

    Image offScreenImage = null;

    public static void main(String[] args) {
        TankClient tankClient = new TankClient();
        tankClient.lunchFrame();
    }

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics getOffScreen = offScreenImage.getGraphics();
        Color color = getOffScreen.getColor();
        getOffScreen.setColor(Color.GRAY);
        getOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        getOffScreen.setColor(color);
        print(getOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void paint(Graphics g) {
        g.drawString("Missiles count:"+missileList.size(),10,50);
        for (int i = 0; i < missileList.size(); i++) {
            Missile m = missileList.get(i);
            m.draw(g);
        }
        myTank.draw(g);
        enemyTank.draw(g);
    }

    public void lunchFrame() {
        this.setLocation(0, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
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
        this.setBackground(Color.gray);
        this.setResizable(false);
        this.addKeyListener(new KeyMonitor());
        setVisible(true);

        new Thread(new PaintThread()).start();
    }

    private class PaintThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }
}
