
import java.util.ArrayList;

class SceneCard{
  String name;
  int budget;
  String sceneDescription;
  String img;
  ArrayList<Role> roleArray;
  ArrayList<Role> remainingRoles;
  int number;

  public SceneCard(String name, String img, int budget, int number, String sceneDescription, ArrayList<Role> roleArray){
    this.name = name;
    this.budget = budget;
    this.sceneDescription = sceneDescription;
    this.roleArray = new ArrayList<Role>();
    this.remainingRoles = new ArrayList<Role>();
    for(Role i:roleArray){
      this.roleArray.add(i);
      this.remainingRoles.add(i);
    }
    this.img = img;
    this.number = number;
  }

  public int getBudget(){
    return this.budget;
  }

  public String getName(){
    return this.name;
  }
}
