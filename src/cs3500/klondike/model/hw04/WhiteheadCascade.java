package cs3500.klondike.model.hw04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.klondike.model.hw02.Card;

/**
 * Represents the cascade piles in a game of whitehead klondike where all the cards are face up.
 */

public class WhiteheadCascade implements Cascade {

  private List<Card> cards;

  /**
   * Constructor for the class, initializes parameter cards
   * to represent the cards in a single vertical pile in a game of whitehead klondike.
   *
   * @param cards represents the cards
   */
  public WhiteheadCascade(List<Card> cards) {
    this.cards = cards;
  }

  /**
   * Returns the number of cards in this pile.
   *
   * @return the number of cards in this pile
   */
  public int getPileHeight() {
    return this.cards.size();
  }

  /**
   * Adds a card to the top of this pile, face up / include in the build.
   *
   * @param card given card being added
   */
  public void addCard(Card card) {
    this.cards.add(card);
  }

  /**
   * adds a list of cards to this pile, face up.
   *
   * @param cards the list to be added
   */
  public void addPile(List<Card> cards) {
    for (Card c : cards) {
      this.addCard(c);
    }
  }

  /**
   * checks if this pile is empty.
   *
   * @return boolean, true if empty
   */
  public boolean isEmpty() {
    return this.getPileHeight() == 0;
  }

  /**
   * Observes a card at a specific position in the cascade pile.
   *
   * @param card represents the index of the row in the pile
   * @return the Card at the given coordinates
   */
  public Card getCascadeCardAt(int card) {
    return this.cards.get(card);
  }

  /**
   * Takes a number of cards from this.up and removes them.
   *
   * @param numCards the number of cards, started from the top of the pile, to be removed
   * @return the list of cards to be removed
   */
  public List<Card> takePile(int numCards) {
    if (numCards > this.getPileHeight()) {
      throw new IllegalArgumentException("Index out of bounds, not that many cards in the pile");
    }
    List<Card> toMove = new ArrayList<>(Arrays.asList());
    // add cards to list
    for (int i = this.getPileHeight() - numCards; i < this.getPileHeight(); i++) {
      toMove.add(this.cards.get(i));
    }
    // remove cards
    for (int i = 0; i < numCards; i++) {
      this.takeLast();
    }
    return toMove;
  }

  /**
   * shows the card at the top of this pile (the lowest card in the cascade).
   *
   * @return the card at the top of this pile.
   */
  public Card peek() {
    if (this.getPileHeight() == 0) {
      throw new IllegalStateException("No cards in the pile");
    }
    return this.cards.get(this.getPileHeight() - 1);
  }


  /**
   * removes the top card from the Pile.
   */
  public void takeLast() {
    this.cards.remove(this.getPileHeight() - 1);
  }
}
