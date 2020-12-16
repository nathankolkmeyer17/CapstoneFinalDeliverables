package capstone;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConvexHull 
{
    private Set<Point> thepoints;
    private List<Edge> thehull;
    /**
     * Given the set of points, the convex hull is created.
     * @param pts
     */
   public ConvexHull(Set<Point> pts)
   {
      thepoints = pts;
  
      Set<Edge> edges = new HashSet<Edge>();
       for (Point p1 : pts)
       {
           for (Point p2: pts)
           {
               if (p1.equals(p2))
                   continue;
               Edge thisEdge = new Edge(p1,p2);
               if (! pointsOnBothSides(thisEdge) && noPointsOnEdge(thisEdge))
                   edges.add(thisEdge);
             
                   

           }
       }
       thehull = new ArrayList<Edge>(edges);   
}
   
   /**
    * 
    * @param thisEdge
    * @return
    */
   private boolean noPointsOnEdge(Edge thisEdge) 
   {
       Point p1 = thisEdge.getPoint1();
       Point p2 = thisEdge.getPoint2();
        for (Point p: thepoints)
        {
            //if (Geometry.collinear(p,p1,p2))
            //    System.out.println("collinear points found");
            if (p.equals(p1) || p.equals(p2))
                continue;
            if (doLinesIntersect.collinear(p,p1,p2) &&
                inBox(p,p1,p2))
                return false;
        }
        
        return true;
        
    }

private boolean inBox(Point p, Point p1, Point p2) 
{
    boolean xdirection;
    boolean ydirection;
    if ((p1.getX() <= p.getX()) && (p.getX() <= p2.getX())
              || (p2.getX() <= p.getX()) && (p.getX() <= p1.getX()))
        xdirection = true;
    else
        xdirection = false;
    
    if ((p1.getY() <= p.getY()) && (p.getY() <= p2.getY())
              || (p2.getY() <= p.getY()) && (p.getY() <= p1.getY()))
        ydirection = true;
    else
        ydirection = false;
    
    return (xdirection && ydirection);
        
}

/**
    * 
    * @param thisEdge
    * @return
    */
private boolean pointsOnBothSides(Edge thisEdge) 
{    
       boolean left = false;
       boolean right = false;
       Point p1 = thisEdge.getPoint1();
       Point p2 = thisEdge.getPoint2();
       for (Point p : thepoints)
       {
          // assumes no collinears?
           if (!left && doLinesIntersect.onLeft(p,p1,p2))
               left = true;
           else
               if (!right && doLinesIntersect.onRight(p,p1,p2))
                   right = true;
           if (left && right)
               return true;
       }
       return (left && right);
    }

/**
 * gives you the set of points for which the Convex Hull was computed
 * @return the set of points.
 */
   public Set getPointSet() 
    {
        return thepoints;
    }
/**
 * Gives you a list of the edges that constitute the Convex Hull.
 * @return the list of edges on the Convex Hull (no particular order)
 */
    public List<Edge> getEdgeList() 
    {
        return thehull;
    }
}

//import java.util.ArrayList;
//import java.util.Set;
//import java.util.Vector;
//import java.util.List;
///**
//*  Write a one-sentence summary of your class here.
//*  Follow it with additional details about its purpose, what abstraction
//*  it represents, and how to use it.
//* 
//*  @author 
//*  @version 
//*/
//public class ConvexHull {
//  private static List<Point> hull = new ArrayList<Point>();
//  //vector to hold hull edges
//static List<Edge> hullEdges = new ArrayList<Edge>();
//  public ConvexHull(Set<Point> pS) {
//      Point[] pointS =new Point[pS.size()];
//      pS.toArray(pointS);
//      int n = pointS.length;
//      convexHull(pointS, n);
//
//  }
// 
//  public static int direction(Point p, Point q, Point r) 
//  { 
//      int val = (q.getY() - p.getY()) * (r.getX() - q.getX()) - 
//                (q.getX() - p.getX()) * (r.getY() - q.getY()); 
//      if (val == 0) {
//          return 0; 
//      }
//      if (val > 0) {
//          return 1;  
//      }
//      return 2;
//  } 
//  
//  // convex hull looping 
//  public static void convexHull(Point points[], int n) 
//  {       
//      //three points to calculate
//      if (n < 3) {
//          return; 
//      }
//      //leftmost point 
//      int leftmost = 0; 
//      for (int i = 1; i < n; i++) {
//          if (points[i].getX() < points[leftmost].getX()) 
//              leftmost = i; 
//      }  
//      int p = leftmost, q; 
//      //loop through until reached first point again
//      do
//      { 
//          hull.add(points[p]);            
//          q = (p + 1) % n;               
//          for (int i = 0; i < n; i++) 
//          { 
//             if (direction(points[p], points[i], points[q]) == 2) 
//                 q = i; 
//          }        
//          p = q;        
//      } while (p != leftmost);      
//      //prints points that are apart of convex hull
//      //start at first point
//      Point p1 = hull.get(0);
//      for(int j = 1; j < hull.size();j++) {
//      //second point starts at second point in convex hull
//          Point p2 = hull.get(j);
//      //create temp edge from two points and add to vector, change point
//      //values
//          Edge temp = new Edge(p1,p2);
//          hullEdges.add(temp);
//      //change point values
//          p1 = p2;
//      }
//      hullEdges.add(new Edge(new Point(hull.get(hull.size()-1).getX(), hull.get(hull.size()-1).getY()),
//          new Point( hull.get(0).getX(),hull.get(0).getY())));
//  }
//
//  public List<Point> getPointList(){
//      return hull;
//  }
//  /**
//   * Place a description of your method here.
//   * @return
//   */
//  public List<Edge> getEdgeListC() {
//      return hullEdges;
//  } 
//}

