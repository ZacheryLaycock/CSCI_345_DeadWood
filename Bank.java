import java.util.*;

class Bank{

  // unnecessary
  int money = 0;
  int fame = 0;

  public Bank(){
  }
  //set specific players money to whatever amount has been decided for on and off the job
  // on the job = 1;
  // off the job = 0;
  int job = 0;
  public void setMoney(Player player, int amount){
    player.setMoney(player.money+ amount);

  }

  // fame is only given when a successfull die roll is made
  public void setFame(Player player, int amount){
   player.setFame(player.fame+ amount);

  }

  public int computePlayerScore(ArrayList<Player> listOfPlayer){
    int maxIndex = 0;
    Player player;
    int max = 0;
    int current;
    for (int i = 0; i < listOfPlayer.size(); i++){
      player = listOfPlayer.get(i);
      current = player.money + player.fame + 5*player.rank;
      if(current > max){
        current = max;
        maxIndex = i;
      }
    }
    return maxIndex;
  }

}
