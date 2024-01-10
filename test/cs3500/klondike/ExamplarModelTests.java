package cs3500.klondike;

import cs3500.klondike.model.hw02.KlondikeModel;
import cs3500.klondike.model.hw02.BasicKlondike;
import cs3500.klondike.model.hw02.Card;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * tests for the examplar.
 */
public class ExamplarModelTests {

  public KlondikeModel km;
  public List<Card> wholeDeck;
  public List<Card> deck;

  /**
   * initializes data so it can be mutated in tests methods.
   */
  private void initData() {
    this.km = new BasicKlondike();
    this.wholeDeck = km.getDeck();
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

  @Test(expected = IllegalArgumentException.class)
  public void testMovePileNowhere() {
    // this method tests the case where the source pile and the destination pile are the same
    this.initData();
    km.startGame(this.wholeDeck, false, 3, 4);
    km.movePile(2, 2, 2);
  }

  @Test(expected = IllegalStateException.class)
  public void testBackwardsMovePile() {
    // throw an exception when you move a higher card onto a lower card
    this.initData();
    this.km.startGame(this.wholeDeck, false, 3, 1);
    this.km.movePile(2, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveDrawInvalidPile() {
    // if num piles is 7, destpile 7 is invalid because the index is 0-based
    this.initData();
    this.km.startGame(this.wholeDeck, false, 7, 1);
    this.km.moveDraw(7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveToFoundationInvalidPile() {
    // If num piles is 7, destPile 7 is invalid (the index is 0-based)
    this.initData();
    this.km.startGame(this.wholeDeck, false, 7, 1);
    this.km.moveToFoundation(7, 2);
  }

  @Test(expected = IllegalStateException.class)
  public void testMoveToFoundationInvalidMove() {
    // tries to move a card higher than an ace to an empty foundation pile
    this.initDeckA3();
    this.km.startGame(this.deck, false, 3, 1);
    this.km.moveToFoundation(2, 0);
  }

  @Test
  public void testMoveToFoundationEmptySrcPile() {
    // empty source pile
    this.initDeckA3();
    this.km.startGame(this.deck, false, 2, 1);
    this.km.moveToFoundation(0, 0);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.km.moveToFoundation(0, 1));
  }

  @Test
  public void testMoveToFoundationInvalidSuitMove() {
    // puts a 2 on an ace of a different suit in a foundation pile
    this.initDeckA3Two();
    this.km.startGame(this.deck, false, 2, 1);
    this.km.moveDrawToFoundation(0);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.km.moveToFoundation(0, 0));
  }

  @Test
  public void testMoveDrawToFoundationInvalidMove() {
    // puts a 2 on an empty foundation pile
    this.initDeckA3();
    this.km.startGame(this.deck, false, 2, 2);
    this.km.moveDrawToFoundation(0);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.km.moveDrawToFoundation(1));
  }

  @Test
  public void testMoveDrawToFoundationInvalidPile() {
    // invalid pile index of 2 (0-based index)
    this.initDeckA3();
    this.km.startGame(this.deck, false, 2, 2);
    this.km.moveDrawToFoundation(0);
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.km.moveDrawToFoundation(2));
  }

  @Test
  public void testDiscardWholeDeck() {
    // tests that the draw deck is reusable
    this.initData();
    this.km.startGame(this.wholeDeck, false, 2, 1);
    for (int i = 1; i < 50; i++) {
      this.km.discardDraw();
    }
    Assert.assertEquals(this.wholeDeck.get(3), this.km.getDrawCards().get(0));
  }

  @Test
  public void testSameColorMovePile() {
    // throws an exception when a black 2 is put on a black A
    this.initDeckA3Two();
    this.km.startGame(this.deck, false, 2, 1);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.km.movePile(1, 1, 0));
  }

  @Test
  public void testMovePileInvalidMove() {
    // tries to move a 3 and an A onto a 2
    this.initDeckA3Two();
    this.km.startGame(this.deck, false, 2, 1);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.km.movePile(1, 2, 0));
  }

  @Test
  public void testMovePileToEmptyDest() {
    // tests that only Kings can be moved to empty cascade piles
    this.initDeckA3();
    this.km.startGame(this.deck, false, 2, 1);
    this.km.moveToFoundation(0, 0);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.km.movePile(1, 1, 0));
  }

  @Test
  public void testMoveDrawToEmptyDest() {
    // tests that only kings can be moved to empty cascade piles
    this.initDeckA3();
    this.km.startGame(this.deck, false, 2, 1);
    this.km.moveToFoundation(0, 0);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.km.moveDraw(0));
  }

  @Test
  public void testGetDrawCards() {
    this.initData();
    this.km.startGame(this.wholeDeck, false, 7, 1);
    Assert.assertEquals(this.km.getDrawCards().size(), 1);
  }

  @Test
  public void testMoveToFoundationInvalidNumMove() {
    this.initDeckA3();
    this.km.startGame(this.deck, false, 2, 1);
    this.km.moveToFoundation(0, 0);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.km.moveToFoundation(1, 0));
  }
}
