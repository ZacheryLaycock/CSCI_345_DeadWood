

class Role{
  String name;
  String description;
  int rank;
  int[] area = new int[4];
  boolean onCard;

  public Role(String name, String description, int rank, int[] area, boolean onCard){
    this.onCard = onCard;
    this.name = name;
    this.description = description;
    this.rank = rank;
    this.area = area;

  }

  public String toString(){
    return name + " N " + description + " D " + rank + " R ";
  }

  public String getName(){
    return name;
  }

  // public checkPlayerRank(){
  //
  // }


}
