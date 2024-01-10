package cs3500.klondike;

import org.junit.Assert;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import cs3500.klondike.controller.KlondikeController;
import cs3500.klondike.controller.KlondikeTextualController;
import cs3500.klondike.model.hw02.BasicKlondike;
import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.KlondikeModel;
import cs3500.klondike.view.KlondikeTextualView;
import cs3500.klondike.view.TextualView;

/**
 * Tests for the controller. Makes sure the controller relays the appropriate information to
 * the model and displays the correct messages and game state.
 */
public class ControllerTests {

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
  public void mockTestStartGame() {
    initData();
    Reader in = new StringReader("q");
    StringBuilder dontCare = new StringBuilder();
    StringBuilder log = new StringBuilder();
    MockModel mm = new MockModel(log);
    KlondikeController kc = new KlondikeTextualController(in, dontCare);
    kc.playGame(mm, km.getDeck(), false, 2, 1);

    Assert.assertTrue(log.toString().contains("Game started."));
  }

  @Test
  public void mockTestMovePile() {
    initData();
    Reader in = new StringReader("mpp 1 1 1 q");
    StringBuilder dontCare = new StringBuilder();
    StringBuilder log = new StringBuilder();
    MockModel mm = new MockModel(log);
    KlondikeController kc = new KlondikeTextualController(in, dontCare);
    kc.playGame(mm, km.getDeck(), false, 2, 1);

    Assert.assertTrue(log.toString().contains("Moving pile"));
  }

  @Test
  public void mockTestMoveDraw() {
    initData();
    Reader in = new StringReader("md 2 q");
    StringBuilder dontCare = new StringBuilder();
    StringBuilder log = new StringBuilder();
    MockModel mm = new MockModel(log);
    KlondikeController kc = new KlondikeTextualController(in, dontCare);
    kc.playGame(mm, km.getDeck(), false, 2, 1);

    Assert.assertTrue(log.toString().contains("Moving top draw card to pile 1"));
  }

  @Test
  public void mockTestMoveToFoundation() {
    initData();
    Reader in = new StringReader("mpf 1 1 q");
    StringBuilder dontCare = new StringBuilder();
    StringBuilder log = new StringBuilder();
    MockModel mm = new MockModel(log);
    KlondikeController kc = new KlondikeTextualController(in, dontCare);
    kc.playGame(mm, km.getDeck(), false, 2, 1);

    Assert.assertTrue(log.toString().contains("Moving card from pile 0 to foundation pile 0"));
  }

  @Test
  public void mockTestMoveDrawToFoundation() {
    initData();
    Reader in = new StringReader("mdf 1 q");
    StringBuilder dontCare = new StringBuilder();
    StringBuilder log = new StringBuilder();
    MockModel mm = new MockModel(log);
    KlondikeController kc = new KlondikeTextualController(in, dontCare);
    kc.playGame(mm, km.getDeck(), false, 2, 1);

    Assert.assertTrue(log.toString().contains("Moving top draw card to foundation pile 0"));
  }

  @Test
  public void mockTestDiscardDraw() {
    initData();
    Reader in = new StringReader("dd q");
    StringBuilder dontCare = new StringBuilder();
    StringBuilder log = new StringBuilder();
    MockModel mm = new MockModel(log);
    KlondikeController kc = new KlondikeTextualController(in, dontCare);
    kc.playGame(mm, km.getDeck(), false, 2, 1);

    Assert.assertTrue(log.toString().contains("Discarding draw."));
  }

  @Test
  public void testControllerQuittingDuringCommand() {
    initDeckA3Two();
    Readable in = new StringReader("mpp 1 q 2 3");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(this.km, this.deck, false, 2, 1);
    System.out.println(out.toString());

    Assert.assertTrue(out.toString().contains("Game quit!"));
  }

