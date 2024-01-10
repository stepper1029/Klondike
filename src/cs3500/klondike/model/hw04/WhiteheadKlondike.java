package cs3500.klondike.model.hw04;

import cs3500.klondike.model.hw02.KlondikeModel;
import cs3500.klondike.model.hw02.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is a stub implementation of the {@link KlondikeModel} interface.
 * You may assume that the actual implementation of WhiteheadKlondike will have a zero-argument
 * (i.e. default) constructor, and that all the methods below will be implemented.  You may not make
 * any other assumptions about the implementation of this class (e.g. what fields it might have,
 * or helper methods, etc.).
 */
public class WhiteheadKlondike extends AbstractKlondike {

  protected List<WhiteheadCascade> cascade;

  public WhiteheadKlondike() {
    cascade = new ArrayList<>(Arrays.asList());
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
  }

  public boolean validMove(Card pileCard, Card moveCard) {
    return !oppColor(pileCard, moveCard) && isValPlusOne(moveCard, pileCard);
  }

  void makeCascade(List<Card> deck, int numPiles) {
    for (int np = 0; np < numPiles; np++) {
      WhiteheadCascade casc = new WhiteheadCascade(new ArrayList<>(Arrays.asList()));
      this.cascade.add(casc);
    }
    int cardIndex = 0;
    for (int row = 0; row < numPiles; row++) { // keep track of the row
      for (int column = 0; column < numPiles; column++) { //keep track of the column
        if (column >= row) {
          this.cascade.get(column).addCard(deck.get(cardIndex));
          cardIndex++;
        }
      }
    }
  }

  @Override
  public void movePile(int srcPile, int numCards, int destPile) {
    this.movePileExceptions(srcPile, numCards, destPile);
    if (this.cascade.get(srcPile).isEmpty()) {
      throw new IllegalArgumentException("srcPile is empty, cannot movePile");
    } else if (numCards > this.getPileHeight(srcPile)) {
      throw new IllegalStateException("not that many cards in the pile, move fewer cards");
    }
    WhiteheadCascade src = this.cascade.get(srcPile);
    WhiteheadCascade dest = this.cascade.get(destPile);
    Card topSrcCard = src.getCascadeCardAt(this.getPileHeight(srcPile) - numCards);
    if (dest.isEmpty() && sameSuitForMovePile(src, numCards)) {
      dest.addPile(src.takePile(numCards));
    } else if (numCards == 1) {
      this.movePileOneCard(srcPile, destPile);
    } else {
      Card destCard = dest.peek();
      if (validMove(destCard, topSrcCard) && sameSuitForMovePile(src, numCards)) {
        dest.addPile(src.takePile(numCards));
      } else {
        throw new IllegalStateException(
                "invalid move and/or not all moving cards are the same suit");
      }
    }
  }

  @Override
  public void moveDraw(int destPile) throws IllegalStateException {
    moveDrawExceptions(destPile);
    WhiteheadCascade destCasc = this.cascade.get(destPile);
    if (destCasc.isEmpty()) {
      destCasc.addCard(this.draw.peek()); // add the card
      this.draw.takeLast(); // remove the card
    } else {
      Card drawCard = this.draw.peek();
      moveDrawHelper(destCasc, drawCard);
    }
  }

  private boolean sameSuitForMovePile(WhiteheadCascade srcPile, int numCards) {
    List<Card> cards = new ArrayList<>(Arrays.asList());
    for (int index = srcPile.getPileHeight() - numCards; index < srcPile.getPileHeight(); index++) {
      cards.add(srcPile.getCascadeCardAt(index));
    }

    for (Card c : cards) {
      if (!this.sameSuit(cards.get(0).toString(), c.toString())) {
        return false;
      }
    }
    return true;
  }

  private void movePileOneCard(int srcPile, int destPile) {
    WhiteheadCascade src = this.cascade.get(srcPile);
    WhiteheadCascade dest = this.cascade.get(destPile);
    Card srcCard = src.peek();
    Card destCard = dest.peek();

    if (validMove(destCard, srcCard)) {
      dest.addCard(srcCard);
      src.takeLast();
    } else {
      throw new IllegalStateException("invalid move, cards must be descending and the same color");
    }
  }

  @Override
  public boolean isCardVisible(int pileNum, int card) throws IllegalStateException {
    this.gameStarted();
    this.validCascadePileNum(pileNum);
    this.validCardOrFoundationNum(card, this.getPileHeight(pileNum));
    return true;
  }

