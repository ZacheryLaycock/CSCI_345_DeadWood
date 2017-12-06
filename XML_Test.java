import java.util.ArrayList;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class XML_Test{


  public static void main(String[] args){
  boardXML("board.xml");
  cardXML("cards.xml");
  }

  public static ArrayList<Room> boardXML(String filename){
    try{
      ArrayList<Room> roomList = new ArrayList<Room>();
      String roomName;
      String roleName;
      int roleLevel;
      int[] area = new int[4];
      int[] trailerArea = new int[4];
      int[] roleArea = new int[4];
      int[] shotMarkersArea = new int[4];
      String description ="";
      ArrayList<Role> roleArray = new ArrayList<Role>();
      Role role;
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(filename);
      doc.getDocumentElement().normalize();


      NodeList trailerL = doc.getElementsByTagName("trailer");
      for (int k = 0; k < trailerL.getLength(); k++){
        ArrayList<String> neighbors = new ArrayList<String>();
        Node nNode = trailerL.item(k);
        if (nNode.getNodeType() == Node.ELEMENT_NODE){
          Element eElement = (Element) nNode;
          NodeList attributeList = eElement.getChildNodes();
          for(int z = 0; z< attributeList.getLength(); z++){
            if(attributeList.item(z) instanceof Element){
              if(attributeList.item(z).getNodeName() == "neighbors"){
                Element jones = (Element)attributeList.item(z);
                NodeList johnson = jones.getChildNodes();
                for(int w = 0; w< johnson.getLength(); w++){
                  if(johnson.item(w) instanceof Element){
                    // ADD NEIGHBORS TO Array LIST
                    neighbors.add(((Element)johnson.item(w)).getAttribute("name"));
                  }
                }
              }
              if(attributeList.item(z).getNodeName().equals("area")){
                trailerArea[0] = Integer.parseInt(((Element)attributeList.item(z)).getAttribute("x"));
                trailerArea[1] = Integer.parseInt(((Element)attributeList.item(z)).getAttribute("y"));
                trailerArea[2] = Integer.parseInt(((Element)attributeList.item(z)).getAttribute("h"));
                trailerArea[3] = Integer.parseInt(((Element)attributeList.item(z)).getAttribute("w"));
              //   System.out.println(((Element)attributeList.item(z)).getAttribute("x")+" "+((Element)attributeList.item(z)).getAttribute("y")
              //   +" "+((Element)attributeList.item(z)).getAttribute("h")+" "+((Element)attributeList.item(z)).getAttribute("w"));
              }
            }
          }
        }
        roomList.add( new Room("trailer", neighbors, trailerArea));
      }


      NodeList office = doc.getElementsByTagName("office");
      for (int k = 0; k < office.getLength(); k++){
        ArrayList<int[]> dollarMap = new ArrayList<int[]>();
        ArrayList<int[]> fameMap = new ArrayList<int[]>();
        ArrayList<String> neighbors = new ArrayList<String>();
        //System.out.println(trailerL.getLength());
        Node nNode = office.item(k);
        if (nNode.getNodeType() == Node.ELEMENT_NODE){
          Element eElement = (Element) nNode;
          NodeList attributeList = eElement.getChildNodes();
          for(int z = 0; z< attributeList.getLength(); z++){
            if(attributeList.item(z) instanceof Element){
              if(attributeList.item(z).getNodeName() == "neighbors"){
                Element jones = (Element)attributeList.item(z);
                NodeList johnson = jones.getChildNodes();
                for(int w = 0; w< johnson.getLength(); w++){
                  if(johnson.item(w) instanceof Element){
                    // ADD NEIGHBORS TO Array LIST
                    neighbors.add(((Element)johnson.item(w)).getAttribute("name"));
                    //System.out.println(((Element)johnson.item(w)).getAttribute("name"));
                  }
                }
              }
              if(attributeList.item(z).getNodeName().equals("area")){
                area[0] = Integer.parseInt(((Element)attributeList.item(z)).getAttribute("x"));
                area[1] = Integer.parseInt(((Element)attributeList.item(z)).getAttribute("y"));
                area[2] = Integer.parseInt(((Element)attributeList.item(z)).getAttribute("h"));
                area[3] = Integer.parseInt(((Element)attributeList.item(z)).getAttribute("w"));
                //  System.out.println(((Element)attributeList.item(z)).getAttribute("x")+" "+((Element)attributeList.item(z)).getAttribute("y")
                //  +" "+((Element)attributeList.item(z)).getAttribute("h")+" "+((Element)attributeList.item(z)).getAttribute("w"));
              }
              if(attributeList.item(z).getNodeName().equals("upgrades")){
                int[] amtArea = new int[6];
                Element upgrades = (Element)attributeList.item(z);
                NodeList upgrade = upgrades.getChildNodes();
                for(int w = 0; w< upgrade.getLength(); w++){
                  if(upgrade.item(w) instanceof Element){
                    amtArea = new int[6];
                    NodeList cheese = upgrade.item(w).getChildNodes();
                    int level;
                    int amt;
                    String currency = "";
                    for (int p = 0; p < cheese.getLength(); p++){
                      if (cheese.item(p) instanceof Element){
                        amtArea[2] = Integer.parseInt(((Element)cheese.item(p)).getAttribute("x"));
                        amtArea[3] = Integer.parseInt(((Element)cheese.item(p)).getAttribute("y"));
                        amtArea[4] = Integer.parseInt(((Element)cheese.item(p)).getAttribute("h"));
                        amtArea[5] = Integer.parseInt(((Element)cheese.item(p)).getAttribute("w"));
                      }
                    }
                    level = Integer.parseInt(((Element)upgrade.item(w)).getAttribute("level"));
                    amtArea[1] = level;
                    amt = Integer.parseInt(((Element)upgrade.item(w)).getAttribute("amt"));
                    amtArea[0] = amt;
                    currency = ((Element)upgrade.item(w)).getAttribute("currency");
                    if(currency.equals("dollar")){
                      dollarMap.add(amtArea);
                    }
                    if(currency.equals("credit")){
                      fameMap.add(amtArea);
                    }
                  }
                }
              }
            }
          }
        }
        roomList.add(new CastingOfficeRoom("office", neighbors, area, dollarMap, fameMap));

      }








      NodeList nList = doc.getElementsByTagName("set");
      for (int i = 0; i < nList.getLength(); i++){
        ArrayList<String> neighbors = new ArrayList<String>();
        ArrayList<int[]> shotMarkers = new ArrayList<int[]>();
        //HashMap<Integer, int[]> shotMarkers = new HashMap<Integer, int[]>();
        Node nNode = nList.item(i);
        if (nNode.getNodeType() == Node.ELEMENT_NODE){
          Element eElement = (Element) nNode;
          String name = eElement.getAttribute("name");
          // ROOM NAME
          roomName = name;
          NodeList attributeList = eElement.getChildNodes();
          for(int z = 0; z< attributeList.getLength(); z++){
            if(attributeList.item(z) instanceof Element){
              if(attributeList.item(z).getNodeName() == "neighbors"){
                Element jones = (Element)attributeList.item(z);
                NodeList johnson = jones.getChildNodes();
                for(int w = 0; w< johnson.getLength(); w++){
                  if(johnson.item(w) instanceof Element){
                    // ADD NEIGHBORS TO Array LIST
                    neighbors.add(((Element)johnson.item(w)).getAttribute("name"));
                  }
                }
              }
              if(attributeList.item(z).getNodeName() == "area"){
                area[0] = Integer.parseInt(((Element)attributeList.item(z)).getAttribute("x"));
                area[1] = Integer.parseInt(((Element)attributeList.item(z)).getAttribute("y"));
                area[2] = Integer.parseInt(((Element)attributeList.item(z)).getAttribute("h"));
                area[3] = Integer.parseInt(((Element)attributeList.item(z)).getAttribute("w"));
              }
              if(attributeList.item(z).getNodeName() == "takes"){
                //SHOT MARKERS
                Element jones = (Element)attributeList.item(z);
                NodeList johnson = jones.getChildNodes();
                for(int w = 0; w< johnson.getLength(); w++){
                  if(johnson.item(w) instanceof Element){
                    // number of shot MARKERS


                    Element jon = (Element)johnson.item(w);
                    NodeList steve = jon.getChildNodes();
                    for(int o = 0; o<steve.getLength(); o++){
                      if(steve.item(o) instanceof Element){

                        shotMarkersArea[0] = Integer.parseInt(((Element)steve.item(o)).getAttribute("x"));
                        shotMarkersArea[1] = Integer.parseInt(((Element)steve.item(o)).getAttribute("y"));
                        shotMarkersArea[2] = Integer.parseInt(((Element)steve.item(o)).getAttribute("h"));
                        shotMarkersArea[3] = Integer.parseInt(((Element)steve.item(o)).getAttribute("w"));

                        shotMarkers.add(shotMarkersArea);
                        shotMarkersArea = new int[4];
                      }
                    }
                  }
                }
              }

              //ROLE OBJECTS
              if(attributeList.item(z).getNodeName() == "parts"){

                Element jones = (Element)attributeList.item(z);

                NodeList johnson = jones.getChildNodes();
                for(int w = 0; w< johnson.getLength(); w++){
                  if(johnson.item(w) instanceof Element){
                    roleName =  ((Element)johnson.item(w)).getAttribute("name") ;
                    roleLevel = Integer.parseInt(((Element)johnson.item(w)).getAttribute("level"));

                    NodeList steve = ((Element)johnson.item(w)).getChildNodes();

                    for(int o = 0; o <steve.getLength(); o++){
                      if(steve.item(o) instanceof Element){
                        if(steve.item(o).getNodeName().equals("area")){

                        roleArea[0] = Integer.parseInt(((Element)steve.item(o)).getAttribute("x"));
                        roleArea[1] = Integer.parseInt(((Element)steve.item(o)).getAttribute("y"));
                        roleArea[2] = Integer.parseInt(((Element)steve.item(o)).getAttribute("h"));
                        roleArea[3] = Integer.parseInt(((Element)steve.item(o)).getAttribute("w"));

                        }
                        else if(steve.item(o).getNodeName().equals("line")){
                        //DESCRIPTION
                        description = ((Element)steve.item(o)).getTextContent();
                        }
                      }
                    }
                    role = new Role(roleName, description, roleLevel, roleArea, false);
                    roleArray.add(role);
                    }
                  }
                }
              }
            }
            System.out.println(shotMarkers.size());
            roomList.add(new SetRoom(roomName, roleArray.size(), roleArray, neighbors, shotMarkers, area));
            //shotMarkers.clear();
            roleArray.clear();
          }
        }
        return roomList;
      }
  catch(Exception e){
    e.printStackTrace();
  }
    return null;
  }


  public static ArrayList<SceneCard> cardXML (String filename){
    try{
      // initialize variables
      ArrayList<SceneCard> sceneList = new ArrayList<SceneCard>();
      String name;
      String img;
      int budget;
      String description = "";;
      int number = 0;
      String roleName;
      int roleLevel;
      int[] roleArea = new int[4];
      ArrayList<Role> roleArray = new ArrayList<Role>();
      //Role role;
      // use Builder to create an object to parse
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(filename);
      doc.getDocumentElement().normalize();
      // get all card nodes
      NodeList nList = doc.getElementsByTagName("card");
      for (int i = 0; i < nList.getLength(); i++){
        Node nNode = nList.item(i);
        if (nNode.getNodeType() == Node.ELEMENT_NODE){
          Element eElement = (Element) nNode;
          name = eElement.getAttribute("name");
          img = eElement.getAttribute("img");
          budget = Integer.parseInt(eElement.getAttribute("budget"));
          //get all card child nodes such as the scenes and the roles for on the card play
          NodeList attributeList = eElement.getChildNodes();
            for(int z = 0; z< attributeList.getLength(); z++){
              if(attributeList.item(z) instanceof Element){
                if(attributeList.item(z).getNodeName() == "scene"){
                  Element scene = (Element)attributeList.item(z);
                  number = Integer.parseInt(scene.getAttribute("number"));
                  description = scene.getTextContent();
                }
                // get all the roles for on the card
                if(attributeList.item(z).getNodeName() == "part"){
                  //System.out.println("made it");
                  Element part = (Element)attributeList.item(z);
                  roleName =  part.getAttribute("name");
                  roleLevel = Integer.parseInt(part.getAttribute("level"));
                  //Element jones = (Element)attributeList.item(z);
                  NodeList subNodes = part.getChildNodes();
                  for(int w = 0; w< subNodes.getLength(); w++){
                    if(subNodes.item(w) instanceof Element){
                      Element thing = (Element)subNodes.item(w);
                      if (thing.getNodeName().equals("area")){
                        roleArea[0] = Integer.parseInt(thing.getAttribute("x"));
                        roleArea[1] = Integer.parseInt(thing.getAttribute("y"));
                        roleArea[2] = Integer.parseInt(thing.getAttribute("h"));
                        roleArea[3] = Integer.parseInt(thing.getAttribute("w"));
                      }
                      else if(thing.getNodeName().equals("line")){
                        //DESCRIPTION of role
                        description = thing.getTextContent();
                      }
                    }
                  }
                  Role role = new Role(roleName, description, roleLevel, roleArea, true);
                  roleArray.add(role);
                }
              }
            }
            sceneList.add(new SceneCard(name, img, budget, number, description, roleArray));
            roleArray.clear();
          }
        }
        return sceneList;
      }
      catch(Exception e){
        e.printStackTrace();
      }
      return null;
    }
  }
