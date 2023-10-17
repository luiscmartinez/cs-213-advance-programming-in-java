//Libraries
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

class Main {
    public static void main(String[] args)throws IOException {
        Scanner keyboard = new Scanner(System.in);
        int menueSelect = 0;
        do {
            menueSelect = DisplayMenue();
            switch(menueSelect){
                case 1:
                    System.out.println("Add Student");
                    StudentFileManager e = new StudentFileManager("C:\\Users\\acast\\OneDrive\\Documents\\GitHub\\student.txt");
                    System.out.print("ID:");
                    String SID = keyboard.nextLine();
                    if(e.GetStudent(SID) != null){
                        System.out.print("First Name: "); 
                        String firstName = keyboard.nextLine(); 
                        System.out.print("Last Name: "); 
                        String lastName = keyboard.nextLine(); 
                        System.out.print("Street Address: "); 
                        String stAddress = keyboard.nextLine(); 
                        System.out.print("City: "); 
                        String City = keyboard.nextLine(); 
                        System.out.print("State: "); 
                        String state = keyboard.nextLine(); 
                        System.out.print("Zip Code:"); 
                        String zipCode = keyboard.nextLine();
                        e.AddStudent(SID,firstName,lastName,stAddress,City,state,zipCode);
                    }
                    else
                        System.out.println("Error: Student already exist");
                    break;
                case 2:
                    System.out.println("Edit Student");
                    break;
                case 3:
                    System.out.println("Add Course");
                    break; 
                case 4:
                    System.out.println("Edit Course");
                    break;
                case 5:
                    System.out.println("Add Enrollment ");
                    break; 
                case 6:
                    System.out.println("Edit Enrollment");
                    break; 
                case 7:
                    System.out.println("Display Student");
                    break; 
                case 8:
                    System.out.println("Display Course");
                    break; 
                case 9:
                    System.out.println("Display Enrollment");
                    break; 
                case 10:
                    System.exit(0);
                default:
                    System.out.println("Error: Please Enter a valid Selction");
            }
        } while(menueSelect != 10);
        System.out.println("***********Thank You***********");
    }
    public static int DisplayMenue(){ 
        Scanner keyboard = new Scanner(System.in); 
        System.out.println("*********Menu********* \n 1.Add student"
                                          + "\n 2.Edit Student"
                                          + "\n 3.Add Course"
                                          + "\n 4.Edit Course"
                                          + "\n 5.Add Enrollment"
                                          + "\n 6.Edit Enrollment"
                                          + "\n 7.Display Student"
                                          + "\n 8.Display Course."
                                          + "\n 9.Display Enrollment"
                                          + "\n10.Exit\n**********************"); 
        int menuSelect = keyboard.nextInt(); 
        keyboard.nextLine(); //skip to next line   
        return menuSelect;  
    }     
}
class StudentFileManager{
    
    
    ArrayList<Student> student = new ArrayList<Student>();// arrayList<Student>
  
    StudentFileManager(String filename)throws IOException{//constuctor //cs136
        File file = new File (filename); //Open Studen File
        if (file.exists()){
            Scanner FileScanner = new Scanner(file); //Create File scanner
            //Read File line by line
            while (FileScanner.hasNext()){ 
                String line = FileScanner.nextLine();//read next line
                String[] s = line.split(",");//Split line into a string array
                String id = s[0];
                //Asign the element of array to variables
                String firstname = s[1];
                String lastname = s[2];
                String address = s[3];
                String city = s[4];
                String state = s[5];
                String zip = s[6];
                //Create Student object using variables
                Student stud = new Student(id, firstname, lastname, address, city, state, zip);
                student.add(stud);//Add Student Object to array list
            }
        }
        else{
            System.out.println("Error: File does not exist");
        }
    }
    Student GetStudent(String id ){
        for(int i = 0; i < student.size(); i++){
            Student current = student.get(i);
            String ID = current.getID();
            if (ID.equals(id)){
                System.out.print(ID);         
                return current;
            }    
        }
        return null; 
    }
    boolean AddStudent(String ID, String FirstName,String LastName, String Address, String City, String State, String Zip){
        if (GetStudent(ID) == null){
            Student stud = new Student(ID, FirstName, LastName, Address, City, State, Zip);
            student.add(stud);
            System.out.println("Student has been added");
            return true;
        }
        else {
            System.out.println("Student Already Exists");
            return false;
        }
    }
    boolean updateStudent(String id, String firstname, String lastname, String address, String city, String state, String zip){
        if (GetStudent(id) != null){
            System.out.println("Student Exists");
            Student stud = GetStudent(id);
            stud.setFirstName(firstname);
            stud.setLastName(lastname);
            stud.setAddress(address);
            stud.setCity(city);
            stud.setState(state);
            stud.setZip(zip);
            return true;
        }
        System.out.println("Student Does Not Exist");
        return false;
    }
}
class CourseFileManager{
    String filename = "course.txt";
    File file = new File (filename); //Open course File
    Scanner FileScanner = new Scanner(file); //Create File scanner
    ArrayList<Course> courses = new ArrayList<Course>();
    
