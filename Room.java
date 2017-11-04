import java.util.LinkedList;

// BEST IF THIS IS AN INTERFACE
class Room{

  LinkedList<String> adjacentRooms;
  String name;

  public Room(String name, LinkedList<String> adjacentRooms){
    this.adjacentRooms = adjacentRooms;
  }

  public String getName(){
    return this.name;
  }

  public LinkedList<String> findAdjacentRooms(){
    return adjacentRooms;
  }

}
