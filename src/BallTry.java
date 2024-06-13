import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BallTry extends JPanel implements ActionListener {
    V2 ballPosition; // Initial position of the ball
    V2 velocity; // Initial velocity of the ball
    private Timer timer;
    S2 coordinateSystem; // Coordinate system for transformation

    public BallTry() {
        timer = new Timer(50, this); // Timer for animation, fires every 50 milliseconds
        timer.start();

        // Initialize coordinate system with appropriate parameters
        //coordinateSystem = new S2(1, 1, getWidth() / 2, getHeight() / 2);

        // Initialize position and velocity
        ballPosition = new V2(getWidth() / 2, getHeight() / 2);
        velocity = new V2(0, -1);


    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set background color
        setBackground(Color.DARK_GRAY);
        // Set color for drawing the ball
        g.setColor(Color.BLUE);

        //Initialize coordinate system with appropriate parameters
        if (coordinateSystem == null) {
            coordinateSystem = new S2(10, 10, getWidth() / 2, getHeight() / 2);
            //coordinateSystem.moveTo(ballPosition);
        }

        // Draw the ball using coordinate system transformation
        //coordinateSystem.moveTo(ballPosition);
        coordinateSystem.drawPoint(g, ballPosition, 30); // Draw the ball at the transformed position
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the position of the ball
        ballPosition = ballPosition.add(velocity);

        // Change direction if the ball hits the top or bottom edge
        if (ballPosition.y <= - getHeight() / 14 + 15|| ballPosition.y >= getHeight() / 14 - 15) {
            velocity = velocity.mulVByScalar(-1); // Reverse the velocity
            //velocity = new V2(velocity.x, -velocity.y);

        }
        repaint(); // Trigger paintComponent method
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ball Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.add(new BallTry());
        frame.setVisible(true);
    }

}
