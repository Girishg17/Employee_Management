package Interface.Implementation;

import Interface.MyFileHandler;

import java.io.*;

public class JsonFileHandler implements MyFileHandler {
    private String filePath;

    public JsonFileHandler(String filePath) {
        this.filePath = filePath;
    }


    public void read() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("JSON Data: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading JSON file.");
        }
    }


    public void write() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write("{\"firstName\":\"Saahil\",\"lastName\":\"faizal\",\"dateOfBirth\":\"17/10/2001\",\"experience\":7}");
            System.out.println("Wrote to JSON file.success");
        } catch (IOException e){

            System.out.println("Error writing to JSON file.");
        }
    }
}
