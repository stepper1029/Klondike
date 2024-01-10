package cs3500.klondike.model.hw02;

import java.util.Objects;

/**
 * Represents a Card used to play the game Klondike, implements the Card interface.
 * Has fields to represent the suit, color, and number value of the card.
 */
public class KlondikeCard implements Card {
  private final Suit suit;
  private final int numVal;

  /**
   * Constructor initializes the fields of the class.
   *
   * @param suit   to represent the suit, one of the enum options of Suit
   * @param numVal to represent the number value, from 1-13.
   * @throws IllegalArgumentException if the numValue is invalid
   */
  public KlondikeCard(Suit suit, int numVal) {
    this.suit = suit;
    if (numVal > 0 && numVal < 14) {
      this.numVal = numVal;
    } else {
      throw new IllegalArgumentException("This is not a valid Card value");
    }
  }

  @Override
  public String toString() {
    String stringVal = "";
    if (this.numVal == 1) {
      stringVal = "A";
    } else if (this.numVal == 11) {
      stringVal = "J";
    } else if (this.numVal == 12) {
      stringVal = "Q";
    } else if (this.numVal == 13) {
      stringVal = "K";
    } else {
      stringVal = String.valueOf(this.numVal);
    }
    return stringVal + this.suit.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KlondikeCard that = (KlondikeCard) o;
    return this.numVal == that.numVal && this.suit == that.suit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.suit, this.numVal);
  }
}
