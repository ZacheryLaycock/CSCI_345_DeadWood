
import java.util.ArrayList;

class SceneCard{
  String name;
  int budget;
  String sceneDescription;
  ArrayList<Role> roleArray;

  public SceneCard(String name, int budget, String sceneDescription, ArrayList<Role> roleArray){
    this.name = name;
    this.budget = budget;
    this.sceneDescription = sceneDescription;
    this.roleArray = roleArray;
  }

  // public flip(){
  //
  // }
  //
  // public returnToDeck(){
  //
  // }

}
