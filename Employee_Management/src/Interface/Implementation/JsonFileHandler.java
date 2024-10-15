package Interface.Implementation;

import Entity.Employee;
import Interface.MyFileHandler;
import Repository.MyCollection;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class JsonFileHandler implements MyFileHandler {
    private String filePath;

    public JsonFileHandler(String filePath) {
        this.filePath = filePath;
    }


    public void read() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Clean the line and parse the fields
                line = line.trim().replace("{", "").replace("}", "").replace("\"", "");
                String[] parts = line.split(",");

                String firstName = "";
                String lastName = "";
                String dateOfBirthStr = "";
                double experience = 0;

                // Extract values from the parts
                for (String part : parts) {
                    String[] keyValue = part.split(":");
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim();
                        String value = keyValue[1].trim();

                        // Clean the value to handle trailing characters

                        switch (key) {
                            case "firstName":
                                firstName = value;
                                break;
                            case "lastName":
                                lastName = value;
                                break;
                            case "dateOfBirth":
                                dateOfBirthStr = value;
                                break;
                            case "experience":
                                value = value.replaceAll("[^0-9.]", "").trim(); // Remove any non-numeric characters
                                experience = Double.parseDouble(value);
                                break;
                        }
                    }
                }

                // Parse the date
                Date dateOfBirth = dateFormat.parse(dateOfBirthStr);

                // Create Employee object
//                System.out.println(firstName+"'"+lastName);
                Employee employee = new Employee(firstName, lastName, dateOfBirth, experience);
                MyCollection.add(employee);
            }
        } catch (IOException | ParseException e) {

            System.out.println(e);
        }
    }



    public void write() {
                Employee person;

                for(int i=0;i<100;i++){
                    person=MyCollection.get();
                    System.out.println(i+"->"+person.firstName);
                }
    }
}
