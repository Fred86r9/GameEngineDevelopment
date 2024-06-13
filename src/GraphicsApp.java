import javax.swing.*;
import java.awt.*;

public class GraphicsApp extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new GraphicsApp();
        frame.setSize(700, 700);
        frame.setTitle("GraphicsApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public GraphicsApp() {
        add(new DrawPanel());
    }

    class DrawPanel extends JPanel {
        S2 S=new S2(50,50, 120,300);
        int n = 5;
        V2[] points = new V2[n];
        DrawPanel(){
            double v = 2*Math.PI/n;

            for (int i=0; i<n; i++){
            double x = Math.cos(i*v+Math.PI/2);
            double y = Math.sin(i*v+Math.PI/2);
            points[i] = new V2(x, y);
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            //g.drawOval(170-5, 200-5, 10, 10);
            //S.drawLine(g, new V2(0,0), new V2(1,2));
            S.drawAxis(g);
            /*for (double v=0; v<2*Math.PI; v += 0.001){
                double x = Math.cos(v);
                double y = Math.sin(v);
                S.drawPoint(g, new V2(x,y));
            }*/
            /*for (int i=0; i<n; i++){
                //S.drawPoint(g, points[i]);
                int j = (i + n/2) % n;
                S.drawLine(g, points[i], points[j]);
            }*/
            V2 p1 = new V2(3,0);
            V2 p2 = new V2(3,4);
            V2 p3 = new V2(0,0);
            S.drawTriangle(g,p1, p2, p3);
            System.out.println(p1.angleBetweenVectors(p2));
        }
    } // class DrawPanel

} // GraphicsApp
