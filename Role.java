

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
    return this.name + " Name " + this.description + " Description " + this.rank + " Rank ";
  }

  public String getName(){
    return this.name;
  }

  public int getRank(){
    return this.rank;
  }

  public int[] getArea(){
    return this.area;
  }

  public boolean getOnCard(){
    return this.onCard;
  }
}
