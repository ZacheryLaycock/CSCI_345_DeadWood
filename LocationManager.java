import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class LocationManager{

  LinkedList<String> neighbors;
  Scanner scanner;

  public LocationManager(){
    scanner = new Scanner(System.in);
  }


  // updatePlayerLocation call this
  public void availableLocation(String currentLocation){
    neighbors = BoardManager.findRoom(currentLocation).findAdjacentRooms();
    System.out.println("neighbors of " + currentLocation + ": ");
    for(int i = 0; i < neighbors.size(); i++){
      System.out.println(i +". " +neighbors.get(i));
    }
    return;
  }

  // player call this
  public void updatePlayerLocation(String currentLocation){
    int option;

    availableLocation(currentLocation);

    while(option<neighbors.size()){
      System.out.print("move to: ");
      option = scanner.nextInt();
    }

    return neighbors.get(i);

    // move if legal input
    // if not call availableLocation

  }


}
