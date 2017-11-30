import java.util.Scanner;

class DeadWood{


  public static void main(String[] args){

    Scanner scanner = new Scanner(System.in);
    System.out.print("how many player? ");
    BoardManager boardManager = new BoardManager(scanner.nextInt());
    
  }

}
