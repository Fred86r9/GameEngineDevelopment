//Vector class
public class V2 {
    double x,y;

    public V2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Methods for: Add, sub, multiply, divide, length, angle (x-axis), determinant

    public V2 add(V2 v){
        return new V2(x + v.x,y + v.y);
    }

    public V2 subtract(V2 v){
        return new V2(x - v.x, y - v.y);
    }

    public V2 mulVByScalar(double scalar){
        return new V2(x * scalar, y * scalar);
    }

    public V2 divVByScalar(double scalar){
        if (scalar == 0){
            throw new IllegalArgumentException("ILLEGAL!!! Cant divide by zero!");
        }
        return new V2(x / scalar, y / scalar);
    }

    public double dotProduct(V2 v){
        return x * v.x + y * v.y;
    }

    public double length(){
        return Math.sqrt(x * x + y * y);
    }

    //atan2(y, x) = atan(y / x), if x > 0
    //            = atan(y / x) + π, if x < 0 and y >= 0
    //            = atan(y / x) + π, if x < 0 and y < 0
    //            = π / 2, if x = 0 and y > 0
    //            = -π / 2, if x = 0 and y < 0
    //            = undefined, if x = 0 and y = 0
    public double angleToXAxis(){
        return Math.atan2(y, x);
    }


    //dotProduct = |a| * |b| * cos(angle)
    //=> angle = acos(dotProduct / (|a| * |b|))
    public double angleBetweenVectors(V2 v){
        double dotProduct = this.dotProduct(v);
        double thisLength = this.length(); //length of vector on which the method is called (i.e., the current vector object).
        double vLength = v.length(); //length of vector passed as argument

        //Avoid division by zero
        if (thisLength == 0 || vLength == 0){
            throw new IllegalArgumentException("ILLEGAL!!! Division by zero!");
            //return 0; //Maybe i should rewrite to trow exception
        }

        double cosAngle = dotProduct / (thisLength * vLength);
        return Math.acos(cosAngle);
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }

    /*public static void main(String[] args) {
        V2 v1 = new V2(1,2);
        V2 v2 = new V2(3,5);

        System.out.println();

    }*/
}
