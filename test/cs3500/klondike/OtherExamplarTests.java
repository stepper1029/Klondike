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
import cs3500.klondike.view.KlondikeTextualView;
import cs3500.klondike.view.TextualView;


/**
 * tests the public methods in the implementation of KlondikeModel and other classes or interfaces.
 */
public class OtherExamplarTests {

  public KlondikeModel km;
  public Card aSpades;
  public Card twoSpades;
  public Card threeSpades;
  public Card fourSpades;
  public Card fiveSpades;
  public Card sixSpades;
  public Card sevenSpades;
  public Card eightSpades;
  public Card nineSpades;
  public Card tenSpades;
  public Card jSpades;
  public Card qSpades;
  public Card kSpades;
  public Card aClubs;
  public Card twoClubs;
  public Card threeClubs;
  public Card fourClubs;
  public Card fiveClubs;
  public Card sixClubs;
  public Card sevenClubs;
  public Card eightClubs;
  public Card nineClubs;
  public Card tenClubs;
  public Card jClubs;
  public Card qClubs;
  public Card kClubs;
  public Card aHearts;
  public Card twoHearts;
  public Card threeHearts;
  public Card fourHearts;
  public Card fiveHearts;
  public Card sixHearts;
  public Card sevenHearts;
  public Card eightHearts;
  public Card nineHearts;
  public Card tenHearts;
  public Card jHearts;
  public Card qHearts;
  public Card kHearts;
  public Card aDiamonds;
  public Card twoDiamonds;
  public Card threeDiamonds;
  public Card fourDiamonds;
  public Card fiveDiamonds;
  public Card sixDiamonds;
  public Card sevenDiamonds;
  public Card eightDiamonds;
  public Card nineDiamonds;
  public Card tenDiamonds;
  public Card jDiamonds;
  public Card qDiamonds;
  public Card kDiamonds;
  public List<Card> fullDeck;
  public List<Card> fullDeck2;
  public List<Card> miniDeck;
  public List<Card> miniDeck2;
  public List<Card> miniDeck3;
  public List<Card> invalidDeck;
  public List<Card> invalidDeck2;
  public List<Card> invalidDeck3;
  public TextualView textview;


