package cs3500.klondike;

import cs3500.klondike.model.hw02.BasicKlondike;
import cs3500.klondike.model.hw02.KlondikeModel;
import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw04.WhiteheadKlondike;
import cs3500.klondike.model.hw04.LimitedDrawKlondike;
import cs3500.klondike.view.KlondikeTextualView;
import cs3500.klondike.view.TextualView;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests and examples for the classes added to extend KlondikeModel to LimitedDraw and Whitehead.
 */
public class ExamplarExtendedModelTests {

  public KlondikeModel km;
  public List<Card> wholeDeck;
  public List<Card> deck;
  public TextualView textview;
  public LimitedDrawKlondike ldk;
  public WhiteheadKlondike whk;

  /**
   * initializes data so it can be mutated in tests methods.
   */
  private void initData() {
    this.km = new BasicKlondike();
    this.wholeDeck = km.getDeck();
    this.textview = new KlondikeTextualView(this.km);
    this.ldk = new LimitedDrawKlondike(1);
    this.whk = new WhiteheadKlondike();
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
      if (c.toString().contains("2♠")) {
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
      if (c.toString().contains("A♠")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("2♣")) {
        this.deck.add(c);
      }
    }
    for (Card c : this.wholeDeck) {
      if (c.toString().contains("3♠")) {
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
  public void testAllCardsFaceUp() {
    this.initDeckA3();
    this.whk.startGame(deck, false, 2, 1);
    Assert.assertTrue(whk.isCardVisible(1, 0));
  }

  @Test
  public void testNotAlternatingBuilds() {
    this.initDeckA3Three();
    this.whk.startGame(deck, false, 2, 1);
    Assert.assertThrows(IllegalStateException.class,
        () -> whk.movePile(1, 1, 0));
  }

  @Test
  public void testMoveBuildSameSuit() {
    this.initDeckA3Two();
    this.whk.startGame(deck, false, 2, 1);
    whk.movePile(1, 1, 0);
    Assert.assertThrows(IllegalStateException.class,
        () -> whk.movePile(0, 2, 1));
  }

  @Test
  public void testAnyValueToEmptyCascade() {
    this.initDeckA3();
    this.whk.startGame(deck, false, 2, 1);
    whk.moveToFoundation(0, 0);
    whk.movePile(1, 1, 0);
    Assert.assertEquals("3♣", whk.getCardAt(0, 0).toString());
  }

  @Test
  public void testLimitedDrawPileReturnsWhenMoreThanZero() {
    this.initDeckA3();
    this.ldk.startGame(deck, false, 2, 1);
    this.ldk.discardDraw();
    this.ldk.discardDraw();
    this.ldk.discardDraw();

    Assert.assertEquals("A♢", this.ldk.getDrawCards().get(0).toString());

  }
}
