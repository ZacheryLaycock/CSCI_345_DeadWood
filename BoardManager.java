import java.util.LinkedList;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.Collections;


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



    //determine play order
    determinePlayOrder();

    bank = new Bank();
    dice = new Dice();
    locationManager = new LocationManager();
    rehearsalManager = new RehearsalManager();
    castingOffice = new CastingOfficeRoom();

    // a bunch of set rooms with roles
    // parse through xml file to build rooms
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(board.xml);
    doc.getDocumentElement().normalize();
    NodeList nList = doc.getElementsByTagName("set");
    for (int i = 0; i < nList.getLength(); i++){

    }

    // a bunch of set rooms with roles
          // set room organized into an arraylist
    // a bunch of scene card with roles
          // scene cards organized into an arraylist

  }



  private LinkedList<Integer> determinePlayOrder(){
    LinkedList<Integer> playerOrder = new LinkedList<Integer>();
    int max = roll(numberOfPlayers);

    for(int i = 0; i< numberOfPlayers; i++){
        if(max == -1){
          max += numberOfPlayers;
        }
        playerOrder.add(max);
        max--;
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

    return max;
  }


  private void parseRooms(){

  }

  private void parseCards(){

  }


  public Room findRoom(String location){
    for(int i = 0; i< roomList.size(); i ++){
      if(roomList.get(i).getName().equals(location)){
        return roomList.get(i);
      }
    }
    System.out.println("no room found");
    return null;
  }


  public void refreshGameBoard(){
    // shuffle deck, assign cards to rooms
    // return player to TrailerRoom
    // reset shot markers
  }


  public void endGame(){
    ArrayList<Integer> scoreArray = new ArrayList<Integer>();
    for(int i = 0; i < numberOfPlayers; i++){
      scoreArray.add(bank.computePlayerScore(listOfPlayer.get(i)));
    }
    // haven't taken into account many players with the same score
    int winner = listOfPlayer(scoreArray.indexOf(Collections.max(scoreArray)));
    // announce winner
  }

}
