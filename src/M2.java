//Matrix class 2D
public class M2 {
    double a, b;
    double c, d;

    public M2(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public V2 multiply(V2 v){
        return new V2(a * v.x + b * v.y,
                c * v.x + d * v.y);
    }

    public M2 multiply(M2 m){
        return new M2(
                a * m.a + b * m.c,
                a * m.b + b * m.d,
                c * m.a + d * m.c,
                c * m.b + d * m.d);
    }

    //Flips/swaps rows and columns
    public M2 transpose(){
        return new M2(a, c, b, d);
    }

    public double determinant(){
        return a * d - b * c;
    }

    public M2 inverse(){
        double det = determinant();
        if (det == 0){
            throw new ArithmeticException("Matrix is singular, cannot compute inverse");
        }
        double invDet = 1.0 / det;
        return new M2(d * invDet, -b * invDet, -c * invDet, a * invDet);
    }

    public M2 mulMByScalar(double scalar){
        return new M2(a * scalar, b * scalar, c * scalar, d * scalar);
    }

    public M2 divMByScalar(double scalar){
        return new M2(a / scalar, b / scalar, c / scalar, d / scalar);
    }

    public M2 add(M2 m){
        return new M2(a + m.a, b + m.b, c + m.c, d + m.d);
    }

    public M2 subtract(M2 m){
        return new M2(a - m.a, b - m.b, c - m.c, d - m.d);
    }


}
