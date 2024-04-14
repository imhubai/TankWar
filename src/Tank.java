import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tank {
    public static final int XSPEED = 5;
    public static final int YSPEED = 5;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    private static Random r = new Random();
    TankClient tankClient = null;
    private int step = r.nextInt(12) + 3;
    private int x;
    private int y;
    private boolean good;
    private boolean bL = false, bU = false, bR = false, bD = false;
    private Direction dir = Direction.STOP;
    private Direction ptDir = Direction.D;
    private boolean live = true;

    public Tank(int x, int y, TankClient tankClient) {
        this.x = x;
        this.y = y;
        this.tankClient = tankClient;
    }

    public Tank(int x, int y, boolean good, Direction dir, TankClient tankClient) {
        this.x = x;
        this.y = y;
        this.tankClient = tankClient;
        this.good = good;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isGood() {
        return good;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void draw(Graphics g) {
        if (!live) {
            return;
        }
        Color color = g.getColor();
        if (good) g.setColor(Color.GREEN);
        else g.setColor(new Color(255, 100, 50, 255));
        g.fillRect(x, y, 30, 30);
        g.setColor(color);

        switch (ptDir) {
            case L -> g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT / 2);
            case LU -> g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y);
            case U -> g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2, y);
            case RU -> g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y);
            case R -> g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y + Tank.HEIGHT / 2);
            case RD -> g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y + Tank.HEIGHT);
            case D -> g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2, y + Tank.HEIGHT);
            case LD -> g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT);
            case STOP -> {
            }
        }
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
        if (this.dir != Direction.STOP) {
            this.ptDir = this.dir;
        }
        if (x < 0) x = 0;
        if (y < 25) y = 25;
        if (x + Tank.WIDTH > TankClient.GAME_WIDTH) x = TankClient.GAME_WIDTH - Tank.WIDTH;
        if (y + Tank.HEIGHT > TankClient.GAME_HEIGHT) y = TankClient.GAME_HEIGHT - Tank.HEIGHT;

        if (!good) {
            Direction[] dirs = Direction.values();
            if (step == 0) {
                step = r.nextInt(12) + 3;
                int rn = r.nextInt(dirs.length);
                dir = dirs[rn];
            }
            step--;
            if (r.nextInt(40) > 38) this.fire();
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
            case KeyEvent.VK_SPACE -> fire();
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

    public Missile fire() {
        if (!live) return null;
        int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
        int y = this.y + Tank.HEIGHT / 2 - Missile.WIDTH / 2;
        Missile m = new Missile(x, y, good, ptDir, this.tankClient);
        tankClient.missileList.add(m);
        return m;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    enum Direction {L, LU, U, RU, R, RD, D, LD, STOP}
}
