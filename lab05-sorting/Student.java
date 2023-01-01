import structure.Assert;

public class Student {
	private String studentName;
	private String campusAddress;
	private long campusPhoneNum;
	private int suBox;
	private long homePhoneNum;
	public Student(String studentName, String campusAddress, long campusPhoneNum, int suBox, long homePhoneNum){
		this.studentName = studentName;
		this.campusAddress = campusAddress;
		this.campusPhoneNum = campusPhoneNum;
		this.suBox = suBox;
		this.homePhoneNum = homePhoneNum;
	}
    /**
     * @pre must be a string.
     * @param newName Name of student
     */
    public void setName(String name) {
        this.studentName = name;
    }
    /**
     * @pre must be a valid student name
     * @return 
     */
    public String getName(){
        return studentName;
    }
    /**
     * @pre must be a string
     * @param address address of student
     */
    public void setAddress(String address) {
        this.campusAddress = address;
    }
    /**
     * 
     * @return campus address of student
     */
    public String getAddress(){
        return campusAddress;
    }
    /**
     * @pre number must be a long
     * @param number phone number of student 
     */
    public void setCampusNum(long campusNum) {
        this.campusPhoneNum = campusNum;
    }
    /**
     * 
     * @return returns campus phone num of student
     */
    public long getCampusNum() {
        return campusPhoneNum;
    }
    /**
     * @pre suBox must be an int
     * @param suBox student SuBox Number
     */
    public void setSuBox(int suBox) {
        this.suBox = suBox;
    }
    /**
     * 
     * @return
     */
    public int getSuBox() {
        return suBox;
    }
    /**
     * @pre homeNum must be a long 
     * @param homeNum student homenumber
     */
    public void setHomeNum(long homeNum) {
        this.homePhoneNum = homeNum;
    }
    /**
     * 
     * @return returns student homenumber
     */
    public long getHomeNum() {
        return homePhoneNum;
    }
}