import java.util.Scanner;

class Player{
  int rank;
  int fame;
  int money;
  Role currentRole;
  int rehearsalBonuses;
  // String location;
  Room currentRoom;
  public Player(int num){
    money = 99;
    rehearsalBonuses = 0;
    switch(num){
      // case 1 means 3 players
      case 0: rank = 1;
              fame = 99;
              break;
      case 1: rank = 1;
              fame = 2;
              break;
      case 2: rank = 1;
              fame = 4;
              break;
      case 3: rank = 2;
              fame = 0;
              break;
      default:rank = 0;
              fame = 0;
              break;
    }

  }

  public void setRank(int rank){

    this.rank = rank;

  }

  public void setFame(int fame){
    this.fame = fame;
  }

  public void setMoney(int money){
    this.money = money;
  }

  public void changeRole(Role role){
    // on the card or off the card
    // list the roles,

    // player can only choose roles within ones rank
    this.currentRole = role;
  }

  public void rehearse(){
    // rehearsalBonuses = RehearsalManager.givePlayerRehearsalToken(this);
  }

  public void roleComplete(){
    this.currentRole = null;
    this.rehearsalBonuses = 0;
  }

  public void setLocation(Room newRoom){
    this.currentRoom = newRoom;
  }

  public int getMoney(){
    return this.money;
  }

  public int getFame(){
    return this.fame;
  }

  public int getRank(){
    return this.rank;
  }

  public Role getRole(){
    return this.currentRole;
  }

  public int getRehearsalBonuses(){
    return rehearsalBonuses;
  }

}
