import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import java.io.Serializable;

public class StudentList extends ObservableListWrapper<Student>  implements Serializable {

    public StudentList(){
        super(FXCollections.observableArrayList());
    }

    public void addStudent(String id, String fn, String inits, String ln,
                           String course, String email, String homePhone,
                           String mobilePhone) {
        super.add(new Student(id, fn, inits, ln,
                course, email, homePhone, mobilePhone));
    }

    public Student findByID(String id){
        Student temp;
        int indexLocation = -1;
        for (int i = 0; i < super.size(); i++) {
            temp = super.get(i);
            if (temp.getStudentNumber().equals(id)){
                indexLocation = i;
                break;
            }
        }

        if (indexLocation == -1) {
            return null;
        } else {
            return super.get(indexLocation);
        }
    }

    public Student findByLastName(String name){
        Student temp;
        int indexLocation = -1;
        for (int i = 0; i < super.size(); i++) {
            temp = super.get(i);
            if (temp.getLastname().equals(name)){
                indexLocation = i;
                break;
            }
        }

        if (indexLocation == -1) {
            return null;
        } else {
            return super.get(indexLocation);
        }
    }

    public void removeStudent(String id){
        Student toGo = this.findByID(id);
        super.remove(toGo);
    }

}
