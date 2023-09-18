package nucleus.tutget.qna.domain;

public class Student extends User {
    public Student(String name) {
        super(name, UserType.STUDENT);
    }
}
