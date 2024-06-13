import javax.swing.*;
import java.awt.*;
import static java.lang.Math.*;

public class Rotation2D extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new Rotation2D();
        frame.setSize(700, 700);
        frame.setTitle("Rotation2D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
    }

    public Rotation2D() {
        add(new DrawPanel());
    }

    class DrawPanel extends JPanel {
        S2 S=new S2(50,50, 120,300);
        V2 A=new V2(2,2);
        V2 B=new V2(4,2);
        V2 C=new V2(4,4);
        V2 D=new V2(2,4);
        V2 P=A.add(B).add(C).add(D).mulVByScalar(1.0/4);     // Rotations punkt
        double phi=PI/3;                       // Rotations vinkel
        M2 M=new M2(cos(phi), -sin(phi),
                sin(phi), cos(phi));
        V2 Ar=M.multiply(A.subtract(P)).add(P);
        V2 Br=M.multiply(B.subtract(P)).add(P);
        V2 Cr=M.multiply(C.subtract(P)).add(P);
        V2 Dr=M.multiply(D.subtract(P)).add(P);

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            S.drawAxis(g);
            S.drawLine(g,A,B);
            S.drawLine(g,B,C);
            S.drawLine(g,C,D);
            S.drawLine(g,D,A);
            S.drawPoint(g,P);
            S.drawLine(g,Ar,Br);
            S.drawLine(g,Br,Cr);
            S.drawLine(g,Cr,Dr);
            S.drawLine(g,Dr,Ar);
        }
    } // class DrawPanel

} // GraphicsApp
