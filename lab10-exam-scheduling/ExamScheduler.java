import java.util.Iterator;
import java.util.Scanner;
import structure5.*;
/**
 * The ExamSceduler class builds a vector of timeSlots
 * from an undirected graphlist of courses as vertices.
 */
public class ExamScheduler {
    protected static Vector<Vector<String>> allTimeSlots = new Vector<>(); //vector of vector of timeslots. 
    protected static Vector<Student> studentInfo = new Vector<>(); //vector of students.
    
    /**
     * Reads in file and returns a vector of all student classes formed
     * from file. 
     * @return Vector of all students.
     * @bigO n
     */
    public static Vector<Student> studentInfoCreator() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String name = in.nextLine();
            Vector<String> subjects = new Vector<>();
            for (int i = 0; i < 4; i++) {
                subjects.add(in.nextLine());
            }
            Student student = new Student(name, subjects);
            studentInfo.add(student);
        }
        return studentInfo;
    }

    /**
     * Builds an undirected graphlist from Vector of Students. It uses courses as vertices
     * and also creates the appropriate edges among them.
     * @pre studentVector is not empty.
     * @param studentVector Vector of Students. 
     * @return Undirected Graphlist.
     * @bigO n^2.
     */
    public static GraphListUndirected<String, Integer> graphBuilder(Vector<Student> studentVector) {
        Assert.pre(!studentVector.isEmpty(),"Student Vector is empty");
        GraphListUndirected<String, Integer> examGraph = new GraphListUndirected<>();
        for (Student s: studentVector){ //iterating over vector of students.
            for(String node: s.getSubjectVector()){ //iterating over each subject in Vector of Subjects.
                if (!examGraph.contains(node)) {
                    examGraph.add(node);
                }
            }
            for(int i = 0; i < s.getSubjectVector().size(); i++) { //traversing through Suject Vector to create Edges.
                for(int j = i+1; j < s.getSubjectVector().size(); j++) {
                    examGraph.addEdge(s.getSubjectVector().elementAt(i), s.getSubjectVector().elementAt(j), 1);
                }
            }
        }
        return examGraph;
    }

    /**
     * Returns a vector of vector of timeslots(String) from examGraph.
     * @pre examGraph is not null. 
     * @param examGraph undirected list created from vector of students.
     * @return Vector of Vector of timeslots.
     */
    public static Vector<Vector<String>> timeSlot(GraphListUndirected<String, Integer> examGraph) {
        while(!examGraph.isEmpty()){
            Vector<String> slot = new Vector<>();
            Iterator<String> iter = examGraph.iterator(); //creating iterator. 
            slot.add(iter.next());
            while(iter.hasNext()) {
                String course = iter.next();
                Boolean conflict = false;
                for (int i = 0; i < slot.size(); i++) {
                    if (examGraph.containsEdge(course, slot.get(i))) {
                        conflict = true; //if two vertices have an edge, don't add to slot.
                    }
                }
                if(!conflict) {
                    slot.add(course); //else add to slot.
                }
            }
            allTimeSlots.add(slot);
            for (int i = 0; i < slot.size(); i++) {
                examGraph.remove(slot.get(i)); //remove already traversed courses from graph.
            }
        }
        return allTimeSlots; 
    }
    
    //----------------------------------///
    //extension 1: Print final course ordered by course name and print students taking that course.
    /**
     * Returns string representing the students taking each given course.
     * Makes use of hashtables.
     * @return String representing the staudents taking each given course.
     */
    public static String studentsTakingEachClass() {
        String text = "";
        Hashtable<String, Vector<String>> table = new Hashtable<>();
        for (Student a: studentInfo) { //looping through vector of students.
            for(String subject: a.getSubjectVector()) { //looping through vector of subjects.
                if(table.containsKey(subject)) { //if table contains table, update table.
                    table.get(subject).add(a.getName());
                } else {                        //add subject and name of student to table.
                    Vector<String> names = new Vector<>();
                    names.add(a.getName());
                    table.put(subject, names);
                }
            }
        }
        //string representation of table.
        for(String course: table.keySet()) {
            String row = "";
            for(int i = 0; i < table.get(course).size(); i++) {
                row += " " + table.get(course).get(i) + ",";
            }
            text += course + ": " + row + "\n";
        }
        return text;
    }

    /**
     * -------prints in sorted order---------
     * This method is automatically run when print method
     * is called on ExamScheduler
     * @return String representation of Class ExamScheduler.
     */
    public String toString() {
        String text = "";
        for(int i = 0; i < allTimeSlots.size(); i++) {
            OrderedVector<String> order = new OrderedVector<>();
            for(int j = 0; j < allTimeSlots.get(i).size(); j++) {
                order.add(allTimeSlots.get(i).get(j));
            }
            String row = "";
            Iterator<String> it = order.iterator();
            while(it.hasNext()) {
                row += it.next() + " ";
            }
            text += "Slot " + (i+1) + ":" + row + "\n";
        }
        return text;
    }

    /**
     * execution of Program happens here. 
     * @param args not used.
     */
    public static void main(String[] args) {
        ExamScheduler exams = new ExamScheduler();
        Vector<Student> studentVector = studentInfoCreator();
        GraphListUndirected<String, Integer> examGraph = graphBuilder(studentVector);
        timeSlot(examGraph);
        System.out.println(exams.toString());
        System.out.println(studentsTakingEachClass());
    }
}
