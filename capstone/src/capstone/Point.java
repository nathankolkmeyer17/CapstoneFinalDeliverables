package capstone;
/**
 * 
 * @author nkolk
 * class to create and utilize point throughout
 */
public class Point {
    
    private int x;
    
    private int y;
   
   public Point(int x, int y) {
       this.x = x;
       this.y = y;
       
   }
   
   /**
 * Create a new Point object.
 */
public Point() {
    // TODO Auto-generated constructor stub
}

public int getX() {
       return x;
   }
   
   public int getY() {
       return y;
   }
   
   public boolean equals(Object aPoint) {
       Point other = (Point)aPoint;
       return this.getX() == other.getX() && this.getY() == other.getY();
   }
   
     /**
      * returns a hash code for this Point.  Computed as a linear combination of the x and y coordinates.
      * @return the hash code
      * @see java.lang.Object#hashCode()
      */
     public int hashCode()
     {
         return x + 17 * y;
     }
     
     /**
      *  returns the string representation of this object
      * @return the string in (x,y) format
      */
     public String toString()
     {
          return  "("+ x + "," + y +")";
     }
     
     
     
}
