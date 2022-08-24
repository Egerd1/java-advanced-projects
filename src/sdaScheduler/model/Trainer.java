package sdaScheduler.model;

import java.time.LocalDate;

public class Trainer extends Person{
    private boolean isAuthorised;

    public Trainer(String firstname, String lastname, LocalDate dateOfBirth, boolean isAuthorised) {
        super(firstname, lastname, dateOfBirth);  // call to super constructor MUST always be the first line in child's constructor
        this.isAuthorised = isAuthorised;
    }

    public Trainer(){}

    public boolean isAuthorised() {
        return isAuthorised;
    }

    public void setAuthorised(boolean authorised) {
        isAuthorised = authorised;
    }

    @Override
    public String toString() {
        return "Trainer{" + super.toString() +
                "isAuthorised=" + isAuthorised +
                '}';
    }
}
