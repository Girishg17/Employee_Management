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

public class CSVFileHandler implements MyFileHandler {
    private String filePath;


    public CSVFileHandler(String filePath) {
        this.filePath = filePath;
    }

    public void read() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Employee employee = new Employee();
        List<Employee> tempList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");


                if (parts.length != 4) {
                    System.out.println("Skipping line due to insufficient data: " + line);
                    continue;
                }

                String firstName = parts[0].trim();
                if (firstName.equals("firstName")) {
                    continue; // Skip header line
                }

                // Extract lastName, dateOfBirth, and experience
                String lastName = parts[1].trim();
                String date = parts[2].trim();
                Date dateOfBirth = dateFormat.parse(date);
                double experience = Double.parseDouble(parts[3].trim());


                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setDateOfBirth(dateOfBirth);
                employee.setExperience(experience);

                tempList.add(new Employee(employee));
            }

            // Add all employees to MyCollection at once
            MyCollection.addAll(tempList);

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (ParseException e) {
            System.out.println("ParseException: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public void write() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/girishggonda/Desktop/output.csv"))) {
            writer.write("firstName,lastName,dateOfBirth,experience\n"); // Header

            Employee person;
            for (int i = 0; i < 100; i++) {
                person = MyCollection.get();

                assert person != null;
                String csvRecord = String.format(
                        "%s,%s,%s,%d\n",
                        person.getFirstName(),
                        person.getLastName(),
                        dateFormat.format(person.getDateOfBirth()),
                        (int) person.getExperience()
                );

                writer.write(csvRecord);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


