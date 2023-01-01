import java.util.Comparator;
import structure5.*;
public class AddressComparator implements Comparator<Association<String, Integer>> {
    /**
     * @pre Person 1 and person 2 cannot be null
     * @return returns a positive if name of person1 comes after 
     *         name of person2. 
     *         returns a negative if name of person1 comes before 
     *         name of person2
     *         returns 0 if names are the same. 
     * @param person1 is one of the students. 
     * @param person2 is the other student.
     */
    public int compare(Association <String, Integer> person1, Association <String, Integer> person2) {
        Assert.pre(person1 != null && person2 != null, "Null Value found");
        return person1.getValue() - person2.getValue();

    }

    

}
