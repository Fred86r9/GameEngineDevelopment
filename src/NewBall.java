import javax.swing.*;
import java.awt.*;

public class NewBall extends JFrame{
    public static void main(String[] args) {
        JFrame frame = new NewBall();
        frame.setSize(700, 700);
        frame.setTitle("Drawing a Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    NewBall(){ add(new DrawPanel());}

    class DrawPanel extends JPanel{

        S2 S = new S2(50, 50, 350, 350);
        V2 b = new V2(0,0);



        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            S.drawAxis(g);
            S.drawPoint(g, b, Color.ORANGE, 10);
        }

    }

}