import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;

class SetRoom extends Room{
  boolean flipped;
  SceneCard sceneCard;
  ArrayList<Role> roleArray = new ArrayList<Role>();
  ArrayList<Role> remainingRoles;
  ArrayList<int[]> shotMarkerData = new ArrayList<int[]>();

  int amountOfRoles;
  boolean done;
  int shotMarkers;



  public SetRoom(String name, int amountOfRoles, ArrayList<Role> roleArray,
                 ArrayList<String> neighbors,  ArrayList<int[]> shotMarkerData, int[] area){


    super(name, neighbors, area);
    this.shotMarkerData = shotMarkerData;
    this.amountOfRoles = amountOfRoles;
    this.roleArray = new ArrayList<Role>();
    this.remainingRoles = new ArrayList<Role>();
    for(Role i:roleArray){
      this.roleArray.add(i);
      this.remainingRoles.add(i);
    }
    this.flipped = false;
    this.done = false;
    this.sceneCard = null;
    this.shotMarkers = shotMarkerData.size();
  }

  public void removeShotMarkers(){
    shotMarkers--;
  }

  public int mapSize(){
    return shotMarkerData.size();
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
