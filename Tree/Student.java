public class Student implements Comparable<Student>{
    private int studentID;
    private String studentLastName;
    private String homeDepartment;
    private String studentProgram;
    private int studentYear;

    //Constructors
    public Student(){
        this.studentID = 0;
        this.studentLastName = null;
        this.homeDepartment = null;
        this.studentProgram = null;
        this.studentYear = 0;
    }

    public Student(String inputData){
        this.studentID = Integer.parseInt(inputData.substring(0, 7));
        this.studentLastName = inputData.substring(7, 32);
        this.homeDepartment = inputData.substring(32, 36);
        this.studentProgram = inputData.substring(36, 40);
        this.studentYear = Integer.parseInt(inputData.substring(40));
    }

    //Getter Methods
    public int getStudentID(){
        return this.studentID;
    }

    public String getStudentLastName(){
        return this.studentLastName;
    }

    public String getHomeDepartment(){
        return this.homeDepartment;
    }

    public String getStudentProgram(){
        return this.studentProgram;
    }

    public int getStudentYear(){
        return this.studentYear;
    }

    @Override
    public String toString(){
        return String.format("%s %s %s %s %s", this.studentID, this.studentLastName, this.homeDepartment, this.studentProgram, this.studentYear);
    }

    @Override
    public int compareTo(Student other){
        return this.studentLastName.compareTo(other.getStudentLastName());
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof Student){
            Student otherStudent = (Student) other;
            return (this.getStudentLastName().equals(otherStudent.getStudentLastName()) && 
                this.getStudentID() == otherStudent.getStudentID() && 
                this.getStudentProgram().equals(otherStudent.getStudentProgram()) && 
                this.getStudentYear() == otherStudent.getStudentYear() &&
                this.getHomeDepartment().equals(otherStudent.getHomeDepartment())
            );
        }
        else
            return super.equals(other);
    }
}