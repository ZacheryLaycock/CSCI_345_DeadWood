import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

class CastingOfficeRoom extends Room{
  HashMap<Integer, int[]> dollarMap = new HashMap<Integer, int[]>();
  HashMap<Integer, int[]> fameMap = new HashMap<Integer, int[]>();


  public CastingOfficeRoom(String name, LinkedList<String> neighbors, int[] area,
                           HashMap<Integer,int[]> dollarMap, HashMap<Integer,int[]> fameMap){
    super(name,neighbors, area);
    this.dollarMap = dollarMap;
    this.fameMap = fameMap;
  }

  // new rank if successful, or 0 if fail
  // public int upgradeWithMoney(int rank, int money){
  //   int cost = upgradeCost.get(rank).getMoney();
  //   if(cost <= money){
  //     return cost;
  //   }
  //   else{
  //     System.out.println("insufficient fund");
  //     return 0;
  //   }
  // }
  //
  // public int upgradeWithFame(int rank, int fame){
  //   int cost = upgradeCost.get(rank).getFame();
  //   if(cost <= fame){
  //     return cost;
  //   }
  //   else{
  //     System.out.println("insufficient fund");
  //     return 0;
  //   }
  // }

  public void listOptions(){
    System.out.println("2 2 2");
    System.out.println("3 3 3");
    System.out.println("4 4 4");
    System.out.println("5 5 5");
    System.out.println("6 6 6");
    return;
  }

}
