import java.util.Scanner;

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

  public void setRank(CastingOfficeRoom castingOffice){
    // call casting office pass rank and money in
    Scanner scanner = new Scanner(System.in);
    int option;
    int rank;
    int cost;

    castingOffice.listOptions();

    System.out.println("which rank would you like?");
    rank = scanner.nextInt();
    System.out.println("how do you want to upgrade? money(0) or fame(1)?");
    option = nextInt();

    if (option == 1){
      cost = castingOffice.upgradeWithFame(rank, fame);
    }
    else if (option == 0){
      cost = castingOffice.upgradeWithMoney(rank, money);
    }

    if(cost != 0){
      if(option == 1){
        fame-= cost;
      }
      else if(option == 0){
        money-=cost;
      }
    }

    while(cost == 0){
      System.out.println("do still want to upgrade? no(0) yes(1)")
      option = scanner.nextInt();
      if(option == 0) break;
      setRank(castingOffice);
    }
    return;
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
    this.currentRole = role;
  }

  public void rehearse(){
    // rehearsalBonuses = RehearsalManager.givePlayerRehearsalToken(this);
  }

  public void setLocation(LocationManager locationManager){
    // location manager presents options
    // choose an option
    // if legal then move
    this.location = locationManager.updatePlayerLocation();
  }

}
