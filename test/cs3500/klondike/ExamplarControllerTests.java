package cs3500.klondike;

import cs3500.klondike.controller.KlondikeController;
import cs3500.klondike.controller.KlondikeTextualController;
import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.KlondikeModel;
import cs3500.klondike.model.hw02.BasicKlondike;
import cs3500.klondike.view.KlondikeTextualView;
import cs3500.klondike.view.TextualView;


import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


/**
 * tests the controller for the examplar. Finds one line mistakes.
 */
public class ExamplarControllerTests {

  public KlondikeModel km;
  public List<Card> wholeDeck;
  public List<Card> deck;
  public TextualView textview;

  /**
   * initializes data so it can be mutated in tests methods.
   */
  private void initData() {
    this.km = new BasicKlondike();
    this.wholeDeck = km.getDeck();
    this.textview = new KlondikeTextualView(this.km);
  }

  /**
   * initializes data so it can be mutated in tests methods.
   */
  private void initDeckA3() {
    this.initData();
    this.deck = new ArrayList<Card>();
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("A♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("2♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("3♣")) {
        this.deck.add(c);
      }
    }

    for (Card c : this.wholeDeck) {
      if (c.toString().contains("A♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("2♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("3♢")) {
        this.deck.add(c);
      }
    }
  }

  /**
   * initializes data so it can be mutated in tests methods.
   */
  private void initDeckA3Two() {
    this.initData();
    this.deck = new ArrayList<Card>();
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("2♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("3♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("A♣")) {
        this.deck.add(c);
      }
    }

    for (Card c : this.wholeDeck) {
      if (c.toString().contains("A♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("2♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("3♢")) {
        this.deck.add(c);
      }
    }
  }

  private void initDeckA3Three() {
    this.initData();
    this.deck = new ArrayList<Card>();
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("2♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("3♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("A♢")) {
        this.deck.add(c);
      }
    }

    for (Card c : this.wholeDeck) {
      if (c.toString().contains("3♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("2♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("A♣")) {
        this.deck.add(c);
      }
    }
  }

  @Test
  public void testMovePileIllegalMoveColor() {
    initDeckA3Two();
    Readable in = new StringReader("mpp 1 1 0 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(this.km, this.deck, false, 2, 1);

    Assert.assertTrue(out.toString().contains("Invalid move."));
  }

  @Test
  public void testMoveDrawIllegalMove() {
    initDeckA3Two();
    Readable in = new StringReader("md 0 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(this.km, this.deck, false, 2, 1);

    Assert.assertTrue(out.toString().contains("Invalid move."));
  }


  @Test
  public void testValidMovePile() {
    initDeckA3Three();

    Readable in = new StringReader("mpf 2 1 mpp 1 1 2 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(this.km, this.deck, false, 2, 1);
    String expected = "Foundation: A♢, <none>\n  X 3♢\n    2♣";
    String actual = out.toString();

    Assert.assertTrue(actual.contains(expected));
  }

  @Test
  public void testGameState() {
    initDeckA3();
    Readable in = new StringReader("Hello World 3 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(this.km, this.deck, false, 2, 1);

    Assert.assertTrue(out.toString().contains("State of game when quit:\n"));
  }

  @Test
  public void testDiscardDrawScore() {
    initDeckA3();
    Readable in = new StringReader("dd md gibberish 3 dd q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(this.km, this.deck, false, 2, 1);

    Assert.assertTrue(out.toString().contains("Score: 0"));
  }

  @Test
  public void testMoveDrawInvalid() {
    initDeckA3();
    Readable in = new StringReader("dd md gibberish 3 dd q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(this.km, this.deck, false, 2, 1);
    String expected = "Game quit!\n" +
            "State of game when quit:\n" +
            "Draw: 3♢\n" +
            "Foundation: <none>, <none>\n" +
            " A♣  ?\n" +
            "    3♣\n" +
            "Score: 0";

    Assert.assertTrue(out.toString().contains(expected));
  }

  @Test
  public void testDiscardDrawWhatever() {
    initDeckA3();
    Readable in = new StringReader("dd md gibberish 3 dd q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(this.km, this.deck, false, 2, 1);
    String expected =
            " A♣  ?\n" +
                    "    3♣\n";

    Assert.assertTrue(out.toString().contains(expected));
  }

  @Test
  public void testQuit() {
    initDeckA3();
    Readable in = new StringReader("q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(this.km, this.deck, false, 2, 1);

    Assert.assertTrue(out.toString().contains("Score: 0"));
  }


  @Test
  public void testInvalidInput() {
    initDeckA3Three();
    Readable in = new StringReader("mpp 2 invalid q 1 1");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(this.km, this.deck, false, 2, 1);
    System.out.println(out.toString());

    Assert.assertTrue(out.toString().contains("Game quit!"));
  }

  @Test
  public void testMoveBlackFourToBlackFiveDoesntEraseBlack4AndLeaveEmptyPile() {
    initData();
    Readable in = new StringReader("mpf 1 1 mpp 6 1 1 mpf 3 2 dd dd md 4 dd md 2 md 3 mpp 4 2 2 "
            + "md 5 mpf 4 2 md 6 mpp 3 2 6 mpp 7 1 3 mpp 2 4 5 mpf 7 3 md 7 mpp 5 6 7 md 1 "
            + "mpp 6 4 1 mpp 5 1 1 mpf 2 1 md 2 mpp 7 8 2 mdf 4 mdf 4 mpf 3 3 mpf 3 1 mpf 5 2 "
            + "mdf 4 dd dd md 1 md 6 md 7 md 4 md 5 mpp 6 2 4 mpp 7 2 5 mpp 7 1 1 mpp 7 1 3 "
            + "mpp 7 1 5 mpp 6 1 2 md 6 mpp 4 4 6 mpp 4 1 1 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(this.km, this.km.getDeck(), false, 7, 3);

    String expected = "Draw: Q♢, K♢, 3♡\n" +
            "Foundation: 3♠, 3♣, 2♡, 3♢\n" +
            " K♣ K♡ K♠ 4♠  ?  ?  X\n" +
            " Q♡ Q♣       J♠ Q♠   \n" +
            " J♣ J♡      10♢ J♢   \n" +
            "10♡10♣       9♣10♠   \n" +
            " 9♠ 9♡       8♢ 9♢   \n" +
            " 8♡ 8♠       7♠ 8♣   \n" +
            " 7♣ 7♡          7♢   \n" +
            " 6♢ 6♣               \n" +
            " 5♣ 5♡               \n" +
            "    4♣               \n" +
            "Score: 11\n" +
            "Invalid move. Play again. invalid move pile\n" +
            "Draw: Q♢, K♢, 3♡\n" +
            "Foundation: 3♠, 3♣, 2♡, 3♢\n" +
            " K♣ K♡ K♠ 4♠  ?  ?  X\n" +
            " Q♡ Q♣       J♠ Q♠   \n" +
            " J♣ J♡      10♢ J♢   \n" +
            "10♡10♣       9♣10♠   \n" +
            " 9♠ 9♡       8♢ 9♢   \n" +
            " 8♡ 8♠       7♠ 8♣   \n" +
            " 7♣ 7♡          7♢   \n" +
            " 6♢ 6♣               \n" +
            " 5♣ 5♡               \n" +
            "    4♣               \n" +
            "Score: 11";
    System.out.println(out.toString());
    Assert.assertTrue(out.toString().contains(expected));
  }
}