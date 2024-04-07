import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    public static final int XSPEED = 5;
    public static final int YSPEED = 5;
    private int x, y;
    private boolean bL = false, bU = false, bR = false, bD = false;
    private Direction dir = Direction.STOP;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        Color color = g.getColor();
        g.setColor(new Color(255, 100, 50, 255));
        g.fillRect(x, y, 30, 30);
        g.setColor(new Color(10, 100, 50, 255));
        g.fillRect(x, y + 5, 5, 10);
        g.fillRect(x + 25, y + 5, 5, 10);
        g.setColor(Color.WHITE);
        g.fillRect(x + 12, y - 2, 5, 20);
        g.setColor(color);

        move();
    }

    void move() {
        switch (dir) {
            case L -> x -= XSPEED;
            case LU -> {
                x -= XSPEED;
                y -= YSPEED;
            }
            case U -> y -= YSPEED;
            case RU -> {
                x += XSPEED;
                y -= YSPEED;
            }
            case R -> x += XSPEED;
            case RD -> {
                x += XSPEED;
                y += YSPEED;
            }
            case D -> y += YSPEED;
            case LD -> {
                x -= XSPEED;
                y += YSPEED;
            }
            case STOP -> {
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> bL = true;
            case KeyEvent.VK_UP -> bU = true;
            case KeyEvent.VK_RIGHT -> bR = true;
            case KeyEvent.VK_DOWN -> bD = true;
        }
        locateDirection();
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> bL = false;
            case KeyEvent.VK_UP -> bU = false;
            case KeyEvent.VK_RIGHT -> bR = false;
            case KeyEvent.VK_DOWN -> bD = false;
        }
        locateDirection();
    }

    void locateDirection() {
        if (bL && !bU && !bR && !bD) dir = Direction.L;
        else if (bL && bU && !bR && !bD) dir = Direction.LU;
        else if (!bL && bU && !bR && !bD) dir = Direction.U;
        else if (!bL && bU && bR && !bD) dir = Direction.RU;
        else if (!bL && !bU && bR && !bD) dir = Direction.R;
        else if (!bL && !bU && bR && bD) dir = Direction.RD;
        else if (!bL && !bU && !bR && bD) dir = Direction.D;
        else if (bL && !bU && !bR && bD) dir = Direction.LD;
        else if (!bL && !bU && !bR && !bD) dir = Direction.STOP;
    }

    enum Direction {L, LU, U, RU, R, RD, D, LD, STOP}
}
