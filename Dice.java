import java.util.*;
import java.util.Random;

class Dice{

  public static void main(Args[] String){
    Random random = new Random();
  }
  int diceRoll = 0;
  public int rollDice(){
    diceRoll = Random.ints(0,6);
    System.out.println(diceRoll);
    return diceRoll;
  }

}
