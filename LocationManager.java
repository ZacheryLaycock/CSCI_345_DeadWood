import java.util.ArrayList;
import java.util.LinkedList;

class LocationManager{

  ArrayList<String> neighbors;

  public LocationManager(){
    this.neighbors = null;
  }

  public boolean updatePlayerLocation(Player player, Room newRoom, Room currentRoom){
    if (currentRoom.findAdjacentRooms().contains(newRoom.getName()) && player.currentRole == null){
      player.setLocation(newRoom);
      return true;
    } else return false;
  }
}
