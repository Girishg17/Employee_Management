package Interface.Implementation;

import Entity.Employee;
import Interface.MyFileHandler;
import Repository.MyCollection;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class JsonFileHandler implements MyFileHandler {
    private String filePath;

    public JsonFileHandler(String filePath) {
        this.filePath = filePath;
    }


//    public void read() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//                String[] parts = line.split(",");
//
//                String firstName = parts[0].trim();
//                String lastName = parts[1].trim();
//                Date dateOfBirth = dateFormat.parse(parts[2].trim());
//                double experience= Double.parseDouble(parts[3].trim());
//                Employee employee = new Employee(firstName, lastName,dateOfBirth,experience);
//                MyCollection.add(employee);
//            }
//        } catch (IOException | ParseException e) {
//            System.out.println(e);
//        }
//    }


    public void read() {
        // Define the accepted date formats
        String[] dateFormats = {
                "MM-dd-yyyy",
                "M-dd-yyyy",
                "M-d-yyyy",
                "MM-d-yyyy"
        };

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line assuming the format is "firstName,lastName,dateOfBirth,experience"
                String[] parts = line.split(",");
                if (parts.length >= 4) { // Ensure there are enough parts
                    String firstName = parts[0].trim();
                    String lastName = parts[1].trim();
                   // System.out.println("lastname"+lastName);
                    String dateString = parts[2].trim();

                    String experienceString = parts[3].trim();
                    experienceString = experienceString.replaceAll("\"experience\":", "").replace("}", "").trim();
                   // System.out.println("eperience"+experienceString);

                    // Validate and parse experience
                    double experience = 0.0;
                    if (!experienceString.isEmpty()) {
                        try {
                            experience = Double.parseDouble(experienceString);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid experience value: " + experienceString);
                            continue; // Skip this entry if experience is invalid
                        }
                    } else {
                        System.out.println("Experience value is empty, skipping entry for: " + firstName + " " + lastName);
                        continue; // Skip this entry if experience is empty
                    }

                    Date dateOfBirth = null;
                    boolean dateParsed = false;

                    // Try each date format until one succeeds
                    for (String format : dateFormats) {
                        try {
                            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                            dateOfBirth = dateFormat.parse(dateString);
                            dateParsed = true;
                            break;
                        } catch (ParseException e) {

                            System.out.println(e);
                        }
                    }

//                    if (!dateParsed) {
//                        System.out.println("Error parsing date: " + dateString);
//                        continue; // Skip this entry if all formats fail
//                    }

                    Employee employee = new Employee(firstName, lastName, dateOfBirth, experience);
                    System.out.println("employeeedata"+employee.firstName+employee.experience+employee.dateOfBirth);
                    MyCollection.add(employee); // Call to add the employee
                } else {
                    System.out.println("Insufficient data in line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file.");
        }
    }
//    public  void read(){
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(new File("mydata.json"));
//    }
    public void write() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write("{\"firstName\":\"Saahil\",\"lastName\":\"faizal\",\"dateOfBirth\":\"17/10/2001\",\"experience\":7}");
            System.out.println("Wrote to JSON file.success");
        } catch (IOException e){

            System.out.println("Error writing to JSON file.");
        }
    }
}
