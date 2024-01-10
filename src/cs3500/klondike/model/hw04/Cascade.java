package cs3500.klondike.model.hw04;

import java.util.List;

import cs3500.klondike.model.hw02.Card;

/**
 * Represents a cascade pile, is implemented by BasicCascade and WhiteheadCascade.
 */
public interface Cascade {

  /**
   * Returns the number of cards in the specified pile.
   * @return the number of cards in the pile
   */
  int getPileHeight();

  /**
   * Adds a card at the end of the pile (displays as bottom most in a view).
   * @param card the card to be added to the cascade.
   */
  void addCard(Card card);

  /**
   * Adds multiple cards at the end of the pile (displays as bottom most in a view).
   * @param cards the multiple cards to be added.
   */
  void addPile(List<Card> cards);

  /**
   * Removes a number of cards from the end of a pile.
   * @param numCards the number of cards to be removed
   * @return the list of cards removed, in the same order as they were on the board.
   */
  List<Card> takePile(int numCards);

  /**
   * Removes one card from the end of the pile (displays as bottom most in a view).
   */
  void takeLast();

  /**
   * Shows the card at the end of the pile.
   * @return the card at the end of the pile.
   */
  Card peek();

  /**
   * Checks if the Cascade pile is empty.
   * @return
   */
  boolean isEmpty();
}
