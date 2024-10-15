package Interface.Implementation;

import Entity.Employee;
import Interface.MyFileHandler;
import Repository.MyCollection;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XMLFileHandler implements MyFileHandler {
    private String filePath;

    public XMLFileHandler(String filePath) {
        this.filePath = filePath;
    }


    public void read() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
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
            bw.write("<firstname>saahil <lastname>faizal<dateofbirth>17/10/2001<experience>10\n");
            System.out.println("Wrote to XML file.");
        } catch (IOException e) {
            System.out.println("Error writing to XML file.");
        }
    }
}
