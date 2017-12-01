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


public class BoardLayersListener extends JFrame {

  // Private Attributes

  // JLabels
  JLabel boardlabel;
  JLabel cardlabel;
  JLabel playerlabel;
  JLabel mLabel;

  //JButtons
  JButton bAct;
  JButton bRehearse;
  JButton bMove;
  JButton bStart;
  JButton bWork;
  JButton bEnd;
  JButton selectButton;
  JButton bUpgrade;

  String current = "";
  // JLayered Pane
  JLayeredPane bPane;

  JComboBox<String> comboBox;
  ImageIcon icon;

  boardMouseListener bActListener;
  boardMouseListener bRehearseListener;
  boardMouseListener bMoveListener;
  boardMouseListener bStartListener;
  boardMouseListener bWorkListener;
  boardMouseListener bEndListener;
  boardMouseListener bUpgradeListener;
  // Constructor

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
       setSize(icon.getIconWidth()+200,icon.getIconHeight());

       // Add a scene card to this room
       cardlabel = new JLabel();
       ImageIcon cIcon =  new ImageIcon("01.png");
       cardlabel.setIcon(cIcon);
       cardlabel.setBounds(20,60,cIcon.getIconWidth(),cIcon.getIconHeight());
       cardlabel.setOpaque(true);

       // Add the card to the lower layer
       bPane.add(cardlabel, new Integer(1));


       // Add a dice to represent a player.
       // Role for Crusty the prospector. The x and y co-ordiantes are taken from Board.xml file
       playerlabel = new JLabel();
       ImageIcon pIcon = new ImageIcon("r2.png");
       playerlabel.setIcon(pIcon);
       //playerlabel.setBounds(114,227,pIcon.getIconWidth(),pIcon.getIconHeight());
       playerlabel.setBounds(114,227,46,46);
       bPane.add(playerlabel,new Integer(3));

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

       bStart = new JButton("START");
       bStart.setBackground(Color.white);
       bStart.setBounds(icon.getIconWidth()+10,120,100, 20);
       bStartListener = new boardMouseListener();
       bStart.addMouseListener(bStartListener);

       bWork = new JButton("WORK");
       bWork.setBackground(Color.white);
       bWork.setBounds(icon.getIconWidth()+10,150,100, 20);
       bWorkListener = new boardMouseListener();
       bWork.addMouseListener(bWorkListener);

       bEnd = new JButton("END");
       bEnd.setBackground(Color.white);
       bEnd.setBounds(icon.getIconWidth()+10,180,100, 20);
       bEndListener = new boardMouseListener();
       bEnd.addMouseListener(bEndListener);

       bUpgrade = new JButton("Upgrade");
       bUpgrade.setBackground(Color.white);
       bUpgrade.setBounds(icon.getIconWidth()+10,210,100, 20);
       bUpgradeListener = new boardMouseListener();
       bUpgrade.addMouseListener(bUpgradeListener);

       selectButton = new JButton("Select");
       selectButton.setBackground(Color.white);
       selectButton.setBounds(icon.getIconWidth()+10,500,100, 20);



       // Place the action buttons in the top layer
       bPane.add(bAct, new Integer(2));
       bPane.add(bRehearse, new Integer(2));
       bPane.add(bMove, new Integer(2));
       bPane.add(bStart, new Integer(2));
       bPane.add(bWork, new Integer(2));
       bPane.add(bEnd, new Integer(2));
       bPane.add(selectButton, new Integer(2));
       bPane.add(bUpgrade ,new Integer(2));
       selectButton.setVisible(false);
;

  }


  public void greyOut(String action){

      switch(action){
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

      }
    }


    public void whiteAgain(){
      System.out.println("in white again");

      bAct.setBackground(Color.white);
      bRehearse.setBackground(Color.white);
      bMove.setBackground(Color.white);
      bStart.setBackground(Color.white);
      bWork.setBackground(Color.white);
      bEnd.setBackground(Color.white);
      bUpgrade.setBackground(Color.white);
      bAct.addMouseListener(bActListener);
      bRehearse.addMouseListener(bRehearseListener);
      bMove.addMouseListener(bMoveListener);
      bStart.addMouseListener(bStartListener);
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
    comboBox.setBounds(icon.getIconWidth()+10,410,100, 20);
    bPane.add(comboBox, new Integer(2));
    boardMouseListener bML = new boardMouseListener();
    selectButton.addMouseListener(bML);
    selectButton.setVisible(true);
    while(clicked == false){

      System.out.print("");

    }
    selectButton.setVisible(false);
    selectButton.removeMouseListener(bML);
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