  private void initData() {
    this.km = new BasicKlondike();
    this.aSpades = new KlondikeCard(Suit.Spades, 1);
    this.aClubs = new KlondikeCard(Suit.Clubs, 1);
    this.aHearts = new KlondikeCard(Suit.Hearts, 1);
    this.aDiamonds = new KlondikeCard(Suit.Diamonds, 1);
    this.twoSpades = new KlondikeCard(Suit.Spades, 2);
    this.twoClubs = new KlondikeCard(Suit.Clubs, 2);
    this.twoHearts = new KlondikeCard(Suit.Hearts, 2);
    this.twoDiamonds = new KlondikeCard(Suit.Diamonds, 2);
    this.threeSpades = new KlondikeCard(Suit.Spades, 3);
    this.threeClubs = new KlondikeCard(Suit.Clubs, 3);
    this.threeHearts = new KlondikeCard(Suit.Hearts, 3);
    this.threeDiamonds = new KlondikeCard(Suit.Diamonds, 3);
    this.fourSpades = new KlondikeCard(Suit.Spades, 4);
    this.fourClubs = new KlondikeCard(Suit.Clubs, 4);
    this.fourHearts = new KlondikeCard(Suit.Hearts, 4);
    this.fourDiamonds = new KlondikeCard(Suit.Diamonds, 4);
    this.fiveSpades = new KlondikeCard(Suit.Spades, 5);
    this.fiveClubs = new KlondikeCard(Suit.Clubs, 5);
    this.fiveHearts = new KlondikeCard(Suit.Hearts, 5);
    this.fiveDiamonds = new KlondikeCard(Suit.Diamonds, 5);
    this.sixSpades = new KlondikeCard(Suit.Spades, 6);
    this.sixClubs = new KlondikeCard(Suit.Clubs, 6);
    this.sixHearts = new KlondikeCard(Suit.Hearts, 6);
    this.sixDiamonds = new KlondikeCard(Suit.Diamonds, 6);
    this.sevenSpades = new KlondikeCard(Suit.Spades, 7);
    this.sevenClubs = new KlondikeCard(Suit.Clubs, 7);
    this.sevenHearts = new KlondikeCard(Suit.Hearts, 7);
    this.sevenDiamonds = new KlondikeCard(Suit.Diamonds, 7);
    this.eightSpades = new KlondikeCard(Suit.Spades, 8);
    this.eightClubs = new KlondikeCard(Suit.Clubs, 8);
    this.eightHearts = new KlondikeCard(Suit.Hearts, 8);
    this.eightDiamonds = new KlondikeCard(Suit.Diamonds, 8);
    this.nineSpades = new KlondikeCard(Suit.Spades, 9);
    this.nineClubs = new KlondikeCard(Suit.Clubs, 9);
    this.nineHearts = new KlondikeCard(Suit.Hearts, 9);
    this.nineDiamonds = new KlondikeCard(Suit.Diamonds, 9);
    this.tenSpades = new KlondikeCard(Suit.Spades, 10);
    this.tenClubs = new KlondikeCard(Suit.Clubs, 10);
    this.tenHearts = new KlondikeCard(Suit.Hearts, 10);
    this.tenDiamonds = new KlondikeCard(Suit.Diamonds, 10);
    this.jSpades = new KlondikeCard(Suit.Spades, 11);
    this.jClubs = new KlondikeCard(Suit.Clubs, 11);
    this.jHearts = new KlondikeCard(Suit.Hearts, 11);
    this.jDiamonds = new KlondikeCard(Suit.Diamonds, 11);
    this.qSpades = new KlondikeCard(Suit.Spades, 12);
    this.qClubs = new KlondikeCard(Suit.Clubs, 12);
    this.qHearts = new KlondikeCard(Suit.Hearts, 12);
    this.qDiamonds = new KlondikeCard(Suit.Diamonds, 12);
    this.kSpades = new KlondikeCard(Suit.Spades, 13);
    this.kClubs = new KlondikeCard(Suit.Clubs, 13);
    this.kHearts = new KlondikeCard(Suit.Hearts, 13);
    this.kDiamonds = new KlondikeCard(Suit.Diamonds, 13);
    this.fullDeck = new ArrayList<>(Arrays.asList(aSpades, twoSpades, threeSpades, fourSpades,
            fiveSpades, sixSpades, sevenSpades, eightSpades, nineSpades, tenSpades, jSpades,
            qSpades, kSpades, aClubs, twoClubs, threeClubs, fourClubs, fiveClubs, sixClubs,
            sevenClubs, eightClubs, nineClubs, tenClubs, jClubs, qClubs, kClubs, aHearts, twoHearts,
            threeHearts, fourHearts, fiveHearts, sixHearts, sevenHearts, eightHearts, nineHearts,
            tenHearts, jHearts, qHearts, kHearts, aDiamonds, twoDiamonds, threeDiamonds,
            fourDiamonds, fiveDiamonds, sixDiamonds, sevenDiamonds, eightDiamonds, nineDiamonds,
            tenDiamonds, jDiamonds, qDiamonds, kDiamonds));
    this.fullDeck2 = new ArrayList<>(Arrays.asList(aSpades, twoSpades, threeSpades, fourSpades,
            fiveSpades, sixSpades, sevenSpades, eightSpades, nineSpades, tenSpades, jSpades,
            qSpades, kSpades, aClubs, twoClubs, threeClubs, fourClubs, fiveClubs, sixClubs,
            sevenClubs, eightClubs, nineClubs, tenClubs, jClubs, qClubs, kClubs, aHearts, qHearts,
            kHearts, fourHearts, fiveHearts, sixHearts, sevenHearts, eightHearts, nineHearts,
            tenHearts, jHearts, twoHearts, threeHearts, aDiamonds, twoDiamonds, threeDiamonds,
            fourDiamonds, fiveDiamonds, sixDiamonds, sevenDiamonds, eightDiamonds, nineDiamonds,
            tenDiamonds, jDiamonds, qDiamonds, kDiamonds));
    this.miniDeck = new ArrayList<>(Arrays.asList(this.twoSpades, this.threeSpades, this.aSpades,
            this.aHearts, this.twoHearts, this.threeHearts));
    this.miniDeck2 = new ArrayList<>(Arrays.asList(this.twoHearts, this.threeSpades, this.aSpades,
            this.aHearts, this.twoSpades, this.threeHearts));
    this.miniDeck3 = new ArrayList<>(Arrays.asList(this.twoSpades, this.threeSpades, this.aSpades,
            this.threeHearts, this.twoHearts, this.aHearts));
    this.invalidDeck = new ArrayList<>(Arrays.asList(threeSpades, fiveHearts));
    this.invalidDeck2 = new ArrayList<>(Arrays.asList(twoSpades, threeSpades, fourSpades));
    this.invalidDeck3 = new ArrayList<>(Arrays.asList(aDiamonds, twoDiamonds, threeDiamonds, aClubs,
            twoClubs, threeClubs, fourClubs));
    this.textview = new KlondikeTextualView(this.km);
  }


