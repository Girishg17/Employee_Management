package Entity;

import java.util.Date;

public class Employee {
    public String firstName;
    public  String lastName;
    public Date dateOfBirth;
    public double experience;

    public  Employee(String firstName,String lastName,Date dateOfBirth,double experience){
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=dateOfBirth;
        this.experience=experience;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }
    public double getExperience(){
        return experience;
    }

}
