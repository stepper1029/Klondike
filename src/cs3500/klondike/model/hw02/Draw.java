package cs3500.klondike.model.hw02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * represents a draw pile, and extends the abstract pile class. Has one additional field
 * to represent the number of cards that should be visible at the top of the draw pile.
 */
public class Draw extends Pile {

  private int numDraw;

  /**
   * Constructor for the class, initializes fields down and up to represent
   * face down and face up cards. Initializes numDraw as the size of the up
   * cards.Index 0 for the down cards is the card closest to table. Index 0
   * for the up cards is the top draw card, or the next accessible draw card.
   *
   * @param down cards not in the up
   * @param up   cards in the up
   */
  public Draw(List<Card> down, List<Card> up) {
    super(down, up);
    this.numDraw = this.upSize();
  }

  /**
   * Discards the currently visible draw cards, and puts them at the bottom of the pile.
   */
  public void discardDraw() {
    // make all the face up cards face down cards
    this.down.add(0, this.up.get(0));
    this.up.remove(0);
    this.nextAvailDraw();
  }

  /**
   * shows this.numDraw more cards if this.up is empty.
   */
  public void nextAvailDraw() {
    if (this.upSize() < numDraw && !this.down.isEmpty()) {
      this.up.add(this.down.get(this.down.size() - 1));
      this.down.remove(this.down.size() - 1);
    }
  }

  /**
   * observes currently visible draw cards.
   *
   * @return a list of the currently visible draw cards, ordered.
   */
  public List<Card> getDrawCards() {
    return this.up;
  }

  /**
   * Returns the maximum number of visible cards in the draw pile.
   *
   * @return the number of visible cards in the draw pile
   */
  public int getNumDraw() {
    return numDraw;
  }

  /**
   * makes the draw pile.
   *
   * @param deck    given deck of cards to start the game
   * @param cascNum the number of cards dealt in the cascade piles
   * @param numDraw the number of draw cards that should be visible
   */
  public void makeDraw(List<Card> deck, int cascNum, int numDraw) {
    this.numDraw = numDraw;
    if (cascNum > -1) {
      for (int i = deck.size() - 1; i > cascNum + numDraw; i--) {
        this.dealCard(deck.get(i));
      }
      for (int i = cascNum + 1; i < cascNum + numDraw + 1; i++) {
        this.addCard(deck.get(i));
      }
    }
  }

  /**
   * shows the card at the top of this pile.
   *
   * @return the card at the top of this pile.
   */
  public Card peek() {
    return this.up.get(0);
  }

  /**
   * removes the top card (displayed as the bottom most) from the Pile.
   */
  public void takeLast() {
    this.up.remove(0);
  }

  /**
   * returns a list that represents the cards in the draw pile and their order. Does not distinguish
   * between visible and non-visible cards.
   * @return List of cards in the draw pile
   */
  public List<Card> makeDrawList() {
    List<Card> cards = new ArrayList<>(Arrays.asList());
    for (Card c : up) {
      cards.add(c);
    }
    for (Card c : down) {
      cards.add(3, c);
    }

    return cards;
  }
}
