import java.util.Comparator;
import structure5.*;
public class AreaCodeComparator implements Comparator<Association<Long, Integer>> {
    /**
     * @pre a1 and a2 cannot be null
     * @param a1 first association 
     * @param a2 second association
     * @return returns difference between value of both associations. 
     */
    public int compare(Association<Long, Integer> a1, Association<Long, Integer> a2) {
        Assert.pre(a1 != null && a2 != null, "Null Value found");
        int num1 = a1.getValue(); //value of a1
        int num2 = a2.getValue(); //value of a2
        return num2 - num1;   //to sort in decending order. 
    }
}
