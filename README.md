# Computer-Science-PreReqChecker

These programs when given a list courses and their prerequisites is able to:
- tell you what courses you are eligible to take based on courses already taken
- tell you what courses you still need to take for an upper-level class
- tell you the number of semesters and the courses for each semseter you need to complete in order to take a certain upper-level course
- tell you whether you are able to take an upper-level course with a "yes" or "no" based on courses you have already completed

Java Files
----------
1) AdjList.java - takes a txt file of courses and their prerequisites in order to build a graph representation of the information which later programs use to determine the above mentioned things
2) Eligible.java - given the number of courses and thier course ids, lists out courses you are subsequently eligible to take 
3) NeedToTake.java - given an upper-level course id, number of courses already taken and their course ids, lists out the other courses need to complete in order to take the upper-level one
4) SchedulePlan.java -  given an upper-level course id, number of courses already taken and their course ids, lists out the number of semesters needed to be able to take the upper-level course as well as what courses to take per semester
5) ValidPrereq.java - given an upper-level course id, number of courses already taken and their course ids, tells you whether you are able to the course by a "yes" or "no" based on if the courses mentioned satisifes all the prerequisites of the upper-level course 
------------
In order to compile, run this in terminal: 
```
javac -d bin src/prereqchecker/*.java
```
In order to run each program, run this in terminal:
```
java -cp bin prereqchecker.<program_name>  <course_list_file>.in <input_file>.in <output_file>.out
````
Included is a sample of: 
- course list and their prerequisites (adjlist.in)
- input for Eligible.java
- input for NeedToTake.java
- input for SchedulePlan.java
- input for ValidPrereq.java
