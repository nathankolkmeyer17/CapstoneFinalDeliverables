package capstone;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinWeightTriangulation
{

    private Set<Point> pts;
    private List<Edge> edges;
    
    
    private int numEdgesInATriangulation;
    long dataTime;
    long dataRemoved;
    long possibleBefore;
    long possibleAfter;
    long dataTime1;
    long dataRemoved1;
    long possibleBefore1;
    long possibleAfter1;
    long dataTime2;
    long dataRemoved2;
    long possibleBefore2;
    long possibleAfter2;
    long dataTime3;
    long dataRemoved3;
    long possibleBefore3;
    long possibleAfter3;
    long dataTime4;
    long dataRemoved4;
    long possibleBefore4;
    long possibleAfter4;
    long dataTime5;
    long dataRemoved5;
    long possibleBefore5;
    long possibleAfter5;
    long dataTime6;
    long dataRemoved6;
    long possibleBefore6;
    long possibleAfter6;
    static List<Edge> tempL;
    
    private Set<Edge> smallestSoFar;
    private ArrayList<Edge> possTriangulation;
    private ArrayList<Edge> possEdges;
    private ArrayList<Edge> mwtEdges = new ArrayList<Edge>();


/**
 * 
 * @param pts
 */
public MinWeightTriangulation(Set<Point> pts)
{
    
    this.pts = pts;

    
    Set<Edge> edgeSet = new HashSet<Edge>();
    
    
    for (Point p1: pts)
        for (Point p2:pts)
            if (!p1.equals(p2))
            {   Edge e = new Edge(p1,p2);
                if (!edgeSet.add(e))
                    edgeSet.add(e);
            }
    
    possEdges = new ArrayList<Edge>(edgeSet);
    
    
    ConvexHull ch = new ConvexHull(pts);
    numEdgesInATriangulation = 3 * pts.size() - 3 - ch.getEdgeList().size();
    System.out.println("Convex Hull Edges: " +ch.getEdgeList());
         
    possTriangulation = new ArrayList<Edge>();

    
    //*** first put CH in and remove from possibility
    for (Edge e : ch.getEdgeList())
    {
       possTriangulation.add(e);
       possEdges.remove(e);
    }
    System.out.println("Number of Possible Edges Before Pruning: "+ possEdges.size());
    final long startTimeAll = System.currentTimeMillis();
    int tempSize6 = possEdges.size();
    possibleBefore6=possEdges.size();
/////////////////////////////////////////////////////////////////////////////////////////
//    final long startTime1 = System.currentTimeMillis();
////    
////    int tempSize1 = possEdges.size();
//    possEdges.removeAll(exclusionTest(possEdges));
//    final long endTime1 = System.currentTimeMillis();
////  dataTime1 =endTime1-startTime1;
////  dataRemoved1=tempSize1-possEdges.size();
////  possibleAfter1=possEdges.size();
//    System.out.println("Exclusion Time: "+ (endTime1-startTime1));
////    System.out.println("Total Removed: "+(tempSize6-possEdges.size()));
//    System.out.println("Number poss edges after checking exclusion region: "+possEdges.size());
////////////////////////////////////////////////////////////////////////////////////////
////   
//    possTriangulation.addAll(edgesCrossNone(possEdges));
//// remove added edges
//    if (possTriangulation.size() != 0) {
//        List<Edge> tempPoss1 = new ArrayList<Edge>(possEdges);
//
//        for (Edge tempP1 : tempPoss1) {
//            if (possTriangulation.contains(tempP1)) {
//                possEdges.remove(tempP1);
//            }
//        }
//    }
//// check if cross
//    Set<Edge> tempTri1 = new HashSet<Edge>(possTriangulation);
//    List<Edge> tempEd1 = new ArrayList<Edge>(possEdges);
//    for (Edge edgesIn1 : tempEd1) {
//        if (crossOtherEdges(edgesIn1, tempTri1)) {
//            possEdges.remove(edgesIn1);
//        }
//    }
//
/////////////////////////////////////////////////////////////////////////////////////////////// 
//
//  possTriangulation.addAll(Bskelly(possEdges));
//  if(possTriangulation.size()!=0) {
//      List<Edge> tempPoss5 = new ArrayList<Edge>(possEdges);
//
//      for(Edge tempP5 : tempPoss5) {
//          if(possTriangulation.contains(tempP5)) {
////              System.out.println("These are mutual: "+tempP.toString());
//              possEdges.remove(tempP5);
//          }
//      }
//  }
//  Set<Edge> tempTri5 = new HashSet<Edge>(possTriangulation);
//  List<Edge> tempEd5 = new ArrayList<Edge>(possEdges);
//  for(Edge edgesIn5:tempEd5) {
//      if(crossOtherEdges(edgesIn5, tempTri5)) {
//          possEdges.remove(edgesIn5);
//      }
//  }
//
///////////////////////////////////////////////////////////////////////////////////////////////
//
//    possTriangulation.addAll(mutualNearest(possEdges));
//    if(possTriangulation.size()!=0) {
//        List<Edge> tempPoss = new ArrayList<Edge>(possEdges);
//
//        for(Edge tempP : tempPoss) {
//            if(possTriangulation.contains(tempP)) {
////                System.out.println("These are mutual: "+tempP.toString());
//                possEdges.remove(tempP);
//            }
//        }
//    }
//    Set<Edge> tempTri = new HashSet<Edge>(possTriangulation);
//    List<Edge> tempEd = new ArrayList<Edge>(possEdges);
//    for(Edge edgesIn2:tempEd) {
//        if(crossOtherEdges(edgesIn2, tempTri)) {
//            possEdges.remove(edgesIn2);
//        }
//    }

//////////////////////////////////////////////////////////////////////////////////
        
//    while( possEdges.removeAll(simpleLMT(possEdges))){
//        possEdges.removeAll(simpleLMT(possEdges));
//    }
/////////////////////////////////////////////////////////////////////////////////////////////
    System.out.println("Number of Possible Edges After All Functions: "+possEdges.size());
    final long endTimeAll = System.currentTimeMillis();
    dataTime6 = endTimeAll-startTimeAll;
    dataRemoved6 =tempSize6-possEdges.size();
    possibleAfter6=possEdges.size();
    Set<Edge> subset = new HashSet<Edge>(possTriangulation);
    final long startTime = System.currentTimeMillis();
    solve(possEdges,0,subset );
    final long endTime = System.currentTimeMillis();
    System.out.println("Backtracking Time: "+ (endTime-startTime));
    
    mwtEdges= new ArrayList<Edge>(smallestSoFar);
    
    System.out.println("Total Weight: "+weight(mwtEdges));
////////////////////////////////////////////////////////////////////////////
    System.out.println("Checking results against results file for pointSet.");
    if(tryCompare(mwtEdges) && numEdgesInATriangulation == tempL.size()) {
        System.out.println("Same MWT");
    }
    else{
        System.out.println("Results are different from previous result file.");
    }
}
/**
 * 
 * Simplified LMT-Skeleton
 * @param possEdges2
 * @return
 */
private List<Edge> simpleLMT(List<Edge> possEdges2) {

    List<Edge> LMTList = new ArrayList<Edge>();
    Set<Point> quadPts = new HashSet<Point>();
    Set<Point> tempSet2 = new HashSet<Point>(pts);

    boolean edgeStillPoss;
    boolean triOnePoss;
    boolean triTwoPoss;
    Point tempP1 = new Point();
    Point tempP2 = new Point();

    for (Edge edgesInP : possEdges2) {
        Point edgeP1 = edgesInP.getPoint1();
        Point edgeP2 = edgesInP.getPoint2();

        triOnePoss = false;
        triTwoPoss = false;
        edgeStillPoss = false;

        tempSet2.remove(edgeP1);
        tempSet2.remove(edgeP2);

        for (Point p1 : tempSet2) {
            if (doLinesIntersect.onRight(p1, edgeP1, edgeP2)) {
                tempP1 = new Point(p1.getX(), p1.getY());

                Edge edg1 = new Edge(edgeP1, tempP1);
                Edge edg2 = new Edge(edgeP2, tempP1);

                if (((possEdges.contains(edg1)
                    || possTriangulation.contains(edg1))
                    && (possEdges.contains(edg2)
                        || possTriangulation.contains(edg2)))
                    && (triEmpty(edgeP1, tempP1, edgeP2, tempSet2))) {

                    triOnePoss = true;
                    triTwoPoss = false;
                    for (Point p2 : tempSet2) {
                        if (doLinesIntersect.onLeft(p2, edgeP1, edgeP2)) {

                            tempP2 = new Point(p2.getX(), p2.getY());

                            Edge edg3 = new Edge(edgeP1, tempP2);
                            Edge edg4 = new Edge(edgeP2, tempP2);

                            if (((possEdges.contains(edg3)
                                || possTriangulation.contains(edg3))
                                && (possEdges.contains(edg4)
                                    || possTriangulation.contains(edg4)))
                                && (triEmpty(
                                    edgeP2,
                                    tempP2,
                                    edgeP1,
                                    tempSet2))) {
                                triTwoPoss = true;
                            }

                        }
                        if (triTwoPoss && triOnePoss) {
                            quadPts.add(edgeP1);
                            quadPts.add(tempP1);
                            quadPts.add(tempP2);
                            quadPts.add(edgeP2);

                            if ((convexQuad(quadPts) == false)
                                || (convexQuad(quadPts) == true
                                    && doLinesIntersect.distance(
                                        edgeP1,
                                        edgeP2) < doLinesIntersect
                                            .distance(tempP1, tempP2))) {
                                edgeStillPoss = true;

                            }
                        }
                        if(!quadPts.isEmpty());
                        quadPts.clear();
                    }

                }
            }

        }
        if (!edgeStillPoss) {
            LMTList.add(edgesInP);
        }
        tempSet2.add(edgeP1);
        tempSet2.add(edgeP2);

    }
    System.out.println(LMTList.size());

    return LMTList;
}
private static boolean triEmpty(Point point1, Point point2, Point point3, Set<Point> pointS) {
    boolean empty = true;

    for (Point pointSTemp : pointS) {
        if (!pointSTemp.equals(point1) && !pointSTemp.equals(point2)
            && !pointSTemp.equals(point3)
            && isInside(
                point1.getX(),
                point1.getY(),
                point2.getX(),
                point2.getY(),
                point3.getX(),
                point3.getY(),
                pointSTemp.getX(),
                pointSTemp.getY())) {
            empty = false;
        }
    }
    return empty;
}
private static float area(int x1, int y1, int x2, int y2, int x3, int y3) {
    return (float)Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
}


private static boolean isInside(int x1, int y1, int x2, int y2, int x3, int y3, int x, int y) {

    float A = area(x1, y1, x2, y2, x3, y3);

    float A1 = area(x, y, x2, y2, x3, y3);

    float A2 = area(x1, y1, x, y, x3, y3);

    float A3 = area(x1, y1, x2, y2, x, y);

    return (A == A1 + A2 + A3);
}


/**
 * 
 * Quadrilateral Hull for LMT
 * @param quadPoints
 * @return
 */
private boolean convexQuad(Set<Point> quadPoints) {
//  System.out.println(quadPoints.toString());
   ConvexHull tempH = new ConvexHull(quadPoints);
   
   if(tempH.getPointSet().containsAll(quadPoints)) {
       return true;
   }
   return false;
}
/**
 * 
 * Beta Skeleton
 * @param possedg
 * @return
 */
private List<Edge> Bskelly(List<Edge> possedg){
    List<Edge> skelly = new ArrayList<Edge>();
    Set<Point> tempPts = new HashSet<Point>(pts);
    boolean inSkelly;
    for(Edge possEd : possedg) {
        inSkelly =true;
        Point p1 = possEd.getPoint1();
        Point p2 = possEd.getPoint2();
        tempPts.remove(p1);
        tempPts.remove(p2);
        double dis = doLinesIntersect.distance(p1,p2);
        for(Point ptsIn : tempPts) {
               double testDis = ((Math.sqrt(2))*(dis/2));
               double dis1 = doLinesIntersect.distance(p1,ptsIn);
               double dis2 = doLinesIntersect.distance(p2,ptsIn);
               if(dis1 < testDis || dis2 < testDis) {
                   inSkelly = false;
               }             
                   
        }
        if(inSkelly) {
            skelly.add(possEd);
        }
        tempPts.add(p1);
        tempPts.add(p2);
    }
    return skelly;
}
/**
 * 
 * Exclusion Test using circles
 * @param potentialEdges
 * @return
 */
private List<Edge> exclusionTest(List<Edge> potentialEdges){
    List<Edge> exclusionEdges = new ArrayList<Edge>();
    for(Edge potEdges : potentialEdges) {
        long cirMidX = (potEdges.getPoint1().getX() + potEdges.getPoint2().getX())/2;
        long cirMidY = (potEdges.getPoint1().getY() + potEdges.getPoint2().getY())/2;
        double dist = doLinesIntersect.distance(potEdges.getPoint1(),potEdges.getPoint2());
        double cirRad = ((dist/2)/2);// 2 inplace of Math.sqrt(2);
        boolean right = false;
        boolean left = false;
        for(Point pt1 : pts) {
            if(((pt1.getX() - cirMidX) * (pt1.getX() - cirMidX) +
                (pt1.getY() - cirMidY) * (pt1.getY() - cirMidY) <= cirRad * cirRad)) { 
                if(doLinesIntersect.onLeft(pt1,potEdges.getPoint1(), potEdges.getPoint2())) {
                    left = true;
                }
                    
            }   
        }
        for(Point pt2 : pts) {
            if(((pt2.getX() - cirMidX) * (pt2.getX() - cirMidX) +
                (pt2.getY() - cirMidY) * (pt2.getY() - cirMidY) <= cirRad * cirRad)) { 
                if(doLinesIntersect.onRight(pt2,potEdges.getPoint1(),potEdges.getPoint2())) {
                    right = true;
                }
            }
        }
        if(right && left) {
            exclusionEdges.add(potEdges);
        }
    }
    return exclusionEdges;
}
/**
 * 
 * Which Edges cross no other possible edges
 * @param possEdgesIn
 * @return
 */
private List<Edge> edgesCrossNone(List<Edge> possEdgesIn) {
    Set<Edge> copyTemp = new HashSet<Edge>(possEdgesIn);
    List<Edge> noneCrossList = new ArrayList<Edge>();
    for(Edge edgs:possEdgesIn) {
        copyTemp.remove(edgs);
        if(crossOtherEdges(edgs,copyTemp)==false) {
            noneCrossList.add(edgs);
        }
        copyTemp.add(edgs);
    }
    return noneCrossList;
}
/**
 * 
 * Mutual Nearest Neighbors 
 * @param possibleEdges
 * @return
 */
private List<Edge> mutualNearest(List<Edge> possibleEdges) {
    List<Edge> mutualEdges = new ArrayList<Edge>();
    Set<Point> tempPts = new HashSet(pts);
    boolean AtoB;
    boolean BtoA;
    for(Edge edgeIn : possibleEdges) {   
        
        Point p1 = edgeIn.getPoint1();
        Point p2 = edgeIn.getPoint2();
        
        tempPts.remove(p2);
        tempPts.remove(p1);  
        
        AtoB = true;
        BtoA = true;
        
        double dis = doLinesIntersect.distance(p1,p2);
       
        for(Point pt : tempPts) {
            double dis2 = doLinesIntersect.distance(p1,pt);
            if(dis2<=dis) { 
                AtoB = false;
            }
        }
        
        if(AtoB) {     
            for(Point pt2 : tempPts) {   
                double dis3 = doLinesIntersect.distance(p2,pt2);
                if(dis3<=dis) {
                    BtoA = false;
                }
            }
        }
       if(AtoB && BtoA) {
           mutualEdges.add(edgeIn);   
       }
      
       tempPts.add(p1);
       tempPts.add(p2);
       
    
    }
    return mutualEdges;
}


/**
 * do backtracking to find a triangulation of the possibleEdges, starting at whichEdge position in the list,
 * and the set of edges already in the triangulation
 * @param possEdges
 * @param whichEdge
 */
private void solve(List<Edge> possibleEdges, int whichEdge, Set <Edge> potentialTriangulation) 
{
    if (whichEdge == possibleEdges.size())
    {
        // check if actually a triangulation
        if (isTriangulation(potentialTriangulation))
        {
            // if so, then compare weight to smallestSoFar
            if ((smallestSoFar == null) ||(weight(potentialTriangulation) < weight(smallestSoFar)))
                smallestSoFar = new HashSet<Edge>(potentialTriangulation);
//                System.out.println("SmallestSoFar: "+smallestSoFar);
                
        }
        
    }
    else
    {
        // try this edge in
        //check if potential edge crosses anything in potential triangulation first, reduces time
        if(crossOtherEdges(possibleEdges.get(whichEdge), potentialTriangulation)==false){
            potentialTriangulation.add(possibleEdges.get(whichEdge));
            solve(possibleEdges,whichEdge+1, potentialTriangulation);
        }

        // try this edge not in
        potentialTriangulation.remove(possibleEdges.get(whichEdge));
        solve(possibleEdges,whichEdge+1, potentialTriangulation);

    }
}



/**
 * compute the weight of the collection  (the sum of all of the lengths of edges)
 * @param edges
 * @return
 */
private double weight(Collection<Edge> edges) 
{
    double sum = 0;
    for (Edge e: edges)
        sum+= e.length();
    return sum;
}



private boolean isTriangulation(Set<Edge> potentialTriangulation) 
{    
    if (potentialTriangulation.size() != numEdgesInATriangulation)
        return false;
    
    for (Edge e: potentialTriangulation)
        if (crossOtherEdges(e,potentialTriangulation))
            return false;

    return true;
}


/**
 * checks if e crosses any edges in edges other than itself
 * @param e
 * @param edges
 * @return
 */
private boolean crossOtherEdges(Edge e, Set<Edge> edges) 
{
    for (Edge check:edges)
        if (doLinesIntersect.cross(e,check))
            return true;
    return false;
}



/**
 * gives you the set of points for which the MWT was computed
 * @return the set of points.
 */
   public Set<Point> getPointSet() 
    {
        return pts;
    }
/**
 * Gives you a list of the edges that constitute the MWT.
 * @return the list of edges in the MWT (no particular order)
 */
    public List<Edge> getEdgeList() 
    {
        return mwtEdges;
    }
    
    //////////////////////////
    public static boolean tryCompare(List<Edge> thisMWT) {
        try {
             tempL = FileIO.otherMWT("Results"+MyCanvas.str.substring(0,4)+".txt");
            if(thisMWT.containsAll(tempL)) {
                return true;
            }
            
        }
        catch (FileNotFoundException e) {
            return false;
        }
        return false;
    }
    public long returnTime() {
        return dataTime;
    }
    public long returnRemoved() {
        return dataRemoved;
    }
    
    public long possibleBefore() {
        return possibleBefore;
    }
    
    public long possibleAfter() {
        return possibleAfter;
    }
    public long returnTime1() {
        return dataTime1;
    }
    public long returnRemoved1() {
        return dataRemoved1;
    }
    
    public long possibleBefore1() {
        return possibleBefore1;
    }
    
    public long possibleAfter1() {
        return possibleAfter1;
    }
    public long returnTime2() {
        return dataTime2;
    }
    public long returnRemoved2() {
        return dataRemoved2;
    }
    
    public long possibleBefore2() {
        return possibleBefore2;
    }
    
    public long possibleAfter2() {
        return possibleAfter2;
    }
    public long returnTime3() {
        return dataTime3;
    }
    public long returnRemoved3() {
        return dataRemoved3;
    }
    
    public long possibleBefore3() {
        return possibleBefore3;
    }
    
    public long possibleAfter3() {
        return possibleAfter3;
    }
    public long returnTime4() {
        return dataTime4;
    }
    public long returnRemoved4() {
        return dataRemoved4;
    }
    
    public long possibleBefore4() {
        return possibleBefore4;
    }
    
    public long possibleAfter4() {
        return possibleAfter4;
    }
    public long returnTime5() {
        return dataTime5;
    }
    public long returnRemoved5() {
        return dataRemoved5;
    }
    
    public long possibleBefore5() {
        return possibleBefore5;
    }
    
    public long possibleAfter5() {
        return possibleAfter5;
    }
    public long returnTime6() {
        return dataTime6;
    }
    public long returnRemoved6() {
        return dataRemoved6;
    }
    
    public long possibleBefore6() {
        return possibleBefore6;
    }
    
    public long possibleAfter6() {
        return possibleAfter6;
    }
}