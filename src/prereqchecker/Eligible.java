package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }

        //ADJ CODE
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
        //ELIGIBLE CODE 
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);

        ArrayList<String> collection = new ArrayList<String>();
        HashSet<String> tot = new HashSet<String>();
        int numofCourses = StdIn.readInt();
        int count = 0;

        while(count < numofCourses){
            collection.add(StdIn.readString());
            count++;
        }
        tot = fullCourse.whatEls(collection);
        for(String key: tot){
            StdOut.println(key);
        }
/*
        int numofCourses = StdIn.readInt();
        int count = 0;
        ArrayList<String> coursesTaken = new ArrayList<String>();
        while(count < numofCourses){
            coursesTaken.add(StdIn.readString());
            count++;
        }

        /*
        ArrayList<String> finalList = new ArrayList<String>();
        for(String courseID:fullCourse.getMap().keySet()){
            if(fullCourse.everything(coursesTaken).contains(courseID) == false){
                for(int i =0; i < fullCourse.getMap().get(courseID).size(); i++){
                    if(fullCourse.everything(coursesTaken).contains(fullCourse.getMap().get(courseID).get(i)) == true) {
                        finalList.add(courseID);
                    }
                }
            }
        }

        HashSet<String> all = fullCourse.everything(coursesTaken);
        ArrayList<String> finalList = new ArrayList<String>();
        for(String courseID:fullCourse.getMap().keySet()){
            if(all.contains(courseID) == false && fullCourse.canTake(courseID, all) == true){
                StdOut.println(courseID);
            }
        }
        */
        /*for(int i = 0; i < finalList.size(); i++){
            StdOut.println(finalList.get(i));
        }*/
        //HashSet<String> all = fullCourse.everything(coursesTaken);


    }
}
