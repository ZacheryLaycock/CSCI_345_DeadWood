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
import java.util.Collections;
import java.util.Random;
import java.lang.Math;


class BoardManager{

  BoardLayersListener board;
  // game objects
  Random random = new Random();
  ArrayList<Player> listOfPlayer = new ArrayList<Player>();
  ArrayList<Integer> playerOrder;
  Bank bank;
  Dice dice;
  LocationManager locationManager;
  RehearsalManager rehearsalManager;
  String buffer = "";
  ArrayList<Room> roomList = new ArrayList<Room>();
  ArrayList<SceneCard> cardList = new ArrayList<SceneCard>();
  CastingOfficeRoom castingOffice;
  int numberOfDays;
  int numberOfPlayers;
  int numberOfRemainingRoom;


  public BoardManager(){



    setUp();
    for (Room room:roomList){
      if (room.getName().equals("office")){
        this.castingOffice = (CastingOfficeRoom) room;
      }
    }
    ///////////////////////////Testing


    ////////////////////////////
    play();
  }

  public void play(){
    //main game loop
    for (Room i : roomList){
      if(i instanceof SetRoom){
        SetRoom sRoom = (SetRoom) i;
        //System.out.println("Room name = " + sRoom.getName() + "SceneCard name" + sRoom.getSC().getName());
      }
    }
    while (numberOfDays != 0){
      //decrement days after all but 1 room complete
      while(numberOfRemainingRoom != 1){
        for (int currentPlayer = 0; currentPlayer < numberOfPlayers; currentPlayer++){
          System.out.println("Player "+(currentPlayer+1)+"'s turn");
          board.changeTextArea(listOfPlayer.get(currentPlayer),currentPlayer + 1);
          playerAction(currentPlayer);
        }
      }
      numberOfDays--;
      resetBoard();
    }
    endGame();
  }

  private void disableButton(Player currentPlayer){
    if(!(currentPlayer.currentRoom instanceof SetRoom)){
      if(currentPlayer.currentRoom.getName().equals("trailer")){
        board.greyOut("upgrade");
        //System.out.println("b");

      }
      ArrayList<int[]> dollarMap = castingOffice.dollarMap;
      ArrayList<int[]> fameMap = castingOffice.fameMap;
      int playerRank = currentPlayer.getRank();
      int playerMoney = currentPlayer.getMoney();
      int playerFame = currentPlayer.getFame();
      if(playerRank == 6){
        board.greyOut("upgrade");
      }
      else if(playerMoney < dollarMap.get(playerRank-1)[0] && playerFame < dollarMap.get(playerRank-1)[0]){
        board.greyOut("upgrade");
      }
      //System.out.println("a");
      board.greyOut("work");
      board.greyOut("act");
      board.greyOut("rehearse");

    }
    else if(currentPlayer.currentRoom instanceof SetRoom){
      SetRoom tempRoom = (SetRoom)currentPlayer.currentRoom;
      if (tempRoom.done == true){
        //System.out.println("sadlk;fhasdkfhsakdlfhasdlkfjhh");
        board.greyOut("work");
      }
      if (tempRoom.getSC() != null){
        // System.out.println(currentPlayer.getRehearsalBonuses() + " OOOOOOOOOOO");
        // System.out.println((tempRoom.getSC().budget - 1) + " kkkkkkkkkkkkk");
        if (currentPlayer.getRehearsalBonuses() == (tempRoom.getSC().budget - 1)){
          board.greyOut("rehearse");
        }
      }

      if(currentPlayer.currentRole == null){
        //System.out.println("c");
        board.greyOut("act");
        board.greyOut("rehearse");
        board.greyOut("upgrade");
      }
      else{
        //System.out.println("d");
        board.greyOut("move");
        board.greyOut("work");
        board.greyOut("upgrade");
      }
    }
  }


