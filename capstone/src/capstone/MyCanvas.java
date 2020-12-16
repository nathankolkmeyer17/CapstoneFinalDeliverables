package capstone;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.awt.*;
import java.awt.desktop.SystemSleepEvent;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 
 * @author nkolk
 *  main class to take point input data from fileIO, and using Point, Edge, doLinesIntersect
 *  calculate the convex hull and triangulation of the polygon it generates
 */
public class MyCanvas
{
	int nlength = 0;
    JLabel view;
    BufferedImage surface;

    static Random random = new Random();

    //point set and array to hold generated points
     private Set<Point> pSet = new HashSet<Point>();

    //vector to hold hull points
     static String str;
    static FileIO fileIO = new FileIO();

    public static void main(String[] args) throws IOException
    {
        MyCanvas canvas = new MyCanvas();
        JFrame frame = new JFrame(); 
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(canvas.view);
        frame.pack();
        frame.setVisible(true);
        frame.repaint();
    }
   
    public MyCanvas() throws FileNotFoundException, UnsupportedEncodingException
    {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter file name with data set to use: ");
		str = scan.nextLine();
		System.out.println("Loading point set from file and performing operations: " + str);
    	FileIO.readFile(str, pSet);
    	
        if(pSet.size()==2) {
        	System.out.println("Need at least 3 points to run program.");
        }
        else {
        System.out.println("Given points in file: "+pSet);
        surface = new BufferedImage(1920,1080,BufferedImage.TYPE_INT_RGB);
        view = new JLabel(new ImageIcon(surface));
        Graphics g = surface.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,1920,1080);
//
        for(Point temp : pSet) {
        	int x = temp.getX();
        	int y = temp.getY();
            g = surface.getGraphics();
            //add point to image
            drawPoint(x,y,g);	 
        }

        MinWeightTriangulation min = new MinWeightTriangulation(pSet);
        System.out.println("MWT Edges: ");
        for(Edge edges : min.getEdgeList()) {
            System.out.println(edges.toString());
        }
//            for(Edge temp : min.getEdgeList()) {
//                Point p4= temp.getPoint1();
//                Point p5 = temp.getPoint2();
////                System.out.println(temp.toString());
//                g.drawLine(p4.getX(),p4.getY(),p5.getX(),p5.getY());            
//            }

//            for(Edge tempEdge: ch2.getEdgeListC()) {
//                Point p4= tempEdge.getPoint1();
//                Point p5 = tempEdge.getPoint2();
//                System.out.println(tempEdge.toString());
//                g.drawLine(p4.getX(),p4.getY(),p5.getX(),p5.getY()); 
//            }
//        for(int iy = 10; iy < 510;iy+=10) {
//        	      PrintWriter writer = new PrintWriter(iy+ "Points.txt", "UTF-8");
//        	      for(int tempI =0; tempI < iy;tempI++) {
//        	          int tempX = (int)(Math.random()*15000);
//        	          int tempY = (int)(Math.random()*15000);
//        	          writer.print(tempX+","+tempY);
//        	          if(tempI+1 != iy) {
//        	              writer.print("\n");
//        	          }
//        	      }
//        	  
//        	      writer.close();
//        	      
//        	  
//        }
//        PrintWriter tempPW2 = new PrintWriter("DataResults.txt","UTF-8");
//        for(int temp2 = 10; temp2<110;temp2+=10) {
//            Set<Point> fileSets = new HashSet<Point>();
//            FileIO.readFile(temp2+"Points.txt", fileSets);
//            MinWeightTriangulation min = new MinWeightTriangulation(fileSets);
//            
//            tempPW2.print(min.returnTime6()+","+min.possibleBefore6()+","+min.returnRemoved6()+","+min.possibleAfter6()+"\n"
//                );
//        }
//        tempPW2.close();
        // calculate vector of hull points and construct edges of convex hull
//        int n = p.length;
//        convexHull(p,n);
//    	g.setColor(Color.RED);
//    	//start at first point
//    	Point p1 = hull.get(0);
//        for(int j = 1; j < hull.size();j++) {
//        	//second point starts at second point in convex hull
//        	Point p2 = hull.get(j);
//        	//create temp edge from two points and add to vector, change point
//        	//values
//        	Edge temp = new Edge(p1,p2);
//        	hullEdges.add(temp);
//        	//change point values
//        	p1 = p2;
//        }
//        hullEdges.add(new Edge(new Point(hull.get(hull.size()-1).getX(), hull.get(hull.size()-1).getY()),
//        		new Point( hull.get(0).getX(),hull.get(0).getY())));
//        //check if hull edges array filled
//        System.out.println("Convex Hull Edges: ");
//        for(int e = 0; e < hullEdges.size();e++) {
//        	Edge aTemp = hullEdges.get(e);
//        	System.out.println("Edge " + e + ": "+ aTemp.toString());
//        }
//    	System.out.println("Number of all potential edges before adding edges: "+AllPotentialEdges.size());
//    	
//    	for(int i1  =0; i1 < p.length;i1++) {	
//    		for(int j = i1+1; j < p.length;j++) {
//    			Edge tempedge = new Edge(p[i1],p[j]);   			
//    			AllPotentialEdges.add(tempedge);
//    			
//    		}
//    	}
//    	System.out.println("Number of all potential edges after adding edges: "+ AllPotentialEdges.size());
// 	
//    	//develop triangulation solution
//    	System.out.println("Number of triangulation edges before adding edges: " +triangEdge.size());
//   

//    	Triangulation aNewT = new Triangulation(AllPotentialEdges);
//    	aNewT.findTriang();
//    	System.out.println(aNewT.possEdges.size());
//    	numTriEdges = aNewT.returnSizeTri();
//    	triangsVec.add(aNewT);
    	
    	
    
//    	mwt(AllPotentialEdges, AllPotentialEdges, triangEdge);
//    	Triangulation ty = T.get(1);
//    	ty.findTriang();
        

//    	    System.out.println(triangEdge.size());
    	}
    }  
    

//    Vector<Edge> vecAll2=new Vector<Edge>();
//    Vector<Edge> vecCell = new Vector<Edge>(AllPotentialEdges);
//    Vector<Triangulation> T= new Vector<Triangulation>();
//    public void mwt(Vector<Edge> vecAll, Vector<Edge> possEdgs, Vector<Edge> possTrisEdgs ) {
//        
//        
//        Triangulation tempTri = new Triangulation(possEdgs);
////        tempTri.trisEdges=possTrisEdgs;
//        tempTri.findTriang();
//        T.add(tempTri);
//        possEdgs.addAll(AllPotentialEdges);
//        
//        
//        System.out.println(tempTri.possEdges.size());
//        if(vecAll.size()==0) {  
//                        
//        }
//        
//        else {
//            Edge tempEdge = vecAll.get(0);
//            possEdgs.remove(tempEdge);
//            possTrisEdgs = new Vector<Edge>();
//            vecAll.remove(0);
//            mwt(vecAll, possEdgs, possTrisEdgs );
//            
//            System.out.println(vecAll.size());
//           
//        }
//        
//    }
//    //draws points
    public void drawPoint(int x, int y, Graphics g)
    {
    		//x and y location for point
            int xLoc = x;
            int yLoc = y;
            //set point color and image
            g.setColor(Color.blue);
            g.fillOval(xLoc, yLoc, 8, 8);
            g.drawOval(xLoc, yLoc, 8, 8);
            
    }
 
    //direction to start
}