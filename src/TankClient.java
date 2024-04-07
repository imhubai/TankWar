import javax.swing.*;
import java.awt.*;

public class TankClient extends JFrame {
    public void lunchFrame(){
        this.setLocation(0,100);
        this.setSize(800,600);
        setVisible(true);
    }
    public static void main(String[] args) {
        TankClient tankClient = new TankClient();
        tankClient.lunchFrame();
    }
}
