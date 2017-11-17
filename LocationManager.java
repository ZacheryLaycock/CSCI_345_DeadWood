import java.util.ArrayList;
import java.util.LinkedList;

class LocationManager{

  LinkedList<String> neighbors;

  public LocationManager(){
  }

  public boolean updatePlayerLocation(Player player, Room newRoom, Room currentRoom){
    //String currentLocation = player.location;

    neighbors = currentRoom.findAdjacentRooms();

    if (neighbors.contains(newRoom.getName())){
      player.setLocation(newRoom);
      return true;
    }
    else{
      return false;
    }
  }
}
