package Repository;

import Entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class MyCollection {
    private static Employee[] employees = new Employee[300];
    private static int writeCounter = 0;
    private int readCounter = 0;

    public static synchronized void add(Employee employee) {
        if (writeCounter < 300) {
            employees[writeCounter] = employee;
            writeCounter++;
        }
    }

    public synchronized Employee get() {
        if (readCounter < writeCounter) {
            return employees[readCounter++];
        }
        return null; // or throw exception
    }

    public int getWriteCounter() {
        return writeCounter;
    }
}