  private void upgrade(Player currentPlayer){
    ArrayList<int[]> dollarMap = castingOffice.dollarMap;
    ArrayList<int[]> fameMap = castingOffice.fameMap;
    int playerRank = currentPlayer.getRank();
    int playerMoney = currentPlayer.getMoney();
    int playerFame = currentPlayer.getFame();

    System.out.println("rank " + playerRank);
    ArrayList<String> option = new ArrayList<String>();
    for(int i = playerRank - 1; i< 5; i++){
      if(playerMoney >= dollarMap.get(i)[0]){
        option.add("dollar " + dollarMap.get(i)[0] + " rank = " + (i+2));
        //System.out.println(dollarMap.get(i)[0]);
      }
      if(playerFame >= fameMap.get(i)[0]){
        option.add("fame " + fameMap.get(i)[0] + " rank = " + (i+2));
      }
    }
    //System.out.println("Hopefully we filled an array with values");

    for(String s : option){
      System.out.println(s);
    }
    int index = board.moveHelper(option.toArray(new String[option.size()]));

    // resetBuffer();
    // while(buffer.equals("")){
    //   System.out.print("");
    //   getInput();
    // }

    String output = option.get(index);
    System.out.println(output);
    String[] outputArray = output.split(" ");

    if(output.charAt(0) == 'd'){
      castingOffice.upgradeWithMoney(Integer.parseInt(outputArray[4]), currentPlayer);
    }
    else{
      castingOffice.upgradeWithFame(Integer.parseInt(outputArray[4]), currentPlayer);
    }

    // if (currentPlayer.currentRoom.getName().equals("office")){
    //   if (input[1].equals("$")){
    //     castingOffice.upgradeWithMoney(Integer.parseInt(input[2]), currentPlayer);
    //   }else if (input[1].equalsIgnoreCase("cr")){
    //     castingOffice.upgradeWithFame(Integer.parseInt(input[2]), currentPlayer);
    //   } else{
    //     invalidInput(player);
    //   }
    // }
  }

  //takes player action and changes game state
  public void playerAction(int player){

    board.whiteAgain();

    Player currentPlayer = listOfPlayer.get(player);
    Scanner scanner = new Scanner(System.in);
    System.out.print("What would you like to do? \n$$$$$");
    resetBuffer();
    disableButton(currentPlayer);



    //buffer stores commands from gameboard

    while(buffer.equals("")){
      getInput();
      System.out.print("");
    }
    //disableButton(currentPlayer);

    String[] input = this.buffer.split(" ");

    if (input[0].equalsIgnoreCase("who")){
      System.out.println((player+1));
    }
    else if (input[0].equalsIgnoreCase("test1")){
      numberOfRemainingRoom = 1;
    }
    else if (input[0].equalsIgnoreCase("test2")){
      currentPlayer.setMoney(99);
      currentPlayer.setFame(99);
    }
    else if (input[0].equalsIgnoreCase("test3")){
      for (Room i : roomList){
        if (i.getName().equals("office")){
                currentPlayer.setLocation(i);
        }
      }


    }else if (input[0].equalsIgnoreCase("where")){
      System.out.println("location = " + currentPlayer.currentRoom.getName());
      System.out.println("money = " + currentPlayer.getMoney() + "\nfame = " + currentPlayer.getFame() + "\nrank = " + currentPlayer.getRank() + "\nrole " + currentPlayer.getRole() +
                         "\nrehearsalBonuses = " + currentPlayer.getRehearsalBonuses());
      playerAction(player);

    }else if (input[0].equalsIgnoreCase("move")){
      disableButton(currentPlayer);
      System.out.println("here");
      //get player input here
      resetBuffer();
      ArrayList<String> adjacencyList = currentPlayer.currentRoom.findAdjacentRooms();
      String[] arrayAdj = adjacencyList.toArray(new String[adjacencyList.size()]);
      String roomName = arrayAdj[board.moveHelper(arrayAdj)];
      Room newRoom = null;

      for (int i = 0; i < roomList.size(); i++){
        if (roomList.get(i).getName().equalsIgnoreCase(roomName)){
          newRoom = roomList.get(i);
        }
      }
      boolean moved = locationManager.updatePlayerLocation(currentPlayer, newRoom, currentPlayer.currentRoom);
      if (!moved){
        System.out.println("You can't move there.");
        invalidInput(player);
      }
      //player can choose to work after moving
      board.whiteAgain();
      disableButton(currentPlayer);


      board.greyOut("move");
      resetBuffer();
      while(buffer.equals("")){
        System.out.print("");
        getInput();
      }
      String[] input2 = buffer.split(" ");





      //System.out.println("Choose to work or end");
       if (input2[0].equalsIgnoreCase("work") ){
        board.greyOut("work");
        String[] roles = workBox(currentPlayer);
        int index = board.moveHelper(roles);
        String role = roles[index];
        String[] temp = role.split(" ");
        String arole = "";
        int y = 0;
        for (int i = 2; i < temp.length; i++){
          if (i < temp.length -1){
            arole += (temp[i] + " ");
          }
          else{
            arole += temp[i];
          }
        }
        System.out.println(arole);
        work(currentPlayer, arole);
      }else if(input2[0].equalsIgnoreCase("upgrade")){
        upgrade(currentPlayer);
      }

      // else if (input2[0].equalsIgnoreCase("end")){
      //   //do nothing
      // }

    }

    else if (input[0].equalsIgnoreCase("work")){

        if (currentPlayer.currentRoom instanceof SetRoom){
          board.greyOut("work");
          String[] roles = workBox(currentPlayer);
          int index = board.moveHelper(roles);
          String role = roles[index];
          String[] temp = role.split(" ");
          String arole = "";
          int y = 0;
          for (int i = 2; i < temp.length; i++){
            if (i < temp.length -1){
              arole += (temp[i] + " ");
            }
            else{
              arole += temp[i];
            }
          }
          System.out.println(arole);
          work(currentPlayer, arole);
        } else{
          System.out.println("You need to be on set to work");
          invalidInput(player);
        }
    }

    else if (input[0].equalsIgnoreCase("upgrade")){
      //System.out.println("We are now in Upgrade!");
      upgrade(currentPlayer);


    }else if (input[0].equalsIgnoreCase("rehearse")){
      Room room = currentPlayer.currentRoom;
      SetRoom setRoom = null;
      if (room instanceof SetRoom){
        setRoom = (SetRoom) currentPlayer.currentRoom;
        if (!rehearsalManager.checkRehearsalLevel(currentPlayer, setRoom.getSC().getBudget())){
          System.out.println("You have rehearsed too many times already.");
        }
        else{
          rehearsalManager.givePlayerRehearsalToken(currentPlayer);
        }
      }
      else{
        System.out.println("You are not allowed to rehearse in " + room.getName() + ".");
        invalidInput(player);
      }


    }else if (input[0].equalsIgnoreCase("act")){
      act(player);


    }else if (input[0].equalsIgnoreCase("end")){
    } else invalidInput(player);
    resetBuffer();

  }



