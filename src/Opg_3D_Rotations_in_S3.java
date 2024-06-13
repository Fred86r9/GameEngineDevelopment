import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.*;

public class Opg_3D_Rotations_in_S3 extends JFrame {
    public static void main(String[] args) {
        Opg_3D_Rotations_in_S3 frame=new Opg_3D_Rotations_in_S3();
        frame.setTitle("Opg_3D_Rotations_in_S3");
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    } // main()

    Opg_3D_Rotations_in_S3() {
        add(new PaintPanel());
    }

    class PaintPanel extends JPanel {
        Timer myTimer=new Timer(50, new TimerListener());
        int count=0;
        S3 S=new S3(100,100,200,400);
        M3 I=new M3(1,0,0,
                0,1,0,
                0,0,1);
        M3 Sz=new M3(0,-1, 0,
                1, 0, 0,
                0, 0, 0);
        double phi=PI/100;
        M3 Rz=I.add(Sz.mul(sin(phi))).add(Sz.mul(Sz).mul(1-cos(phi))); // Rotation matrix
        V3[] cube=new V3[8];
        V3 C=new V3(0,0,0);     // Center ogf cube

        PaintPanel() {
            // Start simulation
            myTimer.start();
//            Java code to create cube
            cube[0]=new V3(1,4,1);
            cube[1]=new V3(1,4,3);
            cube[2]=new V3(1,6,1);
            cube[3]=new V3(1,6,3);
            cube[4]=new V3(3,4,1);
            cube[5]=new V3(3,4,3);
            cube[6]=new V3(3,6,1);
            cube[7]=new V3(3,6,3);
            for (V3 p: cube) C=C.add(p);
            C=C.mul(1.0/cube.length);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            count++;
            g.drawString("count="+count, 10, 10);
//            Java code to draw cube
            S.drawAxis(g);
            S.drawLine(g, cube[0], cube[1]);
            S.drawLine(g, cube[1], cube[3]);
            S.drawLine(g, cube[3], cube[2]);
            S.drawLine(g, cube[2], cube[0]);
            S.drawLine(g, cube[4], cube[5]);
            S.drawLine(g, cube[5], cube[7]);
            S.drawLine(g, cube[7], cube[6]);
            S.drawLine(g, cube[6], cube[4]);
            S.drawLine(g, cube[0], cube[4]);
            S.drawLine(g, cube[1], cube[5]);
            S.drawLine(g, cube[3], cube[7]);
            S.drawLine(g, cube[2], cube[6]);

            // Rotate the cube
            for (int i=0; i<cube.length; i++){
                cube[i]=Rz.mul(cube[i].sub(C)).add(C);
            }
        }

        class TimerListener implements ActionListener {
            public void actionPerformed(ActionEvent evt){
                repaint();
            }
        }
    } // class PaintPanel

} // class MainFrame