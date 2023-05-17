package prereqchecker;
import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }

        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        CoursesAdjacencyListMaker fullCourse = new CoursesAdjacencyListMaker();
        
        int numberofCourses = StdIn.readInt();
        int counter = 0;

        while(counter < numberofCourses){
            String courseID = StdIn.readString();
            fullCourse.addCourse(courseID);
            counter++;
        }

        int numberofPrereq = StdIn.readInt();
        int counter2 = 0;

        while(counter2 < numberofPrereq){
            String course2 = StdIn.readString();
            String prereqID = StdIn.readString();
            fullCourse.addPrereqToCourse(course2, prereqID);
            counter2++;
        }
        
        String adjList = "";
        for(String courseID:fullCourse.getMap().keySet()){
            ArrayList<String> instance = fullCourse.getMap().get(courseID);
            adjList += courseID;
            for(int i = 0; i < instance.size(); i++){
                adjList += " " + instance.get(i);
            }
            adjList += "\n";
        }
        
        StdOut.print(adjList);
    }
}
