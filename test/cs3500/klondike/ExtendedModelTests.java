package cs3500.klondike;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cs3500.klondike.model.hw02.BasicKlondike;
import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.KlondikeCard;
import cs3500.klondike.model.hw02.KlondikeModel;
import cs3500.klondike.model.hw02.Suit;
import cs3500.klondike.model.hw04.LimitedDrawKlondike;
import cs3500.klondike.model.hw04.WhiteheadKlondike;
import cs3500.klondike.view.KlondikeTextualView;

/**
 * Tests and examples to test the functionality of the new Klondike types whiteheadKlondike
 * and LimitedDrawKlondike.
 */
public class ExtendedModelTests {

  public KlondikeModel bk;
  public KlondikeModel ldk;
  public KlondikeModel whk;
  public List<Card> bkFullDeck;
  public List<Card> ldkFullDeck;
  public List<Card> whkFullDeck;
  public List<Card> deck;

  /**
   * initializes data so it can be mutated in tests methods.
   */
  private void initData() {
    this.bk = new BasicKlondike();
    this.ldk = new LimitedDrawKlondike(1);
    this.whk = new WhiteheadKlondike();
    this.bkFullDeck = bk.getDeck();
    this.ldkFullDeck = ldk.getDeck();
    this.whkFullDeck = whk.getDeck();
  }

