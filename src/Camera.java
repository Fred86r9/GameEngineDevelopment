import java.awt.*;

public class Camera {

    V3 O = new V3(0,0,0); //Origo for virtuel world

    //Virtuel world basis's i,j,k
    V3 i = new V3(1,0,0);
    V3 j = new V3(0,1,0);
    V3 k = new V3(0,0,1);

    V3 E = new V3(0,0,0); //Origo/Eye-point for Camera

    //Camera basis's D,U,R
    V3 D = new V3(1,0,0);
    V3 U = new V3(0,1,0);
    V3 R = new V3(0,0,1);

    double z = 2; //Zoom-factor for projection plane (S2)


    S2 s2;

    Camera(int sx, int sy, int ox, int oy){
        s2=new S2(sx,sy, ox,oy);
    }

    V2 project(V3 p){
        V3 EP = p.sub(E); //EP = OP - OE
        double d = D.dot(EP);
        double u = U.dot(EP);
        double r = R.dot(EP);

        //Make projection to find skæringspunkt (intersection) on projection plane
        double rm = r*z/d; //r mærke, er x-værdi
        double um = u*z/d; //er y-værdi

        return new V2(rm, um);
    }

    void moveTo(V3 p){
        //E = p;
        E = new V3(p.x, p.y, p.z);
    }


    void focus(V3 p){
        D = p.sub(E).unit(); //D skal pege langs EP => EP = OP - EP (unit da det skal være enhedsvektore)
        R = D.cross(k).unit(); //Krydsproduktet giver en ny vektor der er vinkelret på de to. HUSK rækkefølge er vigtig
        U = R.cross(D);

    }

    void drawAxis(Graphics g){
        drawLine(g, O, i);
        drawLine(g, O, j);
        drawLine(g, O, k);
    }

    void drawPoint(Graphics g, V3 p){
        s2.drawPoint(g, project(p));
    }

    void drawLine(Graphics g, V3 p1, V3 p2){
        s2.drawLine(g, project(p1), project(p2));
    }
}
