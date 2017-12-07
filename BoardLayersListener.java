/*

   Deadwood GUI helper file
   Author: Moushumi Sharmin
   This file shows how to create a simple GUI using Java Swing and Awt Library
   Classes Used: JFrame, JLabel, JButton, JLayeredPane

*/


import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;



public class BoardLayersListener extends JFrame {

  // Private Attributes
  ArrayList<JLabel> players = new ArrayList<JLabel>();
  ArrayList<JLabel> cards = new ArrayList<JLabel>();

  // JLabels
  JLabel boardlabel;
  JLabel cardlabel;
  JLabel playerlabel;
  JLabel mLabel;
  JLabel sLabel;

  //JButtons
  JButton bAct;
  JButton bRehearse;
  JButton bMove;
  JButton bWork;
  JButton bEnd;
  JButton selectButton;
  JButton bUpgrade;


  String current = "";
  // JLayered Pane
  JLayeredPane bPane;

  JComboBox<String> comboBox;
  ImageIcon icon;
  ImageIcon shotMarkers;
  ArrayList<JLabel> shotMarkerList;

  boardMouseListener bActListener;
  boardMouseListener bRehearseListener;
  boardMouseListener bMoveListener;
  boardMouseListener bWorkListener;
  boardMouseListener bEndListener;
  boardMouseListener bUpgradeListener;
  boardMouseListener bML;
  // Constructor

  JTextArea textArea;
  JTextArea rollArea;

  public BoardLayersListener() {

      super("DeadWood");


      setDefaultCloseOperation(EXIT_ON_CLOSE);

      bPane = getLayeredPane();
       // Create the deadwood board
       boardlabel = new JLabel();
       icon =  new ImageIcon("board.jpg");
       boardlabel.setIcon(icon);
       boardlabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());

       // Add the board to the lowest layer
       bPane.add(boardlabel, new Integer(0));

       // Set the size of the GUI
       setSize(icon.getIconWidth()+400,icon.getIconHeight());

       // Add a scene card to this room
      //  cardlabel = new JLabel();
      //  ImageIcon cIcon =  new ImageIcon("01.png");
      //  cardlabel.setIcon(cIcon);
      //  cardlabel.setBounds(20,60,cIcon.getIconWidth(),cIcon.getIconHeight());
      //  cardlabel.setOpaque(true);

       // Add the card to the lower layer
      // bPane.add(cardlabel, new Integer(1));


       // Add a dice to represent a player.
       // Role for Crusty the prospector. The x and y co-ordiantes are taken from Board.xml file
       //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());


       // Create the Menu for action buttons
       mLabel = new JLabel("MENU");
       mLabel.setBounds(icon.getIconWidth()+40,0,100,20);
       bPane.add(mLabel,new Integer(2));


       // Create Action buttons
       bAct = new JButton("ACT");
       bAct.setBackground(Color.white);
       bAct.setBounds(icon.getIconWidth()+10, 30,100, 20);
       bActListener = new boardMouseListener();
       bAct.addMouseListener(bActListener);

       bRehearse = new JButton("REHEARSE");
       bRehearse.setBackground(Color.white);
       bRehearse.setBounds(icon.getIconWidth()+10,60,100, 20);
       bRehearseListener = new boardMouseListener();
       bRehearse.addMouseListener(bRehearseListener);

       bMove = new JButton("MOVE");
       bMove.setBackground(Color.white);
       bMove.setBounds(icon.getIconWidth()+10,90,100, 20);
       bMoveListener = new boardMouseListener();
       bMove.addMouseListener(bMoveListener);

       bWork = new JButton("WORK");
       bWork.setBackground(Color.white);
       bWork.setBounds(icon.getIconWidth()+10,120,100, 20);
       bWorkListener = new boardMouseListener();
       bWork.addMouseListener(bWorkListener);

       bEnd = new JButton("END");
       bEnd.setBackground(Color.white);
       bEnd.setBounds(icon.getIconWidth()+10,150,100, 20);
       bEndListener = new boardMouseListener();
       bEnd.addMouseListener(bEndListener);

