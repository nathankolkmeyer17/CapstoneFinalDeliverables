package capstone;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
/**
 * 
 * @author nkolk
 * class to get file input and perform functions on
 */
public class FileIO {

	public static Set<Point> readFile(String filename, Set<Point> setp) {
		try {
	        BufferedReader in = new BufferedReader(
	                               new FileReader(filename));
	        String str;
	        
	        while ((str = in.readLine())!= null) {
	            String[] ar=str.split(",");
	            int xint = Integer.parseInt(ar[0]);
	            int yint = Integer.parseInt(ar[1]);
	            Point thisPoint = new Point(xint, yint);
				setp.add(thisPoint);
			
	        }
	        in.close();
	    } catch (Exception e) {
	        System.out.println("File Read Error");
	    }
		return setp;
	}
	
	
	/////////////////////
    public static List<Edge> otherMWT(String fileName) throws FileNotFoundException{
        BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
        List<Edge> listotherEdge = new ArrayList<Edge>();
        String lines = "";
        try {
            while((lines=bufReader.readLine())!=null) {
                lines = lines.replace("(","");
                lines = lines.replace(")", "");
                String[] data = lines.split(",");
                int x1 = Integer.parseInt(data[0]);
                int x2 = Integer.parseInt(data[2]);
                int y1 = Integer.parseInt(data[1]);
                int y2 = Integer.parseInt(data[3]);
                
                Edge edge = new Edge(new Point(x1,y1),new Point(x2,y2));
                listotherEdge.add(edge);
                
            }
            bufReader.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listotherEdge;
    }
//  String fileOutName = MyCanvas.str.substring(0,4);
//  
//  try {
//      PrintWriter writer = new PrintWriter("Results"+fileOutName+ ".txt", "UTF-8");
//      List<Edge> tempList = new ArrayList<Edge>(mwtEdges);
//      for(Edge newEdg : tempList) {
//      
//          writer.print(newEdg.getPoint1().toString()+","+newEdg.getPoint2().toString());
//          if(!tempList.get(tempList.size()-1).equals(newEdg)) {
//              writer.print("\n");
//          }
//      }
//  
//      writer.close();
//      
//  }
//catch (IOException e) {
//  e.printStackTrace();
//}
}
