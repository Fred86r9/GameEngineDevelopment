import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BallTry2 extends JFrame {
    DrawPanel dp;
    public static void main(String[] args) {
        JFrame frame = new BallTry2();
        frame.setSize(700, 700);
        frame.setTitle("Ball Try 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

    }

    public BallTry2() {
        add(new DrawPanel());
    }


    class DrawPanel extends JPanel implements ActionListener {
        V2 ballPosition = new V2(0,0); // Initial position of the ball
        V2 velocity = new V2(0, -2); // Initial velocity of the ball
        Timer timer;
        S2 coordinateSystem = new S2(10, 10, 350, 350); // Coordinate system for transformation

        DrawPanel() {
            timer = new Timer(50, this); // Timer for animation, fires every 50 milliseconds
            timer.start();


            System.out.println(getHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Set background color
            setBackground(Color.DARK_GRAY);
            // Set color for drawing the ball
            g.setColor(Color.BLUE);

            // Draw the ball using coordinate system transformation
            coordinateSystem.drawPoint(g, ballPosition, 30); // Draw the ball at the transformed position
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Update the position of the ball
            ballPosition = ballPosition.add(velocity);

            // Change direction if the ball hits the top or bottom edge
            if (ballPosition.y <= -getHeight() / 15 + 15 || ballPosition.y >= getHeight() / 14 - 15) {
                velocity = velocity.mulVByScalar(-1); // Reverse the velocity
            }

            repaint(); // Trigger paintComponent method
        }
    }
}