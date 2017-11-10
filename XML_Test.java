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
      NodeList nList = doc.getElementsByTagName("set");
      for (int i = 0; i < nList.getLength(); i++){
        Node nNode = nList.item(i);
        if (nNode.getNodeType() == Node.ELEMENT_NODE){
          Element eElement = (Element) nNode;
          String name = eElement.getAttribute("name");
          //System.out.println(name);
          // ROOM NAME
          roomName = name;
          NodeList attributeList = eElement.getChildNodes();


            for(int z = 0; z< attributeList.getLength(); z++){
              if(attributeList.item(z) instanceof Element){
                //System.out.println(attributeList.item(z).getNodeName());
                neighbors.clear();

                if(attributeList.item(z).getNodeName() == "neighbors"){
                  Element jones = (Element)attributeList.item(z);
                  NodeList johnson = jones.getChildNodes();
                  for(int w = 0; w< johnson.getLength(); w++){
                    if(johnson.item(w) instanceof Element){
                      //System.out.println(johnson.item(w).getNodeName() + " " + ((Element)johnson.item(w)).getAttribute("name"));
                      // ADD NEIGHBORS TO LINKED LIST
                      neighbors.add(((Element)johnson.item(w)).getAttribute("name"));
                    }
                  }
                }

                if(attributeList.item(z).getNodeName() == "area"){
                  //System.out.println(" " + ((Element)attributeList.item(z)).getAttribute("x")
                  //                 + " " + ((Element)attributeList.item(z)).getAttribute("y")
                  //                 + " " + ((Element)attributeList.item(z)).getAttribute("h")
                  //                 + " " + ((Element)attributeList.item(z)).getAttribute("w"));
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
                      //System.out.print(johnson.item(w).getNodeName() + " " + ((Element)johnson.item(w)).getAttribute("number"));
                      // number of shot MARKERS


                      Element jon = (Element)johnson.item(w);
                      NodeList steve = jon.getChildNodes();
                      for(int o = 0; o<steve.getLength(); o++){
                        if(steve.item(o) instanceof Element){
                          // System.out.println(" " + ((Element)steve.item(o)).getAttribute("x")
                          //                  + " " + ((Element)steve.item(o)).getAttribute("y")
                          //                  + " " + ((Element)steve.item(o)).getAttribute("h")
                          //                  + " " + ((Element)steve.item(o)).getAttribute("w"));
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
                  //jones = parts
                  Element jones = (Element)attributeList.item(z);
                  //johnson =list of part nodes
                  NodeList johnson = jones.getChildNodes();
                  for(int w = 0; w< johnson.getLength(); w++){
                    if(johnson.item(w) instanceof Element){
                      //System.out.println(johnson.item(w).getNodeName() + " " + ((Element)johnson.item(w)).getAttribute("name") + " level = " + ((Element)johnson.item(w)).getAttribute("level"));
                      roleName =  ((Element)johnson.item(w)).getAttribute("name") ;
                      roleLevel = Integer.parseInt(((Element)johnson.item(w)).getAttribute("level"));
                      //jon = area
                      // Element jon = (Element)johnson.item(w);
                      // //steve = area or line
                      NodeList steve = ((Element)johnson.item(w)).getChildNodes();

                      // Element area = (Element)steve.item(0);
                      // Element line = (Element)steve.item(1);
                      for(int o = 0; o <steve.getLength(); o++){
                        if(steve.item(o) instanceof Element){
                          if(steve.item(o).getNodeName().equals("area")){
                          // System.out.println(" area =" + " " + ((Element)steve.item(o)).getAttribute("x")
                          //                  + " " + ((Element)steve.item(o)).getAttribute("y")
                          //                  + " " + ((Element)steve.item(o)).getAttribute("h")
                          //                  + " " + ((Element)steve.item(o)).getAttribute("w"));
                          roleArea[0] = Integer.parseInt(((Element)steve.item(o)).getAttribute("x"));
                          roleArea[1] = Integer.parseInt(((Element)steve.item(o)).getAttribute("y"));
                          roleArea[2] = Integer.parseInt(((Element)steve.item(o)).getAttribute("h"));
                          roleArea[3] = Integer.parseInt(((Element)steve.item(o)).getAttribute("w"));

                          }
                          else if(steve.item(o).getNodeName().equals("line")){
                          //System.out.println(" line =" + ((Element)steve.item(o)).getTextContent());
                          //DESCRIPTION
                          description = ((Element)steve.item(o)).getTextContent();
                          }
                        }
                      }
                      role = new Role(roleName, description, roleLevel, roleArea);
                      roleArray.add(role);
                      //System.out.println(role.toString());

                    }
                  }
                }
              }
            }
            roomList.add(new SetRoom(roomName, roleArray.size(), roleArray, neighbors, shotMarkers));
          }
        }
        return roomList;
      }
  catch(Exception e){
    e.printStackTrace();
  }
    return null;
  }

}
