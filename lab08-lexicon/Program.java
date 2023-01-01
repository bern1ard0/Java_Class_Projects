public class Program {
    public static void main(String[] args) {
        Point p2 = new Point(3,3,3);
        Point p1 = new Point(1,1,1);
       

        int dx = Math.abs(p1.x - p2.x);
        int dy = Math.abs(p1.y - p2.y);
        int dz = Math.abs(p1.z - p2.z);
        
        System.out.println("Distance between points on the x axis is: " + dx);
        System.out.println("Distance between points on the y axis is: " + dy);
        System.out.println(p1.z);
        System.out.println(p2.z);
        System.out.println("Distance between points on the z axis is: " + dz);
    }
}