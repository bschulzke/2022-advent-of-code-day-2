package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    File myObj = new File("src/input/input.txt");

    try (Scanner myReader = new Scanner(myObj)) {
      ArrayList<String> lines = new ArrayList<>();

      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        lines.add(data.replace(" ", ""));
      }

      int partOneTotalScore = 0;
      int partTwoTotalScore = 0;

      for (String line : lines) {
        char opponentMove = line.charAt(0);
        char partOneMove = line.charAt(1);
        char partTwoMove = getPartTwoMove(opponentMove, line.charAt(1));

        partOneTotalScore += getMoveScore(partOneMove);
        partOneTotalScore += getMatchScore(opponentMove, partOneMove);

        partTwoTotalScore += getMoveScore(partTwoMove);
        partTwoTotalScore += getMatchScore(opponentMove, partTwoMove);

      }

      System.out.println("Score in Part 1: " + partOneTotalScore);
      System.out.println("Score in Part 2: " + partTwoTotalScore);

    } catch (Exception e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private static char getPartTwoMove(char opponentMove, char resultCondition) throws IOException {
    if (resultCondition == 'X') {
      return getLosingChoice(opponentMove);
    } else if (resultCondition == 'Y') {
      return getDrawChoice(opponentMove);
    } else if (resultCondition == 'Z') {
      return getWinningChoice(opponentMove);
    }
    throw new IOException("Error in getPartTwoMove()");
  }

  private static char getLosingChoice(char opponentMove) throws IOException {
    if (opponentMove == 'A') { // rock
      return 'Z'; // scissors
    } else if (opponentMove == 'B') { // paper
      return 'X'; // rock
    } else if (opponentMove == 'C') { // scissors
      return 'Y'; // paper
    }
    throw new IOException("Error in getLosingChoice()");
  }

  private static char getDrawChoice(char opponentMove) throws IOException {
    if (opponentMove == 'A') { // rock
      return 'X';
    } else if (opponentMove == 'B') { // paper
      return 'Y';
    } else if (opponentMove == 'C') { // scissors
      return 'Z';
    }
    throw new IOException("Error in getDrawChoice()");
  }

  private static char getWinningChoice(char opponentMove) throws IOException {
    if (opponentMove == 'A') { // rock
      return 'Y'; // paper
    } else if (opponentMove == 'B') { // paper
      return 'Z'; // scissors
    } else if (opponentMove == 'C') { // scissors
      return 'X'; // rock
    }
    throw new IOException("Error in getWinningChoice()");
  }

  private static int getMoveScore(char move) throws IOException {
    if (move == 'X') {
      return 1;
    } else if (move == 'Y') {
      return 2;
    } else if (move == 'Z') {
      return 3;
    }
    throw new IOException("Wrong character: " + move);
  }

  private static int getMatchScore(char opponentMove, char myMove) throws IOException {
    if (opponentMove == 'A') { // rock
      return opponentChoseA(myMove);
    } else if (opponentMove == 'B') { // paper
      return opponentChoseB(myMove);
    } else if (opponentMove == 'C') { // scissors
      return opponentChoseC(myMove);
    }
    throw new IOException("Error in getMatchScore()");
  }

  private static int opponentChoseA(char myMove) throws IOException { // rock
    if (myMove == 'X') { // rock
      return 3;
    } else if (myMove == 'Y') { // paper
      return 6;
    } else if (myMove == 'Z') { // scissors
      return 0;
    }
    throw new IOException("Error in opponentChoseA()");
  }

  private static int opponentChoseB(char myMove) throws IOException { // paper
    if (myMove == 'X') { // rock
      return 0;
    } else if (myMove == 'Y') { // paper
      return 3;
    } else if (myMove == 'Z') { // scissors
      return 6;
    }
    throw new IOException("Error in opponentChoseA()");
  }

  private static int opponentChoseC(char myMove) throws IOException { // scissors
    if (myMove == 'X') { // rock
      return 6;
    } else if (myMove == 'Y') { // paper
      return 0;
    } else if (myMove == 'Z') { // scissors
      return 3;
    }
    throw new IOException("Error in opponentChoseA()");
  }

}
