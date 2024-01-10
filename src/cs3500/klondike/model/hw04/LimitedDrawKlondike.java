package cs3500.klondike.model.hw04;

import cs3500.klondike.model.hw02.BasicCascade;
import cs3500.klondike.model.hw02.KlondikeModel;
import cs3500.klondike.model.hw02.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a stub implementation of the {@link KlondikeModel} interface.
 * You may assume that the actual implementation of LimitedDrawKlondike will have a one-argument
 * constructor, and that all the methods below will be implemented.  You may not make
 * any other assumptions about the implementation of this class (e.g. what fields it might have,
 * or helper methods, etc.).
 */
public class LimitedDrawKlondike extends AbstractKlondike {

  int numTimesRedrawAllowed;
  int indexCount;
  List<Card> drawCopy;

  /**
   * Constructor for LimitedDrawKlondike that takes in a parameter for the number of times
   * a card can be redrawn.
   * @param numTimesRedrawAllowed How many times a card can be redrawn.
   */
  public LimitedDrawKlondike(int numTimesRedrawAllowed) {
    super();
    if (numTimesRedrawAllowed >= 0) {
      this.numTimesRedrawAllowed = numTimesRedrawAllowed;
    } else {
      throw new IllegalArgumentException("numTimesRedrawAllowed must be >= 0");
    }
    indexCount = 0;
  }

  private List<Card> makeDrawCopy() {
    return this.draw.makeDrawList();
  }

  @Override
  public void startGame(List<Card> deck, boolean shuffle, int numPiles, int numDraw)
          throws IllegalArgumentException {
    if (!this.cascade.isEmpty()) {
      throw new IllegalStateException("Game has already started");
    }
    startGameHelper(deck, shuffle, numPiles, numDraw);
    List<Card> cascadeCopy = new ArrayList<>(deck);
    // create the cascade piles
    this.makeCascade(cascadeCopy, numPiles);
    List<Card> drawCopy = new ArrayList<>(deck);
    // leftover cards after making cascades in the deck become the draw pile
    this.makeDraw(drawCopy, numDraw);
    this.drawCopy = makeDrawCopy();
  }

  @Override
  public void discardDraw() throws IllegalStateException {
    this.gameStarted();
    if (this.draw.isEmpty()) {
      throw new IllegalStateException("Invalid move");
    }
    fullDrawCycle();
    if (this.numTimesRedrawAllowed >= 0) {
      this.draw.discardDraw();
      indexCount++;
    } else {
      this.draw.takeLast();
      this.drawCopy.remove(indexCount);
    }
    this.draw.nextAvailDraw();
  }

  private void fullDrawCycle() {
    if (drawCopy.equals(makeDrawCopy())) {
      indexCount = 0;
      numTimesRedrawAllowed--;
    }
  }

  @Override
  public void moveDrawToFoundation(int foundationPile) throws IllegalStateException {
    this.gameStarted();
    this.validCardOrFoundationNum(foundationPile, this.foundation.size());
    if (this.draw.isEmpty()) { // if no cards to draw throw exception
      throw new IllegalStateException("Draw pile is empty");
    } else if (foundation.isEmpty()) {
      throw new IllegalStateException("To move a card to an empty foundation, must be an A");
    }
    fullDrawCycle();
    List<Card> foundation = this.foundation.get(foundationPile);
    Card drawCard = this.draw.peek();

    if (foundation.isEmpty()) { // if empty foundation
      if (this.isAce(drawCard)) { // and ace card
        foundation.add(drawCard); // move to foundation
        drawCopy.remove(indexCount);
        this.draw.takeLast();
        this.draw.nextAvailDraw();
      } else {
        throw new IllegalStateException("Only an A can be moved to an empty foundation");
      }
    } else {
      Card foundationCard = this.getCardAt(foundationPile);
      if (validFoundationMove(drawCard, foundationCard)) {
        foundation.add(drawCard);
        drawCopy.remove(indexCount);
        this.draw.takeLast();
        this.draw.nextAvailDraw();
      } else {
        throw new IllegalStateException("Invalid move");
      }
    }
  }

  @Override
  public void moveDraw(int destPile) throws IllegalStateException {
    moveDrawExceptions(destPile);
    fullDrawCycle();
    BasicCascade destCasc = this.cascade.get(destPile);
    Card drawCard = this.draw.peek();
    if (destCasc.isEmpty()) {
      if (this.isKing(drawCard)) {
        destCasc.addCard(this.draw.peek()); // add the card
        this.draw.takeLast(); // remove the card
        this.drawCopy.remove(indexCount);
      } else {
        throw new IllegalStateException("Must move a K to an empty pile");
      }
    } else {
      Card receivingCard = this.cascade.get(destPile).peek();
      if (!this.validMove(receivingCard, drawCard)) { // if not a valid move
        throw new IllegalStateException("Invalid move"); // throw
      } else {
        destCasc.addCard(drawCard); // add card
        this.draw.takeLast(); // remove card
        this.drawCopy.remove(indexCount);
      }
    }
    this.draw.nextAvailDraw(); // flip over next draw if appropriate
  }
}

