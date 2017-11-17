import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.Collections;
import java.util.Scanner;
import java.util.Collections;


class BoardManager{


  // game objects
  ArrayList<Player> listOfPlayer = new ArrayList<Player>();
  LinkedList<Integer> playerOrder;
  Bank bank;
  Dice dice;
  LocationManager locationManager;
  RehearsalManager rehearsalManager;
  CastingOfficeRoom castingOffice;
  ArrayList<Room> roomList = new ArrayList<Room>();
  ArrayList<SceneCard> cardList = new ArrayList<SceneCard>();

  int numberOfDays;
  int numberOfPlayers;
  int numberOfRemainingRoom;

  public BoardManager(int numberOfPlayers){
    if(numberOfPlayers < 4){
      numberOfDays = 3;
    }
    else{
      numberOfDays = 4;
    }

    this.numberOfPlayers = numberOfPlayers;
    this.numberOfRemainingRoom =10;

    setUp();
    play();
  }

  public void play(){
    //main game loop
    while (numberOfDays != 0){
      //decrement days after all but 1 room complete
      while(numberOfRemainingRoom != 1){
        for (int currentPlayer = 0; currentPlayer < numberOfPlayers; currentPlayer++){
          System.out.println("Player "+(currentPlayer+1)+"'s turn");
          playerAction(currentPlayer);
        }
      }
      numberOfDays--;
      resetBoard();
    }
    endGame();
  }

  //takes player action and changes game state
  public void playerAction(int player){
    Scanner scanner = new Scanner(System.in);
    System.out.println("What would you like to do?");
    String[] input = scanner.nextLine().split(" ");
    if (input[0].equalsIgnoreCase("who")){
      System.out.println((player+1));
    }else if (input[0].equalsIgnoreCase("where")){
      System.out.println(listOfPlayer.get(player).currentRoom.getName());
    }else if (input[0].equalsIgnoreCase("move")){
      Room newRoom = null;
      for (int i = 0; i < roomList.size(); i++){
        if (roomList.get(i).getName().equals(input[1])){
          newRoom = roomList.get(i);
        }
      }

      locationManager.updatePlayerLocation(listOfPlayer.get(player), newRoom, listOfPlayer.get(player).currentRoom);
    }else if (input[0].equalsIgnoreCase("work")){

    }else if (input[0].equalsIgnoreCase("upgrade")){

    }else if (input[0].equalsIgnoreCase("rehearse")){
      Room room = null;
      SetRoom setRoom = null;
      if (room instanceof SetRoom){
        setRoom = (SetRoom) listOfPlayer.get(player).currentRoom;
        if (!rehearsalManager.checkRehearsalLevel(listOfPlayer.get(player), setRoom.getSC().getBudget())){
          System.out.println("You have rehearsed too many times already. Get ur shit together!!!");
        }
        else{
          rehearsalManager.givePlayerRehearsalToken(listOfPlayer.get(player));
        }
      }
      else{
        System.out.println("You are not allowed to rehearse in " + room.getName() + ".");
        invalidInput(player);
      }
    }else if (input[0].equalsIgnoreCase("act")){
      act(player);
    }else if (input[0].equalsIgnoreCase("end")){
      //Nothing happens
    } else invalidInput(player);
  }

  public void invalidInput(int currentPlayer){
    System.out.println("Invalid input, try again");
    playerAction(currentPlayer);
  }

  public void resetBoard(){

  }

  public void setUp(){
    //create players
    int playerType;
    switch(numberOfPlayers){
      // start w 2 credits
      case 5: playerType = 1;
      break;
      // start w 4 credits
      case 6: playerType = 2;
      break;
      case 7: case 8:
      // start rank 2
      playerType = 3;
      break;
      // 0 money, 0 fame, rank 1
      default: playerType = 0;
      break;
    }

    for(int i = 0; i< numberOfPlayers; i++){
      listOfPlayer.add(new Player(playerType));
    }




    bank = new Bank();
    dice = new Dice();
    locationManager = new LocationManager();
    rehearsalManager = new RehearsalManager();
    castingOffice = new CastingOfficeRoom();
    //determine play order
    determinePlayOrder();



    // a bunch of set rooms with roles
    // parse through xml file to build rooms
    cardList = XML_Test.cardXML("card.xml");
    roomList = XML_Test.boardXML("board.xml");

    for (int i = 0; i < roomList.size(); i++){
      //System.out.println(roomList.get(i).toString());
      if (roomList.get(i).getName().equals("Trailer")){
        for (int y = 0; y < listOfPlayer.size(); y++){
          listOfPlayer.get(y).setLocation(roomList.get(i));
        }
      }
    }
  }

