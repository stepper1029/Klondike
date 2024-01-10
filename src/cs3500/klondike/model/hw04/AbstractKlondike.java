package cs3500.klondike.model.hw04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.BasicCascade;
import cs3500.klondike.model.hw02.Draw;
import cs3500.klondike.model.hw02.KlondikeCard;
import cs3500.klondike.model.hw02.KlondikeModel;
import cs3500.klondike.model.hw02.Pile;
import cs3500.klondike.model.hw02.Suit;

/**
 * Represents an AbstractKlondike, which implements klondikeModel, and is extended by
 * BasicKlondike, LimitedDrawKlondike, and WhiteheadKlondike.
 */
public abstract class AbstractKlondike implements KlondikeModel {

  protected List<BasicCascade> cascade;
  protected List<List<Card>> foundation;
  protected Draw draw;

  /**
   * Constructor for the class initializes the cascade, foundation, and draw piles for an
   * AbstractKlondike.
   */
  public AbstractKlondike() {
    this.cascade = new ArrayList<>(Arrays.asList());
    this.foundation = new ArrayList<List<Card>>(List.of());
    this.draw = new Draw(new ArrayList<Card>(List.of()), new ArrayList<Card>(List.of()));
  }

  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<Card>(List.of());
    for (Suit s : Suit.values()) {
      for (int i = 1; i < 14; i++) {
        deck.add(new KlondikeCard(s, i));
      }
    }
    return deck;
  }

  @Override
  public void startGame(List<Card> deck, boolean shuffle, int numPiles, int numDraw)
          throws IllegalArgumentException {
    if (!this.cascade.isEmpty()) {
      throw new IllegalStateException("Game has already started");
    }
    startGameHelper(deck, shuffle, numPiles, numDraw);
    List<Card> cascadeCopy = new ArrayList<>(deck);
    // create the cascade piles
    this.makeCascade(cascadeCopy, numPiles);
    List<Card> drawCopy = new ArrayList<>(deck);
    // leftover cards after making cascades in the deck become the draw pile
    this.makeDraw(drawCopy, numDraw);
  }

  protected void startGameHelper(List<Card> deck, boolean shuffle, int numPiles, int numDraw) {
    this.startGameExceptions(deck, numPiles, numDraw);
    if (shuffle) {
      Collections.shuffle(deck);
    }
    // make the foundation piles
    this.makeFoundation(deck);
  }

  @Override
  public void movePile(int srcPile, int numCards, int destPile) throws IllegalStateException,
          IllegalArgumentException {
    movePileExceptions(srcPile, numCards, destPile);
    if (numCards > this.getPileHeight(srcPile)) {
      throw new IllegalStateException("Invalid numCards: " + numCards);
    } else if (this.cascade.get(srcPile).isEmpty()) {
      throw new IllegalArgumentException("srcPile is empty, cannot movePile");
    } else if (numCards > this.cascade.get(srcPile).upSize()) {
      throw new IllegalStateException("not that many cards are visible.");
    }
    BasicCascade srcCasc = this.cascade.get(srcPile);
    BasicCascade destCasc = this.cascade.get(destPile);
    Card movingCard = this.getCardAt(srcPile, srcCasc.getPileHeight() - numCards);

    if (destCasc.isEmpty()) { // if destPile is empty
      this.movePileToEmpty(srcCasc.takePile(numCards), destCasc); // if king, move. else, throw
      srcCasc.flip(); // flip over the next card of the srcPile if empty
    } else { // if destPile is not empty
      Card receivingCard = destCasc.peek(); // else there is a receiving card
      if (!validMove(receivingCard, movingCard)) { // if it's not a valid move
        throw new IllegalStateException("invalid move pile");
      } else { // else if it's a valid move
        destCasc.addPile(srcCasc.takePile(numCards)); // move the cards
        srcCasc.flip(); // flip over the next card of the srcPile if empty
      }
    }
  }

  void startGameExceptions(List<Card> deck, int numPiles, int numDraw) {
    if (deck == null) {
      throw new IllegalArgumentException("deck cannot be null");
    } else if (deck.isEmpty()) {
      throw new IllegalStateException("deck cannot be empty");
    }
    List<Card> deckCopy = new ArrayList<>(deck);
    if (!this.validDeck(deckCopy)) {
      throw new IllegalArgumentException("Invalid deck");
    } else if (numPiles < 1) {
      throw new IllegalArgumentException("Insufficient number of piles");
    }
    int dealtCards = 0;
    for (int i = 1; i < (numPiles + 1); i++) {
      dealtCards += i;
    }
    if (dealtCards > deck.size()) {
      throw new IllegalArgumentException(
              "The number of piles requires more cards than in the given deck.");
    } else if (numDraw < 1) {
      throw new IllegalArgumentException("At least 1 card must be visible");
    } else if (numDraw > (deck.size() - dealtCards) && (deck.size() - dealtCards != 0)) {
      throw new IllegalStateException(("numDraw is too big"));
    }
  }

  protected void movePileExceptions(int srcPile, int numCards, int destPile) {
    this.gameStarted();
    this.validCascadePileNum(srcPile);
    this.validCascadePileNum(destPile);
    this.validCardOrFoundationNum(numCards, getPileHeight(srcPile) + 1);
    if (srcPile == destPile) {
      throw new IllegalArgumentException("pile numbers are the same");
    }
  }

  boolean isKing(Card card) {
    return card.toString().contains("K");
  }

  protected void movePileToEmpty(List<Card> moveCards, Cascade destCasc) {
    Card movingCard = moveCards.get(0);
    if (this.isKing(movingCard)) {
      // move the king
      destCasc.addPile(moveCards);
    } else { // or not trying to move a king
      throw new IllegalStateException("Must move a King to an empty pile");
    }
  }

  boolean isAce(Card card) {
    return card.toString().contains("A");
  }

  @Override
  public void moveDraw(int destPile) throws IllegalStateException {
    moveDrawExceptions(destPile);
    BasicCascade destCasc = this.cascade.get(destPile);
    Card drawCard = this.draw.peek();
    if (destCasc.isEmpty() && this.isKing(drawCard)) {
      if (this.isKing(drawCard)) {
        destCasc.addCard(this.draw.peek()); // add the card
        this.draw.takeLast(); // remove the card
      } else {
        throw new IllegalStateException("Must move a K to empty pile");
      }
    } else {
      moveDrawHelper(destCasc, drawCard);
    }
  }


  void moveDrawExceptions(int destPile) {
    this.gameStarted();
    this.validCascadePileNum(destPile);
    if (this.draw.isEmpty()) {
      throw new IllegalStateException("Draw Pile is empty");
    }
  }

  void moveDrawHelper(Cascade destCasc, Card drawCard) {
    Card receivingCard = destCasc.peek();
    if (!this.validMove(receivingCard, drawCard)) { // if not a valid move
      throw new IllegalStateException("Invalid move"); // throw
    } else {
      destCasc.addCard(drawCard); // add card
      this.draw.takeLast(); // remove card
    }
    this.draw.nextAvailDraw(); // flip over next draw if appropriate
  }

  @Override
  public void moveToFoundation(int srcPile, int foundationPile) throws IllegalStateException {
    this.gameStarted();
    this.validCascadePileNum(srcPile);
    this.validCardOrFoundationNum(foundationPile, this.getNumFoundations());
    BasicCascade srcCasc = this.cascade.get(srcPile);
    if (srcCasc.isEmpty()) {
      throw new IllegalStateException("Pile is empty, no card to move");
    }
    List<Card> foundation = this.foundation.get(foundationPile);
    Card movingCard = srcCasc.peek();
    // if the foundation is empty and the card is an ace....
    if (foundation.isEmpty()) {
      if (isAce(movingCard)) {
        foundation.add(movingCard); // add card
        srcCasc.takeLast(); // remove card
        srcCasc.flip(); // turn over next card
      } else {
        throw new IllegalStateException("only an A can be moved to an empty foundation");
      }
    } else {
      Card foundationCard = this.getCardAt(foundationPile);
      if (this.validFoundationMove(movingCard, foundationCard)) {
        foundation.add(movingCard); // move card to foundation
        srcCasc.takeLast(); // remove the card from cascade
        srcCasc.flip(); // turn over the next cascade card if there is one
      } else {
        throw new IllegalStateException("Invalid foundation move");
      }
    }
  }

  @Override
  public void moveDrawToFoundation(int foundationPile) throws IllegalStateException {
    this.gameStarted();
    this.validCardOrFoundationNum(foundationPile, this.foundation.size());
    if (this.draw.isEmpty()) { // if no cards to draw throw exception
      throw new IllegalStateException("Draw pile is empty");
    }

    List<Card> foundation = this.foundation.get(foundationPile);
    Card drawCard = this.draw.peek();

    // if empty foundation and ace, move to foundation
    if (foundation.isEmpty() && this.isAce(drawCard)) {
      foundation.add(drawCard);
      this.draw.takeLast();
      this.draw.nextAvailDraw();
      //else if invalid move
    } else if (foundation.isEmpty()) {
      throw new IllegalStateException("To move a card to an empty foundation, must be an A");
    } else {
      Card foundationCard = this.getCardAt(foundationPile);
      if (validFoundationMove(drawCard, foundationCard)) {
        foundation.add(drawCard);
        this.draw.takeLast();
        this.draw.nextAvailDraw();
      } else {
        throw new IllegalStateException("Invalid move");
      }
    }
  }

  @Override
  public void discardDraw() throws IllegalStateException {
    this.gameStarted();
    if (this.draw.isEmpty()) {
      throw new IllegalStateException("Invalid move");
    }
    this.draw.discardDraw();
  }

  @Override
  public int getNumRows() {
    this.gameStarted();
    int rows = 0;
    for (Pile p : this.cascade) {
      if (p.getPileHeight() > rows) {
        rows = p.getPileHeight();
      }
    }
    return rows;
  }

  @Override
  public int getNumPiles() {
    this.gameStarted();
    return this.cascade.size();
  }

  @Override
  public int getNumDraw() {
    this.gameStarted();
    return this.draw.getNumDraw();
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    this.gameStarted();
    if (this.getNumRows() == 0 && this.getDrawCards().isEmpty()) {
      return true;
    } else {
      return (!anyPossibleCascadeMove() && this.draw.isEmpty() && !anyPossibleFoundationMove());
    }
  }

  @Override
  public int getScore() throws IllegalStateException {
    this.gameStarted();
    int score = 0;

    for (List<Card> f : this.foundation) {
      if (!f.isEmpty()) {
        String card = f.get(f.size() - 1).toString();
        if (card.contains("A")) {
          score += 1;
        } else if (card.contains("J")) {
          score += 11;
        } else if (card.contains("Q")) {
          score += 12;
        } else if (card.contains("K")) {
          score += 13;
        } else {
          score += Integer.parseInt(card.substring(0, card.length() - 1));
        }
      }
    }
    return score;
  }

  @Override
  public int getPileHeight(int pileNum) throws IllegalStateException {
    this.gameStarted();
    this.validCascadePileNum(pileNum);
    return this.cascade.get(pileNum).getPileHeight();
  }

  @Override
  public boolean isCardVisible(int pileNum, int card) throws IllegalStateException {
    this.gameStarted();
    this.validCascadePileNum(pileNum);
    this.validCardOrFoundationNum(card, this.getPileHeight(pileNum));
    return this.cascade.get(pileNum).isCardVisible(card);
  }

  @Override
  public Card getCardAt(int pileNum, int card) throws IllegalStateException {
    getCardAtExceptions(pileNum, card);
    return this.cascade.get(pileNum).getCascadeCardAt(card);
  }

  @Override
  public Card getCardAt(int foundationPile) throws IllegalStateException {
    this.gameStarted();
    this.validCardOrFoundationNum(foundationPile, this.foundation.size());
    if (this.foundation.get(foundationPile).isEmpty()) {
      return null;
    }
    return this.foundation.get(foundationPile).get(this.foundation.get(foundationPile).size() - 1);
  }

  void getCardAtExceptions(int pileNum, int card) {
    this.gameStarted();
    this.validCascadePileNum(pileNum);
    this.validCardOrFoundationNum(card, this.getPileHeight(pileNum));
    this.gameStarted();
    if (!this.isCardVisible(pileNum, card)) {
      throw new IllegalArgumentException("Cannot access invisible cards");
    }
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    this.gameStarted();
    return this.draw.getDrawCards();
  }

  @Override
  public int getNumFoundations() throws IllegalStateException {
    this.gameStarted();
    return this.foundation.size();
  }

  private boolean anyPossibleFoundationMove() {
    for (int srcCascade = 0; srcCascade < this.getNumPiles(); srcCascade++) {
      for (int foundationPile = 0; foundationPile < this.getNumFoundations(); foundationPile++) {
        if (!this.cascade.get(srcCascade).isEmpty()) {
          Card srcCard = this.cascade.get(srcCascade).peek();
          if (this.foundation.get(foundationPile).isEmpty() && isAce(srcCard)) {
            return true;
          } else if (validFoundationMove(srcCard, this.getCardAt(foundationPile))) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean anyPossibleCascadeMove() {
    for (int srcCascade = 0; srcCascade < this.getNumPiles(); srcCascade++) {
      for (int numCards = 1; numCards <= this.cascade.get(srcCascade).upSize(); numCards++) {
        for (int destCascade = 0; destCascade < this.getNumPiles(); destCascade++) {
          if (srcCascade != destCascade && !this.cascade.get(srcCascade).isEmpty()) {
            BasicCascade destPile = this.cascade.get(destCascade);
            int card = this.getPileHeight(srcCascade) - numCards;
            Card moveCard = this.getCardAt(srcCascade, card);
            if (destPile.isEmpty() && isKing(moveCard)) {
              return true;
            } else if (!destPile.isEmpty()) {
              return validMove(destPile.peek(), moveCard);
            }
          }
        }
      }
    }
    return false;
  }

  private boolean isFaceCard(Card card) {
    return card.toString().contains("J") || card.toString().contains("Q")
            || card.toString().contains("K") || card.toString().contains("A");
  }

  /**
   * initializes the cascade piles.
   *
   * @param deck     is the deck of cards the game was started on
   * @param numPiles the number of cascade piles to be dealt
   */
  void makeCascade(List<Card> deck, int numPiles) {
    for (int np = 0; np < numPiles; np++) {
      BasicCascade casc = new BasicCascade(new ArrayList<>(Arrays.asList()),
              new ArrayList<>(Arrays.asList()));
      this.cascade.add(casc);
    }
    int cardIndex = 0;
    // keep track of the row
    for (int row = 0; row < numPiles; row++) {
      //keep track of the column
      for (int column = 0; column < numPiles; column++) {
        if (row == column) {
          // add visible
          this.cascade.get(column).addCard(deck.get(cardIndex));
          cardIndex++;
        } else if (column > row) {
          // add invisible
          this.cascade.get(column).dealCard(deck.get(cardIndex));
          cardIndex++;
        }
      }
    }
  }

  /**
   * initializes the draw pile.
   *
   * @param deck    is the deck of cards the game was started on
   * @param numDraw is the number of visible draw cards
   */
  void makeDraw(List<Card> deck, int numDraw) {
    int cascNum = -1;
    for (BasicCascade c : this.cascade) {
      cascNum += c.getPileHeight();
    }
    if (cascNum < deck.size() - 1) {
      this.draw.makeDraw(deck, cascNum, numDraw);
    }
  }

  /**
   * initializes the foundation piles.
   *
   * @param deck is the deck of cards the game was started on
   */
  void makeFoundation(List<Card> deck) {
    int aceCount = this.aceCount(deck);
    for (int i = 0; i < aceCount; i++) {
      this.foundation.add(new ArrayList<Card>(List.of()));
    }
  }

  /**
   * checks that a deck is "valid" given the rules of the assignment.
   *
   * @param deck is the deck of cards to be checked
   * @return a boolean, true if deck is valid
   */
  private boolean validDeck(List<Card> deck) {
    int aceCount = this.aceCount(deck);
    if (aceCount == 0) {
      return false;
    }
    this.sortDeck(deck);
    // checks consecutive runs ^^^^
    int runLength = deck.size() / aceCount;
    if (deck.size() % aceCount != 0) {
      throw new IllegalArgumentException("Invalid deck");
    } else if (runLength > 0) {
      //for each run, go through and check same length as average
      for (int i = 1; i < aceCount + 1; i++) {
        // check ascending run
        for (int x = 0; x < runLength; x++) {
          String currVal = deck.get(x).toString().substring(0, deck.get(x).toString().length() - 1);
          if (!this.isValPlusOne(deck.get(x), deck.get(x + 1))) {
            throw new IllegalArgumentException("Invalid deck");
          } else if (x == 0 && !currVal.contains("A")) {
            throw new IllegalArgumentException("Invalid deck");
          }
        }
      }
    }
    return true;
  }

  /**
   * sorts a given deck into consecutive runs, if non-consecutive cards, they are not placed in the
   * sorted deck.
   *
   * @param deck is the deck to be sorted
   */
  private void sortDeck(List<Card> deck) {
    List<Card> sorted = new ArrayList<>(List.of());
    int aceCount = this.aceCount(deck);
    // sorts each run at a time, so repeats once for each ace
    for (int x = 0; x < aceCount; x++) {
      List<Card> deckCopy = new ArrayList<>(deck);
      Iterator<Card> iterator = deckCopy.iterator();
      //find the first ace in the deck
      while (iterator.hasNext()) {
        Card c = iterator.next();
        // adds the ace to a new, sorted list
        if (c.toString().contains("A")) {
          sorted.add(c);
          String currSuit = c.toString().substring(c.toString().length() - 1);
          // finds the next ascending value in the suit
          for (int z = 2; z < 11; z++) {
            Iterator<Card> innerIterator = deckCopy.iterator();
            while (innerIterator.hasNext()) {
              Card card = innerIterator.next();
              // if it finds the next value of the right suit, add it to sorted
              if (card.toString().equals(z + currSuit)) {
                sorted.add(card);
                innerIterator.remove();
              }
              // if it finds a J of the right suit, add it to sorted
              if (card.toString().equals("J" + currSuit)) {
                sorted.add(card);
                innerIterator.remove();
                // if it finds a Q of the right suit, add it to sorted
              } else if (card.toString().contains("Q")) {
                sorted.add(card);
                innerIterator.remove();
              } else if (card.toString().contains("K")) { // find K of the right suit
                sorted.add(card);
                innerIterator.remove();
              }
            }
          }
          for (Card card : sorted) {
            deck.remove(card);
          }
          break;
        }
      }
    }
    if (!deck.isEmpty()) {
      throw new IllegalArgumentException("Deck does not have consecutive runs");
    }
  }

  /**
   * checks if the value of one card is the value of another card +1.
   *
   * @param prevVal the lower card
   * @param nextVal the higher card
   * @return a boolean, true if val is plus one
   */
  boolean isValPlusOne(Card prevVal, Card nextVal) {
    String prev = prevVal.toString().substring(0, prevVal.toString().length() - 1);
    String next = nextVal.toString().substring(0, nextVal.toString().length() - 1);
    if (next.equals("A")) {
      return false;
    } else if (prev.equals("A")) {
      return next.equals("2");
    } else if (prev.equals("10")) {
      return next.equals("J");
    } else if (prev.equals("J")) {
      return next.equals("Q");
    } else if (prev.equals("Q")) {
      return next.equals("K");
    } else if (isFaceCard(prevVal) || isFaceCard(nextVal)) {
      return false;
    } else {
      return Integer.parseInt(prev) + 1 == Integer.parseInt(next);
    }
  }

  /**
   * counts the number of aces in the deck.
   *
   * @param deck the list to be sorted
   * @return the number of aces
   */
  private int aceCount(List<Card> deck) {
    int aceCount = 0;
    for (Card c : deck) {
      if (c.toString().contains("A")) {
        aceCount = aceCount + 1;
      }
    }
    return aceCount;
  }

  /**
   * checks if the move is valid for a cascade or drawToCascade move, ie. opposite suit and +1 val.
   *
   * @param pileCard the card that should be higher
   * @param moveCard the card that should be lower
   * @return a boolean, true if the move is valid
   */
  protected boolean validMove(Card pileCard, Card moveCard) {
    return this.oppColor(pileCard, moveCard)
            && this.isValPlusOne(moveCard, pileCard);
  }

  /**
   * checks if the move is valid for a foundation pile, ie. same suit +1 val.
   *
   * @param srcCard        the higher card
   * @param foundationCard the lower card
   * @return a boolean, true if the move is valid
   */
  boolean validFoundationMove(Card srcCard, Card foundationCard) {
    return this.sameSuit(srcCard.toString(), foundationCard.toString())
            && this.isValPlusOne(foundationCard, srcCard);
  }

  /**
   * checks if two cards are the same suit.
   *
   * @param card1 a card with a suit
   * @param card2 a card with a suit
   * @return a boolean, true if they are the same suit
   */
  boolean sameSuit(String card1, String card2) {
    String suit1 = card1.substring(card1.length() - 1);
    String suit2 = card2.substring(card2.length() - 1);
    return suit1.equals(suit2);
  }

  /**
   * checks that two cards are opposite colors by checking their suits, black is spades and
   * clubs, red is hearts and diamonds.
   *
   * @param card1 a card with a suit
   * @param card2 a card with a suit
   * @return a boolean, true if they are the same color
   */
  boolean oppColor(Card card1, Card card2) {
    String suit1 = card1.toString().substring(card1.toString().length() - 1);
    String suit2 = card2.toString().substring(card2.toString().length() - 1);
    if ((suit1.equals("♠") || suit1.equals("♣")) && (suit2.equals("♡") || suit2.equals("♢"))) {
      return true;
    } else {
      return (suit1.equals("♡") || suit1.equals("♢")) && (suit2.equals("♠") || suit2.equals("♣"));
    }
  }

  /**
   * throws an exception if the game has not started.
   *
   * @throws IllegalStateException if the game has not started.
   */
  void gameStarted() {
    if (this.cascade.isEmpty()) {
      throw new IllegalStateException("Game has not started");
    }
  }

  /**
   * checks if the given pile number is valid.
   *
   * @param pileNum the given index for a pile
   * @throws IllegalArgumentException if the pileNum is not valid
   */
  void validCascadePileNum(int pileNum) {
    if (pileNum < 0 || pileNum > this.getNumPiles() - 1) {
      throw new IllegalArgumentException("Invalid pile or card index");
    }
  }

  void validCardOrFoundationNum(int pileNum, int totalPiles) {
    if (pileNum < 0 || pileNum > totalPiles - 1) {
      throw new IllegalArgumentException("Invalid pile or card index");
    }
  }

}
