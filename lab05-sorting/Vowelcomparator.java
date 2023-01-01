import java.util.Comparator;
import structure5.*;
public class Vowelcomparator implements Comparator<Student> {
     /**
     * @pre Person1 and person 2 cannot be null
     * @return returns a positive if address of person1 comes after 
     *         address of person2. 
     *         returns a negative if address of person1 comes before 
     *         address of person2.
     *         returns 0 if addresses are the same. 
     * @param person1 is one of the students. 
     * @param person2 is the other student.
     */
    public int compare(Student person1, Student person2) {
        Assert.pre(person1 != null && person2 != null, "Null Value found");
        int count1 = 0; //vowel count for person 1;
        int count2 = 0; //vowel count for person2;
        String name1 = person1.getName(); //name for person 1;
        String name2 = person2.getName(); //name for person 2;
        for (int i = 0; i < name1.length(); i++){ //iterating over each character in name 1
            char char1 = Character.toLowerCase(name1.charAt(i));
            if(char1 == 'a' || char1 ==  'e' || char1 ==  'i' 
            || char1 == 'o' || char1 ==  'u') {
                count1++;
            }
        }
        for (int i = 0; i < name2.length(); i++){ //iterating over each character in name 2
            char char2 = Character.toLowerCase(name2.charAt(i));
            if(char2 == 'a' || char2 ==  'e' || char2 ==  'i' 
            || char2 == 'o' || char2 ==  'u') {
                count2++;
            }
        }
        return count1 - count2;
    }
}
