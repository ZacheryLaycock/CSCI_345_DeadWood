import java.util.HashMap;
import java.util.LinkedList;

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
  }

  public CastingOfficeRoom(){
    super("CastingOfficeRoom",new LinkedList<String>());
    upgradeCost = new HashMap<Integer,Cost>();
    //populate
  }

  // new rank if successful, or 0 if fail
  public int upgradeWithMoney(){
    return 0;
  }

  public int upgradeWithFame(){
    return 0;
  }


}
