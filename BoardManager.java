import java.util.LinkedList;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


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
      ArrayList.add(new Player(playerType));
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
    boardXML("board.xml");

    // a bunch of set rooms with roles
          // set room organized into an arraylist
    // a bunch of scene card with roles
          // scene cards organized into an arraylist

  }

  private void boardXML(String filename){
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(filename);
    doc.getDocumentElement().normalize();
    NodeList nList = doc.getElementsByTagName("set");
    for (int i = 0; i < nList.getLength(); i++){
      Node nNode = nList.item(i);
      if (nNode.getNodeType() == Node.ELEMENT_NODE){
        Element eElement = (Element) nNode;
        String name = eElement.getAttribute("name");
        //neighbors = eElement.getElementsByTagName("")
        //int takes = eElement.getElementsByTagName("takes").getLength();
        Node takes = eElement.getElementsByTagName("takes").item(0);
        int numTakes = takes.getElementsByTagName("take").getLength();
        SetRoom newRoom = new SetRoom();
        System.out.println(numTakes);
      }
    }
  }

  private LinkedList<Integer> determinePlayOrder(){
    LinkedList<Integer> playerOrder = new LinkedList<Integer>();
    ArrayList<Integer> diceRoll = new ArrayList<Integer>();

    for(int i = 0; i < numberOfPlayers; i++){
      diceRoll.add(dice.rollDice());
    }
    Collections.sort(diceRoll);
    Collections.reverse();


    return playerOrder;
  }


  private void parseRooms(){

  }

  private void parseCards(){

  }


  public void refreshGameBoard(){
    // shuffle deck, assign cards to rooms
    // return player to TrailerRoom
    // reset shot markers
  }


  public void endGame(){
    // iterate through a player set and calculate score of each
  }

}
