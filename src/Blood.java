import java.awt.*;

/**
 * Blood 回血道具
 *
 * @author Hubai
 */
public class Blood {
    int x, y, w, h;
    TankClient tc;

    int step = 0;
    private boolean live = true;
    private int[][] pos = {
            {350, 300}
    };


    public Blood() {
        x = pos[0][0];
        y = pos[0][1];
        w = h = 15;
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
        Color c = g.getColor();
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, w, h);
        g.setColor(c);

        move();

    }

    private void move() {
        step++;
        if (step == pos.length) {
            step = 0;
        }
        x = pos[step][0];
        y = pos[step][1];
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, w, h);
    }
}