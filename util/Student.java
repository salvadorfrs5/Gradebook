package util;

public class Student {
    private String firstName;
    private String lastName;
    private int pid;
    private Grade grade;
    
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getPid() {
        return pid;
    }
    public Grade getGrade() {
        return grade;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    } 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    } 
    public void setPid(int pid) {
        this.pid = pid;
    } 
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