  private void boardXML(String filename){
  }

  private LinkedList<Integer> determinePlayOrder(){
    LinkedList<Integer> playerOrder = new LinkedList<Integer>();
    int currentPlayer = roll(numberOfPlayers);

    for(int i = 0; i< numberOfPlayers; i++){
      if(currentPlayer == numberOfPlayers){
        currentPlayer = 0;
      }
      playerOrder.add(currentPlayer);
      currentPlayer++;
    }

    return playerOrder;
  }

  private int roll(int equalRolls){
    ArrayList<Integer> diceRoll = new ArrayList<Integer>();
    int max;
    int numOfEqualRolls;

    for(int i = 0; i < numberOfPlayers; i++){
      diceRoll.add(dice.rollDice());
    }


    max = Collections.max(diceRoll);
    numOfEqualRolls = Collections.frequency(diceRoll,max);
    if(numOfEqualRolls > 1){
      return roll(numOfEqualRolls);
    }

    return diceRoll.indexOf(max);
  }


  public void act(int player){
    Player currentPlayer = listOfPlayer.get(player);
    Role currentRole = currentPlayer.currentRole;
    if (currentRole == null){
      System.out.println("You are currently not working a role.");
      invalidInput(player);
    }
    else {
      Room room = null;
      SetRoom setRoom = null;
      SceneCard sceneCard = null;
      int budget = 0;
      boolean onCard = currentPlayer.currentRole.onCard;
      if (room instanceof SetRoom){
        setRoom = (SetRoom) currentPlayer.currentRoom;
        sceneCard = setRoom.getSC();
        budget = sceneCard.getBudget();
      }
      if (dice.rollDice() > budget){
        setRoom.removeShotMarkers();
        if (setRoom.shotMarkers == 0){
          setRoom.completeRoom();
          currentPlayer.roleComplete();
          //payout for on or off the card
          if(onCard){
            ArrayList<Integer> diceArray = new ArrayList<Integer>();
            ArrayList<Integer> rollList = new ArrayList<Integer>();
            int numberOfRoles = setRoom.sceneCard.roleArray.size();
            for(int i = 0; i < budget; i++){
              diceArray.add(dice.rollDice());
            }
            Collections.sort(diceArray);
            Collections.reverse(diceArray);
            int j = numberOfRoles -1;
            for(int i = 0; i < diceArray.size(); i++){
              rollList.add(diceArray.get(i));
              j--;
              if(j < 0){
                j = numberOfRoles -1;
              }
            }
            for(int y = 0; y < listOfPlayer.size(); y++){
              Role aRole = listOfPlayer.get(y).currentRole;
              for (int i = 0; i < numberOfRoles; i++){
                if (aRole.getName().equals(setRoom.sceneCard.roleArray.get(i).getName())){
                  bank.setMoney(listOfPlayer.get(y), rollList.get(i));
                }
              }
            }
          }
          else{
            bank.setMoney(currentPlayer, currentPlayer.rank);
          }
        }
        else{
          int no = 0;
          if(onCard){
            bank.setFame(currentPlayer, 2);
          }
          else{
            bank.setMoney(currentPlayer, 1);
            bank.setFame(currentPlayer, 1);
          }
        }
      }
      else{
        if (!onCard){
          bank.setMoney(currentPlayer,1);
        }
      }
    }
  }

  public void endGame(){
    int winner = bank.computePlayerScore(listOfPlayer) - 1;
    System.out.println("The winner is player " + winner + " ");
  }
}
