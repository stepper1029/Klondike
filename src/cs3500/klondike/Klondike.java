package cs3500.klondike;

import java.io.InputStreamReader;

import cs3500.klondike.controller.KlondikeController;
import cs3500.klondike.controller.KlondikeTextualController;
import cs3500.klondike.model.hw02.KlondikeModel;
import cs3500.klondike.model.hw04.KlondikeCreator;

/**
 * class Klondike that represents the user's entrypoint to the program.
 */
public class Klondike {

  /**
   * main class that holds the program the user interacts with in order to play the game.
   *
   * @param args user input
   */
  public static void main(String[] args) {
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    KlondikeController controller = new KlondikeTextualController(rd, ap);
    KlondikeModel model;

    if (args.length < 1) {
      System.out.println("too few arguments.");
      return;
    }

    String gameTypeString = args[0].toUpperCase();
    if (!gameTypeString.equals("BASIC") && !gameTypeString.equals("LIMITED")
            && !gameTypeString.equals("WHITEHEAD")) {
      System.err.println("first arg must be a type of klondike");
    }

    KlondikeCreator.GameType gameType = KlondikeCreator.GameType.valueOf(gameTypeString);
    model = KlondikeCreator.create(gameType);
    int cascadePiles = 7; // Default number of cascade piles
    int numDraw = 3;   // Default number of visible draw cards
    int redrawAttempts; // Default redraw attempts for non-limited games

    if (gameTypeString.equals("LIMITED") && args.length > 3) {
      try {
        redrawAttempts = Integer.parseInt(args[1]);
        cascadePiles = Integer.parseInt(args[2]);
        numDraw = Integer.parseInt(args[3]);
        model = KlondikeCreator.createLDK(redrawAttempts);
      } catch (NumberFormatException e) {
        System.err.println("args must be numbers");
      }
    } else if (args.length > 1) {
      try {
        cascadePiles = Integer.parseInt(args[1]);
        if (args.length > 2) {
          numDraw = Integer.parseInt(args[2]);
        }
      } catch (NumberFormatException e) {
        System.err.println("args must be numbers");
      }
    }
    controller.playGame(model, model.getDeck(), true, cascadePiles, numDraw);
  }
}