  void gameStarted() {
    if (this.cascade.isEmpty()) {
      throw new IllegalStateException("Game has not started");
    }
  }

  @Override
  public int getNumPiles() {
    this.gameStarted();
    return this.cascade.size();
  }

  @Override
  public int getNumRows() {
    this.gameStarted();
    int rows = 0;
    for (WhiteheadCascade c : this.cascade) {
      if (c.getPileHeight() > rows) {
        rows = c.getPileHeight();
      }
    }
    return rows;
  }

  @Override
  public int getPileHeight(int pileNum) throws IllegalStateException {
    this.gameStarted();
    this.validPileNum(pileNum, this.cascade.size());
    return this.cascade.get(pileNum).getPileHeight();
  }

  @Override
  public void moveToFoundation(int srcPile, int foundationPile) throws IllegalStateException {
    this.gameStarted();
    this.validPileNum(srcPile, this.getNumPiles());
    this.validPileNum(foundationPile, this.getNumFoundations());
    WhiteheadCascade srcCasc = this.cascade.get(srcPile);
    if (srcCasc.isEmpty()) {
      throw new IllegalStateException("Pile is empty, no card to move");
    }
    List<Card> foundation = this.foundation.get(foundationPile);
    Card movingCard = srcCasc.peek();
    // if the foundation is empty and the card is an ace....
    if (foundation.isEmpty()) {
      if (isAce(movingCard)) {
        foundation.add(movingCard); // add card
        srcCasc.takeLast(); // remove card
      } else {
        throw new IllegalStateException("only an A can be moved to an empty foundation");
      }
    } else {
      Card foundationCard = this.getCardAt(foundationPile);
      if (this.validFoundationMove(movingCard, foundationCard)) {
        foundation.add(movingCard); // move card to foundation
        srcCasc.takeLast(); // remove the card from cascade
      } else {
        throw new IllegalStateException("Invalid foundation move");
      }
    }
  }

  void validPileNum(int pileNum, int totalPiles) {
    if (pileNum < 0 || pileNum > totalPiles - 1) {
      throw new IllegalArgumentException("Invalid pile or card index");
    }
  }

  @Override
  public Card getCardAt(int pileNum, int card) throws IllegalStateException {
    getCardAtExceptions(pileNum, card);
    return this.cascade.get(pileNum).getCascadeCardAt(card);
  }

  void makeDraw(List<Card> deck, int numDraw) {
    int cascNum = -1;
    for (WhiteheadCascade c : this.cascade) {
      cascNum += c.getPileHeight();
    }
    this.draw.makeDraw(deck, cascNum, numDraw);
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    this.gameStarted();
    if (this.getNumRows() == 0 && this.getDrawCards().isEmpty()) {
      return true;
    } else {
      return (!anyPossibleCascadeMove() && this.draw.isEmpty() && !anyPossibleFoundationMove());
    }
  }

  boolean anyPossibleCascadeMove() {
    for (int srcCascade = 0; srcCascade < this.getNumPiles(); srcCascade++) {
      for (int numCards = 1; numCards <= this.getPileHeight(srcCascade); numCards++) {
        for (int destCascade = 0; destCascade < this.getNumPiles(); destCascade++) {
          if (srcCascade != destCascade && !this.cascade.get(srcCascade).isEmpty()) {
            WhiteheadCascade destPile = this.cascade.get(destCascade);
            int card = this.getPileHeight(srcCascade) - numCards;
            Card moveCard = this.getCardAt(srcCascade, card);
            if (destPile.isEmpty() && isKing(moveCard)) {
              return true;
            } else if (!destPile.isEmpty()) {
              return validMove(destPile.peek(), moveCard);
            }
          }
        }
      }
    }
    return false;
  }

  boolean anyPossibleFoundationMove() {
    for (int srcCascade = 0; srcCascade < this.getNumPiles(); srcCascade++) {
      for (int foundationPile = 0; foundationPile < this.getNumFoundations(); foundationPile++) {
        if (!this.cascade.get(srcCascade).isEmpty()) {
          Card srcCard = this.cascade.get(srcCascade).peek();
          if (this.foundation.get(foundationPile).isEmpty() && isAce(srcCard)) {
            return true;
          } else if (validFoundationMove(srcCard, this.getCardAt(foundationPile))) {
            return true;
          }
        }
      }
    }
    return false;
  }
}
