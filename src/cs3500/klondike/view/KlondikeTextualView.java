package cs3500.klondike.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.KlondikeModel;

/**
 * A simple text-based rendering of the Klondike game.
 */
public class KlondikeTextualView implements TextualView {
  private final KlondikeModel model;
  private Appendable out;
  public KlondikeTextualView(KlondikeModel model) {
    this.model = model;
  }
  public KlondikeTextualView(KlondikeModel model, Appendable out) {
    this.model = model;
    this.out = out;
  }
  /**
   * represents the model as a simple text value.
   *
   * @return a string representing the model
   */
  public String toString() {
    String output = "";
    output += this.drawToString();
    output += this.foundationToString();
    output += this.cascadeToString();
    return output;
  }

  @Override
  public void render() throws IOException {
    this.out.append(this.toString());
  }

  // renders the draw pile as a string.
  private String drawToString() {
    String output = "Draw: ";
    if (this.model.getDrawCards().isEmpty()) {
      output += "";
    } else {
      List<String> availDrawCards = new ArrayList<>(Arrays.asList());
      for (Card c : this.model.getDrawCards()) {
        availDrawCards.add(c.toString());
      }
      for (int i = 0; i < availDrawCards.size(); i++) {
        if (i == availDrawCards.size() - 1) {
          output += availDrawCards.get(i);
        } else {
          output += availDrawCards.get(i) + ", ";
        }
      }
    }
    output += "\n";
    return output;
  }

  // renders the foundation piles as a string.
  private String foundationToString() {
    String output = "Foundation: ";
    for (int i = 0; i < this.model.getNumFoundations(); i++) {

      if (i == this.model.getNumFoundations() - 1) {
        if (this.model.getCardAt(i) == null) {
          output += "<none>";
        } else {
          output += this.model.getCardAt(i).toString();
        }
      } else {
        if (this.model.getCardAt(i) == null) {
          output += "<none>, ";
        } else {
          output += this.model.getCardAt(i).toString() + ", ";
        }
      }
    }
    output += "\n";
    return output;
  }

  // renders the cascade piles as a string.
  private String cascadeToString() {
    String output = "";
    int numRows = this.model.getNumRows();
    int numPiles = this.model.getNumPiles();
    for (int card = 0; card < numRows; card++) { // keep track of the row
      for (int pileNum = 0; pileNum < numPiles; pileNum++) { //keep track of the column
        int pileHeight = this.model.getPileHeight(pileNum);
        if (pileHeight == 0 && card == 0) {
          output += "  X";
        } else if (pileHeight == 0) {
          output += "   ";
        } else if (card < pileHeight && !this.model.isCardVisible(pileNum, card)) {
          output += "  ?";
        } else if (card < pileHeight && this.model.isCardVisible(pileNum, card)) {
          String stringCard = this.model.getCardAt(pileNum, card).toString();
          if (stringCard.length() == 2) {
            output += " " + stringCard;
          } else {
            output += stringCard;
          }
        } else {
          output += "   ";
        }
      }
      if (card < numRows - 1) {
        output += "\n";
      }
    }
    return output;
  }
}
