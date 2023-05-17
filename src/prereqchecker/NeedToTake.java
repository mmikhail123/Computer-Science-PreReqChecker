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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

        StdIn.setFile(args[0]);
        
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
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);
        
        ArrayList<String> collection = new ArrayList<String>();
        HashSet<String> tot = new HashSet<String>();
        
        String targetCourse = StdIn.readString();

        int num = StdIn.readInt();
        int count = 0;
        while(count < num){
            collection.add(StdIn.readString());
            count++;
        }

        tot = fullCourse.needtoTake(targetCourse, collection);
        for(String key: tot){
            StdOut.println(key);
        }
    }
}
