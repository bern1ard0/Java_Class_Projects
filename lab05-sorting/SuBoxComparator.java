import java.util.Comparator;
import structure5.*;
public class SuBoxComparator implements Comparator<Student> {
      /**
     * @pre person1 and person2 cannot be null. 
     * @return returns a positive if Subox of person1 comes after 
     *         Subox of person2. 
     *         returns a negative if Subox of person1 comes before 
     *         Subox of person2
     *         returns 0 if Suboxes are the same. 
     * @param person1 is one of the students. 
     * @param person2 is the other student.
     */
    public int compare(Student person1, Student person2) {
        Assert.pre(person1 != null && person2 != null, "Null Value found");
        return person1.getSuBox() - person2.getSuBox();
    }
}
