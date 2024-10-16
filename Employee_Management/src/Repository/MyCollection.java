package Repository;

import Entity.Employee;

import java.util.List;

public class MyCollection {
    private static Employee[] employees = new Employee[300];
    private static int writeCounter = 0;
    private static int readCounter = 0;

    public static synchronized void add(Employee employee) {
        if (writeCounter < 300) {
            employees[writeCounter] = employee;
            writeCounter++;
        }
    }

    public static synchronized Employee get() {
        if (readCounter < writeCounter) {
            return employees[readCounter++];
        }
        return null; // or throw an exception
    }

    public static synchronized void addAll(List<Employee> tempList) {
        for (Employee employee : tempList) {
            if (writeCounter < 300) {
                employees[writeCounter] = employee;
                writeCounter++;
            } else {
                System.out.println("Employee array is full. Cannot add more employees.");
                break; // Exit if the array is full
            }
        }
    }

    public int getWriteCounter() {
        return writeCounter;
    }

    public int getReadCounter() {
        return readCounter;
    }
}
