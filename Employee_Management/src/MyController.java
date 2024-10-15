import Interface.Implementation.CSVFileHandler;
import Interface.Implementation.JsonFileHandler;
import Interface.Implementation.XMLFileHandler;
import Interface.MyFileHandler;
import Repository.MyCollection;

public class MyController {
    private MyCollection collection = new MyCollection();
    private MyFileHandler csvHandler = new CSVFileHandler("/Users/saahilmfaizal/Downloads/MOCK_DATA.csv");
    private MyFileHandler xmlHandler = new XMLFileHandler("/Users/saahilmfaizal/Downloads/dataset.xml");
    private MyFileHandler jsonHandler = new JsonFileHandler("/Users/saahilmfaizal/Downloads/MOCK_DATA.json");

    public void loadData() throws InterruptedException {
        Thread csvThread = new Thread(() -> csvHandler.read());
       // Thread xmlThread = new Thread(() -> xmlHandler.read());
        Thread jsonThread = new Thread(() -> jsonHandler.read());

        csvThread.start();
      //  xmlThread.start();
        jsonThread.start();

        csvThread.join();
      //  xmlThread.join();
        jsonThread.join();

        System.out.println("Count of elements: " + collection.getWriteCounter());
    }

    public void saveData() {
    Thread csvThread = new Thread(() -> csvHandler.write());
//        Thread xmlThread = new Thread(() -> xmlHandler.write());
        Thread jsonThread = new Thread(() -> jsonHandler.write());

     csvThread.start();
//        xmlThread.start();
        jsonThread.start();

        try {
             csvThread.join();
//            xmlThread.join();
            jsonThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyController controller = new MyController();
        controller.loadData();
        controller.saveData();
    }
}
