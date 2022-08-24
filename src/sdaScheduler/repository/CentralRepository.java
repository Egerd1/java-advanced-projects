package sdaScheduler.repository;

import org.apache.commons.lang3.RandomStringUtils;
import sdaScheduler.exception.MaximumNumberOfStudentReached;
import sdaScheduler.model.Group;
import sdaScheduler.model.Student;
import sdaScheduler.model.Trainer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * For simplicity, we're creating a central repository(storage) to store all students, trainers, groups data
 * It would be best practice to have a separate repository for each model class (e.g. StudentRepository, TrainerRepository, ...)
 * */
public class CentralRepository {
    private static List<Student> studentList = new ArrayList<>();
    private static List<Trainer> trainerList = new ArrayList<>();
    private static List<Group> groupList = new ArrayList<>();

    // Using static block to initialize our objects
    static {

        // Initializing students
        // Instead of hardcoding 15 different students, we can google "java random string"
        for (int i = 1; i <= 15; i++) {
            Student student = new Student();
            student.setFirstname(RandomStringUtils.random(5, true, false).toLowerCase());
            student.setLastname(RandomStringUtils.random(6, true, false).toLowerCase());
            student.setDateOfBirth(getRandomDateOfBirthBetween(2005, 2010));
            student.setHasPreviousJavaKnowledge(new Random().nextBoolean());

            studentList.add(student);
        }

        // Initializing trainers
        for (int i = 1; i <= 3; i++) {
            String firstname = RandomStringUtils.random(5, true, false).toLowerCase();
            String lastname = RandomStringUtils.random(6, true, false).toLowerCase();
            LocalDate birthDate = getRandomDateOfBirthBetween(1980, 1990);
            boolean isAuthorised = new Random().nextBoolean();

            Trainer trainer = new Trainer(firstname, lastname, birthDate, isAuthorised);

            trainerList.add(trainer);
        }

        // Initializing groups
        for (int i = 1; i <= 4; i++) {
            Group group = new Group();
            group.setName("Group"+i);  // Group1, Group2, Group3, Group4

            Trainer trainer = trainerList.get(getRandomIntBetween(0, 2));
            group.setTrainer(trainer);

            groupList.add(group);
        }


        // Assign 2-3 students to each group
        try {
            groupList.get(0).addStudent(studentList.get(0));
            groupList.get(0).addStudent(studentList.get(1));
            groupList.get(0).addStudent(studentList.get(2));

            groupList.get(1).addStudent(studentList.get(3));
            groupList.get(1).addStudent(studentList.get(4));
            groupList.get(1).addStudent(studentList.get(5));

            groupList.get(2).addStudent(studentList.get(6));
            groupList.get(2).addStudent(studentList.get(7));
            groupList.get(2).addStudent(studentList.get(8));

            groupList.get(3).addStudent(studentList.get(9));
            groupList.get(3).addStudent(studentList.get(10));
            groupList.get(3).addStudent(studentList.get(11));
            groupList.get(3).addStudent(studentList.get(12));

        } catch (MaximumNumberOfStudentReached e) {
            System.out.println("Cannot add more than 5 students to a group " + e.getMessage());
        }



    } // end of static initializer block


    public static List<Student> getStudentList() {
        return studentList;
    }

    public static List<Trainer> getTrainerList() {
        return trainerList;
    }

    public static List<Group> getGroupList() {
        return groupList;
    }

    /*
     * google "java get random birthdate"
     * */
    private static LocalDate getRandomDateOfBirthBetween(int startYear, int endYear) {
        int day = getRandomIntBetween(1, 28);
        int month = getRandomIntBetween(1, 12);
        int year = getRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }

    private static int getRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }


}
