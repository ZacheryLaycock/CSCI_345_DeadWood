import java.util.LinkedList;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import java.util.LinkedList;
import java.util.HashMap;

public class XML_Test{


  public static void main(String[] args){
  boardXML("board.xml");
  cardXML("card.xml");
  }

  public static ArrayList<Room> boardXML(String filename){
    try{
      ArrayList<Room> roomList = new ArrayList<Room>();
      String roomName;
      String roleName;
      int roleLevel;
      LinkedList<String> neighbors = new LinkedList<String>();
      int[] area = new int[4];
      int[] roleArea = new int[4];
      int[] shotMarkersArea = new int[4];
      String description ="";
      ArrayList<Role> roleArray = new ArrayList<Role>();
      Role role;
      HashMap<Integer, int[]> shotMarkers = new HashMap<Integer, int[]>();

      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(filename);
      doc.getDocumentElement().normalize();


      NodeList trailerL = doc.getElementsByTagName("trailer");
      for (int k = 0; k < trailerL.getLength(); k++){
        //System.out.println(trailerL.getLength());
        Node nNode = trailerL.item(k);
        if (nNode.getNodeType() == Node.ELEMENT_NODE){
          Element eElement = (Element) nNode;
          NodeList attributeList = eElement.getChildNodes();
          for(int z = 0; z< attributeList.getLength(); z++){
            if(attributeList.item(z) instanceof Element){
              neighbors.clear();

              if(attributeList.item(z).getNodeName() == "neighbors"){
                Element jones = (Element)attributeList.item(z);
                NodeList johnson = jones.getChildNodes();
                for(int w = 0; w< johnson.getLength(); w++){
                  if(johnson.item(w) instanceof Element){
                    // ADD NEIGHBORS TO LINKED LIST
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
              //   System.out.println(((Element)attributeList.item(z)).getAttribute("x")+" "+((Element)attributeList.item(z)).getAttribute("y")
              //   +" "+((Element)attributeList.item(z)).getAttribute("h")+" "+((Element)attributeList.item(z)).getAttribute("w"));
              }
            }
          }
        }
        roomList.add( new Room("Trailer", neighbors, area));
      }





      NodeList nList = doc.getElementsByTagName("set");

      for (int i = 0; i < nList.getLength(); i++){
        Node nNode = nList.item(i);
        if (nNode.getNodeType() == Node.ELEMENT_NODE){
          Element eElement = (Element) nNode;
          String name = eElement.getAttribute("name");
          // ROOM NAME
          roomName = name;
          NodeList attributeList = eElement.getChildNodes();
          //System.out.println(name);


            for(int z = 0; z< attributeList.getLength(); z++){
              if(attributeList.item(z) instanceof Element){
                neighbors.clear();

                if(attributeList.item(z).getNodeName() == "neighbors"){
                  Element jones = (Element)attributeList.item(z);
                  NodeList johnson = jones.getChildNodes();
                  for(int w = 0; w< johnson.getLength(); w++){
                    if(johnson.item(w) instanceof Element){
                      // ADD NEIGHBORS TO LINKED LIST
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

                          shotMarkers.put(w, shotMarkersArea);
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
            roomList.add(new SetRoom(roomName, roleArray.size(), roleArray, neighbors, shotMarkers, area));
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
      Role role;
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
                          //DESCRIPTION of role
                          description = ((Element)steve.item(o)).getTextContent();
                          }
                        }
                      }
                      role = new Role(roleName, description, roleLevel, roleArea, true);
                      roleArray.add(role);
                    }
                  }
                }
              }
            }
            sceneList.add(new SceneCard(name, img, budget, number, description, roleArray));
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
