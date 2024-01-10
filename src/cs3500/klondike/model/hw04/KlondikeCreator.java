package cs3500.klondike.model.hw04;

import cs3500.klondike.model.hw02.BasicKlondike;
import cs3500.klondike.model.hw02.KlondikeModel;

/**
 * A factory method to create a new Klondike.
 */
public class KlondikeCreator {

  /**
   * An enumeration of the different types of KlondikeModel: basic, limited, and whitehead.
   */
  public enum GameType {
    BASIC("basic"), LIMITED("limited"), WHITEHEAD("whitehead");

    final String descriptor;

    GameType(String d) {
      this.descriptor = d;
    }
  }

  /**
   * Creates a new KlondikeModel object for the user to play.
   * @param type of KlondikeModel
   * @return KlondikeModel instance
   */
  public static KlondikeModel create(GameType type) {
    switch (type) {
      case BASIC:
        return new BasicKlondike();
      case LIMITED:
        return new LimitedDrawKlondike(2);
        // Pass the number of redraw attempts as a parameter
      case WHITEHEAD:
        return new WhiteheadKlondike();
      default:
        throw new IllegalArgumentException("Unsupported game type: " + type);
    }
  }

  /**
   * Creates a LimitedDrawKlondike with a given number of times a card can be redrawn.
   * @param numTimesRedrawAllowed the number of times a card can be redrawn (or shown at the
   *                              top of the draw pile).
   * @return an instance of LimitedDrawKlondike
   */
  public static LimitedDrawKlondike createLDK(int numTimesRedrawAllowed) {
    return new LimitedDrawKlondike(numTimesRedrawAllowed);
  }
}

