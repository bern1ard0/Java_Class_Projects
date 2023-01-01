import structure5.*;
import java.util.Comparator;
public class NameComparator implements  Comparator<Student> {
    /**
     * @pre person1 and person 2 cannot be null;
     * @return returns a positive if name of person1 comes after 
     *         name of person2. 
     *         returns a negative if name of person1 comes before 
     *         name of person2
     *         returns 0 if names are the same. 
     * @param person1 is one of the students. 
     * @param person2 is the other student.
     */
    public int compare(Student person1, Student person2) {
        Assert.pre(person1 != null && person2 != null, "Null value reached.");
        return person1.getName().compareTo(person2.getName());
    }
}
