package Interface.Implementation;

import Interface.MyFileHandler;

import java.io.*;

public class CSVFileHandler implements MyFileHandler {
    private String filePath;


    public CSVFileHandler(String filePath) {
        this.filePath = filePath;
    }

    public void read() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("CSV Data: " + line);
            }
        } catch (IOException e) {
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


