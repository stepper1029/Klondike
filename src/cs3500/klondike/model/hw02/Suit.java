package cs3500.klondike.model.hw02;

/**
 * an enum to represent a suit in a deck of cards: either
 * Spades(♠), Clubs(♣), Hearts(♡) or Diamonds(♢).
 */
public enum Suit {
  Spades("♠"), Clubs("♣"), Hearts("♡"), Diamonds("♢");

  private final String descriptor;

  /**
   * Shows the suit as it's symbol in a string.
   *
   * @return one of "♠", "♣", "♡", or "♢"
   */
  public String toString() {
    return descriptor;
  }

  Suit(String d) {
    this.descriptor = d;
  }
}




