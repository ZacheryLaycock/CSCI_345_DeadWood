


class RehearsalManager{

  public RehearsalManager(){
  }

  //givePlayerRehearsalToken call this thing
  public boolean checkRehearsalLevel(Player player, int budget){
    if (player.rehearsalBonuses < (budget - 1)){
      return true;
    }
    return false;
  }

  public void givePlayerRehearsalToken(Player player) {
    player.rehearsalBonuses = player.rehearsalBonuses + 1;
  }
}
