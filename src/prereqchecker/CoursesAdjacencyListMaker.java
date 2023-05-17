package prereqchecker;
import java.util.ArrayList;
import java.util.*;

public class CoursesAdjacencyListMaker {
    private HashMap<String, ArrayList<String>> coursesAndPreReqs;
    private HashMap<String, Boolean> marked;

    public CoursesAdjacencyListMaker(){
        coursesAndPreReqs = new HashMap<String, ArrayList<String>>();
        marked = new HashMap<String, Boolean>();
    }

    public void addCourse(String courseID){
        if(coursesAndPreReqs.containsKey(courseID) == false){
            coursesAndPreReqs.put(courseID, new ArrayList<String>());
            marked.put(courseID, false);
        }
    }

    public void addPrereqToCourse(String courseID, String prereqID){
        coursesAndPreReqs.get(courseID).add(prereqID);
    }

    public String coolStuff(){
        String adjList = "";
        for(String courseID: coursesAndPreReqs.keySet()){
            adjList += courseID + "";
            for(int i = 0; i < coursesAndPreReqs.get(courseID).size(); i++){
                adjList += coursesAndPreReqs.get(courseID).get(i) + "";
            }
            adjList = "\n";
        }
        return adjList;
    }

    public HashMap<String, ArrayList<String>> getMap(){
        return coursesAndPreReqs;
    }

    public String isValid(String course1, String course2){
        for(String ID:marked.keySet()){
            marked.put(ID, false);
        }
        //getDiandIn(course2);
        if(getDiandIn(course2).contains(course1)){
            return "NO";
        } else {
            return "YES";
        }
    }

    public void DFS(String courseID, HashSet<String> example){
        for(String c: marked.keySet()){
            marked.put(c, false); 
        }
        for(String preReq:coursesAndPreReqs.get(courseID)){
            if(marked.get(courseID) != true){
                example.add(preReq);
                DFS(preReq, example);
            }
        }
        marked.put(courseID, true);
    }

    public HashSet<String> getDiandIn(String courseID){
        HashSet<String> DiandHi = new HashSet<String>();
        ArrayList<String> preReqList =  coursesAndPreReqs.get(courseID);
        for(int i = 0; i < preReqList.size(); i++){
            DiandHi.add(preReqList.get(i));
            DFS(preReqList.get(i), DiandHi);
        }
        return DiandHi;
    }

    public HashSet<String> everything(ArrayList<String> collection){
        HashSet<String> all = new HashSet<String>();
        for(int i = 0; i < collection.size(); i++){
            all.add(collection.get(i));
            ArrayList<String> preReList = coursesAndPreReqs.get(collection.get(i));
            for(int j = 0; j < preReList.size(); j++){
                all.add(preReList.get(j));
                DFS(preReList.get(j), all);
            }
        }
        return all;
    }

    /*public Boolean checkBigList(String courseID, HashSet<String> all){
        boolean check = true;

        if(coursesAndPreReqs.get(courseID).size() == 0){
            check = true;
        } else {
            for(int i = 0; i < coursesAndPreReqs.get(courseID).size(); i++){
                if(!all.contains(coursesAndPreReqs.get(courseID).get(i))){
                    check = false;
                    break;
                }
            }
        }
        return check;
    }*/
//For the above: might be helpful to make a method that, given a course ID and a HashSet of
// direct and indirect prerequisites, checks every single prerequisite associated with the course ID 
//and sees if it's in the HashSet. If all the course ID's prerequisites are satisfied, then you can take
// the course itself.
    public HashSet<String> whatEls(ArrayList<String> collection){
        HashSet<String> all = new HashSet<String>();
        HashSet<String> finalList = new HashSet<String>();
        for(int i = 0; i < collection.size(); i++){
            all.add(collection.get(i));
            DFS(collection.get(i), all);
        }

        for(String key:coursesAndPreReqs.keySet()){
            boolean check = true;
            if((!all.contains(key))){
                if(haveAllpres(all,key)){
                    check = false;
                }
            }
            if(check == false){
                finalList.add(key);
            }
        }
        return finalList;
    }

    public boolean haveAllpres(HashSet<String> all, String courseID){
        boolean check = true;

        if(coursesAndPreReqs.get(courseID).size() == 0){
            check = true;
        } else {
            for(int i = 0; i < coursesAndPreReqs.get(courseID).size(); i++){
                if(!all.contains(coursesAndPreReqs.get(courseID).get(i))){
                    check = false;
                }
            }
        }
        return check;
    }

    public HashSet<String> needtoTake(String target, ArrayList<String> collection){
        HashSet<String> targetSet = new HashSet<String>();
        HashSet<String> takenSet = new HashSet<String>();
        for(int i = 0; i < collection.size(); i++){
            takenSet.add(collection.get(i));
            DFS(collection.get(i), takenSet);
        }

        for(String key:coursesAndPreReqs.get(target)){
            targetSet.add(key);
            DFS(key, targetSet);
        }   

        ArrayList<String> toRemove = new ArrayList<String>();
        for(String i: targetSet){
            if(targetSet.contains(i) && takenSet.contains(i)){
                toRemove.add(i);
            }
        }
        targetSet.removeAll(toRemove);
       
        return targetSet;
    }

    public HashMap<Integer, HashSet<String>> schedulePlan(String targetCourse, ArrayList<String> collection){
        HashSet<String> need = needtoTake(targetCourse, collection);
        HashMap<Integer, HashSet<String>> canTake = new HashMap<Integer, HashSet<String>>();
        int semCount = 1;
        HashSet<String> prereqNeeded = new HashSet<String>();
        HashSet<String> setCanTake = new HashSet<String>();

        while(!need.isEmpty()){
            for(String key:need){
                //for(String i:coursesAndPreReqs.get(targetCourse)){
                    prereqNeeded = needtoTake(key, collection);
                   // needtoTake(key, collection);
                   // prereqNeeded.add(key);
                   // DFS(key, prereqNeeded);
                    //System.out.print("a");
               // }
                if(prereqNeeded.isEmpty()){
                    setCanTake.add(key);
                }  
            }

            if(!setCanTake.isEmpty()){
                HashSet<String> copysetCanTake = new HashSet<String>();
                for(String i: setCanTake){
                    copysetCanTake.add(i);
                }
                //deep copy of CanTake 

                canTake.put(semCount, copysetCanTake);
                semCount++;

                need.removeAll(copysetCanTake);
                collection.addAll(copysetCanTake);
                setCanTake.clear();
            }
        }
        return canTake;
    }
}