  private void invalidDeck(List<Card> deck) {
    this.deck = new ArrayList<Card>();
    for (Card c : deck) {
      if (c.toString().contains("4♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("2♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("3♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("A♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("2♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("3♢")) {
        this.deck.add(c);
      }
    }
  }

  /**
   * initializes data so it can be mutated in tests methods.
   */
  private void initDeckA3(List<Card> deck) {
    this.deck = new ArrayList<Card>();
    for (Card c : deck) {
      if (c.toString().contains("A♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("2♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("3♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("A♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("2♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("3♢")) {
        this.deck.add(c);
      }
    }
  }

  /**
   * initializes data so it can be mutated in tests methods.
   */
  private void initDeckA3Two(List<Card> deck) {
    this.deck = new ArrayList<Card>();
    for (Card c : deck) {
      if (c.toString().contains("2♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("3♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("A♣")) {
        this.deck.add(c);
      }
    }

    for (Card c : deck) {
      if (c.toString().contains("A♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("2♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("3♢")) {
        this.deck.add(c);
      }
    }
  }

  /**
   * initializes data so it can be mutated in tests methods.
   */
  private void initDeckA3Three(List<Card> deck) {
    this.deck = new ArrayList<Card>();
    for (Card c : deck) {
      if (c.toString().contains("2♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("3♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("A♢")) {
        this.deck.add(c);
      }
    }

    for (Card c : deck) {
      if (c.toString().contains("A♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("2♢")) {
        this.deck.add(c);
      }
    }
    for (Card c : deck) {
      if (c.toString().contains("3♢")) {
        this.deck.add(c);
      }
    }
  }

  @Test
  public void testStartGameExceptionsOnLDK() {
    initData();
    this.initDeckA3(ldkFullDeck);
    Assert.assertThrows(IllegalArgumentException.class, () -> this.ldk.startGame(deck,
            false, 20, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> this.ldk.startGame(deck,
            false, 20, 55));
    Assert.assertThrows(IllegalArgumentException.class, () -> this.ldk.startGame(deck,
            false, 20, 3));
    Assert.assertThrows(IllegalArgumentException.class, () -> this.ldk.startGame(deck,
            false, 0, 3));
    this.ldk.startGame(deck, false, 2, 1);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.ldk.startGame(deck, false, 7, 3));

    initData();
    this.invalidDeck(ldkFullDeck);
    Assert.assertThrows(IllegalArgumentException.class, () -> this.ldk.startGame(deck,
            false, 2, 3));
  }

  @Test
  public void testStartGameExceptionsOnWHK() {
    initData();
    this.initDeckA3(whkFullDeck);
    Assert.assertThrows(IllegalArgumentException.class, () -> this.whk.startGame(deck,
            false, 20, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> this.whk.startGame(deck,
            false, 20, 55));
    Assert.assertThrows(IllegalArgumentException.class, () -> this.whk.startGame(deck,
            false, 20, 3));
    Assert.assertThrows(IllegalArgumentException.class, () -> this.whk.startGame(deck,
            false, 0, 3));
    this.whk.startGame(deck, false, 2, 1);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.whk.startGame(deck, false, 7, 3));

    initData();
    this.invalidDeck(whkFullDeck);
    Assert.assertThrows(IllegalArgumentException.class, () -> this.whk.startGame(deck,
            false, 2, 3));
  }

  @Test
  public void testStartGameCascadeLDK() {
    this.initData();
    this.ldk.startGame(ldkFullDeck, false, 2, 1);
    Assert.assertEquals(2, this.ldk.getNumPiles());
    Assert.assertEquals(4, this.ldk.getNumFoundations());
    Assert.assertEquals(1, this.ldk.getNumDraw());
  }

  @Test
  public void testStartGameCascadeWHK() {
    this.initData();
    this.whk.startGame(whkFullDeck, false, 2, 1);
    Assert.assertEquals(2, this.whk.getNumPiles());
    Assert.assertEquals(4, this.whk.getNumFoundations());
    Assert.assertEquals(1, this.whk.getNumDraw());
  }

  @Test
  public void testStartGameNumDrawLDK() {
    this.initData();
    this.ldk.startGame(ldkFullDeck, false, 2, 1);
    Assert.assertEquals(1, this.ldk.getNumDraw());
  }

  @Test
  public void testStartGameNumDrawWHK() {
    this.initData();
    this.whk.startGame(whkFullDeck, false, 2, 1);
    Assert.assertEquals(1, this.whk.getNumDraw());
  }

  @Test
  public void testStartGameShuffleLDK() {
    initData();
    this.ldk.startGame(ldkFullDeck, true, 7, 3);
    Assert.assertNotEquals(new KlondikeCard(Suit.valueOf("Clubs"), 13),
            this.ldk.getCardAt(5, 5));
  }

  @Test
  public void testStartGameShuffleWHK() {
    initData();
    this.whk.startGame(whkFullDeck, true, 7, 3);
    Assert.assertNotEquals(new KlondikeCard(Suit.valueOf("Clubs"), 13),
            this.whk.getCardAt(5, 5));
  }

  @Test
  public void testStartGameFoundationLDK() {
    this.initData();
    initDeckA3(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 1);
    Assert.assertEquals(2, this.ldk.getNumFoundations());
  }

  @Test
  public void testStartGameFoundationWHK() {
    this.initData();
    initDeckA3(whkFullDeck);
    this.whk.startGame(deck, false, 2, 1);
    Assert.assertEquals(2, this.whk.getNumFoundations());
  }

  @Test
  public void testMakeDrawLDK() {
    this.initData();
    initDeckA3(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 1);
    List<Card> expected = new ArrayList(Arrays.asList(new KlondikeCard(Suit.Diamonds, 1)));
    List<Card> actual = this.ldk.getDrawCards();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testMakeDrawWHK() {
    this.initData();
    initDeckA3(whkFullDeck);
    this.whk.startGame(deck, false, 2, 1);
    List<Card> expected = new ArrayList(Arrays.asList(new KlondikeCard(Suit.Diamonds, 1)));
    List<Card> actual = this.whk.getDrawCards();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testCascadeVisibilityLDK() {
    this.initData();
    initDeckA3(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 1);
    Assert.assertTrue(this.ldk.isCardVisible(0, 0));
    Assert.assertTrue(this.ldk.isCardVisible(1, 1));
    Assert.assertFalse(this.ldk.isCardVisible(1, 0));
  }

  @Test
  public void testCascadeVisibilityWHK() {
    this.initData();
    initDeckA3(whkFullDeck);
    this.whk.startGame(deck, false, 2, 1);
    Assert.assertTrue(this.whk.isCardVisible(0, 0));
    Assert.assertTrue(this.whk.isCardVisible(1, 1));
    Assert.assertTrue(this.whk.isCardVisible(1, 0));
  }

  @Test
  public void testMoveDrawLDK() {
    this.initData();
    initDeckA3Two(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 1);
    this.ldk.moveDraw(0);
    List<Card> expected = new ArrayList<Card>(Arrays.asList(new KlondikeCard(Suit.Diamonds, 1),
            new KlondikeCard(Suit.Clubs, 2)));
    List<Card> actual = new ArrayList<Card>(Arrays.asList(this.ldk.getCardAt(0, 1),
            this.ldk.getCardAt(0, 0)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testInvalidMoveDrawWHK() {
    this.initData();
    initDeckA3(whkFullDeck);
    this.whk.startGame(deck, false, 2, 1);
    this.whk.moveToFoundation(0, 0);
    this.whk.movePile(1, 1, 0);
    Assert.assertThrows(IllegalStateException.class, () -> this.whk.moveDraw(1));
  }

  @Test
  public void testValidMoveDrawWHK() {
    this.initData();
    initDeckA3Three(whkFullDeck);
    this.whk.startGame(deck, false, 2, 1);
    this.whk.moveDraw(0);
    List<Card> expected = new ArrayList<Card>(Arrays.asList(new KlondikeCard(Suit.Clubs, 2),
            new KlondikeCard(Suit.Clubs, 1)));
    List<Card> actual = new ArrayList<Card>(Arrays.asList(this.whk.getCardAt(0, 0),
            this.whk.getCardAt(0, 1)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testMovePileLDK() {
    this.initData();
    this.initDeckA3Three(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 1);
    this.ldk.movePile(1, 1, 0);
    List<Card> expected = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Diamonds, 1),
            new KlondikeCard(Suit.Clubs, 2)));
    List<Card> actual = new ArrayList<>(Arrays.asList(this.ldk.getCardAt(0, 1),
            this.ldk.getCardAt(0, 0)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testMovePileWHK() {
    this.initData();
    this.initDeckA3Two(whkFullDeck);
    this.whk.startGame(deck, false, 2, 1);
    this.whk.movePile(1, 1, 0);
    this.whk.movePile(0, 2, 1);
    List<Card> expected = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Clubs, 1),
            new KlondikeCard(Suit.Clubs, 2), new KlondikeCard(Suit.Clubs, 3)));
    List<Card> actual = new ArrayList<>(Arrays.asList(this.whk.getCardAt(1, 2),
            this.whk.getCardAt(1, 1), this.whk.getCardAt(1, 0)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testMovePileThrowsExceptionsLDK() {
    initData();
    this.ldk.startGame(this.ldkFullDeck, false, 7, 3);
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.ldk.movePile(7, 1, 3));
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.ldk.movePile(3, 3, -1));
    Assert.assertThrows(IllegalStateException.class, () ->
            this.ldk.movePile(5, 3, 4));
    this.ldk.moveToFoundation(2, 0);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.ldk.movePile(2, 1, 4));
  }

  @Test
  public void testMovePileThrowsExceptionsWHK() {
    initData();
    this.whk.startGame(this.whkFullDeck, false, 7, 3);
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.whk.movePile(7, 1, 3));
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.whk.movePile(3, 2, -1));
    Assert.assertThrows(IllegalStateException.class, () ->
            this.whk.movePile(5, 10, 4));
    this.whk.moveToFoundation(2, 0);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.whk.movePile(0, 1, 6));
  }

  @Test
  public void testMoveToFoundationLDK() {
    this.initData();
    initDeckA3Two(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 1);
    this.ldk.moveToFoundation(1, 0);
    List<Card> expectedFoundation = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Clubs, 1)));
    List<Card> actualFoundation = new ArrayList<>(Arrays.asList(this.ldk.getCardAt(0)));
    List<Card> expectedSrc = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Clubs, 3)));
    List<Card> actualSrc = new ArrayList<>(Arrays.asList(this.ldk.getCardAt(1, 0)));
    Assert.assertEquals(expectedFoundation, actualFoundation);
    Assert.assertEquals(expectedSrc, actualSrc);
    Assert.assertTrue(this.ldk.isCardVisible(1, 0));
  }

  @Test
  public void testMoveToFoundationWHK() {
    this.initData();
    initDeckA3Two(whkFullDeck);
    this.whk.startGame(deck, false, 2, 1);
    this.whk.moveToFoundation(1, 0);
    List<Card> expectedFoundation = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Clubs, 1)));
    List<Card> actualFoundation = new ArrayList<>(Arrays.asList(this.whk.getCardAt(0)));
    List<Card> expectedSrc = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Clubs, 3)));
    List<Card> actualSrc = new ArrayList<>(Arrays.asList(this.whk.getCardAt(1, 0)));
    Assert.assertEquals(expectedFoundation, actualFoundation);
    Assert.assertEquals(expectedSrc, actualSrc);
    Assert.assertTrue(this.whk.isCardVisible(1, 0));
  }

