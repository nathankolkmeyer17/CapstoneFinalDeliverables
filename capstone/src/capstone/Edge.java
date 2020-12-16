package capstone;
/**
 * 
 * @author nkolk
 * class to create and utilize edge throughout
 */
public class Edge {
    private Point p1;
    private Point p2;
    //edge constructor
    public Edge(Point p1, Point p2) {
        this.p1 = p1;
        this.p2= p2;
    }
    /**
     * Create a new Edge object.
     */
    //edge length method
    public double length() {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    //return point 1 of edge
    public Point getPoint1()
    {
        return p1;
    }
    //returns point 2 of edge
    public Point getPoint2() {
        return p2;
    }
    //equals comparison function between two edges
    public boolean equals(Object aEdge) {
        Edge other = (Edge)aEdge;
        return (this.p1.equals(other.p1)&& this.p2.equals(other.p2)) 
                || (this.p1.equals(other.p2)&& this.p2.equals(other.p1));
    }
     /**
      * returns a hash code for this Edge.  Computed by adding the hashCodes of its points
      * @return the hash code
      * @see java.lang.Object#hashCode()
      */
    public int hashCode()
    {
        return  p1.hashCode() + p2.hashCode();
    }
    

    /**
     * Determines if this edge is shorter than the given edge.
     * @param otherEdge the edge being compared to
     * @return true if this edge is shorter than otherEdge; false otherwise
     */
public boolean shorterThan(Edge otherEdge)
{
      return this.length() < otherEdge.length();
}

//string method to print edge
public String toString() {
    return p1.toString() + "---" + p2.toString();
}



}


