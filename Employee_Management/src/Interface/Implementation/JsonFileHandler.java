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
        Employee employee = new Employee();
        List<Employee> tempList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {

                line = line.trim().replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("\"", "");
                String[] parts = line.split(",");
                Date dateOfBirth;
                double experience;

                // Extract values from the parts
                for (String part : parts) {
                    String[] keyValue = part.split(":");
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim();
                        String value = keyValue[1].trim();

                        switch (key) {
                            case "firstName":
                                employee.setFirstName(value);
                                break;
                            case "lastname":
                                employee.setLastName(value);
                                break;
                            case "dateOfBirth":

                                dateOfBirth = dateFormat.parse(value);
                                employee.setDateOfBirth(dateOfBirth);
                                break;
                            case "experience":
                                value = value.replaceAll("[^0-9.]", "").trim(); // Remove any non-numeric characters
                                experience = Double.parseDouble(value);
                                employee.setExperience(experience);
                                break;
                        }
                    } else {
                        System.out.println("Incomplete records...");
                    }
                }


//                Employee employee = new Employee(firstName, lastName, dateOfBirth, experience);
//                MyCollection.add(employee);


                tempList.add(employee);
            }
            MyCollection.addAll(tempList);
        } catch (ParseException e) {
            System.out.println("Caught ParseException: " + e.getMessage());
            throw new RuntimeException("Date parsing failed", e);

        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());
            throw new RuntimeException("Error in Reading Jsonfile", e);
        }


    }


    public void write() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/girishggonda/Desktop/output.json"))) {
            writer.write("[");
            Employee person;
            for (int i = 0; i < 100; i++) {
                person = MyCollection.get();
//                System.out.println("lastname ->"+person.lastName);
                String jsonRecord = String.format(
                        "{\"firstName\":\"%s\", \"lastName\":\"%s\", \"dateOfBirth\":\"%s\", \"experience\":%d}",
                        person.getFirstName(),
                        person.getLastName(),
                        new SimpleDateFormat("MM/dd/yyyy").format(person.getDateOfBirth()),
                        (int) person.getExperience()
                );

                writer.write(jsonRecord);

                if (i < 99) {
                    writer.write(",");
                }
            }
            writer.write("]");
        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());
            throw new RuntimeException("Error in writing JsonFile", e);
        }
    }

}
