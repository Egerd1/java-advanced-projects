package sdaScheduler;

import sdaScheduler.model.Group;
import sdaScheduler.model.Student;
import sdaScheduler.model.Trainer;
import sdaScheduler.repository.CentralRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // displayStudentsAlphabetically();

        System.out.println("\n -------------------------------------- \n");

        //   displayGroupWithMaxStudents();

        System.out.println("\n -------------------------------------- \n");

        // displayStudentsYoungerThan25();

        System.out.println("\n -------------------------------------- \n");

        // displayStudentsByTrainer();

        System.out.println("\n -------------------------------------- \n");

        //  displayStudentsWithJavaKnowledge();

        System.out.println("\n -------------------------------------- \n");

        displayGroupWithMaxStudentsWithNoJavaKnowledge();

        System.out.println("\n -------------------------------------- \n");

        //  removeStudentsYoungerThan20FromGroups();

    }

    private static void displayStudentsAlphabetically() {
        System.out.println("The list of students in every group sorted alphabetically by lastname");

        for (Group g : CentralRepository.getGroupList()) {
            System.out.println(g.getName());

            // Set is not sorted. In order for us to be able to sort, we need a list
            // So we copy the set into a list and then sort it using Collections.sort()
            List<Student> students = new ArrayList<>(g.getStudentList());

            // below is only possible because Student implements Comparable
            Collections.sort(students);

            for (Student s : students) System.out.println(s);
        }

    }

    private static void displayGroupWithMaxStudents() {
        System.out.println("The group with the highest number of students");

        // We initially consider the first group as the one with max number of students
        Group groupWithMaxStudents = CentralRepository.getGroupList().get(0);

        // If we identify a group with a higher number of students then we store it in groupWithMaxStudents variable
        for (Group g : CentralRepository.getGroupList()) {
            if (g.getStudentList().size() > groupWithMaxStudents.getStudentList().size()) {
                groupWithMaxStudents = g;
            }
        }

        System.out.println("Group with max students: " + groupWithMaxStudents.getName() + " has " +
                groupWithMaxStudents.getStudentList().size());

//        private static void displayGroupWithMaxNumberOfStudents() {
//            System.out.println("Group with the maximum number of students");
//            CentralRepository.getGroupList().stream().reduce((group, group2) -> group.getStudentList().size() > group2.getStudentList().size() ? group : group2).ifPresent(System.out::println);
//        }
    }

    private static void displayStudentsYoungerThan25() {
        System.out.println("Students younger than 25 from all the groups:");
        for (Group g : CentralRepository.getGroupList()) {
            for (Student s : g.getStudentList()) {
                if (Period.between(s.getDateOfBirth(), LocalDate.now()).getYears() < 25) {
                    System.out.println(s);
                }
            }
        }
//        for (Group g: CentralRepository.getGroupList()) {
//            g.getStudentList().stream().filter(student -> Period.between( student.getDateOfBirth(),LocalDate.now()).getYears() < 25).forEach(System.out::println);
//        }

    }

    private static void displayStudentsByTrainer() {
        System.out.println("Students grouped by trainer:");

        /* Whenever you want to group multiple elements by a common key, consider using a map
         * In this case, Trainer will be the key and the list of students will be the value
         */

        Map<Trainer, List<Student>> studentsByTrainerMap = new HashMap<>();

        for (Group g : CentralRepository.getGroupList()) {
            List<Student> studentsForTrainer = studentsByTrainerMap.get(g.getTrainer());
            if (studentsForTrainer == null) {
                studentsForTrainer = new ArrayList<>(g.getStudentList());
                studentsByTrainerMap.put(g.getTrainer(), studentsForTrainer);
            } else {
                studentsForTrainer.addAll(new ArrayList<>(g.getStudentList()));
            }
        }

        for (Map.Entry<Trainer, List<Student>> entry : studentsByTrainerMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().size() + " students");
            System.out.println(entry.getValue() + "\n");
        }

    }

    private static void displayStudentsWithJavaKnowledge() {
        System.out.println("Display students with previous Java knowledge");

        for (Group g : CentralRepository.getGroupList()) {
            for (Student s : g.getStudentList()) {
                if (s.ishasPreviousJavaKnowledge()) {
                    System.out.println(s);
                }
            }
//                  TEACHER CODE
//            for(Student s: CentralRepository.getStudentList()) {
//                if(s.isHasPreviousJavaKnowledge()) {
//                    System.out.println(s);
//                }
//            }

//            for (Group g : CentralRepository.getGroupList()) {
//                g.getStudentList().stream().filter(student -> !student.isHasPreviousJavaKnowledge()).forEach(System.out::println);
//            }
        }
    }

    public static void displayGroupWithMaxStudentsWithNoJavaKnowledge() {
        System.out.println("Display group where is more students without previous Java knowledge");

        Group groupWhereAreMoreStudentsWithNoJavaKnowledge = CentralRepository.getGroupList().get(0);

        int counter = 0;
        int secondCounter = 0;
        for (Group g : CentralRepository.getGroupList()) {
            for (Student s : g.getStudentList()) {
                if (!s.ishasPreviousJavaKnowledge()) {
                    counter++;
                }
                if (counter > secondCounter){
                    secondCounter = counter;
                    groupWhereAreMoreStudentsWithNoJavaKnowledge = g;
                    counter = 0;
                }else {
                    continue;
                }
            }
        }
        System.out.println("Group where are the most students with no Java knowledge : " + groupWhereAreMoreStudentsWithNoJavaKnowledge.getName());

    }

