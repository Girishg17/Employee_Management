import Interface.Implementation.CSVFileHandler;
import Interface.Implementation.JsonFileHandler;
import Interface.Implementation.XMLFileHandler;
import Interface.MyFileHandler;
import Repository.MyCollection;

public class MyController {
    private MyCollection collection = new MyCollection();
    private MyFileHandler csvHandler = new CSVFileHandler("/Users/girishggonda/Downloads/MOCK_DATA.csv");
    private MyFileHandler xmlHandler = new XMLFileHandler("/Users/girishggonda/Downloads/dataset.xml");
    private MyFileHandler jsonHandler = new JsonFileHandler("/Users/girishggonda/Downloads/MOCK_DATA.json");

    public void loadData()  {
        Thread csvThread = new Thread(() -> csvHandler.read());
        Thread xmlThread = new Thread(() -> xmlHandler.read());
        Thread jsonThread = new Thread(() -> jsonHandler.read());

        csvThread.start();
        xmlThread.start();
        jsonThread.start();
        try {
            csvThread.join();
            xmlThread.join();
            jsonThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Write Counter " + collection.getWriteCounter());
    }

    public void saveData() {
        Thread csvThread = new Thread(() -> csvHandler.write());
        Thread xmlThread = new Thread(() -> xmlHandler.write());
        Thread jsonThread = new Thread(() -> jsonHandler.write());

        csvThread.start();
        xmlThread.start();
        jsonThread.start();

        try {
            csvThread.join();
            xmlThread.join();
            jsonThread.join();
            System.out.println("Read Counter " + collection.getReadCounter());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
