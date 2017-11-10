import java.util.LinkedList;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;


public class XML_Test{

  public static void main(String[] args){
  boardXML("board.xml");
  }

  private static void boardXML(String filename){
    try{
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
          System.out.println(name);
          ///////////////////////////////////////////////////////////////////
          NodeList attributeList = eElement.getChildNodes();
          //for (int y = 0; y < attributeList.getLength(); y++){
            // if (bacon.getNodeType() == Node.ELEMENT_NODE){
            //   Element aElement = (Element) bacon;
            //   String cheese = aElement.getAttribute("name");
            //   System.out.println(cheese);
            // }

            for(int z = 0; z< attributeList.getLength(); z++){
              if(attributeList.item(z) instanceof Element){
                System.out.println(attributeList.item(z).getNodeName());

                //System.out.println();
                if(attributeList.item(z).getNodeName() == "neighbors"){
                  Element jones = (Element)attributeList.item(z);
                  NodeList johnson = jones.getChildNodes();
                  for(int w = 0; w< johnson.getLength(); w++){
                    if(johnson.item(w) instanceof Element){
                      System.out.println(johnson.item(w).getNodeName() + " " + ((Element)johnson.item(w)).getAttribute("name"));
                    }
                  }
                }

                if(attributeList.item(z).getNodeName() == "area"){
                  System.out.println(" " + ((Element)attributeList.item(z)).getAttribute("x")
                                   + " " + ((Element)attributeList.item(z)).getAttribute("y")
                                   + " " + ((Element)attributeList.item(z)).getAttribute("h")
                                   + " " + ((Element)attributeList.item(z)).getAttribute("w"));
                }


                // if(attributeList.item(z).getNodeName() == "area"){
                //   Element jones = (Element)attributeList.item(z);
                //   NodeList johnson = jones.getChildNodes();
                //   for(int w = 0; w< johnson.getLength(); w++){
                //     if(johnson.item(w) instanceof Element){
                //       System.out.println(johnson.item(w).getNodeName() + " " + ((Element)johnson.item(w)).getAttribute("x") + " " + ((Element)johnson.item(w)).getAttribute("y")
                //                                                        + " " + ((Element)johnson.item(w)).getAttribute("h") + " " + ((Element)johnson.item(w)).getAttribute("w"));
                //     }
                //   }
                // }
                if(attributeList.item(z).getNodeName() == "takes"){
                  Element jones = (Element)attributeList.item(z);
                  NodeList johnson = jones.getChildNodes();
                  for(int w = 0; w< johnson.getLength(); w++){
                    if(johnson.item(w) instanceof Element){
                      System.out.print(johnson.item(w).getNodeName() + " " + ((Element)johnson.item(w)).getAttribute("number"));

                      Element jon = (Element)johnson.item(w);
                      NodeList steve = jon.getChildNodes();
                      for(int o = 0; o<steve.getLength(); o++){
                        if(steve.item(o) instanceof Element){
                          System.out.println(" " + ((Element)steve.item(o)).getAttribute("x")
                                           + " " + ((Element)steve.item(o)).getAttribute("y")
                                           + " " + ((Element)steve.item(o)).getAttribute("h")
                                           + " " + ((Element)steve.item(o)).getAttribute("w"));
                        }
                      }
                    }
                  }
                }
                if(attributeList.item(z).getNodeName() == "parts"){
                  //jones = parts
                  Element jones = (Element)attributeList.item(z);
                  //johnson =list of part nodes
                  NodeList johnson = jones.getChildNodes();
                  for(int w = 0; w< johnson.getLength(); w++){
                    if(johnson.item(w) instanceof Element){
                      System.out.println(johnson.item(w).getNodeName() + " " + ((Element)johnson.item(w)).getAttribute("name") + " level = " + ((Element)johnson.item(w)).getAttribute("level"));

                      //jon = area
                      // Element jon = (Element)johnson.item(w);
                      // //steve = area or line
                      NodeList steve = ((Element)johnson.item(w)).getChildNodes();

                      // Element area = (Element)steve.item(0);
                      // Element line = (Element)steve.item(1);
                      for(int o = 0; o <steve.getLength(); o++){
                        if(steve.item(o) instanceof Element){
                          if(steve.item(o).getNodeName().equals("area")){
                          System.out.println(" area =" + " " + ((Element)steve.item(o)).getAttribute("x")
                                           + " " + ((Element)steve.item(o)).getAttribute("y")
                                           + " " + ((Element)steve.item(o)).getAttribute("h")
                                           + " " + ((Element)steve.item(o)).getAttribute("w"));
                          }
                          else if(steve.item(o).getNodeName().equals("line")){
                          System.out.println(" line =" + ((Element)steve.item(o)).getTextContent());
                          }
                        }
                      }
                      // //Element cheese = (Element)johnson.item(w);
                      // // NodeList bacon = cheese.getChildNodes();
                      //  System.out.println(line.getTextContent());
                    }
                  }
                }
                //System.out.println();
                }
              }


                // NamedNodeMap innerStuff = attributeList.item(z).getAttributes();
                // for(int w=0; w< innerStuff.getLength(); w++){
                //   Node attr = innerStuff.item(i);
                //   System.out.println(attr.getNodeName() + " = " + attr.getNodeValue());
                // }



            // Node bacon = attributeList.item(y);
            //System.out.println(attributeList.item(y).getAttributes().getNamedItem("name").getNodeValue());

          //  }
          }
          //int takes = eElement.getElementsByTagName("takes").getLength();
          // Node takes = eElement.getElementsByTagName("takes").item(0);
          // takes = (Element) takes;
          // int numTakes = takes.getElementsByTagName("take").getLength();
        //  SetRoom newRoom = new SetRoom();
          // System.out.println(numTakes);
        }
      }
  catch(Exception e){
    e.printStackTrace();
  }

  }

}
