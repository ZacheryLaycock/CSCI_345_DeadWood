import java.util.*;
import java.util.Random;

class Dice{

  Random rand;
  public Dice(){
    rand = new Random();
  }

  public int rollDice(){
    int randomNum = rand.nextInt()%6 + 1;
    return randomNum;
  }

}
