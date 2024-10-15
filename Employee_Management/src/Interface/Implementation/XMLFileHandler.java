package Interface.Implementation;

import Interface.MyFileHandler;

import java.io.*;

public class XMLFileHandler implements MyFileHandler {
    private String filePath;

    public XMLFileHandler(String filePath) {
        this.filePath = filePath;
    }


    public void read() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("XML Data: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading XML file.");
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
