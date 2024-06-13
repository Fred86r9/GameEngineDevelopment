import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ball extends JPanel implements ActionListener {
    private V2 ballPosistion; // Initial Y position of the ball
    private V2 velocity; // Initial velocity of the ball
    private Timer timer;
    private S2 coordinateSystem; // Coordinate system for transformation

    public Ball() {
        timer = new Timer(50, this); // Timer for animation, fires every 50 milliseconds
        timer.start();
        // Initialize coordinate system with appropriate parameters

        coordinateSystem = new S2(1, 1, getWidth() / 2, getHeight() / 2);

        //Initialize posistion and velocity
        ballPosistion = new V2(0, 0);
        velocity = new V2(0, -10);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set background color
        setBackground(Color.WHITE);
        // Set color for drawing the ball
        g.setColor(Color.RED);

        // Draw the ball using coordinate system transformation
       // ballPosistion = new V2(0, 400);
        //velocity = new V2(0, -10);
        //coordinateSystem = new S2(1, 1, getWidth() / 2, getHeight() / 2);
        coordinateSystem.moveTo(ballPosistion);
        coordinateSystem.drawPoint(g, ballPosistion, 50); // Draw the ball at the transformed position
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the position of the ball
        ballPosistion = ballPosistion.add(velocity);
        // Change direction if the ball hits the top or bottom edge
        if (ballPosistion.y <= 0 || ballPosistion.y >= getHeight() - 50) {
            velocity = velocity.mulVByScalar(-1); //change direction
        }
        repaint(); // Trigger paintComponent method
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ball Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new BallTry2());
        frame.setVisible(true);
    }
}
