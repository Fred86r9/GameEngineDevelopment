/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExamProject extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new Eksamen();
        frame.setSize(700, 700);
        frame.setTitle("Spinning Circle with Slit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public ExamProject() {
        add(new DrawPanel());
    }

    class DrawPanel extends JPanel implements ActionListener {
        S2 S = new S2(1, 1, 350, 350); // Centered coordinate system, no scaling
        Timer timer = new Timer(1000 / 60, this); // 60 FPS
        double angle = 0;
        V2 ballPosition;
        V2 ballVelocity;
        double ballRadius = 10;

        V2 circleCenter = new V2(0,0);

        public DrawPanel() {
            ballPosition = new V2(350, 350);

            //Randomizing the initial velocity
            double initialAngle = Math.random() * 2 * Math.PI;
            ballVelocity = new V2(Math.cos(initialAngle), Math.sin(initialAngle)).mulVByScalar(5);
            timer.start();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawCircleWithSlit(g, 100, angle); // Radius 100
            drawBall(g);
            S.drawAxis(g,100,100);
            S.drawLine(g, circleCenter, ballPosition.subtract(circleCenter));

        }

        private void drawCircleWithSlit(Graphics g, int radius, double angle) {
            //Gain additional functionalities
            Graphics2D g2d = (Graphics2D) g;
            //Get a smoother graphic
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int centerX = 350; // Center of the circle
            int centerY = 350;
            double slitAngle = Math.toRadians(90); // 90 degrees slit width

            // Draw the circle with a slit
            g2d.setColor(Color.BLACK);
            // Calculate start and end angles for the arc
            double startAngle = Math.toDegrees(angle - slitAngle / 2);
            double arcAngle = 360 - Math.toDegrees(slitAngle); //Angular extend/length of arc

            // Draw the arc (the circle with the slit)
            g2d.drawArc(centerX - radius, centerY - radius, radius * 2, radius * 2, (int) startAngle, (int) arcAngle);

            // Calculate slit bounds for collision detection
            double slitStartAngle = (angle - slitAngle / 2 + 2 * Math.PI) % (2 *Math.PI);
            double slitEndAngle = (angle + slitAngle / 2 + 2 * Math.PI) % (2 *Math.PI);

            // Ball update and collision
            ballPosition = ballPosition.add(ballVelocity);

            // Check collision with circle
            V2 circleCenter = new V2(centerX, centerY);
            V2 toBall = ballPosition.subtract(circleCenter);
            double distance = toBall.length();

            if (distance > radius - ballRadius){
                double collisionAngle = Math.atan2(toBall.y, toBall.x);

                System.out.println("distance " + distance);
                System.out.println("collosionAngle " + collisionAngle);

                // Normalize the collision angle to be withing [0,2pi]
                collisionAngle = (collisionAngle + 2 * Math.PI); //% (2 * Math.PI);
                //System.out.println("collosionAngleNorm " + collisionAngle);
                // Normalize slit angles to be within [0, 2 * PI]
                slitStartAngle = (slitStartAngle + 2 * Math.PI) % (2 * Math.PI);
                slitEndAngle = (slitEndAngle + 2 * Math.PI) % (2 * Math.PI);
                System.out.println("slitstart " + slitStartAngle);
                System.out.println("slitend " + slitEndAngle);

                //Check if the ball is in slit, handling wraparound
                if ((slitStartAngle < slitEndAngle && (collisionAngle >= slitStartAngle && collisionAngle <= slitEndAngle)) ||
                        (slitStartAngle > slitEndAngle && (collisionAngle <= slitStartAngle && collisionAngle >= slitEndAngle))) {
                    //Ball escaped and is reset
                    System.out.println("Ball was reset with slitStartAngle " + slitStartAngle + ", slitEndAngle " + slitEndAngle + ", collisionAngle " + collisionAngle);
                    ballPosition = new V2(350,350);
                    // Random initial velocity
                    double initialAngle = Math.random() * 2 * Math.PI;
                    System.out.println("initialAngle " + initialAngle);
                    ballVelocity = new V2(Math.cos(initialAngle), Math.sin(initialAngle)).mulVByScalar(5);
                    System.out.println("ballVelocity " + ballVelocity);
                } else {
                    //Ball is not in slit, bounce it
                    System.out.println("ball bouncing on arc");
                    V2 normal = toBall.divVByScalar(distance);
                    ballVelocity = ballVelocity.subtract(normal.mulVByScalar(2 * ballVelocity.dotProduct(normal)));
                }
            }
        }



        void drawBall(Graphics g){
            //S.transform(ballPosition);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillOval((int) (ballPosition.x - ballRadius), (int) (ballPosition.y - ballRadius), (int) (2 * ballRadius), (int) (2 * ballRadius));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            angle += Math.toRadians(2); // Rotate by 2 degrees per frame
            angle = angle % (2 * Math.PI); // Ensure angle stays within range [0, 2*pi]
            System.out.println("inside actionPerformed");
            System.out.println("angle " + angle + "in actionPerformed");
            repaint();
        }
    }
}*/