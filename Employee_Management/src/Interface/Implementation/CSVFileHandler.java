package Interface.Implementation;

import Entity.Employee;
import Interface.MyFileHandler;
import Repository.MyCollection;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVFileHandler implements MyFileHandler {
    private String filePath;


    public CSVFileHandler(String filePath) {
        this.filePath = filePath;
    }

    public void read() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("csv"+line);
                // Split the line assuming the format is "firstName,lastName"
                String[] parts = line.split(",");

                    String firstName = parts[0].trim();
                    String lastName = parts[1].trim();
                    Date dateOfBirth = dateFormat.parse(parts[2].trim());
                    double experience= Double.parseDouble(parts[3].trim());
                    Employee employee = new Employee(firstName, lastName,dateOfBirth,experience);
                    MyCollection.add(employee);

            }
        } catch (IOException | ParseException e) {
            System.out.println("Error reading CSV file.");
        }
    }

    public void write() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write("saahil,faizal,17/10/2001\n"); // Writing sample data
            System.out.println("successs...Wrote to CSV file.");
        } catch (IOException e) {
            System.out.println("Error writing to CSV file.");
        }
    }
}


