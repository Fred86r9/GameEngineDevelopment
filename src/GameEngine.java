public class GameEngine {
    private V2 ballPosition;
    private V2 ballVelocity;
    private double angle = 0;
    private double ballRadius = 10;
    private double slitAngle = Math.toRadians(90);

    public GameEngine() {
        // Initialize ball at center of the screen
        ballPosition = new V2(350, 350);

        // Randomize initial velocity
        double initialAngle = Math.random() * 2 * Math.PI;
        ballVelocity = new V2(Math.cos(initialAngle), Math.sin(initialAngle)).mulVByScalar(5);
    }

    public void update() {
        // Update ball position based on velocity
        ballPosition = ballPosition.add(ballVelocity);

        // Handle collision with circle
        handleCollisionWithCircle();

        angle += Math.toRadians(2); // Rotate by 2 degrees per frame
        angle = angle % (2 * Math.PI); // Ensure angle stays within range [0, 2*pi]
    }

    private void handleCollisionWithCircle() {
        int centerX = 350;
        int centerY = 350;
        int radius = 100;

        V2 circleCenter = new V2(centerX, centerY);
        V2 toBall = ballPosition.subtract(circleCenter);
        double distance = toBall.length();

        if (distance > radius - ballRadius) {
            double collisionAngle = Math.atan2(toBall.y, toBall.x);
            collisionAngle = (collisionAngle + 2 * Math.PI) % (2 * Math.PI);

            // Calculate slit bounds for collision detection
            double slitStartAngle = (angle - slitAngle / 2 + 2 * Math.PI) % (2 * Math.PI);
            double slitEndAngle = (angle + slitAngle / 2 + 2 * Math.PI) % (2 * Math.PI);

            // Check if the ball is in the slit, handling wraparound
            if ((slitStartAngle < slitEndAngle && (collisionAngle >= slitStartAngle && collisionAngle <= slitEndAngle)) ||
                    (slitStartAngle > slitEndAngle && (collisionAngle <= slitStartAngle && collisionAngle >= slitEndAngle))) {
                // Ball escaped the slit, reset its position
                ballPosition = new V2(350, 350);

                // Random initial velocity
                double initialAngle = Math.random() * 2 * Math.PI;
                ballVelocity = new V2(Math.cos(initialAngle), Math.sin(initialAngle)).mulVByScalar(5);
            } else {
                // Ball collided with the circle, bounce it
                V2 normal = toBall.divVByScalar(distance);
                ballVelocity = ballVelocity.subtract(normal.mulVByScalar(2 * ballVelocity.dotProduct(normal)));
            }
        }
    }

    public V2 getBallPosition() {
        return ballPosition;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}