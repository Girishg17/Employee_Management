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
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder xmlContent = new StringBuilder();

            while ((line = br.readLine()) != null) {

                xmlContent.append(line);
            }


            String xml = xmlContent.toString();
            String recordTag = "<record>";
            String endRecordTag = "</record>";
            int startIndex = 0;

            while ((startIndex = xml.indexOf(recordTag, startIndex)) != -1) {
                int endIndex = xml.indexOf(endRecordTag, startIndex);
                String record = xml.substring(startIndex, endIndex + endRecordTag.length());
                String firstName = extractValue(record, "firstName");
                String lastName = extractValue(record, "lastname");
                String dateOfBirthStr = extractValue(record, "dateOfBirth");
                String experienceStr = extractValue(record, "experience");
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date dateOfBirth = dateFormat.parse(dateOfBirthStr);
                double experience = Double.parseDouble(experienceStr);


                Employee employee = new Employee(firstName, lastName, dateOfBirth, experience);
                MyCollection.add(employee);

                startIndex = endIndex + endRecordTag.length();
            }
        } catch (IOException | ParseException e) {
            System.out.println(e);
        }
    }

    private String extractValue(String record, String tagName) {
        String startTag = "<" + tagName + ">";
        String endTag = "</" + tagName + ">";
        int startIndex = record.indexOf(startTag) + startTag.length();
        int endIndex = record.indexOf(endTag, startIndex);
        return record.substring(startIndex, endIndex).trim();
    }



    public void write() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {

        } catch (IOException e) {
            System.out.println("Error writing to XML file.");
        }
    }
}
