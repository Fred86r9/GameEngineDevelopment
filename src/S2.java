import java.awt.*;

//Coordinate system class
public class S2 {

    V2 origo = new V2(0,0);
    V2 o;  //Origo position in pixel value on screen
    M2 s;  //Scalar matrix
    M2 f;  //Flip matrix
    M2 t;  //Transform matrix: f*s

    public S2(int sX, int sY, int oX, int oY){
        o = new V2(oX, oY);
        s = new M2(sX, 0, 0, sY);
        f = new M2(1, 0, 0, -1);
        t = f.multiply(s);
    }

    public V2 transform(V2 v){
        //v' = T * v + O
        return t.multiply(v).add(o);
    }

    //p1 starting point, p2 end point
    public void drawLine(Graphics g, V2 p1, V2 p2){
        V2 pp1 = transform(p1);
        V2 pp2 = transform(p2);
        g.drawLine((int) pp1.x, (int) pp1.y, (int) pp2.x, (int) pp2.y);

    }

    public void drawPoint(Graphics g, V2 p){
        V2 pp = transform(p);
        g.fillOval((int) pp.x-1, (int) pp.y-1,2, 2);
    }

    public void drawPoint(Graphics g, V2 v, int size){
        V2 p=transform(v);                // p in pixels
        g.fillOval((int)p.x-size/2, (int)p.y-size/2, size, size);
    }

    public void drawPoint(Graphics g, V2 v, Color c, int size){
        Color oldColor = g.getColor();
        g.setColor(c);
        drawPoint(g, v, size);
        g.setColor(oldColor);
    }

    public void drawAxis(Graphics g){
        drawLine(g, origo, new V2(1,0));
        drawLine(g, origo, new V2(0,1));
    }

    public void drawAxis(Graphics g, int x, int y){
        drawLine(g, origo, new V2(x,0));
        drawLine(g, origo, new V2(0,y));
    }

    public void drawTriangle(Graphics g, V2 p1, V2 p2, V2 p3){
        //V2 pp1 = transform(p1);
        //V2 pp2 = transform(p2);
        //V2 pp3 = transform(p3);

        drawLine(g, p1, p2);
        drawLine(g, p2, p3);
        drawLine(g, p3, p1);

    }

    public void moveTo(V2 p){
        o=transform(p);
    }

    public void rotate(double phi){
        M2 R=new M2(Math.cos(phi), -Math.sin(phi),
                Math.sin(phi), Math.cos(phi));
        t=t.multiply(R);
    }


}
