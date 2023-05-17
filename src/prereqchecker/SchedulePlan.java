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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
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

        //schedule 
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);
        ArrayList<String> collection = new ArrayList<String>();
        String targetCourse = StdIn.readString();
        int num = StdIn.readInt();
        int count = 0;
        while(count < num){
            collection.add(StdIn.readString());
            count++;
        }
        HashMap<Integer, HashSet<String>> schedule = fullCourse.schedulePlan(targetCourse, collection);
        int count5 = 0;

        for (Integer semester : schedule.keySet()) {
            count5++;
            //StdOut.println();
        }
        StdOut.println(count5);
        for (Integer semester : schedule.keySet()) {
            for (String course : schedule.get(semester)) {
                StdOut.print(course + " ");
            }
            StdOut.println();
        }
    }
}
