
/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;
import java.util.Arrays;

/**
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */
public class EscapeRoom {

  // describe the game with brief welcome message
  // determine the size (length and width) a player must move to stay within the
  // grid markings
  // Allow game commands:
  // right, left, up, down: if you try to go off grid or bump into wall, score
  // decreases
  // jump over 1 space: you cannot jump over walls
  // if you land on a trap, spring a trap to increase score: you must first check
  // if there is a trap, if none exists, penalty
  // pick up prize: score increases, if there is no prize, penalty
  // help: display all possible commands
  // end: reach the far right wall, score increase, game ends, if game ended
  // without reaching far right wall, penalty
  // replay: shows number of player steps and resets the board, you or another
  // player can play the same board
  // Note that you must adjust the score with any method that returns a score
  // Optional: create a custom image for your player use the file player.png on
  // disk

  /****
   * provided code:
   * // set up the game
   * boolean play = true;
   * while (play)
   * {
   * // get user input and call game methods to play
   * play = false;
   * }
   */

  public static void main(String[] args) {
    // welcome message
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps");
    System.out.println("and picking up all the prizes.\n");
    System.out.println("The less moves you make (moving, checking for traps), the higher your score will be.");

    GameGUI game = new GameGUI();
    game.createBoard();

    // size of move
    int m = 60;
    // individual player moves
    int px = 0;
    int py = 0;

    int score = 0;

    String currentCommand;

    Scanner in = new Scanner(System.in);
    String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d",
        "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
        "pickup", "p", "quit", "q", "replay", "help", "?", "springtrap", "st", "checktrap", "ct" };

    String[] validDirectionalCommands = { "right", "left", "up", "down", "r", "l", "u", "d" };

    // set up game
    boolean play = true;
    System.out.println("Valid commands for the game are " + Arrays.toString(validCommands));

    while (play) {
      System.out.println("What would you like to do?");
      currentCommand = UserInput.getValidInput(validCommands);
      if (currentCommand.equals("right") || currentCommand.equals("r")) {
        score += game.movePlayer(m, 0);
      } else if (currentCommand.equals("left") || currentCommand.equals("l")) {
        score += game.movePlayer(-m, 0);
      } else if (currentCommand.equals("up") || currentCommand.equals("u")) {
        score += game.movePlayer(0, -m);
      } else if (currentCommand.equals("down") || currentCommand.equals("d")) {
        score += game.movePlayer(0, m);
      } else if (currentCommand.equals("jump") || currentCommand.equals("jr")) {
        score += game.movePlayer(2 * m, 0);
      } else if (currentCommand.equals("jumpleft") || currentCommand.equals("jl")) {
        score += game.movePlayer(-2 * m, 0);
      } else if (currentCommand.equals("jumpup") || currentCommand.equals("ju")) {
        score += game.movePlayer(0, -2 * m);
      } else if (currentCommand.equals("jumpdown") || currentCommand.equals("jd")) {
        score += game.movePlayer(0, 2 * m);
      } else if (currentCommand.equals("pickup") || currentCommand.equals("p")) {
        score += game.pickupPrize();
      } else if (currentCommand.equals("springtrap") || currentCommand.equals("st")) {
        System.out.println("Which adjacent square would you like to spring the trap of? Your options are "
            + Arrays.toString(validDirectionalCommands));
        currentCommand = UserInput.getValidInput(validDirectionalCommands);
        if (currentCommand.equals("right") || currentCommand.equals("r")) {
          score += game.springTrap(m, 0, false);
        } else if (currentCommand.equals("left") || currentCommand.equals("l")) {
          score += game.springTrap(-m, 0, false);
        } else if (currentCommand.equals("up") || currentCommand.equals("u")) {
          score += game.springTrap(0, -m, false);
        } else if (currentCommand.equals("down") || currentCommand.equals("d")) {
          score += game.springTrap(0, m, false);
        }
      } else if (currentCommand.equals("checktrap") || currentCommand.equals("ct")) {
        System.out.println("Which adjacent square would you like to check? Your options are "
            + Arrays.toString(validDirectionalCommands));
        currentCommand = UserInput.getValidInput(validDirectionalCommands);
        if (currentCommand.equals("right") || currentCommand.equals("r")) {
          game.checkTrap(m, 0);
        } else if (currentCommand.equals("left") || currentCommand.equals("l")) {
          game.checkTrap(-m, 0);
        } else if (currentCommand.equals("up") || currentCommand.equals("u")) {
          game.checkTrap(0, -m);
        } else if (currentCommand.equals("down") || currentCommand.equals("d")) {
          game.checkTrap(0, m);
        }
      } else if (currentCommand.equals("quit") || currentCommand.equals("q")) {
        play = false;
      } else if (currentCommand.equals("replay")) {
        score += game.replay();
      } else if (currentCommand.equals("help") || currentCommand.equals("?")) {
        System.out.println("Welcome to EscapeRoom!");
        System.out.println("Get to the other side of the room, avoiding walls and invisible traps");
        System.out.println("and picking up all the prizes.\n");
        System.out.println("Valid commands for the game are " + validCommands);
      }
    }

    score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());

  }
}
