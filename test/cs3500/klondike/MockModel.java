package cs3500.klondike;

import java.util.List;
import java.util.Objects;

import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.KlondikeModel;

/**
 * mock class to represent klondike model. used to assure the controller is correctly calling on
 * the model.
 */
public class MockModel implements KlondikeModel {
  final StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public List<Card> getDeck() {
    return null;
  }

  @Override
  public void startGame(List<Card> deck, boolean shuffle, int numPiles, int numDraw)
          throws IllegalArgumentException, IllegalStateException {
    log.append("Game started.\n");
  }

  @Override
  public void movePile(int srcPile, int numCards, int destPile) throws
          IllegalArgumentException, IllegalStateException {
    log.append(String.format("Moving pile: %d cards from %d srcPile to %d destPile\n",
            numCards, srcPile, destPile));
  }

  @Override
  public void moveDraw(int destPile) throws IllegalArgumentException, IllegalStateException {
    log.append(String.format("Moving top draw card to pile %d\n", destPile));
  }

  @Override
  public void moveToFoundation(int srcPile, int foundationPile) throws IllegalArgumentException,
          IllegalStateException {
    log.append(String.format("Moving card from pile %d to foundation pile %d\n",
            srcPile, foundationPile));
  }

  @Override
  public void moveDrawToFoundation(int foundationPile) throws IllegalArgumentException,
          IllegalStateException {
    log.append(String.format("Moving top draw card to foundation pile %d\n", foundationPile));
  }

  @Override
  public void discardDraw() throws IllegalStateException {
    log.append("Discarding draw.\n");
  }

  @Override
  public int getNumRows() throws IllegalStateException {
    log.append("Get num rows\n");
    return 0;
  }

  @Override
  public int getNumPiles() throws IllegalStateException {
    log.append("Get num piles\n");
    return 0;
  }

  @Override
  public int getNumDraw() throws IllegalStateException {
    log.append("Get num draw\n");
    return 0;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    log.append("Checks is game over\n");
    return false;
  }

  @Override
  public int getScore() throws IllegalStateException {
    log.append("Getting score\n");
    return 0;
  }

  @Override
  public int getPileHeight(int pileNum) throws IllegalArgumentException, IllegalStateException {
    log.append("Getting pile height\n");
    return 0;
  }

  @Override
  public boolean isCardVisible(int pileNum, int card) throws IllegalArgumentException,
          IllegalStateException {
    log.append(String.format("Checking is card visible, card %d in pile %d", card, pileNum));
    return false;
  }

  @Override
  public Card getCardAt(int pileNum, int card) throws IllegalArgumentException,
          IllegalStateException {
    log.append(String.format("Get card %d in pile %d", card, pileNum));
    return null;
  }

  @Override
  public Card getCardAt(int foundationPile) throws IllegalArgumentException, IllegalStateException {
    log.append(String.format("Get top card of foundation pile %d", foundationPile));
    return null;
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    log.append("Getting available draw cards");
    return null;
  }

  @Override
  public int getNumFoundations() throws IllegalStateException {
    log.append("getting number of foundations");
    return 0;
  }
}