  @Test
  public void testToStringWorks() {
    this.initData();
    String actual = this.aSpades.toString();
    String expected = "A♠";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testToStringWordsNum() {
    this.initData();
    String actual = this.fiveDiamonds.toString();
    String expected = "5♢";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testCardConstructorHighValue() {
    this.initData();
    Assert.assertThrows(IllegalArgumentException.class, () -> new KlondikeCard(Suit.Spades,
            14));
  }

  @Test
  public void testCardConstructorLowValue() {
    this.initData();
    Assert.assertThrows(IllegalArgumentException.class, () -> new KlondikeCard(Suit.Spades,
            0));
  }

  @Test
  public void testGetDeck() {
    this.initData();
    Assert.assertEquals(this.fullDeck, this.km.getDeck());
  }

  @Test
  public void testStartGameExceptionOne() {
    this.initData();
    Assert.assertThrows(IllegalArgumentException.class, () -> this.km.startGame(invalidDeck,
            false, 2, 3));
  }

  @Test
  public void testStartGameExceptionTwo() {
    this.initData();
    Assert.assertThrows(IllegalArgumentException.class, () -> this.km.startGame(miniDeck,
            false, 0, 3));
  }

  @Test
  public void testStartGameExceptionThree() {
    this.initData();
    Assert.assertThrows(IllegalArgumentException.class, () -> this.km.startGame(miniDeck,
            false, 20, 3));
  }

  @Test
  public void testStartGameExceptionFour() {
    this.initData();
    Assert.assertThrows(IllegalArgumentException.class, () -> this.km.startGame(miniDeck,
            false, 20, 55));
  }

  @Test
  public void testStartGameExceptionFive() {
    this.initData();
    Assert.assertThrows(IllegalArgumentException.class, () -> this.km.startGame(miniDeck,
            false, 20, 0));
  }

  @Test
  public void testStartGameAfterStartGame() {
    initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.km.startGame(this.fullDeck, false, 7, 3));
  }

  @Test
  public void testStartGameCascade() {
    this.initData();
    this.km.startGame(this.fullDeck, false, 2, 1);
    Assert.assertEquals(2, this.km.getNumPiles());
    Assert.assertEquals(4, this.km.getNumFoundations());
    Assert.assertEquals(1, this.km.getNumDraw());
  }

  @Test
  public void testStartGameNumDraw() {
    this.initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    Assert.assertEquals(1, this.km.getNumDraw());
  }

  @Test
  public void testStartGameShuffle() {
    initData();
    this.km.startGame(this.fullDeck, true, 7, 3);
    Assert.assertNotEquals(kClubs, this.km.getCardAt(5, 5));
  }

  @Test
  public void testStartGameFoundation() {
    this.initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    Assert.assertEquals(2, this.km.getNumFoundations());
  }

  @Test
  public void testMakeDraw() {
    this.initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    List<Card> expected = new ArrayList(Arrays.asList(aHearts));
    List<Card> actual = this.km.getDrawCards();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testCascadeVisibility() {
    this.initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    Assert.assertTrue(this.km.isCardVisible(0, 0));
    Assert.assertTrue(this.km.isCardVisible(1, 1));
  }

  @Test
  public void testMoveDraw() {
    this.initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    this.km.getCardAt(0, 0);
    this.km.moveDraw(0);
    List<Card> expected = new ArrayList<Card>(Arrays.asList(this.aHearts, this.twoSpades));
    List<Card> actual = new ArrayList<Card>(Arrays.asList(this.km.getCardAt(0, 1),
            this.km.getCardAt(0, 0)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testMovePile() {
    this.initData();
    this.km.startGame(this.miniDeck2, false, 2, 1);
    this.km.movePile(1, 1, 0);
    List<Card> expected = new ArrayList<>(Arrays.asList(aSpades, twoHearts));
    this.km.getCardAt(0, 0);
    List<Card> actual = new ArrayList<>(Arrays.asList(this.km.getCardAt(0, 1),
            this.km.getCardAt(0, 0)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testMovePile2() {
    this.initData();
    this.km.startGame(this.miniDeck2, false, 2, 1);
    this.km.movePile(1, 1, 0);
    this.km.movePile(0, 2, 1);
    Assert.assertEquals(3, this.km.getNumRows());
    List<Card> expected = new ArrayList<>(Arrays.asList(aSpades, twoHearts, threeSpades));
    List<Card> actual = new ArrayList<>(Arrays.asList(this.km.getCardAt(1, 2),
            this.km.getCardAt(1, 1), this.km.getCardAt(1, 0)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testMovePileThrowsExceptions() {
    initData();
    this.km.startGame(this.fullDeck, false, 7, 3);
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.km.movePile(7, 1, 3));
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.km.movePile(3, 3, -1));
    Assert.assertThrows(IllegalStateException.class, () ->
            this.km.movePile(5, 3, 4));
    this.km.moveToFoundation(2, 0);
    Assert.assertThrows(IllegalStateException.class, () ->
            this.km.movePile(2, 1, 4));
  }

  @Test
  public void testMoveToFoundation() {
    this.initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    this.km.moveToFoundation(1, 0);
    List<Card> expectedFoundation = new ArrayList<>(Arrays.asList(aSpades));
    List<Card> actualFoundation = new ArrayList<>(Arrays.asList(this.km.getCardAt(0)));
    List<Card> expectedSrc = new ArrayList<>(Arrays.asList(threeSpades));
    List<Card> actualSrc = new ArrayList<>(Arrays.asList(this.km.getCardAt(1, 0)));
    Assert.assertEquals(expectedFoundation, actualFoundation);
    Assert.assertEquals(expectedSrc, actualSrc);
    Assert.assertTrue(this.km.isCardVisible(1, 0));
  }

  @Test
  public void testMoveDrawToFoundation() {
    this.initData();
    this.km.startGame(this.miniDeck2, false, 2, 1);
    Assert.assertEquals(1, this.km.getNumDraw());
    Assert.assertEquals(Arrays.asList(aHearts), this.km.getDrawCards());

    this.km.moveDrawToFoundation(0);

    List<Card> expected = new ArrayList<>(Arrays.asList(aHearts));
    List<Card> actual = new ArrayList<>(Arrays.asList(this.km.getCardAt(0)));
    Assert.assertEquals(Arrays.asList(twoSpades), this.km.getDrawCards());
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testTextualView() {
    this.initData();
    this.km.startGame(fullDeck, false, 7, 2);
    String expected = "Draw: 3♡, 4♡\nFoundation: <none>, <none>, <none>, <none>\n"
            + " A♠  ?  ?  ?  ?  ?  ?\n    8♠  ?  ?  ?  ?  ?\n       A♣  ?  ?  ?  ?\n"
            + "          6♣  ?  ?  ?\n            10♣  ?  ?\n                K♣  ?\n"
            + "                   2♡";
    String actual = textview.toString();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testTextualView2() {
    this.initData();
    this.km.startGame(fullDeck, false, 7, 2);
    String expected = "Draw: 3♡, 4♡\nFoundation: A♠, <none>, <none>, <none>\n"
            + "  X  ?  ?  ?  ?  ?  ?\n    8♠  ?  ?  ?  ?  ?\n       A♣  ?  ?  ?  ?\n"
            + "          6♣  ?  ?  ?\n            10♣  ?  ?\n                K♣  ?\n"
            + "                   2♡";
    this.km.moveToFoundation(0, 0);
    String actual = textview.toString();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testDiscardDraw() {
    this.initData();
    this.km.startGame(this.miniDeck2, false, 2, 2);
    List<Card> expectedDraw = new ArrayList<>(Arrays.asList(aHearts, twoSpades));
    List<Card> actualDraw = this.km.getDrawCards();
    Assert.assertEquals(expectedDraw, actualDraw);
    this.km.discardDraw();
    expectedDraw = new ArrayList<>(Arrays.asList(twoSpades, threeHearts));
    actualDraw = this.km.getDrawCards();
    Assert.assertEquals(expectedDraw, actualDraw);
  }

  @Test
  public void testGetScore() {
    initData();
    this.km.startGame(this.miniDeck2, false, 2, 1);
    Assert.assertEquals(0, this.km.getScore());
    this.km.moveToFoundation(1, 0);
    Assert.assertEquals(1, this.km.getScore());
  }

  @Test
  public void testMoveDrawToEmptyPile() {
    initData();
    this.km.startGame(this.fullDeck2, false, 7, 1);
    System.out.println(this.textview);
    System.out.println();
    this.km.moveToFoundation(0, 0);
    System.out.println(this.textview);
    System.out.println();
    this.km.moveDraw(0);
    System.out.println(this.textview);
    List<Card> expected = new ArrayList<>(Arrays.asList(kHearts));
    List<Card> actual = new ArrayList<>(Arrays.asList(this.km.getCardAt(0, 0)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testMoveDrawToNonEmptyFoundation() {
    initData();
    this.km.startGame(this.miniDeck, false, 2, 2);
    this.km.moveDrawToFoundation(0);
    this.km.moveDrawToFoundation(0);
    List<Card> expectedDraw = new ArrayList<>(Arrays.asList(threeHearts));
    List<Card> actualDraw = this.km.getDrawCards();
    Assert.assertEquals(expectedDraw, actualDraw);
    Card expectedFoundation = twoHearts;
    Card actualFoudnation = this.km.getCardAt(0);
    Assert.assertEquals(expectedFoundation, actualFoudnation);
  }

  @Test
  public void testIsCardVisible() {
    initData();
    this.km.startGame(this.miniDeck, false, 2, 2);
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.km.getCardAt(1, 0));
    Assert.assertTrue(this.km.isCardVisible(1, 1));
  }

  @Test
  public void testStartGameWithInvalidDeck() {
    initData();
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.km.startGame(this.invalidDeck, true, 1, 1));
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.km.startGame(this.invalidDeck2, true, 1, 1));
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.km.startGame(this.invalidDeck3, true, 1, 1));
  }

  @Test
  public void testMovePileToEmptyPile() {
    initData();
    this.km.startGame(this.fullDeck2, false, 7, 3);
    System.out.println(this.textview);
    System.out.println();
    this.km.moveToFoundation(0, 0);
    System.out.println(this.textview);
    System.out.println();
    this.km.movePile(6, 1, 5);
    System.out.println(this.textview);
    System.out.println();
    this.km.movePile(5, 2, 0);
    System.out.println(this.textview);
    System.out.println();
    List<Card> expected = new ArrayList<>(Arrays.asList(kClubs, qHearts));
    List<Card> actual = new ArrayList<>(Arrays.asList(this.km.getCardAt(0, 0),
            this.km.getCardAt(0, 1)));
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testGetCardAtBeforeStartGame() {
    initData();
    Assert.assertThrows(IllegalStateException.class, () -> this.km.getCardAt(1));
    Assert.assertThrows(IllegalStateException.class, () -> this.km.getCardAt(0, 1));
  }

  @Test
  public void testEmptyDrawPileTextView() {
    initData();
    this.km.startGame(this.miniDeck, false, 2, 2);
    this.km.moveDrawToFoundation(0);
    this.km.moveDrawToFoundation(0);
    this.km.moveDrawToFoundation(0);
    String expected = "Draw: X\nFoundation: 3♡, <none>\n 2♠  ?\n    A♠";
    String actual = textview.toString();
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void testGetCardAt() {
    initData();
    this.km.startGame(this.fullDeck, false, 7, 3);
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.km.getCardAt(3, 0));
    Assert.assertEquals(aSpades, this.km.getCardAt(0, 0));
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.km.getCardAt(20, 25));
  }

  @Test
  public void testGameOverTrue() {
    initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    this.km.moveToFoundation(1, 0);
    this.km.moveToFoundation(0, 0);
    this.km.moveToFoundation(1, 0);
    this.km.moveDrawToFoundation(1);
    this.km.moveDrawToFoundation(1);
    this.km.moveDrawToFoundation(1);
    Assert.assertTrue(this.km.isGameOver());
  }

  @Test
  public void testGameOverFalse() {
    initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    Assert.assertFalse(this.km.isGameOver());
  }

  @Test
  public void testMoveDrawToFoundationWorks() {
    initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    this.km.moveDrawToFoundation(0);
    Assert.assertFalse(this.km.getDrawCards().toString().contains(this.aHearts.toString()));
    Assert.assertEquals(this.km.getCardAt(0).toString(), this.aHearts.toString());
  }

  @Test
  public void testMoveDrawToFoundationInvalidMoveWorks() {
    initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    km.discardDraw();
    Assert.assertThrows(IllegalStateException.class, () -> km.moveDrawToFoundation(1));
    List<Card> expected = new ArrayList<Card>(Arrays.asList(twoHearts));
    Assert.assertEquals(km.getDrawCards(), expected);
  }

  @Test
  public void testMovePileInvalidMoveWorks() {
    initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    System.out.println(textview);
    Assert.assertThrows(IllegalStateException.class,
        () -> km.movePile(1, 1, 0));

    System.out.println(textview);
    Assert.assertEquals(km.getCardAt(1, 1), aSpades);
  }

  @Test
  public void testDiscardDrawWithNoDrawPile() {
    initData();
    this.km.startGame(this.miniDeck, false, 3, 1);
    Assert.assertThrows(IllegalStateException.class, () -> this.km.discardDraw());
  }

  @Test
  public void testCardVisibleThrowsExceptions() {
    initData();
    this.km.startGame(this.miniDeck, false, 2, 1);
    Assert.assertTrue(this.km.isCardVisible(0, 0));
    Assert.assertFalse(this.km.isCardVisible(1, 0));
    Assert.assertThrows(IllegalArgumentException.class, () ->
            this.km.isCardVisible(9, 0));
  }
}
