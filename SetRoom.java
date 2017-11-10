import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

class SetRoom extends Room{
  boolean flipped;
  SceneCard sceneCard;
  ArrayList<Role> roleArray = new ArrayList<Role>();
  HashMap<Integer, int[]> shotMarkers = new HashMap<Integer, int[]>();
  int amountOfRoles;
  boolean done;
  int maxShotMarkers;



  public SetRoom(String name, int amountOfRoles, ArrayList<Role> roleArray,
                 LinkedList<String> neighbors,  HashMap<Integer, int[]> shotMarkers){


    super(name, neighbors);
    this.shotMarkers = shotMarkers;
    this.amountOfRoles = amountOfRoles;
    this.roleArray = roleArray;
    this.flipped = false;
    this.done = false;
    maxShotMarkers = shotMarkers.size();
  }

  public void removeShotMarkers(){
    maxShotMarkers--;

  }

  public void assignSceneCard(SceneCard card){
    this.sceneCard = card;
  }

  public void resetShotMarkers(){
    maxShotMarkers = shotMarkers.size();
  }
  //
  //
  //
  //
  //
}
