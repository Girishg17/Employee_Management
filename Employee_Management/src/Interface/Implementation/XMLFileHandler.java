package Interface.Implementation;

import Entity.Employee;
import Interface.MyFileHandler;
import Repository.MyCollection;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XMLFileHandler implements MyFileHandler {
    private String filePath;

    public XMLFileHandler(String filePath) {
        this.filePath = filePath;
    }


    public void read() {
        Employee employee = new Employee();
        List<Employee> tempList = new ArrayList<>();

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


//                Employee employee = new Employee(firstName, lastName, dateOfBirth, experience);
//                MyCollection.add(employee);

                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setDateOfBirth(dateOfBirth);
                employee.setExperience(experience);


                tempList.add(employee);

                startIndex = endIndex + endRecordTag.length();
            }
            MyCollection.addAll(tempList);
        } catch (IOException e) {
            throw new RuntimeException("Error While Reading File",e);
        }
        catch (ParseException e){
            System.out.println("Caught ParseException: " + e.getMessage());
            throw new RuntimeException("Date parsing failed", e);
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
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/girishggonda/Desktop/output.xml"))) {
            Employee person;
            for (int i = 0; i < 100; i++) {
                person = MyCollection.get();
                if(person!=null) {
                    writer.write("  <record>\n    <firstName>" + person.firstName + "</firstName>\n    <lastName>" + person.lastName + "</lastName>\n    <dateOfBirth>" + dateFormat.format(person.dateOfBirth) + "</dateOfBirth>\n    <experience>" + (int) person.experience + "</experience>\n  </record>\n");
                }
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
