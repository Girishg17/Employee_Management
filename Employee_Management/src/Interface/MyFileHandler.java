package Interface;

import Repository.MyCollection;

public interface MyFileHandler {

    public void write(MyCollection collection);
    public void read(MyCollection collection);
}
