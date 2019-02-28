import java.util.ArrayList;
import java.io.Serializable;

public class Student implements Serializable {

    private String studentNumber;
    private String firstname;
    private String lastname;
    private String initials;
    private String pathway;
    private String homeEmail;
    private String telephoneNo;
    private String mobileNo;

    ArrayList<String> meetingNotes;

    public Student() {
        meetingNotes = new ArrayList<>();
    }

    public Student (String id, String fn, String inits, String ln,
                    String course, String email, String homePhone,
                    String mobilePhone) {
        studentNumber = id;
        firstname = fn;
        initials = inits;
        lastname = ln;
        pathway = course;
        homeEmail = email;
        telephoneNo = homePhone;
        mobileNo = mobilePhone;

        meetingNotes = new ArrayList<>();
    }

    public void addMeetingNote(String notes) {
        meetingNotes.add(notes);
    }

    public ArrayList<String> getMeetingNotes(){
        return meetingNotes;
    }

    public String getStudentNumber(){
        return studentNumber;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getInitials(){
        return initials;
    }

    public void setInitials(String initials){
        this.initials = initials;
    }

    public String getPathway(){
        return pathway;
    }

    public void setPathway(String pathway){
        this.pathway = pathway;
    }

    public String getHomeEmail(){
        return homeEmail;
    }

    public void setHomeEmail(String homeEmail){
        this.homeEmail = homeEmail;
    }

    public String getTelephoneNo(){
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo){
        this.telephoneNo = telephoneNo;
    }

    public String getMobileNo(){
        return mobileNo;
    }

    public void setMobileNo(String mobileNo){
        this.mobileNo = mobileNo;
    }

    public String toString(){
        return studentNumber + ": " + lastname + ", " + firstname + " " + initials;
    }
}
