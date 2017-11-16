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


class BoardManager{


    int numberOfDays;
    int numberOfPlayers;
    int numberOfRemainingRoom;

    // game objects
    ArrayList<Player> listOfPlayer = new ArrayList<Player>();
    LinkedList<Integer> playerOrder;
    Bank bank;
    Dice dice;
    LocationManager locationManager;
    RehearsalManager rehearsalManager;
    CastingOfficeRoom castingOffice;
    ArrayList<Room> roomList = new ArrayList<Room>();

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
          System.out.println(listOfPlayer.get(player).location);
      }else if (input[0].equalsIgnoreCase("move")){
        Room room = null;
        for (int i = 0; i < roomList.size(); i++){
          if (roomList.get(i).getName().equals(listOfPlayer.get(player).location)){
            room = roomList.get(i);
          }
        }
        locationManager.updatePlayerLocation(listOfPlayer.get(player), input[1], room);
      }else if (input[0].equalsIgnoreCase("work")){

      }else if (input[0].equalsIgnoreCase("upgrade")){

      }else if (input[0].equalsIgnoreCase("rehearse")){
        Room room = null;
        SetRoom setRoom = null;
        for (int i = 0; i < roomList.size(); i++){
          if (roomList.get(i).getName().equals(listOfPlayer.get(player).location)){
            room = roomList.get(i);
            if (room instanceof SetRoom){

              if (!checkRehearsalLevel(listOfPlayer.get(player), room.getSC().getBudget())){
                System.out.println("You have rehearsed too many times already. Get ur shit together!!!");
              }
              else{
                givePlayerRehearsalToken(listOfPlayer.get(player));
              }
            }
            else{
              System.out.println("You are not allowed to rehearse in " + room.getName() + ".");
              invalidInput(player);
              break;
            }
          }
        }



      }else if (input[0].equalsIgnoreCase("act")){

      }else if (input[0].equalsIgnoreCase("end")){

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
    roomList = XML_Test.boardXML("board.xml");
    for (int i = 0; i < roomList.size(); i++){
      System.out.println(roomList.get(i).toString());
    }

    // a bunch of set rooms with roles
          // set room organized into an arraylist
    // a bunch of scene card with roles
          // scene cards organized into an arraylist

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




  // public Room findRoom(String location){
  //   for(int i = 0; i < roomList.size(); i++){
  //       if(roomList.get(i).getName().equals(location)){
  //       return roomList.get(i);
  //     }
  //   }
  //   System.out.println("no room found");
  //   return null;
  // }



  public void endGame(){
    ArrayList<Integer> scoreArray = new ArrayList<Integer>();
    for(int i = 0; i < numberOfPlayers; i++){
      scoreArray.add(bank.computePlayerScore(listOfPlayer.get(i)));
    }
    // haven't taken into account many players with the same score
    Player winner = listOfPlayer.get(scoreArray.indexOf(Collections.max(scoreArray)));
    // announce winner
  }

}