  @Test
  public void testMoveDrawToFoundationLDK() {
    this.initData();
    initDeckA3(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 1);
    Assert.assertEquals(1, this.ldk.getNumDraw());
    Assert.assertEquals(Arrays.asList(new KlondikeCard(Suit.Diamonds, 1)),
            this.ldk.getDrawCards());

    this.ldk.moveDrawToFoundation(0);

    List<Card> expected = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Diamonds, 1)));
    List<Card> actual = new ArrayList<>(Arrays.asList(this.ldk.getCardAt(0)));
    Assert.assertEquals(Arrays.asList(new KlondikeCard(Suit.Diamonds, 2)),
            this.ldk.getDrawCards());
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testMoveDrawToFoundationWHK() {
    this.initData();
    initDeckA3(whkFullDeck);
    this.whk.startGame(deck, false, 2, 1);
    Assert.assertEquals(1, this.whk.getNumDraw());
    Assert.assertEquals(Arrays.asList(new KlondikeCard(Suit.Diamonds, 1)),
            this.whk.getDrawCards());

    this.whk.moveDrawToFoundation(0);

    List<Card> expected = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Diamonds, 1)));
    List<Card> actual = new ArrayList<>(Arrays.asList(this.whk.getCardAt(0)));
    Assert.assertEquals(Arrays.asList(new KlondikeCard(Suit.Diamonds, 2)),
            this.whk.getDrawCards());
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testDiscardDrawLDK() {
    this.initData();
    initDeckA3(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 2);
    List<Card> expectedDraw = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Diamonds, 1),
            new KlondikeCard(Suit.Diamonds, 2)));
    List<Card> actualDraw = this.ldk.getDrawCards();
    Assert.assertEquals(expectedDraw, actualDraw);
    this.ldk.discardDraw();
    expectedDraw = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Diamonds, 2),
            new KlondikeCard(Suit.Diamonds, 3)));
    actualDraw = this.ldk.getDrawCards();
    Assert.assertEquals(expectedDraw, actualDraw);
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    Assert.assertEquals(this.ldk.getDrawCards().size(), 1);
    this.ldk.discardDraw();
    Assert.assertEquals(this.ldk.getDrawCards().size(), 0);
  }

  @Test
  public void testDiscardDrawWHK() {
    this.initData();
    initDeckA3(whkFullDeck);
    this.whk.startGame(deck, false, 2, 2);
    List<Card> expectedDraw = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Diamonds, 1),
            new KlondikeCard(Suit.Diamonds, 2)));
    List<Card> actualDraw = this.whk.getDrawCards();
    Assert.assertEquals(expectedDraw, actualDraw);
    this.whk.discardDraw();
    expectedDraw = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Diamonds, 2),
            new KlondikeCard(Suit.Diamonds, 3)));
    actualDraw = this.whk.getDrawCards();
    Assert.assertEquals(expectedDraw, actualDraw);

    this.whk.discardDraw();
    this.whk.discardDraw();
    this.whk.discardDraw();
    this.whk.discardDraw();
    Assert.assertEquals(this.whk.getDrawCards().size(), 2);
    this.whk.discardDraw();
    Assert.assertEquals(this.whk.getDrawCards().size(), 2);
  }

  @Test
  public void testGetScoreLDK() {
    initData();
    initDeckA3(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 1);
    Assert.assertEquals(0, this.ldk.getScore());
    this.ldk.moveToFoundation(0, 0);
    Assert.assertEquals(1, this.ldk.getScore());
  }

  @Test
  public void testGetScoreWHK() {
    initData();
    initDeckA3(whkFullDeck);
    this.whk.startGame(deck, false, 2, 1);
    Assert.assertEquals(0, this.whk.getScore());
    this.whk.moveToFoundation(0, 0);
    Assert.assertEquals(1, this.whk.getScore());
  }

  @Test
  public void testInvalidMoveToFoundationLDK() {
    initData();
    initDeckA3(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 1);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.ldk.moveToFoundation(1, 0));
  }

  @Test
  public void testInvalidMoveToFoundationWHK() {
    initData();
    initDeckA3(whkFullDeck);
    this.whk.startGame(deck, false, 2, 1);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.whk.moveToFoundation(1, 0));
  }

  @Test
  public void testMoveDrawToEmptyPileLDK() {
    initData();
    this.ldk.startGame(ldkFullDeck, false, 7, 1);
    this.ldk.moveToFoundation(0, 0);
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.moveDraw(0);
    List<Card> expected = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Hearts, 13)));
    List<Card> actual = new ArrayList<>(Arrays.asList(this.ldk.getCardAt(0, 0)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testMoveDrawToEmptyPileWHK() {
    initData();
    this.whk.startGame(whkFullDeck, false, 7, 1);
    this.whk.moveToFoundation(0, 0);
    this.whk.moveDraw(0);
    List<Card> expected = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Hearts, 3)));
    List<Card> actual = new ArrayList<>(Arrays.asList(this.whk.getCardAt(0, 0)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testInvalidMoveDrawToEmptyPileLDK() {
    initData();
    this.ldk.startGame(ldkFullDeck, false, 7, 1);
    this.ldk.moveToFoundation(0, 0);
    Assert.assertThrows(IllegalStateException.class, () -> this.ldk.moveDraw(0));
  }

  @Test
  public void testMoveDrawToNonEmptyFoundationLDK() {
    initData();
    initDeckA3(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 2);
    this.ldk.moveDrawToFoundation(0);
    this.ldk.moveDrawToFoundation(0);
    List<Card> expectedDraw = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Diamonds, 3)));
    List<Card> actualDraw = this.ldk.getDrawCards();
    Assert.assertEquals(expectedDraw, actualDraw);
    Card expectedFoundation = new KlondikeCard(Suit.Diamonds, 2);
    Card actualFoundation = this.ldk.getCardAt(0);
    Assert.assertEquals(expectedFoundation, actualFoundation);
  }

  @Test
  public void testMoveDrawToNonEmptyFoundationWHK() {
    initData();
    initDeckA3(whkFullDeck);
    this.whk.startGame(deck, false, 2, 2);
    this.whk.moveDrawToFoundation(0);
    this.whk.moveDrawToFoundation(0);
    List<Card> expectedDraw = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Diamonds, 3)));
    List<Card> actualDraw = this.whk.getDrawCards();
    Assert.assertEquals(expectedDraw, actualDraw);
    Card expectedFoundation = new KlondikeCard(Suit.Diamonds, 2);
    Card actualFoundation = this.whk.getCardAt(0);
    Assert.assertEquals(expectedFoundation, actualFoundation);
  }

  @Test
  public void testInvalidMoveDrawToNonEmptyFoundationLDK() {
    initData();
    initDeckA3(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 2);
    this.ldk.discardDraw();
    Assert.assertThrows(IllegalStateException.class, () ->
            this.ldk.moveDrawToFoundation(0));
  }

  @Test
  public void testInvalidMoveDrawToNonEmptyFoundationWHK() {
    initData();
    initDeckA3(whkFullDeck);
    this.whk.startGame(deck, false, 2, 2);
    this.whk.discardDraw();
    Assert.assertThrows(IllegalStateException.class, () ->
            whk.moveDrawToFoundation(0));
  }

  @Test
  public void testIsCardVisibleLDK() {
    initData();
    initDeckA3(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 2);
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.ldk.getCardAt(1, 0));
    Assert.assertTrue(this.ldk.isCardVisible(1, 1));
  }

  @Test
  public void testIsCardVisibleWHK() {
    initData();
    initDeckA3(whkFullDeck);
    this.whk.startGame(deck, false, 2, 2);
    Assert.assertTrue(this.whk.isCardVisible(1, 0));
    Assert.assertTrue(this.whk.isCardVisible(1, 1));
  }

  @Test
  public void testStartGameWithInvalidDeckLDKAndWHK() {
    initData();
    invalidDeck(ldkFullDeck);
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.ldk.startGame(deck, true, 1, 1));
    initData();
    invalidDeck(whkFullDeck);
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.whk.startGame(deck, true, 1, 1));
  }

  @Test
  public void testMovePileToEmptyPileLDK() {
    initData();
    this.ldk.startGame(ldkFullDeck, false, 7, 3);
    this.ldk.moveToFoundation(0, 0);
    this.ldk.movePile(5, 1, 0);
    List<Card> expected = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Clubs, 13)));
    List<Card> actual = new ArrayList<>(Arrays.asList(this.ldk.getCardAt(0, 0)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testInvalidMovePileToEmptyPileLDK() {
    initData();
    this.ldk.startGame(ldkFullDeck, false, 7, 3);
    this.ldk.moveToFoundation(0, 0);
    Assert.assertThrows(IllegalStateException.class, () -> this.ldk.movePile(1, 1,
            0));
  }

  @Test
  public void testMovePileToEmptyPileWHK() {
    initData();
    this.whk.startGame(whkFullDeck, false, 7, 3);
    this.whk.moveToFoundation(0, 0);
    this.whk.movePile(1, 1, 0);
    List<Card> expected = new ArrayList<>(Arrays.asList(new KlondikeCard(Suit.Spades, 8)));
    List<Card> actual = new ArrayList<>(Arrays.asList(this.whk.getCardAt(0, 0)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testGetCardAtBeforeStartGameLDKAndWHK() {
    initData();
    Assert.assertThrows(IllegalStateException.class, () -> this.ldk.getCardAt(1));
    Assert.assertThrows(IllegalStateException.class, () -> this.ldk.getCardAt(0, 1));
    Assert.assertThrows(IllegalStateException.class, () -> this.whk.getCardAt(1));
    Assert.assertThrows(IllegalStateException.class, () -> this.whk.getCardAt(0, 1));
  }

  @Test
  public void testGetCardAtLDK() {
    initData();
    this.ldk.startGame(ldkFullDeck, false, 7, 3);
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.ldk.getCardAt(3, 0));
    Assert.assertEquals(new KlondikeCard(Suit.Spades, 1), this.ldk.getCardAt(0, 0));
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.ldk.getCardAt(20, 25));
  }

  @Test
  public void testGetCardAtWHK() {
    initData();
    this.whk.startGame(whkFullDeck, false, 7, 3);
    Assert.assertEquals(new KlondikeCard(Suit.Spades, 4),
            this.whk.getCardAt(3, 0));
    Assert.assertEquals(new KlondikeCard(Suit.Spades, 1), this.whk.getCardAt(0, 0));
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.whk.getCardAt(20, 25));
  }

  @Test
  public void testGameOverTrueLDK() {
    initData();
    initDeckA3Two(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 1);
    this.ldk.moveToFoundation(1, 0);
    this.ldk.moveToFoundation(0, 0);
    this.ldk.moveToFoundation(1, 0);
    this.ldk.moveDrawToFoundation(1);
    this.ldk.moveDrawToFoundation(1);
    this.ldk.moveDrawToFoundation(1);
    Assert.assertTrue(this.ldk.isGameOver());
  }

  @Test
  public void testGameOverTrueWHK() {
    initData();
    initDeckA3Two(whkFullDeck);
    this.whk.startGame(deck, false, 2, 1);
    this.whk.moveToFoundation(1, 0);
    this.whk.moveToFoundation(0, 0);
    this.whk.moveToFoundation(1, 0);
    this.whk.moveDrawToFoundation(1);
    this.whk.moveDrawToFoundation(1);
    this.whk.moveDrawToFoundation(1);
    Assert.assertTrue(this.whk.isGameOver());
  }

  @Test
  public void testGameOverFalse() {
    initData();
    this.ldk.startGame(ldkFullDeck, false, 2, 1);
    Assert.assertFalse(this.ldk.isGameOver());
    this.whk.startGame(whkFullDeck, false, 2, 1);
    Assert.assertFalse(this.whk.isGameOver());
  }

  @Test
  public void testLimitedDrawProperlyRemoves() {
    initData();
    this.initDeckA3(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 3);
    Assert.assertEquals(ldk.getDrawCards().size(), 3);
    this.ldk.discardDraw();
    Assert.assertEquals(ldk.getDrawCards().size(), 3);
    this.ldk.moveDraw(1);
    Assert.assertEquals(ldk.getDrawCards().size(), 2);
    this.ldk.discardDraw();
    Assert.assertEquals(ldk.getDrawCards().size(), 2);
    this.ldk.discardDraw();
    Assert.assertEquals(ldk.getDrawCards().size(), 1);
    this.ldk.discardDraw();
    Assert.assertEquals(ldk.getDrawCards().size(), 0);
  }

  @Test
  public void testLimitedDrawRecycles() {
    this.initData();
    initDeckA3(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 3);
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    Assert.assertEquals(3, ldk.getDrawCards().size());
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    Assert.assertEquals(0, this.ldk.getDrawCards().size());
  }

  @Test
  public void testLimitedDrawMoveFirst() {
    this.initData();
    initDeckA3Two(ldkFullDeck);
    this.ldk.startGame(deck, false, 2, 3);
    System.out.println(new KlondikeTextualView(ldk));
    this.ldk.moveToFoundation(1, 0);
    System.out.println(new KlondikeTextualView(ldk));
    this.ldk.discardDraw();
    System.out.println(new KlondikeTextualView(ldk));
    this.ldk.moveDraw(1);
    System.out.println(new KlondikeTextualView(ldk));
    this.ldk.discardDraw();
    System.out.println(new KlondikeTextualView(ldk));
    Assert.assertEquals(new KlondikeCard(Suit.Diamonds, 1), ldk.getDrawCards().get(0));
    this.ldk.moveDraw(0);
    ldk.moveToFoundation(0, 1);
    ldk.moveToFoundation(1, 1);
    ldk.moveDrawToFoundation(1);
  }

  @Test
  public void testEnums() {
    this.initData();
    System.out.println();
    System.out.println(Arrays.toString(Suit.values()));
    System.out.println(Suit.valueOf("Clubs"));
  }

}