  @Test
  public void testGarbageCommand() {
    initDeckA3Two();
    Readable in = new StringReader("mpp a b c q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(this.km, this.deck, false, 2, 1);
    System.out.println(out.toString());

    Assert.assertTrue(out.toString().contains("Invalid move."));
  }

  @Test
  public void testTooFewInputs() {
    initDeckA3Two();
    Readable in = new StringReader("mpp 1 3 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    Assert.assertThrows(IllegalStateException.class, () ->
            kc.playGame(this.km, this.deck, false, 2, 1));
  }

  @Test
  public void testNoQuit() {
    initDeckA3Two();
    Readable in = new StringReader("mpp 1 3");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    Assert.assertThrows(IllegalStateException.class, () ->
            kc.playGame(this.km, this.deck, false, 2, 1));
  }

  @Test
  public void testNoInputs() {
    initDeckA3Two();
    Readable in = new StringReader("mpp q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    Assert.assertThrows(IllegalStateException.class, () ->
            kc.playGame(this.km, this.deck, false, 2, 1));
  }

  @Test
  public void testValidControllerInputButInvalidMoveToFoundationArgs() {
    initDeckA3();
    Readable in = new StringReader("mpf 4 1 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(km, deck, false, 2, 1);

    System.out.println(out.toString());

    Assert.assertTrue(out.toString().contains("Invalid move. Play Again. Invalid pile index"));
  }

  @Test
  public void testNumInvalidMoves() {
    initDeckA3();
    Readable in = new StringReader("mpf 1 b 1 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(km, deck, false, 2, 1);

    System.out.println(out.toString());

    Assert.assertTrue(out.toString().contains("Invalid move."));
  }

  @Test
  public void testGameWon() {
    initDeckA3Two();
    Readable in = new StringReader("mpf 2 1 "
            + "mpf 1 1 "
            + "mpf 2 1 "
            + "q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(km, deck, false, 2, 1);

    System.out.println(out.toString());

    Assert.assertTrue(out.toString().contains("You win!"));
  }

  @Test
  public void testGameOver() {
    initDeckA3();
    Readable in = new StringReader("mdf 1 "
            + "mdf 1 "
            + "q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(km, deck, false, 2, 1);
    System.out.println(out.toString());

    Assert.assertTrue(out.toString().contains("Game over."));
  }

  @Test
  public void testValidControllerInputButIllogicalMoveToFoundation() {
    initDeckA3();
    Readable in = new StringReader("mpf 2 1 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(km, deck, false, 2, 1);

    System.out.println(out.toString());
    Assert.assertTrue(out.toString().contains("Invalid move."));
  }

  @Test
  public void testValidControllerInputButIllogicalMoveToPile() {
    initDeckA3();
    Readable in = new StringReader("mpp 17 4 3 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(km, deck, false, 2, 1);

    System.out.println(out.toString());
    Assert.assertTrue(out.toString().contains("Invalid move."));
  }

  @Test
  public void testValidControllerInputButIllogicalMoveDraw() {
    initDeckA3();
    Readable in = new StringReader("md 1 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(km, deck, false, 2, 1);

    System.out.println(out.toString());
    Assert.assertTrue(out.toString().contains("Invalid move."));
  }

  @Test
  public void testValidControllerInputButIllogicalMoveDrawToFoundation() {
    initDeckA3();
    Readable in = new StringReader("mdf 1 mdf 2 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(km, deck, false, 2, 1);

    System.out.println(out.toString());
    Assert.assertTrue(out.toString().contains("Invalid move."));
  }

  @Test
  public void testDiscardDrawWithInputs() {
    initDeckA3();
    Readable in = new StringReader("dd 3 6 2 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(km, deck, false, 2, 1);

    System.out.println(out.toString());
    Assert.assertTrue(out.toString().contains("Invalid move."));
  }

  @Test
  public void testInvalidCommand() {
    initDeckA3();
    Readable in = new StringReader("mtp 5 5 5 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(km, deck, false, 2, 1);

    System.out.println(out.toString());
    Assert.assertTrue(out.toString().contains("Invalid move."));
  }

  @Test
  public void testEmptyReadable() {
    initDeckA3();
    Readable in = new StringReader("");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    Assert.assertThrows(IllegalStateException.class, () ->
            kc.playGame(km, deck, false, 2, 1));
  }

  @Test
  public void testScannerSkipsOverInvalidControllerArgs() {
    initDeckA3();
    Readable in = new StringReader("mdf -1 -3 1 q");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    kc.playGame(km, deck, false, 2, 1);

    System.out.println(out.toString());
    Assert.assertTrue(out.toString().contains("Foundation: A♢"));
  }

  @Test
  public void testThrowsWhenNoQuit() {
    initDeckA3();
    Readable in = new StringReader("dd");
    Appendable out = new StringBuilder();
    KlondikeController kc = new KlondikeTextualController(in, out);

    Assert.assertThrows(IllegalStateException.class,
        () -> kc.playGame(km, deck, false, 2, 1));
  }
}