       bUpgrade = new JButton("Upgrade");
       bUpgrade.setBackground(Color.white);
       bUpgrade.setBounds(icon.getIconWidth()+10,180,100, 20);
       bUpgradeListener = new boardMouseListener();
       bUpgrade.addMouseListener(bUpgradeListener);

       selectButton = new JButton("Select");
       selectButton.setBackground(Color.white);
       selectButton.setBounds(icon.getIconWidth()+10,210,100, 20);

       textArea = new JTextArea();
       textArea.setPreferredSize(new Dimension(100,100));
       textArea.setBounds(icon.getIconWidth()+10,775,200,200);
       textArea.setEditable(false);
       textArea.setBackground(bPane.getBackground());
       textArea.setFont(textArea.getFont().deriveFont(18f));

       rollArea = new JTextArea();
       rollArea.setPreferredSize(new Dimension(100,300));
       rollArea.setBounds(icon.getIconWidth()+10+120,30,200,50);
       rollArea.setEditable(false);
       rollArea.setBackground(bPane.getBackground());
       rollArea.setFont(rollArea.getFont().deriveFont(12f));

       shotMarkerList = new ArrayList<JLabel>();

       sLabel = new JLabel();
       shotMarkers = new ImageIcon("shot.png");
       sLabel.setIcon(shotMarkers);


