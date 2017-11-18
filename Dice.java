import java.util.*;
import java.util.Random;
import java.lang.Math;

class Dice{

  Random rand;
  public Dice(){
    rand = new Random();
  }

  public int rollDice(){
    int randomNum = Math.abs(rand.nextInt())%6 + 1;
    return randomNum;
  }

}
