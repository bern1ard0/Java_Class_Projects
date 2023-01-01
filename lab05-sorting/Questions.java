import java.util.Scanner;
import structure5.*;
import java.util.Comparator;

public class Questions {
    public static MyVector<Student> ReadIn() {
        MyVector<Student> students = new MyVector<>();
        Scanner r = new Scanner(System.in);
        while(r.hasNextLine()) {
            String name = r.nextLine();
            String address = r.nextLine();
            Long campusNumber = r.nextLong();
            int suBox = r.nextInt();
            Long homeNumber = r.nextLong();
            r.nextLine();
            r.nextLine();
            Student student = new Student(name, address, campusNumber, suBox, homeNumber);
            students.add(student);
        }
        return students;
    }
    public static void question1(MyVector<Student> students) {
        NameComparator n = new NameComparator();
        students.sort(n);
        System.out.println("The first name is: " + students.firstElement().getName());
    }
    public static void question2(MyVector<Student> students) {
        SuBoxComparator s = new SuBoxComparator();
        students.sort(s);
        System.out.println("The smallest SuBox number belongs to: " + students.firstElement().getName());
        System.out.println("The largest SuBox number belongs to: " + students.lastElement().getName());
    }
    public static void question3(MyVector<Student> students) {
        Vowelcomparator v = new Vowelcomparator();
        students.sort(v);
        System.out.println("The student with the most vowels in their name is: " + students.lastElement().getName());
    }
    public static MyVector<Association<String, Integer>> makeAssociation(MyVector<Student> students) {
        MyVector<Association<String, Integer>> addressVector = new MyVector<Association<String, Integer>>();
        for (int i = 0; i < students.size(); i++) {
            String address = students.get(i).getAddress();
            if (!address.equals("UNKNOWN")) {
                Association<String, Integer> a = new Association<String,Integer>(address, 1);
                int location = addressVector.indexOf(a);
                if(location > -1){
                    a = addressVector.get(location);
                    a.setValue(a.getValue() + 1);
                } else {
                    addressVector.add(a);
                }
            }
        }
        return addressVector;
    }
    public static void question4(MyVector<Student> student) {
        MyVector<Association<String, Integer>> addresses = new MyVector<Association<String, Integer>>();
        addresses = makeAssociation(student);
        AddressComparator a = new AddressComparator();
        addresses.sort(a);
        String address = addresses.get(0).getKey();
        System.out.println("The address with the most students is: " + address);
        System.out.println("Students living at this address are: ");
        for(Student s : student) {
            if (s.getAddress().equals(address)) {
                System.out.print(s.getName() + ", ");
            }
        }
    }
    public static MyVector<Association<Long, Integer>> makeAssociation2(MyVector<Student> students) {
        MyVector<Association<Long, Integer>> areaCodes = new MyVector<Association<Long, Integer>>();
        for (int i = 0; i < students.size(); i++) {
            Student student1 = students.get(i);
            if (student1.getHomeNum() != -1) {
                Long areaCode = student1.getHomeNum() / 10000000;
                Association<Long, Integer> a = new Association<Long, Integer>(areaCode, 1);
                int location = areaCodes.indexOf(a);
                if(location > -1){
                    a = areaCodes.get(location);
                    a.setValue(a.getValue() + 1);
                } else {
                    areaCodes.add(a);
                }
            }
        }
    return areaCodes;
    }
    public static void printStudents(MyVector<Student> student, Long areaCode) {
        System.out.println("Students with " + areaCode + "area code: " );
        for (Student s : student) {
            if(areaCode == s.getHomeNum() / 10000000) {
                System.out.print(s.getName() + ", ");
            }
        }
        System.out.println();
    }
    public static void question5 (MyVector<Student> student) {
        MyVector<Association<Long, Integer>> areaCodes = new MyVector<>();
        areaCodes = makeAssociation2(student);
        AreaCodeComparator a = new AreaCodeComparator();
        areaCodes.sort(a);
        System.out.println("Top ten area codes: ");
        for(int i = 0; i < 10; i++) {
            System.out.println(areaCodes.get(i).getKey());
        }
        for (int i = 0; i < 10; i++) {
        printStudents (student, areaCodes.get(i).getKey());
        }
    }
    public static void main(String[] args) {  
        MyVector<Student> students = new MyVector<>();
        students = ReadIn();
        question1(students);
        question2(students);
        question3(students);
        question4(students);
        System.out.println();
        question5(students);
    }
}
