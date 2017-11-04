

class Player{
  int rank;
  int fame;
  int money;
  String currentRole;
  int rehearsalBonuses;
  String location;
  public Player(int num){

    switch(num){
      // case 1 means 3 players
      case 1: rank = 1;
              fame = 0;
              money = 0;
              location = "TrailerRoom";
              break;
      case 2: break;
      case 3: break;
      case 4: break;
      case 5: break;
      default: break;
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
