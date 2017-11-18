

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
    return name + " Name " + description + " Description " + rank + " Rank ";
  }

  public String getName(){
    return name;
  }

  public int getRank(){
    return rank;
  }

  // public checkPlayerRank(){
  //
  // }


}
