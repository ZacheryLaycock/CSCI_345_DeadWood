import java.util.ArrayList;
import java.util.LinkedList;

class LocationManager{

  LinkedList<String> neighbors;

  public LocationManager(){
  }

  public boolean updatePlayerLocation(Player player, String newLocation, Room room){
    String currentLocation = player.location;

    neighbors = room.findAdjacentRooms();

    if (neighbors.contains(newLocation)){
      player.setLocation(newLocation);
      return true;
    }
    else{
      return false;
    }
  }
}
