package sdaScheduler.model;

import java.time.LocalDate;

public class Person implements Comparable<Person>{
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;

    public Person(String firstname, String lastname, LocalDate dateOfBirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }

    public Person() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }


    /*
     * Overriding compareTo method in order to be able to sort instances of Person by lastname
     * Another way to sort would be using a Comparator
     * https://www.geeksforgeeks.org/comparable-vs-comparator-in-java/
     * */
    @Override
    public int compareTo(Person o) {
        // int > 0  this > o
        // int = 0  this = o
        // int < 0  this < o
        return this.lastname.compareTo(o.getLastname());
    }

}
