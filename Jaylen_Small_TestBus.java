/*
  Written by: Jaylen Small

  Output: 
  vacant              vacant
  John Doe 001        vacant
  Sally Miller 002    vacant
  vacant              Fred Johnson 003    
  vacant              vacant
  0001                Jane Doe
*/

// Student class
class Student {
    String studentName;
    String studentID;
    int seatNum;

    Student(String name, String ID, int seat){
        studentName = name;
        studentID = ID;
        seatNum = seat;
    }
}

// Bus class
class Bus{
    String driver; 
    String busID;
    Student[][] assignedSeat = new Student[5][2];

    // Sets driver name
    Bus(String d){
        driver = d;
    }

    // Takes the student seat, splits it into two single didgit numbers, and uses them to index the student into the assignedSeat array
    void assignSeat(Student s, int seatNum){
        double temp = seatNum / 10.0;

        int index1 = (int)temp;
        int index2 = (int)((temp - index1) * 10);

        assignedSeat[index1][index2] = s;
    }

    // Prints the assignedSeat array
    void getSeatRoster(){
        for (Student[] row : assignedSeat) {
            for (Student cell : row) {
                if (cell == null){
                    System.out.printf("%-20s", "vacant" + " ");
                } else {
                    System.out.printf("%-20s", cell.studentName + " " + cell.studentID);
                }
            }
            System.out.println();
        }

        System.out.printf("%-20s", busID);
        System.out.printf("%-20s", driver);
    }
}

public class Jaylen_Small_TestBus {
    public static void main(String[] args) {
        Bus bus = new Bus("Jane Doe");
        bus.busID = "0001";

        Student johnDoe = new Student("John Doe", "001", 10);
        Student sallyMiller = new Student("Sally Miller", "002", 20);
        Student fredJohnson = new Student("Fred Johnson", "003", 31);

        bus.assignSeat(johnDoe, johnDoe.seatNum);
        bus.assignSeat(sallyMiller, sallyMiller.seatNum);
        bus.assignSeat(fredJohnson, fredJohnson.seatNum);

        bus.getSeatRoster();
    }
}
