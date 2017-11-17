
import java.util.ArrayList;

class SceneCard{
  String name;
  int budget;
  String sceneDescription;
  String img;
  ArrayList<Role> roleArray;
  int number;

  public SceneCard(String name, String img, int budget, int number, String sceneDescription, ArrayList<Role> roleArray){
    this.name = name;
    this.budget = budget;
    this.sceneDescription = sceneDescription;
    this.roleArray = roleArray;
    this.img = img;
    this.number = number;
  }

  public int getBudget(){
    return this.budget;
  }
  // public flip(){
  //
  // }
  //
  // public returnToDeck(){
  //
  // }

}
