import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Arrays;

class CastingOfficeRoom extends Room{
  ArrayList<int[]> dollarMap = new ArrayList<int[]>();
  ArrayList<int[]> fameMap = new ArrayList<int[]>();

  public CastingOfficeRoom(String name, ArrayList<String> neighbors, int[] area,
                           ArrayList<int[]> dollarMap, ArrayList<int[]> fameMap){
    super(name,neighbors, area);
    this.dollarMap = dollarMap;
    this.fameMap = fameMap;
    for (int i = 0; i < dollarMap.size(); i++){
      System.out.println("dolla dolla " + dollarMap.get(i)[0]);
      System.out.println("fameayama " + fameMap.get(i)[0]);
    }
  }

  public void upgradeWithMoney(int rank, Player player){
    int r = rank -2;
    int cost = dollarMap.get(r)[0];
    if(player.money >= cost){
      player.setMoney(player.money - cost);
      player.setRank(rank);
    }
    else{
      System.out.println("insufficient $");
    }
  }

  public void upgradeWithFame(int rank, Player player){
    int r = rank - 2;
    int cost = fameMap.get(r)[0];
    if(player.fame >= cost){
      player.setFame(player.fame - cost);
      player.setRank(rank);
    }
    else{
      System.out.println("insufficient cr");
    }
  }
}
