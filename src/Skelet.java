import javax.swing.*;
import java.awt.*;
import static java.lang.Math.*;

public class Skelet extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new Skelet();
        frame.setSize(700, 700);
        frame.setTitle("Skelet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public Skelet() {
        add(new DrawPanel());
    }

    class DrawPanel extends JPanel {
        S2 S=new S2(50,50, 120,300);

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            S.drawAxis(g);
        }
    } // class DrawPanel

} // GraphicsApp
