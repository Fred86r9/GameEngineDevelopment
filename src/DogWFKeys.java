import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DogWFKeys extends JFrame {
    public static void main(String[] args) {
        DogWFKeys frame = new DogWFKeys();
        frame.setTitle("WaveFrontTest");
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    } // main()

    DogWFKeys() {
        add(new PaintPanel());
    }  // Constructor

    class PaintPanel extends JPanel {
        Camera camera = new Camera(1000, 800, 500, 300);
        WaveFrontElement wf = new WaveFrontElement("src/WaveFrontFiles/13043_German_Shorthaired_Pointer_v1_l2.obj");

        PaintPanel() {

            //Set cam to a position on dogs side
            V3 dogCenter = wf.center();
            V3 cameraPosition = dogCenter.add(new V3(90,0,30));

            camera.moveTo(cameraPosition);
            camera.focus(dogCenter);

            camera.z = 1;
            addKeyListener(new MyKeyListener());
            setFocusable(true); // To ensure the panel can receive key events
            requestFocusInWindow(); // To ensure the panel has focus on startup

            /*camera.moveTo(new V3(10, 5, 2));
            camera.focus(wf.center());
            camera.z = 1;
            addKeyListener(new MyKeyListener());
            setFocusable(true); // To ensure the panel can receive key events
            requestFocusInWindow(); // To ensure the panel has focus on startup

             */
        }

        class MyKeyListener extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                double step = 0.5;
                V3 newPosition = camera.E;

                if (key == KeyEvent.VK_LEFT) {
                    newPosition = camera.E.add(camera.R.mul(-step)); // Move left
                } else if (key == KeyEvent.VK_RIGHT) {
                    newPosition = camera.E.add(camera.R.mul(step)); // Move right
                } else if (key == KeyEvent.VK_UP) {
                    newPosition = camera.E.add(camera.D.mul(step)); // Move forward
                } else if (key == KeyEvent.VK_DOWN) {
                    newPosition = camera.E.add(camera.D.mul(-step)); // Move backward
                } else if (key == KeyEvent.VK_W) {
                    newPosition = camera.E.add(camera.U.mul(step)); // Move up
                } else if (key == KeyEvent.VK_S) {
                    newPosition = camera.E.add(camera.U.mul(-step)); // Move down
                }

                camera.moveTo(newPosition);
                camera.focus(wf.center());
                repaint();
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            camera.drawAxis(g);
            wf.draw(camera, g);
        }
    }
} // class MainFrame