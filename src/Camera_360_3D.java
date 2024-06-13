import javax.swing.*;
import java.awt.*;

public class Camera_360_3D extends JFrame{
        public static void main(String[] args) {
            JFrame frame = new Camera_360_3D();
            frame.setSize(700, 700);
            frame.setTitle("Camera_360_3D");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        }

        public Camera_360_3D() {
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

