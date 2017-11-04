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
  private void setMoney(String player, int job){
  //if on the job get some amount of money

  //otherwise we know we are not on the job so divy out whatever is necessary to the player

  }

  // fame is only given when a successfull die roll is made
  private void setFame(String player, int job){
   // if on the job then give 2 fame

   // if off the job then give 1 fame

  }

  int playerScore = 0;
  private int computePlayerScore(String player){
    //playerScore = player.dollars() + player.fame() + 5*player.rank();
    return playerScore;
  }

}
