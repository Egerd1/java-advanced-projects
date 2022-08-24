package sdaScheduler.model;

import java.time.LocalDate;

public class Student extends Person{
    private boolean hasPreviousJavaKnowledge;

    public Student(String firstname, String lastname, LocalDate dateOfBirth, boolean hasPreviousJavaKnowledge) {
        super(firstname, lastname, dateOfBirth);
        this.hasPreviousJavaKnowledge = hasPreviousJavaKnowledge;
    }

    public Student() {
    }

    public boolean ishasPreviousJavaKnowledge() {
        return hasPreviousJavaKnowledge;
    }

    public void setHasPreviousJavaKnowledge(boolean hasPreviousJavaKnowledge) {
        this.hasPreviousJavaKnowledge = hasPreviousJavaKnowledge;
    }

    @Override
    public String toString() {
        return "Student{" + super.toString() +
                "hasPreviousJavaKnowledge=" + hasPreviousJavaKnowledge +
                '}';
    }
}
