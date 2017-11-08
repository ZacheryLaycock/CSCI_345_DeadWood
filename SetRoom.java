import java.util.ArrayList;
import java.util.LinkedList;

class SetRoom extends Room{
  boolean flipped;
  SceneCard sceneCard;
  ArrayList<Role> roleArray = new ArrayList<Role>();
  int shotMarkers;
  int amountOfRoles;
  boolean done;
  int maxShotMarkers;

  public SetRoom(String name, int shotMarkers, int amountOfRoles, ArrayList<Role> roleArray,
                 LinkedList<String> neighbors, SceneCard sceneCard, int maxShotMarkers){
    super(name, neighbors);
    this.shotMarkers = shotMarkers;
    this.amountOfRoles = amountOfRoles;
    this.roleArray = roleArray;
    this.sceneCard = sceneCard;
    this.flipped = false;
    this.done = false;
    this.maxShotMarkers = maxShotMarkers;
  }

  public void removeShotMarkers(){
    shotMarkers--;
  }

  public void resetShotMarkers(){
    shotMarkers = maxShotMarkers;
  }
  //
  //
  //
  //
  //
}
