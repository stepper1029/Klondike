package cs3500.klondike.controller;

import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.KlondikeModel;
import cs3500.klondike.view.KlondikeTextualView;
import cs3500.klondike.view.TextualView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a controller that takes in a readable and outputs an appendable. Extends the
 * interface KlondikeController. Allows the player to communicate with the model and play the
 * game through string inputs.
 */
public class KlondikeTextualController implements KlondikeController {
  private Readable in;
  private Appendable out;
  private KlondikeModel model;
  private boolean quit;
  private TextualView textView;

  /**
   * Constructor for the class takes in a readable and an appendable. Initializes quit to false
   * and throws an exception if parameters are null.
   *
   * @param in  Readable of user inputs
   * @param out Appendable of game output
   * @throws IllegalArgumentException if parameters are null
   */
  public KlondikeTextualController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Parameters can not be null");
    }

    this.in = in;
    this.out = out;
    this.quit = false;
  }

  @Override
  public void playGame(KlondikeModel model, List<Card> deck, boolean shuffle, int numPiles,
                       int numDraw) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    Scanner scan = new Scanner(in);

    this.model = model;
    this.textView = new KlondikeTextualView(this.model, this.out);

    try {
      model.startGame(deck, shuffle, numPiles, numDraw);
    } catch (IllegalArgumentException | IllegalStateException e) {
      writeMessage("Invalid start game call: " + e.getMessage() + "\n");
    }

    while (!quit && !model.isGameOver()) { // continue until the user quits
      this.render();
      writeMessage("\nScore: " + this.model.getScore());
      writeMessage("\n");

      String userMove;
      if (scan.hasNext()) {
        userMove = scan.next();
      } else {
        break;
      }

      switch (userMove) { // if the input is.....
        case "mpp": // move pile then....
          try { // tell model to move pile
            List<Integer> inputs = scanInputs(scan, 3);
            this.model.movePile(inputs.get(0) - 1, inputs.get(1), inputs.get(2) - 1);
          } catch (IllegalArgumentException | IllegalStateException e) {
            invalidMove(e.getMessage());
          }
          break;

        case "md": // move draw then.....
          try { // tell the model to move draw
            List<Integer> inputs = scanInputs(scan, 1);
            this.model.moveDraw(inputs.get(0) - 1);
          } catch (IllegalArgumentException | IllegalStateException e) {
            invalidMove(e.getMessage());
          }
          break;

        case "mpf": // move pile to foundation then....
          try { // tell  model to move pile to foundation
            List<Integer> inputs = scanInputs(scan, 2);
            this.model.moveToFoundation(inputs.get(0) - 1, inputs.get(1) - 1);
          } catch (IllegalArgumentException | IllegalStateException e) {
            invalidMove(e.getMessage());
          }
          break;

        case "mdf": // move draw to foundation then....
          try { // tell model to move draw to foundation
            List<Integer> inputs = scanInputs(scan, 1);
            this.model.moveDrawToFoundation(inputs.get(0) - 1);
          } catch (IllegalArgumentException | IllegalStateException e) {
            invalidMove(e.getMessage());
          }
          break;

        case "dd": // discard draw then.....
          try {
            // tell model to discard draw
            this.model.discardDraw();
          } catch (IllegalStateException e) {
            invalidMove("cannot discard draw");
          }
          break;

        case "q":
        case "Q":
          quitMessage();
          this.render();
          writeMessage("\nScore: " + this.model.getScore());
          break;

        default:
          invalidMove("Not a valid move input.");
          break;
      }
    }
    if (this.model.isGameOver()) {
      gameOverMessage();
    } else if (!quit) {
      throw new IllegalStateException("must quit");
    }
  }

  // appends a message to the appendable
  private void writeMessage(String mess) throws IllegalStateException {
    try {
      this.out.append(mess);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  // invalid move message
  private void invalidMove(String e) {
    writeMessage("Invalid move. Play again. " + e + "\n");
  }

  // message for game over, either "You win" or "Game over."
  private void gameOverMessage() {
    this.render();
    if (this.model.getNumRows() == 0) {
      writeMessage("\nYou win!");
    } else {
      writeMessage("\nGame over. Score: " + this.model.getScore() + "\n");
    }
  }

  // quit message and quit game state
  private void quitMessage() {
    writeMessage("Game quit!\nState of game when quit:\n");

    // writeMessage("\nScore: " + this.model.getScore());
    this.quit = true;
  }

  // renders the text view
  private void render() {
    try {
      this.textView.render();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  // scans the readable for inputs to a method
  private List<Integer> scanInputs(Scanner scan, int numInputs) {
    List<Integer> inputs = new ArrayList<>(Arrays.asList());

    while (scan.hasNext()) { // for every token in the scanner
      String token = scan.next();
      if (token.equals("q") || token.equals("Q")) { // if the next token is quit
        quitMessage(); // append the quit game state
        render();
        writeMessage("\nScore: " + this.model.getScore());
      } else if (inputs.size() < numInputs) { // else if still not enough inputs
        if (isNum(token) && Integer.parseInt(token) >= 0) { // and token is a number >= 0
          inputs.add(Integer.parseInt(token)); // add token to inputs
        } else if (!isNum(token)) {
          invalidMove("Garbage input.");
        }
      }
      if (inputs.size() == numInputs) {
        return inputs;
      }
    }
    throw new IllegalStateException("Too few inputs: " + inputs);
  }

  private boolean isNum(String token) {
    try {
      Integer.parseInt(token);
      return true;
    } catch (NumberFormatException ignored) {
      return false;
    }
  }
}
