import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

class CastingOfficeRoom extends Room{
  HashMap<Integer,Cost> upgradeCost;


  private class Cost{
    int money;
    int fame;

    public Cost(int money, int fame){
      this.money = money;
      this.fame = fame;
    }

    public int getMoney(){
      return money;
    }

    public int getFame(){
      return fame;
    }

    // public toString(){
    //   return this.money + "\t" + this.fame;
    // }
  }

  public CastingOfficeRoom(){
    super("CastingOfficeRoom",new LinkedList<String>(), new int[4]);
    upgradeCost = new HashMap<Integer,Cost>();
    //populate
  }

  // new rank if successful, or 0 if fail
  public int upgradeWithMoney(int rank, int money){
    int cost = upgradeCost.get(rank).getMoney();
    if(cost <= money){
      return cost;
    }
    else{
      System.out.println("insufficient fund");
      return 0;
    }
  }

  public int upgradeWithFame(int rank, int fame){
    int cost = upgradeCost.get(rank).getFame();
    if(cost <= fame){
      return cost;
    }
    else{
      System.out.println("insufficient fund");
      return 0;
    }
  }

  public void listOptions(){

    // Iterator iterator = upgradeCost.entrySet().iterator();
    //
    // while(iterator.hasNext()){
    //
    // }

    System.out.println("2 2 2");
    System.out.println("3 3 3");
    System.out.println("4 4 4");
    System.out.println("5 5 5");
    System.out.println("6 6 6");
    return;
  }

}
