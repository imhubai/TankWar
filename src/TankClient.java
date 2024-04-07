import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame {
    public static final int GAME_WIDTH=800;
    public static final int GAME_HEIGHT=600;
    int x = 50, y = 50;

    Image offScreenImage = null;

    public static void main(String[] args) {
        TankClient tankClient = new TankClient();
        tankClient.lunchFrame();
    }

    public void update(Graphics g){
        if(offScreenImage==null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics getOffScreen = offScreenImage.getGraphics();
        Color color=getOffScreen.getColor();
        getOffScreen.setColor(Color.BLACK);
        getOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
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
        g.setColor(Color.WHITE);
        g.fillRect(x + 12, y - 2, 5, 20);
        g.setColor(color);
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
        this.setBackground(Color.BLACK);
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
    private class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int key=e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT -> x-=5;
                case KeyEvent.VK_UP -> y-=5;
                case KeyEvent.VK_RIGHT -> x+=5;
                case KeyEvent.VK_DOWN -> y+=5;
            }
        }
    }
}
