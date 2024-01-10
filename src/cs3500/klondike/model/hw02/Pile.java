package cs3500.klondike.model.hw02;

import java.util.List;

/**
 * Abstract class pile represents a pile of cards, is extended by Cascade and Draw. Has fields
 * up and down to represent the face up and face down card in draw or in build or not in build
 * cards in cascades.
 */
public abstract class Pile {

  protected List<Card> down;
  protected List<Card> up;

  /**
   * Constructor for the class, initializes parameters down and up
   * to represent face down cards and face up cards.
   * @param down face down / not in build
   * @param up face up /in build
   */
  public Pile(List<Card> down, List<Card> up) {
    this.down = down;
    this.up = up;
  }

  /**
   * Returns the number of cards in this pile.
   *
   * @return the number of cards in this pile
   */
  public int getPileHeight() {
    return this.down.size() + this.up.size();
  }

  /**
   * When the cards are being dealt, adds given card, face down, to this pile.
   *
   * @param card the card being dealt
   */
  public void dealCard(Card card) {
    this.down.add(card);
  }

  /**
   * Adds a card to the top of this pile, face up / include in the build.
   *
   * @param card given card being added
   */
  public void addCard(Card card) {
    this.up.add(card);
  }


  /**
   * checks if the card is visible or not.
   *
   * @param card index row of the card to be checked
   * @return boolean, true if visible
   */
  public boolean isCardVisible(int card) {
    return card >= this.down.size() && card < this.getPileHeight();
  }

  /**
   * observes the number of visible cards in this pile.
   *
   * @return the number int of visible cards in this pile.
   */
  public int upSize() {
    return this.up.size();
  }


  /**
   * adds a list of cards to this pile, face up.
   *
   * @param cards the list to be added
   */
  public void addPile(List<Card> cards) {
    for (Card c : cards) {
      this.up.add(c);
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
}