  public String[] workBox(Player currentPlayer){
    SetRoom currentRoom = (SetRoom) currentPlayer.currentRoom;
    ArrayList<String> roles = new ArrayList<String>();
    for(Role i : currentRoom.remainingRoles){
      roles.add("(off card) " +(i.getName()));
    }
    for(Role i : currentRoom.getSC().remainingRoles){
      roles.add("(on card) " +(i.getName()));
    }
    return roles.toArray(new String[roles.size()]);
  }

  public void getInput(){
    this.buffer = board.current;
  }

  public void resetBuffer(){
    buffer = "";
    board.current = "";
  }

  public void invalidInput(int currentPlayer){
    System.out.println("Invalid input, try again");
    playerAction(currentPlayer);
  }

  public void work(Player player, String role){
    //System.out.println(player.currentRoom);
    SetRoom currentRoom = (SetRoom) player.currentRoom;
    int[] temp = new int[4];
    Role newRole = new Role("","",0,temp,false);
    boolean foundInRoom = false;
    boolean foundOnCard = false;
    int index1 = 0;
    int index2 = 0;
    for(Role i : currentRoom.remainingRoles){
      //System.out.println("on room"+i.getName());
      if (i.getName().equalsIgnoreCase(role)){
        if(i.getRank() <= player.getRank()){
          newRole = i;
          foundInRoom = true;
          index1++;
        }
        else{
          System.out.println("insufficient rank");
          return;
        }
        //currentRoom.remainingRoles.remove(i);
      }
    }

    for(Role i : currentRoom.getSC().remainingRoles){
      //System.out.println("on card"+i.getName());
      if (i.getName().equalsIgnoreCase(role)){
        if(i.getRank() <= player.getRank()){
          newRole = i;
          foundOnCard = true;
          index2++;
        }
        else{
          System.out.println("insufficient rank");
          return;
        }
        //currentRoom.getSC().remainingRoles.remove(i);
      }
    }
    //ask player to choose different role
    if (foundInRoom){
      currentRoom.remainingRoles.remove(index1);
    }
    else if (foundOnCard){
      currentRoom.getSC().remainingRoles.remove(index2);
    }
    if (newRole.name.equals("")){
      System.out.println("invalid role");
    } else {
      player.currentRole = newRole;
    }
  }

  public void resetBoard(){
    int counter;
    //int p = 0;
    for(Room r: roomList){

      if(r instanceof SetRoom){
        SetRoom room = (SetRoom) r;
        //reset shot MARKERS
        room.shotMarkers = room.shotMarkerData.size();
        System.out.println(room.getName());
        // if (p < 5){
        //   System.out.println(p);
        //   board.resetShotMarkers(room);
        //   p++;
        // }
        board.resetShotMarkers(room);


        //assignSceneCard
        room.assignSceneCard(cardList.get(Math.abs(random.nextInt())%40));
        room.sceneCard.remainingRoles.clear();
        for(Role role : room.sceneCard.roleArray){
          room.sceneCard.remainingRoles.add(role);
        }
        //refill remainingRoles
        room.remainingRoles.clear();
        counter = 0;
        for(Role role : room.roleArray){
          room.remainingRoles.add(room.roleArray.get(counter));
          counter++;
        }
      }
    }

    for (int i = 0; i < roomList.size(); i++){
      //System.out.println(roomList.get(i).toString());
      if (roomList.get(i).getName().equals("trailer")){
        for (int y = 0; y < listOfPlayer.size(); y++){
          listOfPlayer.get(y).setLocation(roomList.get(i));
        }
      }
    }

    for(Player player: listOfPlayer){

      player.rehearsalBonuses = 0;
      player.changeRole(null);
    }
    numberOfRemainingRoom = 10;
  }