    //Constructor
    CourseFileManager(String filename)throws IOException{
        //Read File line by line
        while (FileScanner.hasNext()){ 
            String line = FileScanner.nextLine();//read next line
            String[] c = line.split(",");//Split line into a string array
            //Asign the element of array to variables
            String cid = c[0];
            String courseName = c[1];
            String courseDescrip = c[2];
        
            //Create Course object using variables
            Course cour = new Course(cid, courseName, courseDescrip);
            courses.add(cour);//Add Course Object to array list
        }
    }
    boolean AddCourse(String CID, String courseName, String courseDescrip){
        if (GetCourse(CID) == null){
            Course cour = new Course(CID, courseName, courseDescrip);
            courses.add(cour);
            System.out.println("Course has been added");
            return true;
        }
        else {
            System.out.println("Course Already Exists");
            return false;
        }
    }
    Course GetCourse(String cid ){
        for(int i = 0; i < courses.size(); i++){
            Course current = courses.get(i);
            String ID = current.getID();
            if (ID.equals(cid)){
                System.out.print(ID);         
                return current;
            }    
        }
        return null; 
    }
    boolean updateCourse(String cid, String courName, String courseDescription){
        if (GetCourse(cid) != null){
            System.out.println("Course Exists");
            Course cour = GetCourse(cid);
            cour.setID(cid);
            cour.setName(courName);
            cour.setDescription(courseDescription);
            System.out.println("Course Has Been Updated");
            return true;
        }
        System.out.println("Course Does Not Exist");
        return false;
    }
}
class EnrollmentFileManager{
    String filename = "enrollment.txt";
    File file = new File (filename); //Open Studen File
    Scanner FileScanner = new Scanner(file); //Create File scanner
    ArrayList<Student> student = new ArrayList<Student>();// arrayList<Student>

    //Constructure
    EnrollmentFileManager(String filename)throws IOException{

    }

}
class Student{
    String ID, FirstName, LastName, Address, City, State, Zip;
    //Constructor
    Student(String ID, String FirstName,String LastName, String Address, String City, String State, String Zip){
        this.ID = ID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Address = Address;
        this.City = City;
        this.State = State;
        this.Zip = Zip;
    }
    void setID(String SID){
        ID = SID;
    }
    void setFirstName(String firstName){
        FirstName = firstName;
    }
    void setLastName(String lastName){
        LastName = lastName;
    }
    void setAddress(String address){
        Address = address;
    }
    void setCity(String city){
        City = city;
    }
    void setState(String state){
        State = state;
    }
    void setZip(String zip){
        Zip = zip;
    }
    String getID(){
        return ID;
    }
}
class Course{
    String courseID, name, description;
    Course(String courseID, String name, String description){
        this.courseID = courseID;
        this.name = name;
        this.description = description;
    }
    void setID(String courseID){
        this.courseID = courseID;
    }
    void setName(String name){
        this.name = name;
    }
    void setDescription(String description){
        this.description = description;
    }
    String getID(){
        return courseID;
    }
}
class Enrollment{
    String SID, CID, year, semester;
    Enrollment(String SID, String CID, String year, String semester){
        this.SID = SID;
        this.CID = CID;
        this.year = year;
        this.semester = semester;
    }
    void setCID(String CID) {
        this.CID = CID;
    }
    void setSID(String SID){
        this.SID = SID;
    }
    void setYear(String year) {
        this.year = year;
    }
    void setSemester(String semester) {
        this.semester = semester;
    }
    String getSID() {
        return SID;
    }
    String getCID() {
        return CID;
    }  
}
