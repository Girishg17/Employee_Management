package Entity;

import java.util.Date;

public class Employee {
    public String firstName;
    public  String lastName;
    public Date dateOfBirth;
    public double experience;
    public Employee(){

    }

    public  Employee(String firstName,String lastName,Date dateOfBirth,double experience){
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=dateOfBirth;
        this.experience=experience;
    }

    public Employee(Employee employee) {
        // Copy fields from the provided employee instance
        this.firstName = employee.firstName;
        this.lastName = employee.lastName;
        this.dateOfBirth = employee.dateOfBirth;
        this.experience = employee.experience;
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

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }
    public  void  setLastName(String lastName){
        this.lastName=lastName;
    }
    public  void  setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth=dateOfBirth;

    }
    public  void  setExperience(double experience){
        this.experience=experience;
    }
}
