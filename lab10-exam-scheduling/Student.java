import structure5.*;

/**
 * This is a class student which stores a student's name and the 
 * courses taken by the student.
 */
public class Student{
    protected Vector<String> subjectVector;
    protected String studentName;
    /**
     * This is the constructor of the class student. Takes two
     * parameters.
     * @pre name must not be null. 
     * @param name Student's name. 
     * @param subjectVector Vector of String which stores
     * subjects taken by student.
     */
    public Student(String name, Vector<String> subjectVector) {
        Assert.pre(!name.equals(null), "Name is null");
        this.studentName = name;
        this.subjectVector = subjectVector;
        Assert.post(subjectVector.size() == 4, "Vector of subjects is not 4");
    }
    
    /**
     * Returns name of student.
     * @pre studentName is not null;
     * @return student's Name
     */
    public String getName() {
        return studentName;
    }
    
    /**
     * Returns a vector of student's subjects. 
     * @pre studentVector is exactly of size 4.
     * @return
     */
    public Vector<String> getSubjectVector() {
        return subjectVector;
    }

    /**
     * This method takes in a subject and returns it
     * if it is contained in the file. 
     * @pre subject is not null
     * @param subject a subject.
     * @return subject if contained in Student,
     *         else, null.
     */
    public String getSubject(String subject) {
        Assert.pre(!subject.equals(null),"Subject is null");
        if (subjectVector.contains(subject)) {
            return subject;
        }
        return null;
    }

    /**
     * This method is automatically run when print method
     * is called on Student class.
     * @return String representation of Class Student.
     */
    public String toString() {
        String subject= "";
        String name = studentName; 
        for (String s: subjectVector) {
            subject += s + "\n";
        }
        return "Student Name is: " + name + "\n" + "Subjects are: " + "\n" + subject;
    }

    /**
     * execution of Program tests happens here.
     * @param args not used here. 
     */
    public static void main(String[] args) {
        Vector<String> subjects = new Vector<>(4);
        subjects.add("Physics");
        subjects.add("Math");
        subjects.add("Chem");
        subjects.add("Bio");
        Student num1 = new Student("Bernard", subjects);
        Vector<String> subject = new Vector<>(4);
        subject.add("Physics");
        subject.add("Math");
        subject.add("Chem");
        subject.add("Dance");
        //subject.add("Rodeo"); **uncomment to show assert.
        Student num2 = new Student("Wongibe", subject);
        System.out.println(num1);
        System.out.println(num2);
    }
}
