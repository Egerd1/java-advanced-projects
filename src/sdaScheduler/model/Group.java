package sdaScheduler.model;

import sdaScheduler.exception.MaximumNumberOfStudentReached;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Group {
    private String name;
    private Trainer trainer;

    /*
     * using Set to make sure the same student doesn't get added twice
     * */
    private Set<Student> studentList = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }


    /*
     * Returning an unmodifiableSet:
     * This will prevent us to try to add a student through group.getStudentList().add(new Student());
     * The add operation will only be available through public addStudent() method
     * */

    /*
     * Whenever you want to return collections in your getter methods, always return a copy of it
     * not the collection itself. This is to avoid manipulation of your object internals.
     * This is a best practice and helps toward 'immutability (the state of being unchangeable)'
     * Please read more about immutability in Java
     * */
    public Set<Student> getStudentList() {
        return Collections.unmodifiableSet(studentList);
    }


    /*
     * Instead of having a setter method for studentList, we define addStudent
     * so that we can throw exception when the maximum number of students is reached
     * this way we'll only have access to studentList through our methods which can control logic
     * */
    public void addStudent(Student s) throws MaximumNumberOfStudentReached {
        if (studentList.size() >= 5) {
            throw new MaximumNumberOfStudentReached();
        } else {
            studentList.add(s);
        }
    }

    /*
     * To remove an element from this set while iterating over it,
     * we can't use for loop cause it throws ConcurrentModificationException
     * To fix it, we have to use iterator
     * */
    public void removeStudentsYoungerThan20() {
//        for (Student s: studentList) {
//            if (Period.between(s.getDateOfBirth(), LocalDate.now()).getYears() < 20) {
//                studentList.remove(s);
//            }
//        }

        Iterator<Student> iterator = studentList.iterator();
        while(iterator.hasNext()) {
            Student st = iterator.next();
            if (Period.between(st.getDateOfBirth(), LocalDate.now()).getYears() < 20) {
                iterator.remove();
            }
        }

    }


    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", trainer=" + trainer +
                ", studentList=" + studentList +
                '}';
    }
}
