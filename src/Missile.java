import java.awt.*;
import java.util.List;

/**
 * Missile 导弹
 *
 * @author Hubai
 */
public class Missile {
    public static final int XSPEED = 10;
    public static final int YSPEED = 10;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    Tank.Direction dir;
    TankClient tankClient;
    private int x, y;
    private boolean good;
    private boolean live = true;

    public Missile(Tank.Direction dir, TankClient tankClient, int x, int y) {
        this.dir = dir;
        this.tankClient = tankClient;
        this.x = x;
        this.y = y;
    }

    public Missile(int x, int y, boolean good, Tank.Direction dir, TankClient tc) {
        this.good = good;
        this.dir = dir;
        this.tankClient = tc;
        this.x = x;
        this.y = y;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    private void move() {
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
        }
        if (x < 0 || y < 0 || y > TankClient.GAME_HEIGHT || x > TankClient.GAME_WIDTH) {
            live = false;
            tankClient.missileList.remove(this);
        }
    }

    public void draw(Graphics g) {
        if (!live) {
            tankClient.missileList.remove(this);
            return;
        }
        Color c = g.getColor();
        if (this.good) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.YELLOW);
        }
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);
        move();
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public boolean hitTank(Tank t) {
        if (this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood()) {
            t.setLive(false);
            this.live = false;
            Explode e = new Explode(x, y, tankClient);
            tankClient.explodeList.add(e);
            return true;
        }
        return false;
    }

    public boolean hitTanks(List<Tank> tanks) {
        for (int i = 0; i < tanks.size(); i++) {
            if (hitTank(tanks.get(i))) {
                tanks.remove(tanks.get(i));
                return true;
            }
        }
        return false;
    }

    public boolean hitWall(Wall w) {
        if (this.live && this.getRect().intersects(w.getRect())) {
            this.live = false;
            return true;
        }
        return false;
    }

    public boolean collidesWithTank(Tank t) {
        if (this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood()) {
            if (t.isGood()) {
                t.setLife(t.getLife() - 20);
                if (t.getLife() <= 0) {
                    t.setLive(false);
                }
            } else {
                t.setLive(false);
            }
            this.live = false;
            Explode e = new Explode(x, y, tankClient);
            tankClient.explodeList.add(e);
            return true;
        }
        return false;
    }
}
