import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BallAnimation extends JPanel implements ActionListener {
    private int ballY = 400; // Initial Y position of the ball
    private int velocity = -10; // Initial velocity of the ball
    private Timer timer;

    public BallAnimation() {
        timer = new Timer(50, this); // Timer for animation, fires every 50 milliseconds
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Set background color
        setBackground(Color.WHITE);
        // Draw the ball
        g.setColor(Color.RED);
        g.fillOval(200, ballY, 50, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the position of the ball
        ballY += velocity;
        // Change direction if the ball hits the top or bottom edge
        if (ballY <= 0 || ballY >= getHeight() - 50) {
            velocity = -velocity;
        }
        repaint(); // Trigger paintComponent method
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ball Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new BallAnimation());
        frame.setVisible(true);
    }
}