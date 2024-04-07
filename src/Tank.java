import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    int x, y;

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
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> x -= 5;
            case KeyEvent.VK_UP -> y -= 5;
            case KeyEvent.VK_RIGHT -> x += 5;
            case KeyEvent.VK_DOWN -> y += 5;
        }
    }
}
