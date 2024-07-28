import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Sat {  // class sat with 3 attributes namely direction,status and data
    String directions;
    boolean status; 
    int data;

    void setDirection(String s) { // to set the direction
        directions = s;
    }

    void setStatus(boolean n) { // to set the status of solar panels
        status = n;
    }

    void addData(int n) {
        data += n; //  for accumulating data
    }
    void activate() {
        setStatus(true);
        System.out.println("Solar Panels Activated");  // panels will be activated
    }

    void deactivate() {
        setStatus(false);
        System.out.println("Solar Panels Deactivated");//panels will be deactivated
    }

    void collectData() {
        if (status) {
            addData(10);
            System.out.println("Collected 10 units of data");//data will be collected if panel is active
        } else {
            System.out.println("Solar panels are inactive. Cannot collect data."); //data will not be collected if panel is inactive
        }
    }

    void displayStatus() { // to display the current status of the satellite
        System.out.println("Orientation: " + directions);
        if (status) {
            System.out.println("Solar Panels: Active");
        } else {
            System.out.println("Solar Panels: Inactive");
        }
        System.out.println("Data Collected: " + data);
    }

    void rotate(String direction) {
        switch (direction.toLowerCase()) { // to rotate the satellite
            case "north":
                directions = "North";
                break;
            case "east":
                directions = "East";
                break;
            case "south":
                directions = "South";
                break;
            case "west":
                directions = "West";
                break;
            default:
                System.out.println("Invalid direction.");
        }
    }


    void performAction(String action) { //to perform the operations as per user input
        if (action.equalsIgnoreCase("activatePanels")) {
            activate(); // Activate Panels
        } else if (action.equalsIgnoreCase("deactivatePanels")) {
            deactivate(); // Deactivate Panels
        } else if (action.equalsIgnoreCase("collectData")) {
            collectData();//to collect data
        } else if (action.equalsIgnoreCase("displayStatus")) { //display the status of satellite
        	displayStatus();
        } else if (action.startsWith("rotate(")) {  //for rotation 
            String d=action.substring(7,action.length()-1);//7th character will have the direction
            rotate(d);
        }else if(action.equalsIgnoreCase("LoadData")) { //to load the data saved
        	loadData();
        }else if (action.equalsIgnoreCase("Updatedata")) {//to save the data
        	updateData(directions,status,data);
        }
        else {
            System.out.println("Invalid operation.");
        }
    }
    private List<Sat> dataHistory = new ArrayList<>();//to store the save details

    void updateData(String directions, boolean status, int data) {//to save the details into list

        this.directions = directions;// Update current values
        this.status = status;
        this.data += data;
        Sat dataPoint = new Sat();// Store past data
        dataPoint.directions = directions;
        dataPoint.status = status;
        dataPoint.data = this.data;
        dataHistory.add(dataPoint);
    }
    void loadData() {//to load the saved details from list
    	  if (dataHistory.isEmpty()) {
    	    System.out.println("No data history found.");//when the list is empty
    	    return;
    	  }

    	  for (Sat pastState : dataHistory) {
    	    System.out.println("------- Past State-------");//when the list is not empty
    	    System.out.println("Orientation: " + pastState.directions);
    	    System.out.println("Solar Panels: " + (pastState.status ? "Active" : "Inactive"));
    	    System.out.println("Data Collected: " + pastState.data);
    	  }
    	}

    public static void main(String[] args) {
        Sat object1 = new Sat();
        object1.setDirection("North"); // Initial values of satellite
        object1.setStatus(false);
        object1.addData(0);

        Scanner scanner = new Scanner(System.in);
        String operation;
        do {
            System.out.println("Enter the operation:");
            operation = scanner.nextLine();  //to get input from user
            if (!operation.equalsIgnoreCase("stop")) {
                object1.performAction(operation); // perform operation until Stop is given as input
            }
        } while (!operation.equalsIgnoreCase("stop"));

        scanner.close();
    }
}
