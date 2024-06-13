import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Eksamen extends JFrame {
    private GameEngine gameEngine;
    private DrawPanel drawPanel;

    public static void main(String[] args) {
        JFrame frame = new Eksamen();
        frame.setSize(700, 700);
        frame.setTitle("Spinning Circle with Slit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public Eksamen() {
        gameEngine = new GameEngine();
        drawPanel = new DrawPanel(gameEngine);
        add(drawPanel);
        Timer timer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double angle = gameEngine.getAngle();
                gameEngine.update();
                angle += Math.toRadians(2); // Rotate by 2 degrees per frame
                angle = angle % (2 * Math.PI); // Ensure angle stays within range [0, 2*pi]
                drawPanel.repaint();
            }
        });
        timer.start();
    }

    static class DrawPanel extends JPanel {
        private GameEngine gameEngine;

        public DrawPanel(GameEngine gameEngine) {
            this.gameEngine = gameEngine;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawCircleWithSlit(g, 100, gameEngine.getAngle()); // Radius 100
            drawBall(g, gameEngine.getBallPosition());
            new S2(1,1,350,350).drawAxis(g,100,100);
            // Draw other components or features if needed
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
            double startAngle = Math.toDegrees(gameEngine.getAngle() - slitAngle / 2);
            double arcAngle = 360 - Math.toDegrees(slitAngle); //Angular extend/length of arc

            // Draw the arc (the circle with the slit)
            g2d.drawArc(centerX - radius, centerY - radius, radius * 2, radius * 2, (int) startAngle, (int) arcAngle);
        }

        private void drawBall(Graphics g, V2 ballPosition) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.DARK_GRAY);
            int ballRadius = 10;
            g2d.fillOval((int) (ballPosition.x - ballRadius), (int) (ballPosition.y - ballRadius), (int) (2 * ballRadius), (int) (2 * ballRadius));
        }
    }
}