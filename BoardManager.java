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
  ArrayList<Player> listOfPlayer = new ArrayList<Player>();
  LinkedList<Integer> playerOrder;
  int numberOfRemainingRoom;

  public BoardManager(int numberOfPlayers){
    // do a bunch of switch case
      this.numberOfPlayers = numberOfPlayers;
      this.numberOfRemainingRoom;

  }

  public void setUp(){
    //determine play order
    Bank bank = new Bank();
    Dice dice = new Dice();
    LocationManager locationManager = new LocationManager();
    RehearsalManager rehearsalManager = new RehearsalManager();
    CastingOfficeRoom castingOffice = new CastingOfficeRoom();
    // a bunch of set rooms with roles
    // parse through xml file to build rooms
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(board.xml);
    doc.getDocumentElement().normalize();
    NodeList nList = doc.getElementsByTagName("set");
    for (int i = 0; i < nList.getLength(); i++){

    }


          // set room organized into an arraylist
    // a bunch of scene card with roles
          // scene cards organized into an arraylist

  }

  public void refreshGameBoard(){
    // shuffle deck, assign cards to rooms
    // return player to TrailerRoom
    // reset shot markers
  }

  public void endGame(){
    // iterate through a player set and calculate score of each
  }

  private LinkedList<Integer> determinePlayOrder(){
    return new LinkedList<Integer>();
  }

  private void parseRooms(){

  }

  private void parseCards(){

  }
}
