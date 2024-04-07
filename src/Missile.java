import java.awt.*;

public class Missile {
    public static final int XSPEED = 10;
    public static final int YSPEED = 10;
    public static final int WIDTH=10;
    public static final int HEIGHT=10;
    Tank.Direction dir;
    private int x, y;

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
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 10, 10);
        g.setColor(c);
        move();
    }
}
