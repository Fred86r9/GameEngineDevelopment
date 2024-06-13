import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EksamenOpg extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new EksamenOpg();
        frame.setSize(700, 700);
        frame.setTitle("Eksamen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public EksamenOpg() {
        add(new DrawPanel());
    }

    class DrawPanel extends JPanel implements ActionListener {
        S2 S = new S2(1, 1, 350, 350); // Centered coordinate system, no scaling
        Timer timer = new Timer(1000 / 60, this); // 60 FPS
        double angle = 0;
        V2 ballPosition;
        V2 ballVelocity;
        double ballRadius = 10;

        public DrawPanel(){
            ballPosition = new V2(0,0);
            //Random initial velocity
            double initialAngle = Math.random() * 2 * Math.PI;
            ballVelocity = new V2(Math.cos(initialAngle), Math.sin(initialAngle)).mulVByScalar(4);
            timer.start();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawCircleWithSlit(g, 100, angle);
            drawBall(g);
        }

        void drawCircleWithSlit(Graphics g, int radius, double angle){
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int centerX = 350; // Center of the circle
            int centerY = 350;
            V2 circleCenter = new V2(centerX, centerY);
            double slitAngle = Math.toRadians(90); // 90 degrees slit width

            // Transform circle center
            //V2 transformedCircleCenter = S.transform(new V2(centerX, centerY));

            // Draw the circle with a slit
            g2d.setColor(Color.BLACK);
            double startAngle = Math.toDegrees(angle - slitAngle / 2);
            double arcAngle = 360 - Math.toDegrees(slitAngle);

            // Draw the arc (the circle with the slit)
            g2d.drawArc((int) (circleCenter.x - radius), (int) (circleCenter.y - radius),
                    radius * 2, radius * 2, (int) startAngle, (int) arcAngle);

            // Calculate slit bounds for collision detection in transformed coordinates
            double slitStartAngle = (angle - slitAngle / 2 + 2 * Math.PI) % (2 * Math.PI);
            double slitEndAngle = (angle + slitAngle / 2 + 2 * Math.PI) % (2 * Math.PI);

            // Ball update and collision in transformed coordinates
            ballPosition = ballPosition.add(ballVelocity);

            // Check collision with circle in transformed coordinates
            V2 toBall = (ballPosition.subtract(circleCenter));
            double distance = toBall.length();

            if (distance > radius - ballRadius) {
                double collisionAngle = Math.atan2(toBall.y, toBall.x);
                collisionAngle = (collisionAngle + 2 * Math.PI) % (2 * Math.PI); // Normalize to [0, 2 * PI]

                // Check if the ball is in slit, handling wraparound
                if ((slitStartAngle < slitEndAngle && (collisionAngle >= slitStartAngle && collisionAngle <= slitEndAngle)) ||
                        (slitStartAngle > slitEndAngle && (collisionAngle >= slitStartAngle || collisionAngle <= slitEndAngle))) {
                    // Ball escaped and is reset
                    ballPosition = new V2(0, 0); // Reset ball position (transformed)
                    // Random initial velocity
                    double randomAngle = Math.random() * 2 * Math.PI;
                    ballVelocity = new V2(Math.cos(randomAngle), Math.sin(randomAngle)).mulVByScalar(5);
                } else {
                    // Ball is not in slit, bounce it
                    V2 normal = toBall.divVByScalar(distance);
                    ballVelocity = ballVelocity.subtract(normal.mulVByScalar(2 * ballVelocity.dotProduct(normal)));
                }
            }
        }
        void drawBall(Graphics g) {
            g.setColor(Color.DARK_GRAY);
            // Transform ball position back to screen coordinates
            V2 screenBallPosition = S.transform(ballPosition);
            g.fillOval((int) (screenBallPosition.x - ballRadius), (int) (screenBallPosition.y - ballRadius),
                    (int) (2 * ballRadius), (int) (2 * ballRadius));
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("inside actionPerformed");
            System.out.println("angle " + angle + "in actionPerformed");
            repaint();
        }
    } // class DrawPanel

} // GraphicsApp