  public void setUp(){
    //create players
    bank = new Bank();
    dice = new Dice();
    this.locationManager = new LocationManager();
    this.rehearsalManager = new RehearsalManager();
    //castingOffice = new CastingOfficeRoom();
    //determine play order


    // a bunch of set rooms with roles
    // parse through xml file to build rooms
    cardList = XML_Test.cardXML("cards.xml");
    roomList = XML_Test.boardXML("board.xml");


    board = new BoardLayersListener();
    board.setVisible(true);
    numberOfPlayers = board.startGame();
    int playerType;
    System.out.println("number of players =" + numberOfPlayers);
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

    if(numberOfPlayers < 4){
      numberOfDays = 3;
    }
    else{
      numberOfDays = 4;
    }

    this.numberOfPlayers = numberOfPlayers;
    this.numberOfRemainingRoom =10;


    for(int i = 0; i< numberOfPlayers; i++){
      listOfPlayer.add(new Player(playerType));
    }


    determinePlayOrder();
    resetBoard();

    //get trailer
    for(Room room : roomList){
      if(room.getName().equalsIgnoreCase("trailer")){
        board.createPlayers(numberOfPlayers,room);
      }
    }

  }

  private void boardXML(String filename){
  }

  private ArrayList<Integer> determinePlayOrder(){
    ArrayList<Integer> playerOrder = new ArrayList<Integer>();
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


  public void act(int player){
    Player currentPlayer = listOfPlayer.get(player);
    Role currentRole = currentPlayer.currentRole;
    if (currentRole == null){
      System.out.println("You are currently not working a role.");
      invalidInput(player);
    }
    else {
      Room room = currentPlayer.currentRoom;
      SetRoom setRoom = null;
      SceneCard sceneCard = null;
      int budget = 0;
      boolean onCard = currentPlayer.currentRole.onCard;
      if (room instanceof SetRoom){
        setRoom = (SetRoom) currentPlayer.currentRoom;
        sceneCard = setRoom.getSC();
        budget = sceneCard.getBudget();
        System.out.println("shot markers: "+setRoom.shotMarkers);
      }
      int dR = (dice.rollDice() + currentPlayer.rehearsalBonuses);
      board.changeRollArea(dR, budget);
      System.out.println(dR + "\n" + budget + "\n");
      if (dR >= budget){
        setRoom.removeShotMarkers();
        board.removeShotMarker(setRoom.shotMarkerData.get(setRoom.shotMarkers));
        //System.out.println("shot markers: "+setRoom.shotMarkers);
        if (setRoom.shotMarkers == 0){
          //payout for on or off the card
          if(onCard){
            ArrayList<Integer> diceArray = new ArrayList<Integer>();
            ArrayList<Integer> rollList = new ArrayList<Integer>();
            int numberOfRoles = setRoom.sceneCard.roleArray.size();
            for(int i = 0; i < budget; i++){
              diceArray.add(dice.rollDice());

            }
            Collections.sort(diceArray);
            Collections.reverse(diceArray);
            int j = numberOfRoles -1;
            for(int i = 0; i < diceArray.size(); i++){
              rollList.add(diceArray.get(i));
              j--;
              if(j < 0){
                j = numberOfRoles -1;
              }
            }
            for(int y = 0; y < listOfPlayer.size(); y++){
              Role aRole = listOfPlayer.get(y).currentRole;
              for (int i = 0; i < numberOfRoles; i++){
                if (aRole != null && aRole.getName().equals(setRoom.sceneCard.roleArray.get(i).getName())){
                  bank.setMoney(listOfPlayer.get(y), rollList.get(i));
                }
              }
            }

          }
          else{
            bank.setMoney(currentPlayer, currentPlayer.rank);
            bank.setMoney(currentPlayer, 1);
            bank.setFame(currentPlayer, 1);
          }
          setRoom.completeRoom();
          currentPlayer.roleComplete();
        }
        else{
          int no = 0;
          if(onCard){
            bank.setFame(currentPlayer, 2);
          }
          else{
            bank.setMoney(currentPlayer, 1);
            bank.setFame(currentPlayer, 1);
          }
        }
      }
      else{
        if (!onCard){
          bank.setMoney(currentPlayer,1);
        }
      }
    }
  }

  public void endGame(){
    int winner = bank.computePlayerScore(listOfPlayer);
    System.out.println("The winner is player " + winner + " ");
  }
}
