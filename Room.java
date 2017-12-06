import java.util.ArrayList;

// BEST IF THIS IS AN INTERFACE
class Room{

  ArrayList<String> adjacentRooms;
  int[] area = new int[4];
  String name;

  public Room(String name, ArrayList<String> adjacentRooms, int[] area ){
    this.area = area;
    this.name = name;
    this.adjacentRooms = adjacentRooms;
  }

  public String getName(){
    return this.name;
  }

  public ArrayList<String> findAdjacentRooms(){
    return this.adjacentRooms;
}

  public String toString(){
    return "Name :" + name;
  }

  public int[] getArea(){
    return this.area;
  }
}
