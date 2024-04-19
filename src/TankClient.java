import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankClient extends Frame {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    int x = 450, y = 350;
    Tank myTank = new Tank(x, y, true, Tank.Direction.STOP, this);
    //Tank enemyTank = new Tank(100, 100, false, this);
    Wall wall=new Wall(50,300,100,50,this);
    List<Missile> missileList = new ArrayList<>();
    List<Explode> explodeList = new ArrayList<>();
    List<Tank> tanks = new ArrayList<Tank>();
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
        g.drawString("tanks count: " + tanks.size(), 10, 90);
        g.drawString("Missiles count:" + missileList.size(), 10, 50);
        g.drawString("explodes count: " + explodeList.size(), 10, 70);
        for (int i = 0; i < missileList.size(); i++) {
            Missile m = missileList.get(i);
            m.hitTanks(tanks);
            m.hitWall(wall);
            m.draw(g);
        }
        for (int i = 0; i < explodeList.size(); i++) {
            Explode e = explodeList.get(i);
            e.draw(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            Tank e = tanks.get(i);
            e.collidesWithWall(wall);
            e.collidesWithTanks(tanks);
            e.draw(g);
        }
        myTank.draw(g);
        myTank.collidesWithTanks(tanks);
        myTank.collidesWithWall(wall);
        wall.draw(g);
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
        for (int i = 0; i < 3; i++) {
            Tank enemy =new Tank(50 + 40 * (i + 1), 50, false, Tank.Direction.STOP, this);
            tanks.add(enemy);
        }
    }

    private class PaintThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(30);
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
