

class Player{
  int rank;
  int fame;
  int money;
  String currentRole;
  int rehearsalBonuses;
  String location;
  public Player(int num){
    location = "TrailerRoom";
    money = 0;
    switch(num){
      // case 1 means 3 players
      case 1: rank = 1;
              fame = 0;
              break;
      case 2: rank = 1;
              fame = 2;
              break;
      case 3: rank = 1;
              fame = 4;
              break;
      case 4: rank = 2;
              fame = 0;
              break;
      default:rank = 0;
              fame = 0;
              break;
    }

  }

  public void setRank(int rank){
    // call casting office to upgrade
  }

  public void setFame(int fame){
    this.fame = fame;
  }

  public void setMoney(int money){
    this.money = money;
  }

  public void changeRole(String role){
    // on the card or off the card
    // list the roles,

    // player can only choose roles within ones rank
    this.currentRole = role
  }

  public void rehearse(){
    // rehearsalBonuses = RehearsalManager.givePlayerRehearsalToken(this);
  }

  public void setLocation(String nextLocation){
    // location manager presents options
    // choose an option
    // if legal then move
  }

}
