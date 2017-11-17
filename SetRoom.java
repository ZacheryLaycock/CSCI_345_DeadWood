import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;

class SetRoom extends Room{
  boolean flipped;
  SceneCard sceneCard;
  ArrayList<Role> roleArray = new ArrayList<Role>();
  HashMap<Integer, int[]> shotMarkerData = new HashMap<Integer, int[]>();
  int amountOfRoles;
  boolean done;
  int shotMarkers;



  public SetRoom(String name, int amountOfRoles, ArrayList<Role> roleArray,
                 LinkedList<String> neighbors,  HashMap<Integer, int[]> shotMarkerData, int[] area){


    super(name, neighbors, area);
    this.shotMarkerData = shotMarkerData;
    this.amountOfRoles = amountOfRoles;
    this.roleArray = roleArray;
    this.flipped = false;
    this.done = false;
    this.sceneCard = null;
    shotMarkers = shotMarkerData.size();
  }

  public void removeShotMarkers(){
    shotMarkers--;

  }

  public void assignSceneCard(SceneCard card){
    this.sceneCard = card;
  }

  public void resetShotMarkers(){
    shotMarkers = shotMarkerData.size();
  }

  public String toString(){
    return "Name :" + super.name;
  }

  public SceneCard getSC(){
    return this.sceneCard;
  }

  public void completeRoom(){
    this.done = true;
    this.sceneCard = null;
  }
}