//                 TEACHER CODE
//    Group groupWithMaxStudentsWithNoJavaKnowledge = null;
//    int maxStudentsWithNoJavaKnowledge = 0;
//
//        for(Group g: CentralRepository.getGroupList()) {
//        int countStudentsWithNoJavaKnowledge = 0;
//        for(Student s : g.getStudentList()) {
//            if(!s.isHasPreviousJavaKnowledge()) {
//                countStudentsWithNoJavaKnowledge++;
//            }
//        }
//        if(countStudentsWithNoJavaKnowledge > maxStudentsWithNoJavaKnowledge) {
//            groupWithMaxStudentsWithNoJavaKnowledge = g;
//            maxStudentsWithNoJavaKnowledge = countStudentsWithNoJavaKnowledge;
//        }
//    }
//
//        System.out.println(groupWithMaxStudentsWithNoJavaKnowledge);
//        System.out.println("Students with no java knowledge: " + maxStudentsWithNoJavaKnowledge);


    //---------------------------------
//   #1             Group groupWithMax = CentralRepository.getGroupList().get(0);
//                long noJava = groupWithMax.getStudentList().stream().filter(student -> !student.isHasPreviousJavaKnowledge()).count();
//
//                for (Group g: CentralRepository.getGroupList()){
//                    if (g.getStudentList().stream().filter(student -> !student.isHasPreviousJavaKnowledge()).count() > noJava){
//                        groupWithMax = g;
//                    }
//                }
//                System.out.println(groupWithMax);


                //----------------------------------------------------------------------
//    #2            CentralRepository.getGroupList()
//                        .stream()
//                        .reduce((group, group2) ->
//                                group.getStudentList().stream().filter(Student::isHasPreviousJavaKnowledge).count() >
//                                        group2.getStudentList().stream().filter(Student::isHasPreviousJavaKnowledge).count() ?
//                                        group2 :
//                                        group)
//                        .ifPresent(System.out::println);


    public static void removeStudentsYoungerThan20FromGroups() {
        System.out.println("Removing students younger than 20: ");
        for (Group g : CentralRepository.getGroupList()) {
            g.removeStudentsYoungerThan20();

            // display the group to make sure students were removed
            System.out.println(g);
        }
    }
}
