package cs3500.klondike.model.hw02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.klondike.model.hw04.Cascade;

/**
 * represents a cascade pile in two lists which determine if the card is visible or not,
 * and extends the abstract class Pile.
 */
public class BasicCascade extends Pile implements Cascade {

  /**
   * initializes the down and up fields of the cascade. For a down list, index 0 is the bottom of
   * the pile and the last index represents the next available hidden card. For an up list, index
   * 0 represents the face up card closest to the face down cards or the table, and the last
   * indice represents the face up card with no other cards on top of it. In a whitehead klondike
   * game, the same indexing is applied but rather than face up and face down, its in build or
   * not in build cards.
   */
  public BasicCascade(List<Card> down, List<Card> up) {
    super(down, up);
  }

  /**
   * Observes a card at a specific position in the cascade pile.
   *
   * @param card represents the index of the row in the pile
   * @return the Card at the given coordinates
   */
  public Card getCascadeCardAt(int card) {
    card -= this.down.size();
    return this.up.get(card);
  }

  /**
   * Takes a number of cards from this.up and removes them.
   *
   * @param numCards the number of cards, started from the top of the pile, to be removed
   * @return the list of cards to be removed
   */
  public List<Card> takePile(int numCards) {
    if (numCards > this.up.size()) {
      throw new IllegalArgumentException("Invalid numCards in Cascade");
    }
    List<Card> toMove = new ArrayList<>(Arrays.asList());
    // add cards to list
    for (int i = this.upSize() - numCards; i < this.upSize(); i++) {
      toMove.add(this.up.get(i));
    }
    // remove cards
    for (int i = 0; i < numCards; i++) {
      this.takeLast();
    }
    return toMove;
  }

  /**
   * checks if there are no more face up cards in this cascade pile, and turns one over.
   */
  public void flip() {
    if (this.up.isEmpty()  && this.getPileHeight() > 0) {
      this.up.add(this.down.get(this.down.size() - 1));
      this.down.remove(this.down.size() - 1);
    }
  }

  /**
   * shows the card at the top of this pile (the lowest card in the cascade).
   *
   * @return the card at the top of this pile.
   */
  public Card peek() {
    if (this.upSize() == 0) {
      throw new IllegalStateException("this.up is empty");
    }
    return this.up.get(this.up.size() - 1);
  }

  /**
   * removes the top card from the Pile.
   */
  public void takeLast() {
    this.up.remove(this.upSize() - 1);
  }
}
