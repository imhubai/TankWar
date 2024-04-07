import java.awt.*;

public class Missile {
    public static final int XSPEED = 10;
    public static final int YSPEED = 10;
    public static final int WIDTH=10;
    public static final int HEIGHT=10;
    Tank.Direction dir;
    TankClient tankClient;
    private int x, y;
    private boolean live =true;

    public boolean isLive() {
        return live;
    }

    public Missile(Tank.Direction dir, TankClient tankClient, int x, int y) {
        this.dir = dir;
        this.tankClient = tankClient;
        this.x = x;
        this.y = y;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Missile(int x, int y, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
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
        if(x<0 || y<0 || y>TankClient.GAME_HEIGHT || x>TankClient.GAME_WIDTH){
            live=false;
            tankClient.missileList.remove(this);
        }
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);
        move();
    }
}
