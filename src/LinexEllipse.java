import javax.swing.*;
import java.awt.*;

public class LinexEllipse extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new LinexEllipse();
        frame.setSize(700, 700);
        frame.setTitle("GraphicsApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public LinexEllipse() {
        add(new DrawPanel());
    }

    class DrawPanel extends JPanel {
        S2 S=new S2(50,50, 120,300);
        V2 p0 = new V2(4,3);   //Center of ellipse
        double a = 3;  //Long axis of ellipse
        double b = 2;  //Short axis of ellipse

        V2 q0 = new V2(2.5,0);  //Const point on line
        double m = 2.0/3;  //Hældningskoefficient

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            S.drawAxis(g);

            for (double v=0; v<2*Math.PI; v +=0.01){
                V2 p = p0.add(new V2(a * Math.cos(v), b * Math.sin(v)));
                S.drawPoint(g, p);
            }

            for (double k=0; k<5; k+=0.01){
                V2 q = q0.add(new V2(1,m).mulVByScalar(k)); //new V2(k*1, k*m)
                S.drawPoint(g, q);
            }

        }
    } // class DrawPanel

} // GraphicsApp