       // Place the action buttons in the top layer
       bPane.add(bAct, new Integer(2));
       bPane.add(bRehearse, new Integer(2));
       bPane.add(bMove, new Integer(2));
       bPane.add(bWork, new Integer(2));
       bPane.add(bEnd, new Integer(2));
       bPane.add(selectButton, new Integer(2));
       bPane.add(bUpgrade ,new Integer(2));
       bPane.add(textArea, new Integer(2));
       bPane.add(rollArea, new Integer(2));
       selectButton.setVisible(false);


  }
  int sMSize = 0;

  public void resetShotMarkers(SetRoom room){
    sMSize = shotMarkerList.size();
    int[] markerArea = new int[4];
    System.out.println("bacon");

    for (int i = 0; i < room.mapSize(); i++){
      markerArea = room.shotMarkerData.get(i);
      shotMarkerList.add(new JLabel());
      shotMarkerList.get(i + sMSize).setIcon(new ImageIcon("shot.png"));
      bPane.add(shotMarkerList.get(i + sMSize), new Integer(2));
      shotMarkerList.get(i + sMSize).setBounds(markerArea[0], markerArea[1], markerArea[2], markerArea[3]);
      System.out.println(markerArea[0] + "  " +  markerArea[1] + "  " +  markerArea[2] + "  " +  markerArea[3]);
    }

  }

  public int startGame(){
    System.out.println("entered ");
    greyOut("act");
    greyOut("rehearse");
    greyOut("work");
    greyOut("move");
    greyOut("upgrade");
    greyOut("end");
    String[] start = {"2 Players", "3 Players", "4 Players", "5 Players",
                      "6 Players", "7 Players", "8 Players"};
    return (moveHelper(start)+2);

  }

  public void removeShotMarker(int[] areaArray){
    System.out.println("0 " + areaArray[0]);
    System.out.println("1 " + areaArray[1]);


    for(JLabel label : shotMarkerList){
      System.out.println("horizontal " + label.getX());
      System.out.println("vertical " +label.getY());
      if(label.getX() == areaArray[0] && label.getY() == areaArray[1]){

        label.setVisible(false);
      }
    }
  }

  public void movePlayer(Player player){
    Room room = player.getRoom();
    System.out.println(player.getPlayerNum());
    JLabel playerIMG = players.get(player.getPlayerNum());
    int[] area = room.getArea();
    if (room instanceof SetRoom){
      playerIMG.setBounds(area[0]+player.getPlayerNum()*40, area[1]+120, 47, 47);
    }
    else{
      int index = player.getPlayerNum();
      if(index > 2 && index < 6){
        players.get(index).setBounds(area[0] + 60 * (index -3), area[1] + 50, 47, 47);
      }
      else if(index >= 6){
        players.get(index).setBounds(area[0] + 60 * (index -6), area[1] + 100, 47, 47);
      }
      else{
        players.get(index).setBounds(area[0] + 60 * index, area[1], 47, 47);
      }
    }
  }



  public void flipCard(SetRoom room){
    SceneCard card = room.getSC();
    if (card != null){
      int[] area = room.getArea();
      JLabel cardIMG = new JLabel();
      cards.add(cardIMG);
      ImageIcon icon = new ImageIcon(card.getImg());
      cardIMG.setIcon(icon);
      cardIMG.setBounds(area[0], area[1], icon.getIconWidth(), icon.getIconHeight());
      bPane.add(cardIMG, new Integer(3));
    }
    //System.out.println(room.getSC());
  }

  public void NUKECARDS(){
    cards.clear();
  }


  public void moveToRole(Player player, int[] area){
    SetRoom room = (SetRoom)(player.getRoom());
    int[] roomArea = room.getRoomArea();
    JLabel playerIMG = players.get(player.getPlayerNum());
    System.out.print(player.getRole().getName());
    if (player.getRole().getOnCard()){
      playerIMG.setBounds(roomArea[0] + area[0], roomArea[1] + area[1], area[2], area[3]);
    }
    else{
      playerIMG.setBounds(area[0], area[1], area[2], area[3]);
    }
  }

  public void changeRollArea(int diceRoll, int budget){
    try{
      greyOut("act");
      greyOut("rehearse");
      greyOut("work");
      greyOut("move");
      greyOut("upgrade");
      if (diceRoll >= budget){
        rollArea.setText("");
        rollArea.append("Congrats! you Rolled : " + diceRoll + "\n" +
                        "The Scene can now progress!");
      }
      else{
        rollArea.setText("");
        rollArea.append("Bummer! you Rolled : " + diceRoll + "\n"
                        + "Best of luck next time partner!");
      }
      current = "";
      while(current.equals("")){
        System.out.println("");
      }
      current = "";

    }
    catch(Exception e){
      e.printStackTrace();
    }
  }




  public void createPlayers(int numberOfPlayers, Room trailer){
    String[] colors = {"b","c","g","o","r","p","v","y"};
    int[] area = trailer.area;
    int index = 0;
    String extension = ".png";
    if(numberOfPlayers < 7){
      extension = "1"+extension;
    }
    else{
      extension = "2"+extension;
    }
      for(String s: colors){
        if(index >= numberOfPlayers){
          break;
        }
        players.add(new JLabel());
        players.get(players.size()-1).setIcon(new ImageIcon(s+extension));
        bPane.add(players.get(players.size()-1), new Integer(3));
        if(index > 2 && index < 6){
          players.get(index).setBounds(area[0] + 60 * (index -3), area[1] + 50, 47, 47);
        }
        else if(index >= 6){
          players.get(index).setBounds(area[0] + 60 * (index -6), area[1] + 100, 47, 47);
        }
        else{
          players.get(index).setBounds(area[0] + 60 * index, area[1], 47, 47);
        }
        index++;
      }
    }



  public void changeTextArea(Player currentPlayer, int playerNum){
    textArea.setText("");
    textArea.append("Player : " + playerNum + "\n" +
                    "Rank : " + currentPlayer.getRank() + "\n" +
                    "Money : " + currentPlayer.getMoney() + "\n" +
                    "Fame : " + currentPlayer.getFame() + "\n");
    if (currentPlayer.getRole() != null){
      textArea.append("Rehearsal Bonus : " + currentPlayer.getRehearsalBonuses());
    }
  }


  public void greyOut(String action){

      switch(action){
        case "all":   bMove.removeMouseListener(bMoveListener);
                      bRehearse.removeMouseListener(bRehearseListener);
                      bAct.removeMouseListener(bActListener);
                      bWork.removeMouseListener(bWorkListener);
                      bUpgrade.removeMouseListener(bUpgradeListener);

        case "move":  bMove.setBackground(Color.gray);
                      bMove.removeMouseListener(bMoveListener);
                      break;
        case "rehearse":  bRehearse.setBackground(Color.gray);
                          bRehearse.removeMouseListener(bRehearseListener);
                          break;
        case "act": bAct.setBackground(Color.gray);
                    bAct.removeMouseListener(bActListener);
                    break;
        case "work":  bWork.setBackground(Color.gray);
                      bWork.removeMouseListener(bWorkListener);
                      break;
        case "upgrade": bUpgrade.setBackground(Color.gray);
                        bUpgrade.removeMouseListener(bUpgradeListener);
                        break;
        case "select" : selectButton.setBackground(Color.gray);
                        break;
        case "end" : bEnd.setBackground(Color.gray);
                     bEnd.removeMouseListener(bEndListener);
                     break;

      }
    }


    public void whiteAgain(){
      greyOut("all");
      //System.out.println("in white again");
      rollArea.setText("");

      bAct.setBackground(Color.white);
      bRehearse.setBackground(Color.white);
      bMove.setBackground(Color.white);
      bWork.setBackground(Color.white);
      bEnd.setBackground(Color.white);
      bUpgrade.setBackground(Color.white);
      bAct.addMouseListener(bActListener);
      bRehearse.addMouseListener(bRehearseListener);
      bMove.addMouseListener(bMoveListener);
      bWork.addMouseListener(bWorkListener);
      bEnd.addMouseListener(bEndListener);
      bUpgrade.addMouseListener(bUpgradeListener);
    }

  boolean clicked = false;
  int temp;

  public int moveHelper(String[] options){
    clicked = false;
    temp = 0;
    comboBox = new JComboBox<String>(options);
    comboBox.setSelectedIndex(0);
    comboBox.setVisible(true);
    String[] choice = options[0].split(" ");
    System.out.println(choice[0]);
    if(choice[0].equals("dollar") || choice[0].equals("fame")){
      greyOut("upgrade");
      greyOut("end");
      comboBox.setBounds(icon.getIconWidth()+10+120,180,250, 20);
    }
    else if(choice[0].equals("(on") || choice[0].equals("(off")){
      greyOut("end");
      comboBox.setBounds(icon.getIconWidth()+10+120,120,250, 20);

    }
    else{
      greyOut("move");
      greyOut("end");
      greyOut("work");
      comboBox.setBounds(icon.getIconWidth()+10+120,90,250, 20);
    }

    bPane.add(comboBox, new Integer(2));
    bML = new boardMouseListener();
    selectButton.addMouseListener(bML);
    selectButton.setVisible(true);
    while(clicked == false){

      System.out.print("");

    }
    selectButton.setVisible(false);
    selectButton.removeMouseListener(bML);
    comboBox.setVisible(false);
    bPane.remove(comboBox);
    return temp;
  }

  // This class implements Mouse Events

  class boardMouseListener implements MouseListener{
      // Code for the different button clicks
      public void mouseClicked(MouseEvent e) {

         if (e.getSource()== bAct){
            current = "act";
         }
         else if (e.getSource()== bWork){
            current = "work";
         }
         else if (e.getSource()== bRehearse){
            current = "rehearse";
         }
         else if (e.getSource()== bMove){

            current = "move Saloon";

         }
         else if (e.getSource() == bEnd){
           current = "end";
         }
         else if (e.getSource() == selectButton){
           temp = comboBox.getSelectedIndex();
           clicked = true;
         }
         else if (e.getSource() == bUpgrade){
           current = "upgrade";
         }
      }
      public void mousePressed(MouseEvent e) {

      }
      public void mouseReleased(MouseEvent e) {
      }
      public void mouseEntered(MouseEvent e) {
      }
      public void mouseExited(MouseEvent e) {
      }
   }

}
